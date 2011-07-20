package org.codingbox.errorreporting.server;

/*
 * Copyright 2006 Etienne Studer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * This servlet class receives the error report submissions and forwards them by email to the specified recipients. The current expected request
 * format is:
 * <p/>
 * <ul> <li>Plugin Id</li> <li>Plugin Name</li> <li>Plugin Version</li> <li>IDEA Build</li> <li>Email To Recipients</li> <li>Email Cc Recipients</li>
 * <li>User Name</li> <li>User Description</li> <li>Message Count</li> <li>Pairs of [Exception Message/Exception Stacktrace]</li></ul>
 *
 * @author <a href="mailto:intellij@studer.nu">Etienne Studer</a>, May 29, 2006
 * @noinspection HardCodedStringLiteral
 * @see SmtpUtil
 */
public class ErrorReceiverServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ErrorReceiverServlet.class);

    private static final String SMTP_CONFIG_PATH = "/smtp.properties";

    private Properties mailServerConfig;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        try {
            mailServerConfig = loadMailServerConfiguration();
        } catch (IOException ioe) {
            throw new ServletException(ioe);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // we have to accept GET requests in order for the client side to first make a
        // connection check, i.e. proxy settings check (HttpConfigurable.prepareURL)
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pluginId;
        String pluginName;
        String pluginVersion;
        String ideaBuild;
        String emailTo;
        String emailCc;
        String user;
        String description;
        List messages;
        List throwables;

        // read error report from client
        DataInputStream inputStream = null;
        try {
            inputStream = new DataInputStream(new InflaterInputStream(request.getInputStream()));

            pluginId = inputStream.readUTF();
            pluginName = inputStream.readUTF();
            pluginVersion = inputStream.readUTF();
            ideaBuild = inputStream.readUTF();

            emailTo = inputStream.readUTF();
            emailCc = inputStream.readUTF();

            user = inputStream.readUTF();
            description = inputStream.readUTF();

            int eventCount = inputStream.readInt();

            messages = new ArrayList(eventCount);
            throwables = new ArrayList(eventCount);
            for (int i = 0; i < eventCount; i++) {
                String message = inputStream.readUTF();
                messages.add(message);

                String throwable = inputStream.readUTF();
                throwables.add(throwable);
            }

            // process error report
            processRequest(pluginId, pluginName, pluginVersion, ideaBuild, user, description, messages, throwables, emailTo, emailCc, response);
        } catch (IOException ioe) {
            LOGGER.error("Unable to process data from client", ioe);
            throw ioe;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    LOGGER.error("Unable to disconnect from client after processing data", ioe);
                }
            }
        }
    }

    private void processRequest(String pluginId, String pluginName, String pluginVersion, String ideaBuild, String user, String description,
                                List messages, List throwables, String emailTo, String emailCc, HttpServletResponse response) throws IOException {
        // prepare response
        response.setContentType("application/octet-stream");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        // prepare error report emails
        String subject = "IDEA Plugin Error Submission - " + pluginId;
        String content = createMailContent(pluginId, pluginName, pluginVersion, ideaBuild, user, description, messages, throwables);
        InternetAddress[] to = createInetAddresses(emailTo);
        InternetAddress[] cc = createInetAddresses(emailCc);

        // abort if no email recipients are available
        if (to.length == 0 && cc.length == 0) {
            // write NOK response to client
            LOGGER.error("No error report recipients available");
            sendResponse(response, false, "No error report recipients available");
            return;
        }

        // send error report emails
        try {
            sendEmail(subject, content, to, cc);
        } catch (MessagingException e) {
            // write NOK response to client
            LOGGER.error("Error while trying to submit error report to recipients");
            sendResponse(response, false, "Error while trying to submit error report to recipients");
            return;
        }

        // write OK response to client
        sendResponse(response, true, "Error report submitted successfully");
    }

    private void sendResponse(HttpServletResponse response, boolean statusOK, String statusMessage) throws IOException {
        DataOutputStream stream = null;
        try {
            stream = new DataOutputStream(new DeflaterOutputStream(response.getOutputStream()));
            stream.writeBoolean(statusOK);
            stream.writeUTF(statusMessage);
        } catch (IOException ioe) {
            LOGGER.error("Unable to send data to client", ioe);
            throw ioe;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ioe) {
                    LOGGER.error("Unable to disconnect from client after sending data", ioe);
                }
            }
        }
    }

    private InternetAddress[] createInetAddresses(String emailsString) {
        String[] emails = emailsString.trim().length() > 0 ? emailsString.trim().split(":") : new String[0];

        List addressList = new ArrayList();
        for (int i = 0; i < emails.length; i++) {
            String email = emails[i];
            try {
                InternetAddress address = new InternetAddress(email, true);
                addressList.add(address);
            } catch (AddressException ae) {
                LOGGER.warn("Illegal email address: " + email, ae);
                // skip invalid email addresses
            }
        }

        InternetAddress[] addresses = (InternetAddress[]) addressList.toArray(new InternetAddress[addressList.size()]);
        return addresses;
    }

    private String createMailContent(String pluginId, String pluginName, String pluginVersion, String ideaBuild, String user, String description,
                                     List messages, List throwables) {
        StringBuffer sb = new StringBuffer();

        sb.append("Plugin Id: ").append((pluginId.trim().length() > 0) ? pluginId.trim() : "Unknown").append("\n");
        sb.append("Plugin Name: ").append((pluginName.trim().length() > 0) ? pluginName.trim() : "Unknown").append("\n");
        sb.append("Plugin Version: ").append((pluginVersion.trim().length() > 0) ? pluginVersion.trim() : "Unknown").append("\n");
        sb.append("IDEA Build: ").append((ideaBuild.trim().length() > 0) ? ideaBuild.trim() : "Unknown");
        sb.append("\n\n\n");

        sb.append("Submitter Email: ").append((user.trim().length() > 0) ? user.trim() : "Unknown").append("\n");
        sb.append("Submitter Description: ").append((description.trim().length() > 0) ? description.trim() : "Unknown");
        sb.append("\n\n\n");

        for (int i = 0; i < messages.size(); i++) {
            if (i > 0) {
                sb.append("\n\n\n");
            }
            String message = (String) messages.get(i);
            String throwable = (String) throwables.get(i);
            sb.append(message).append("\n\n").append(throwable);
        }

        String mailContent = sb.toString();
        return mailContent;
    }

    private void sendEmail(String subject, String content, InternetAddress[] to, InternetAddress[] cc) throws MessagingException {
        SmtpUtil smtpUtil = new SmtpUtil(mailServerConfig);
        smtpUtil.sendEmail(subject, content, to, cc);
    }

    private Properties loadMailServerConfiguration() throws IOException {
        InputStream stream = getClass().getResourceAsStream(SMTP_CONFIG_PATH);
        if (stream == null) {
            LOGGER.error("Cannot find mail server configuration: " + SMTP_CONFIG_PATH);
            throw new FileNotFoundException("Cannot find mail server configuration: " + SMTP_CONFIG_PATH);
        }

        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException ioe) {
            LOGGER.error("Cannot load mail server configuration: " + SMTP_CONFIG_PATH, ioe);
            throw new IOException("Cannot load mail server configuration: " + SMTP_CONFIG_PATH);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Loaded mail server configuration: " + properties);
        }

        return properties;
    }
}