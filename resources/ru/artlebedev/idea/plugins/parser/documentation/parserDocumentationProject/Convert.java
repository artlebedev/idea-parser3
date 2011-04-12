package ru.artlebedev.idea.plugins.parser.documentation.parserDocumentationProject;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 ArtLebedev Studio
 * Copyright 2010 Joachim Ansorg <mail@ansorg-it.com>
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

public class Convert {
  public final static String SOURCES_DIR = "sources/";

  public final static String INTERNAL_CLASSES_DIR = "internalClasses/";
  public final static String INTERNAL_METHODS_DIR = "internalMethods/";

  public final static String OUTPUT_DIR =
          "/Users/dwr/Projects/idea-parser3/resources/" +
          "ru/artlebedev/idea/plugins/parser/documentation/" +
          "parserDocumentationProject/";

  public final static ConvertPair boolClass = new ConvertPair(SOURCES_DIR + "boolclass.htm", INTERNAL_CLASSES_DIR + "bool.xml");
  public final static ConvertPair consoleClass = new ConvertPair(SOURCES_DIR + "consoleclass.htm", INTERNAL_CLASSES_DIR + "console.xml");
  public final static ConvertPair cookieClass = new ConvertPair(SOURCES_DIR + "cookieclass.htm", INTERNAL_CLASSES_DIR + "cookie.xml");
  public final static ConvertPair curlClass = new ConvertPair(SOURCES_DIR + "curlclass.htm", INTERNAL_CLASSES_DIR + "curl.xml");
  public final static ConvertPair dateClass = new ConvertPair(SOURCES_DIR + "dateclass.htm", INTERNAL_CLASSES_DIR + "date.xml");
  public final static ConvertPair doubleClass = new ConvertPair(SOURCES_DIR + "intdoubleclasses.htm", INTERNAL_CLASSES_DIR + "double.xml");
  public final static ConvertPair envClass = new ConvertPair(SOURCES_DIR + "envclass.htm", INTERNAL_CLASSES_DIR + "env.xml");
  public final static ConvertPair fileClass = new ConvertPair(SOURCES_DIR + "fileclass.htm", INTERNAL_CLASSES_DIR + "file.xml");
  public final static ConvertPair formClass = new ConvertPair(SOURCES_DIR + "formclass.htm", INTERNAL_CLASSES_DIR + "form.xml");
  public final static ConvertPair hashClass = new ConvertPair(SOURCES_DIR + "hashclass.htm", INTERNAL_CLASSES_DIR + "hash.xml");
  public final static ConvertPair imageClass = new ConvertPair(SOURCES_DIR + "imageclass.htm", INTERNAL_CLASSES_DIR + "image.xml");
  public final static ConvertPair intClass = new ConvertPair(SOURCES_DIR + "intdoubleclasses.htm", INTERNAL_CLASSES_DIR + "int.xml");
  public final static ConvertPair jsonClass = new ConvertPair(SOURCES_DIR + "jsonclass.htm", INTERNAL_CLASSES_DIR + "json.xml");
  public final static ConvertPair junctionClass = new ConvertPair(SOURCES_DIR + "junctionclass.htm", INTERNAL_CLASSES_DIR + "junction.xml");
  public final static ConvertPair mailClass = new ConvertPair(SOURCES_DIR + "mailclass.htm", INTERNAL_CLASSES_DIR + "mail.xml");
  public final static ConvertPair mathClass = new ConvertPair(SOURCES_DIR + "mathclass.htm", INTERNAL_CLASSES_DIR + "math.xml");
  public final static ConvertPair memoryClass = new ConvertPair(SOURCES_DIR + "memoryclass.htm", INTERNAL_CLASSES_DIR + "memory.xml");
  public final static ConvertPair reflectionClass = new ConvertPair(SOURCES_DIR + "reflectionclass.htm", INTERNAL_CLASSES_DIR + "reflection.xml");
  public final static ConvertPair regexClass = new ConvertPair(SOURCES_DIR + "regexclass.htm", INTERNAL_CLASSES_DIR + "regex.xml");
  public final static ConvertPair requestClass = new ConvertPair(SOURCES_DIR + "requestclass.htm", INTERNAL_CLASSES_DIR + "request.xml");
  public final static ConvertPair responseClass = new ConvertPair(SOURCES_DIR + "responseclass.htm", INTERNAL_CLASSES_DIR + "response.xml");
  public final static ConvertPair statusClass = new ConvertPair(SOURCES_DIR + "statusclass.htm", INTERNAL_CLASSES_DIR + "status.xml");
  public final static ConvertPair stringClass = new ConvertPair(SOURCES_DIR + "stringclass.htm", INTERNAL_CLASSES_DIR + "string.xml");
  public final static ConvertPair tableClass = new ConvertPair(SOURCES_DIR + "tableclass.htm", INTERNAL_CLASSES_DIR + "table.xml");
  public final static ConvertPair voidClass = new ConvertPair(SOURCES_DIR + "voidclass.htm", INTERNAL_CLASSES_DIR + "void.xml");
  public final static ConvertPair xdocClass = new ConvertPair(SOURCES_DIR + "xdocclass.htm", INTERNAL_CLASSES_DIR + "xdoc.xml");
  public final static ConvertPair xnodeClass = new ConvertPair(SOURCES_DIR + "xnodeclass.htm", INTERNAL_CLASSES_DIR + "xnode.xml");

  public final static ConvertPair[] convertQueue = new ConvertPair[]{
          boolClass,
          consoleClass,
          cookieClass,
          curlClass,
          dateClass,
          doubleClass,
          envClass,
          fileClass,
          formClass,
          hashClass,
          imageClass,
          intClass,
          jsonClass,
          junctionClass,
          mailClass,
          mathClass,
          memoryClass,
          reflectionClass,
          regexClass,
          requestClass,
          responseClass,
          statusClass,
          stringClass,
          tableClass,
          voidClass,
          xdocClass,
          xnodeClass
  };

  public static void main(String[] args) {
    for (ConvertPair aConvertQueue : convertQueue) {
      InputStream fromFileInputStream = Convert.class.getResourceAsStream(aConvertQueue.getFromFile());

      if(fromFileInputStream != null) {
        String content = new Scanner(fromFileInputStream).useDelimiter("\\Z").next();

        /*
         * Applying content filters
         */
        content = content.replaceAll("<script(.*?)>([\\w\\W]*?)</script>", "");
        content = content.replaceAll("<hr(.*?)>([\\w\\W]*?)</body>", "</body>");
        content = content.replaceAll("<!--([\\w\\W]*?)-->", "");
        content = content.replaceAll("<(m|M)(e|E)(t|T)(a|A)(.*)>", "");
        content = content.replaceAll("<span style=\"font-family:Monospace; font-size:10pt; color:#0000FF\">([\\w\\W]*?)</span>", "<monoblue>$1</monoblue>");
        content = content.replaceAll("<span style=\"font-family:Monospace; font-size:10pt; color:#000000\">([\\w\\W]*?)</span>", "<mono>$1</mono>");
        content = content.replaceAll("<span style=\"font-family:FreeSetC,Arial; font-size:10pt; color:#0000FF\">([\\w\\W]*?)</span>", "<blue>$1</blue>");
        content = content.replaceAll("<span style=\"font-family:FreeSetC,Arial; font-size:12pt; color:#000000\">([\\w\\W]*?)</span>", "<h1>$1</h1>");
        content = content.replaceAll("<table(.*?)>([\\w\\W]*?)<h1>", "<h1>");
        content = content.replaceAll("</h1>([\\w\\W]*?)</table>", "</h1>");
        content = content.replaceAll("<br>", "<br />");
        content = content.replaceAll("<span(.*?)>", "<normal>");
        content = content.replaceAll("</span>", "</normal>");
        content = content.replaceAll("\\r", "");
        content = content.replaceAll("<body(.*?)>", "<body>");
        content = content.replaceAll("<a href=(.*?)\\.htm>", "<a href=\"$1.htm\">");

        List<String> content2 = new ArrayList<String>();

        String[] contentLines = content.split("\\n");

        for(int i = 0; i < contentLines.length; i++) {
          contentLines[i] = contentLines[i].replaceAll("\\s{2,}", " ");
          if(!contentLines[i].trim().equals("")) {
            content2.add(contentLines[i]);
          }
        }

        content = StringUtils.join(content2.toArray(), '\n');

        File file = new File(OUTPUT_DIR + aConvertQueue.getToFile());

        if(file.exists()) {
          file.delete();
        }

        try {
          FileWriter fileWriter = new FileWriter(file);

          fileWriter.write(content);

          fileWriter.close();
        } catch (IOException ignored) {

        }
      }
    }
  }
}
