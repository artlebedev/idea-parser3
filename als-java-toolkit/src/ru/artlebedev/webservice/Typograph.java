package ru.artlebedev.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011 ArtLebedev Studio
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Typograph {
  private int entityType = 4;
  private int useBr = 1;
  private int useP = 1;
  private int maxNobr = 3;
  private String encoding = "UTF-8";

  private static volatile Typograph instance;

  private Typograph() {

  }

  public static Typograph getInstance() {
    if(instance == null) {
      synchronized (Typograph.class) {
        if(instance == null)
          instance = new Typograph();
      }
    }
    return instance;
  }

  public int getEntityType() {
    return entityType;
  }

  public void setEntityType(int entityType) {
    this.entityType = entityType;
  }

  public int getUseBr() {
    return useBr;
  }

  public void setUseBr(int useBr) {
    this.useBr = useBr;
  }

  public int getUseP() {
    return useP;
  }

  public void setUseP(int useP) {
    this.useP = useP;
  }

  public int getMaxNobr() {
    return maxNobr;
  }

  public void setMaxNobr(int maxNobr) {
    this.maxNobr = maxNobr;
  }

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public String process(String text) {
    text = text.replaceAll("&", "&amp;");
    text = text.replaceAll("<", "&lt;");
    text = text.replaceAll(">", "&gt;");

    StringBuilder SOAPBody = new StringBuilder();
    SOAPBody.append("<?xml version=\"1.0\" encoding=\"").append(encoding).append("\"?>");
    SOAPBody.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
    SOAPBody.append("<soap:Body>");
    SOAPBody.append("<ProcessText xmlns=\"http://typograf.artlebedev.ru/webservices/\">");
    SOAPBody.append("<text>").append(text).append("</text>");
    SOAPBody.append("<entityType>").append(entityType).append("</entityType>");
    SOAPBody.append("<useBr>").append(useBr).append("</useBr>");
    SOAPBody.append("<maxNobr>").append(maxNobr).append("</maxNobr>");
    SOAPBody.append("</ProcessText>");
    SOAPBody.append("</soap:Body>");
    SOAPBody.append("</soap:Envelope>");

    String host = "typograf.artlebedev.ru";

    StringBuilder SOAPRequest = new StringBuilder();
    SOAPRequest.append("POST /webservices/typograf.asmx HTTP/1.1\n");
    SOAPRequest.append("Host: typograf.artlebedev.ru\n");
    SOAPRequest.append("Content-Type: text/xml\n");
    SOAPRequest.append("Content-Length: ").append(SOAPBody.length()).append("\n");
    SOAPRequest.append("SOAPAction: \"http://typograf.artlebedev.ru/webservices/ProcessText\"\n\n");

    SOAPRequest.append(SOAPBody);

    try {
      Socket typographSocket = new Socket(host, 80);

      PrintWriter out
        = new PrintWriter(typographSocket.getOutputStream(), true);
      BufferedReader in
        = new BufferedReader(new InputStreamReader(typographSocket.getInputStream()));

      out.println(SOAPBody);

    } catch (IOException ignored) {}

    text = text.replaceAll("&amp;", "&");
    text = text.replaceAll("&lt;",  "<");
    text = text.replaceAll("&gt;",  ">");

    return text;
  }
}
