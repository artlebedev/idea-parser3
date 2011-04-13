package ru.artlebedev.parser.documentation;

import ru.artlebedev.idea.plugins.parser.documentation.util.ConvertUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
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

public class ConvertTool {
  public static void main(String[] args) {
    convertPackFromDir("ru");
    convertPackFromDir("en");
  }

  private static void convertPackFromDir(String lang) {
    final String outputDir = "/Users/dwr/Projects/idea-parser3/resources/ru/artlebedev/idea/plugins/parser/documentation/" + lang;
    final File file = new File("/Users/dwr/Projects/idea-parser3/resources/ru/artlebedev/parser/documentation/" + lang);

    File[] files = file.listFiles();

    for(int i = 0; i < files.length; i++) {
      if(files[i].getName().endsWith("png")) {
        copyfile(files[i].getAbsolutePath(), outputDir + "/" + getBaseName(files[i].getName()));
      } else if(files[i].getName().endsWith("htm")) {
        try {
          FileWriter fileWriter = new FileWriter(outputDir + "/" + getBaseName(files[i].getName()));

          String content = new Scanner(new FileInputStream(files[i].getAbsolutePath())).useDelimiter("\\Z").next();

          content = ConvertUtil.prettifyContent2(content);

          fileWriter.write(content);

          fileWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static String getBaseName(String file) {
    String[] fileParts = file.split("/");

    return fileParts[fileParts.length - 1];
  }

  private static void copyfile(String srFile, String dtFile){
    try{
      File f1 = new File(srFile);
      File f2 = new File(dtFile);
      InputStream in = new FileInputStream(f1);

      //For Append the file.
//      OutputStream out = new FileOutputStream(f2,true);

      //For Overwrite the file.
      OutputStream out = new FileOutputStream(f2);

      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0){
        out.write(buf, 0, len);
      }
      in.close();
      out.close();
    }
    catch(FileNotFoundException ex){
      ex.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
