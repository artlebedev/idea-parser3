package ru.artlebedev.idea.plugins.parser.documentation.parserDocumentationProject;

import java.io.InputStream;
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

  public final static ConvertPair boolClass = new ConvertPair(SOURCES_DIR + "boolclass.htm", INTERNAL_CLASSES_DIR + "bool.html");
  public final static ConvertPair consoleClass = new ConvertPair(SOURCES_DIR + "consoleclass.htm", INTERNAL_CLASSES_DIR + "console.html");
  public final static ConvertPair cookieClass = new ConvertPair(SOURCES_DIR + "cookieclass.htm", INTERNAL_CLASSES_DIR + "cookie.html");
  public final static ConvertPair curlClass = new ConvertPair(SOURCES_DIR + "curlclass.htm", INTERNAL_CLASSES_DIR + "curl.html");
  public final static ConvertPair dateClass = new ConvertPair(SOURCES_DIR + "dateclass.htm", INTERNAL_CLASSES_DIR + "date.html");
  public final static ConvertPair doubleClass = new ConvertPair(SOURCES_DIR + "intdoubleclass.htm", INTERNAL_CLASSES_DIR + "double.html");
  public final static ConvertPair envClass = new ConvertPair(SOURCES_DIR + "envclass.htm", INTERNAL_CLASSES_DIR + "env.html");
  public final static ConvertPair fileClass = new ConvertPair(SOURCES_DIR + "fileclass.htm", INTERNAL_CLASSES_DIR + "file.html");
  public final static ConvertPair formClass = new ConvertPair(SOURCES_DIR + "formclass.htm", INTERNAL_CLASSES_DIR + "form.html");
  public final static ConvertPair hashClass = new ConvertPair(SOURCES_DIR + "hashclass.htm", INTERNAL_CLASSES_DIR + "hash.html");
  public final static ConvertPair imageClass = new ConvertPair(SOURCES_DIR + "imageclass.htm", INTERNAL_CLASSES_DIR + "image.html");
  public final static ConvertPair intClass = new ConvertPair(SOURCES_DIR + "intdoubleclass.htm", INTERNAL_CLASSES_DIR + "int.html");
  public final static ConvertPair jsonClass = new ConvertPair(SOURCES_DIR + "jsonclass.htm", INTERNAL_CLASSES_DIR + "json.html");
  public final static ConvertPair junctionClass = new ConvertPair(SOURCES_DIR + "junctionclass.htm", INTERNAL_CLASSES_DIR + "junction.html");
  public final static ConvertPair mailClass = new ConvertPair(SOURCES_DIR + "mailclass.htm", INTERNAL_CLASSES_DIR + "mail.html");
  public final static ConvertPair mathClass = new ConvertPair(SOURCES_DIR + "mathclass.htm", INTERNAL_CLASSES_DIR + "math.html");
  public final static ConvertPair memoryClass = new ConvertPair(SOURCES_DIR + "memoryclass.htm", INTERNAL_CLASSES_DIR + "memory.html");
  public final static ConvertPair reflectionClass = new ConvertPair(SOURCES_DIR + "reflectionclass.htm", INTERNAL_CLASSES_DIR + "reflection.html");
  public final static ConvertPair regexClass = new ConvertPair(SOURCES_DIR + "regexclass.htm", INTERNAL_CLASSES_DIR + "regex.html");
  public final static ConvertPair requestClass = new ConvertPair(SOURCES_DIR + "requestclass.htm", INTERNAL_CLASSES_DIR + "request.html");
  public final static ConvertPair responseClass = new ConvertPair(SOURCES_DIR + "responseclass.htm", INTERNAL_CLASSES_DIR + "response.html");
  public final static ConvertPair statusClass = new ConvertPair(SOURCES_DIR + "statusclass.htm", INTERNAL_CLASSES_DIR + "status.html");
  public final static ConvertPair stringClass = new ConvertPair(SOURCES_DIR + "stringclass.htm", INTERNAL_CLASSES_DIR + "string.html");
  public final static ConvertPair tableClass = new ConvertPair(SOURCES_DIR + "tableclass.htm", INTERNAL_CLASSES_DIR + "table.html");
  public final static ConvertPair voidClass = new ConvertPair(SOURCES_DIR + "voidclass.htm", INTERNAL_CLASSES_DIR + "void.html");
  public final static ConvertPair xdocClass = new ConvertPair(SOURCES_DIR + "xdocclass.htm", INTERNAL_CLASSES_DIR + "xdoc.html");
  public final static ConvertPair xnodeClass = new ConvertPair(SOURCES_DIR + "xnodeclass.htm", INTERNAL_CLASSES_DIR + "xnode.html");

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

      System.out.println(new Scanner(fromFileInputStream).useDelimiter("\\Z").next());
    }
  }
}
