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
  // pathes
  public final static String SOURCES_DIR = "sources/";

  public final static String INTERNAL_CLASSES_DIR = "internalClasses/";
  public final static String INTERNAL_METHODS_DIR = "internalMethods/";
  public final static String INTERNAL_STATIC_METHODS_DIR = "internalStaticMethods/";
  public final static String INTERNAL_VARIABLES_DIR = "internalVariables/";
  public final static String INTERNAL_STATIC_VARIABLES_DIR = "internalStaticVariables/";

  public final static String OUTPUT_DIR =
          "/Users/dwr/Projects/idea-parser3/resources/" +
          "ru/artlebedev/idea/plugins/parser/documentation/" +
          "parserDocumentationProject/";

  // bool
  public final static ConvertPair boolClass = new ConvertPair(SOURCES_DIR + "boolclass.htm", INTERNAL_CLASSES_DIR + "bool.xml");

  // console
  public final static ConvertPair consoleClass = new ConvertPair(SOURCES_DIR + "consoleclass.htm", INTERNAL_CLASSES_DIR + "console.xml");

  // cookie
  public final static ConvertPair cookieClass = new ConvertPair(SOURCES_DIR + "cookieclass.htm", INTERNAL_CLASSES_DIR + "cookie.xml");

  // curl
  public final static ConvertPair curlClass = new ConvertPair(SOURCES_DIR + "curlclass.htm", INTERNAL_CLASSES_DIR + "curl.xml");
  public final static ConvertPair curlLoadStaticMethod = new ConvertPair(SOURCES_DIR + "curlload.htm", INTERNAL_STATIC_METHODS_DIR + "curl-load.xml");
  public final static ConvertPair curlOptionsStaticMethod = new ConvertPair(SOURCES_DIR + "curloptions.htm", INTERNAL_STATIC_METHODS_DIR + "curl-options.xml");
  public final static ConvertPair curlSessionStaticMethod = new ConvertPair(SOURCES_DIR + "curlsession.htm", INTERNAL_STATIC_METHODS_DIR + "curl-session.xml");
  public final static ConvertPair curlVersionStaticMethod = new ConvertPair(SOURCES_DIR + "curlversion.htm", INTERNAL_STATIC_METHODS_DIR + "curl-version.xml");

  // date
  public final static ConvertPair dateClass = new ConvertPair(SOURCES_DIR + "dateclass.htm", INTERNAL_CLASSES_DIR + "date.xml");
  public final static ConvertPair dateRollMethod = new ConvertPair(SOURCES_DIR + "dateroll.htm", INTERNAL_METHODS_DIR + "date-roll.xml");
  public final static ConvertPair dateSqlStringMethod = new ConvertPair(SOURCES_DIR + "datesqlstring.htm", INTERNAL_METHODS_DIR + "date-sql-string.xml");
  public final static ConvertPair dateUnixTimestampMethod = new ConvertPair(SOURCES_DIR + "dateunixts.htm", INTERNAL_METHODS_DIR + "date-unix-timestamp.xml");
  public final static ConvertPair dateLastDayMethod = new ConvertPair(SOURCES_DIR + "datelastdaym.htm", INTERNAL_METHODS_DIR + "date-last-day.xml");
  public final static ConvertPair dateGmtStringMethod = new ConvertPair(SOURCES_DIR + "dategmtstring.htm", INTERNAL_METHODS_DIR + "date-gmt-string.xml");
  // static methods missing

  // double
  public final static ConvertPair doubleClass = new ConvertPair(SOURCES_DIR + "intdoubleclasses.htm", INTERNAL_CLASSES_DIR + "double.xml");
  public final static ConvertPair doubleIntMethod = new ConvertPair(SOURCES_DIR + "intdoublefield.htm", INTERNAL_METHODS_DIR + "double-int.xml");
  public final static ConvertPair doubleDoubleMethod = new ConvertPair(SOURCES_DIR + "intdoublefield.htm", INTERNAL_METHODS_DIR + "double-double.xml");
  public final static ConvertPair doubleBoolMethod = new ConvertPair(SOURCES_DIR + "intdoublefield.htm", INTERNAL_METHODS_DIR + "double-bool.xml");
  public final static ConvertPair doubleIncMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "double-inc.xml");
  public final static ConvertPair doubleDecMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "double-dec.xml");
  public final static ConvertPair doubleMulMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "double-mul.xml");
  public final static ConvertPair doubleDivMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "double-div.xml");
  public final static ConvertPair doubleModMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "double-mod.xml");
  public final static ConvertPair doubleFormatMethod = new ConvertPair(SOURCES_DIR + "intdoubleformat.htm", INTERNAL_METHODS_DIR + "double-format.xml");
  public final static ConvertPair doubleSqlStaticMethod = new ConvertPair(SOURCES_DIR + "intdoublesql.htm", INTERNAL_STATIC_METHODS_DIR + "double-sql.xml");

  // env
  public final static ConvertPair envClass = new ConvertPair(SOURCES_DIR + "envclass.htm", INTERNAL_CLASSES_DIR + "env.xml");

  // file
  public final static ConvertPair fileClass = new ConvertPair(SOURCES_DIR + "fileclass.htm", INTERNAL_CLASSES_DIR + "file.xml");

  // form
  public final static ConvertPair formClass = new ConvertPair(SOURCES_DIR + "formclass.htm", INTERNAL_CLASSES_DIR + "form.xml");

  // hash
  public final static ConvertPair hashClass = new ConvertPair(SOURCES_DIR + "hashclass.htm", INTERNAL_CLASSES_DIR + "hash.xml");

  // image
  public final static ConvertPair imageClass = new ConvertPair(SOURCES_DIR + "imageclass.htm", INTERNAL_CLASSES_DIR + "image.xml");

  // int
  public final static ConvertPair intClass = new ConvertPair(SOURCES_DIR + "intdoubleclasses.htm", INTERNAL_CLASSES_DIR + "int.xml");
  public final static ConvertPair intIntMethod = new ConvertPair(SOURCES_DIR + "intdoublefield.htm", INTERNAL_METHODS_DIR + "int-int.xml");
  public final static ConvertPair intDoubleMethod = new ConvertPair(SOURCES_DIR + "intdoublefield.htm", INTERNAL_METHODS_DIR + "int-double.xml");
  public final static ConvertPair intBoolMethod = new ConvertPair(SOURCES_DIR + "intdoublefield.htm", INTERNAL_METHODS_DIR + "int-bool.xml");
  public final static ConvertPair intIncMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "int-inc.xml");
  public final static ConvertPair intDecMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "int-dec.xml");
  public final static ConvertPair intMulMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "int-mul.xml");
  public final static ConvertPair intDivMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "int-div.xml");
  public final static ConvertPair intModMethod = new ConvertPair(SOURCES_DIR + "intdoubleincetc.htm", INTERNAL_METHODS_DIR + "int-mod.xml");
  public final static ConvertPair intFormatMethod = new ConvertPair(SOURCES_DIR + "intdoubleformat.htm", INTERNAL_METHODS_DIR + "int-format.xml");
  public final static ConvertPair intSqlStaticMethod = new ConvertPair(SOURCES_DIR + "intdoublesql.htm", INTERNAL_STATIC_METHODS_DIR + "int-sql.xml");

  // json
  public final static ConvertPair jsonClass = new ConvertPair(SOURCES_DIR + "jsonclass.htm", INTERNAL_CLASSES_DIR + "json.xml");

  // junction
  public final static ConvertPair junctionClass = new ConvertPair(SOURCES_DIR + "junctionclass.htm", INTERNAL_CLASSES_DIR + "junction.xml");

  // mail
  public final static ConvertPair mailClass = new ConvertPair(SOURCES_DIR + "mailclass.htm", INTERNAL_CLASSES_DIR + "mail.xml");

  // math
  public final static ConvertPair mathClass = new ConvertPair(SOURCES_DIR + "mathclass.htm", INTERNAL_CLASSES_DIR + "math.xml");

  // memory
  public final static ConvertPair memoryClass = new ConvertPair(SOURCES_DIR + "memoryclass.htm", INTERNAL_CLASSES_DIR + "memory.xml");

  // reflection
  public final static ConvertPair reflectionClass = new ConvertPair(SOURCES_DIR + "reflectionclass.htm", INTERNAL_CLASSES_DIR + "reflection.xml");

  // regex
  public final static ConvertPair regexClass = new ConvertPair(SOURCES_DIR + "regexclass.htm", INTERNAL_CLASSES_DIR + "regex.xml");

  // request
  public final static ConvertPair requestClass = new ConvertPair(SOURCES_DIR + "requestclass.htm", INTERNAL_CLASSES_DIR + "request.xml");

  // response
  public final static ConvertPair responseClass = new ConvertPair(SOURCES_DIR + "responseclass.htm", INTERNAL_CLASSES_DIR + "response.xml");

  // status
  public final static ConvertPair statusClass = new ConvertPair(SOURCES_DIR + "statusclass.htm", INTERNAL_CLASSES_DIR + "status.xml");

  // string
  public final static ConvertPair stringClass = new ConvertPair(SOURCES_DIR + "stringclass.htm", INTERNAL_CLASSES_DIR + "string.xml");

  // table
  public final static ConvertPair tableClass = new ConvertPair(SOURCES_DIR + "tableclass.htm", INTERNAL_CLASSES_DIR + "table.xml");

  // void
  public final static ConvertPair voidClass = new ConvertPair(SOURCES_DIR + "voidclass.htm", INTERNAL_CLASSES_DIR + "void.xml");
  public final static ConvertPair voidSqlStaticMethod = new ConvertPair(SOURCES_DIR + "voidsql.htm", INTERNAL_STATIC_METHODS_DIR + "void-sql.xml");

  // xdoc
  public final static ConvertPair xdocClass = new ConvertPair(SOURCES_DIR + "xdocclass.htm", INTERNAL_CLASSES_DIR + "xdoc.xml");

  // xnode
  public final static ConvertPair xnodeClass = new ConvertPair(SOURCES_DIR + "xnodeclass.htm", INTERNAL_CLASSES_DIR + "xnode.xml");

  public final static ConvertPair[] convertQueue = new ConvertPair[]{
          // bool
          boolClass,

          // console
          consoleClass,

          // cookie
          cookieClass,

          // curl
          curlClass,
          curlLoadStaticMethod,
          curlOptionsStaticMethod,
          curlSessionStaticMethod,
          curlVersionStaticMethod,

          // date
          dateClass,
          dateRollMethod,
          dateSqlStringMethod,
          dateUnixTimestampMethod,
          dateLastDayMethod,
          dateGmtStringMethod,

          // double
          doubleClass,
          doubleIntMethod,
          doubleDoubleMethod,
          doubleBoolMethod,
          doubleIncMethod,
          doubleDecMethod,
          doubleMulMethod,
          doubleDivMethod,
          doubleModMethod,
          doubleFormatMethod,
          doubleSqlStaticMethod,

          // env
          envClass,

          // file
          fileClass,

          // form
          formClass,

          // hash
          hashClass,

          // image
          imageClass,

          // int
          intClass,
          intIntMethod,
          intDoubleMethod,
          intBoolMethod,
          intIncMethod,
          intDecMethod,
          intMulMethod,
          intDivMethod,
          intModMethod,
          intFormatMethod,
          intSqlStaticMethod,

          // json
          jsonClass,

          // junction
          junctionClass,

          // mail
          mailClass,

          // math
          mathClass,

          // memory
          memoryClass,

          // reflection
          reflectionClass,

          // regex
          regexClass,

          // request
          requestClass,

          // response
          responseClass,

          // status
          statusClass,

          // string
          stringClass,

          // table
          tableClass,

          // void
          voidClass,
          voidSqlStaticMethod,

          // xdoc
          xdocClass,

          // xnode
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
          //file.delete();
          continue;
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
