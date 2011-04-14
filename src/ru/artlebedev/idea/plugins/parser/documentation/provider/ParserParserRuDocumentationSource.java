package ru.artlebedev.idea.plugins.parser.documentation.provider;

import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.documentation.Marker;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;

import java.util.ArrayList;
import java.util.List;
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

public class ParserParserRuDocumentationSource implements DocumentationSource {
  private List<String> standardClasses = new ArrayList<String>();

  public ParserParserRuDocumentationSource() {
    super();

    standardClasses.add("bool");
    standardClasses.add("console");
    standardClasses.add("cookie");
    standardClasses.add("curl");
    standardClasses.add("date");
    standardClasses.add("env");
    standardClasses.add("file");
    standardClasses.add("form");
    standardClasses.add("hash");
    standardClasses.add("hashfile");
    standardClasses.add("image");
    standardClasses.add("inet");
    standardClasses.add("int");
    standardClasses.add("json");
    standardClasses.add("junction");
    standardClasses.add("mail");
    standardClasses.add("math");
    standardClasses.add("memory");
    standardClasses.add("reflection");
    standardClasses.add("regex");
    standardClasses.add("request");
    standardClasses.add("response");
    standardClasses.add("status");
    standardClasses.add("string");
    standardClasses.add("table");
    standardClasses.add("void");
    standardClasses.add("xdoc");
    standardClasses.add("xnode");
  }

  @Override
  public String documentation(PsiElement element, PsiElement originalElement) {
    if(element instanceof ParserClass) {
      if(standardClasses.contains(((ParserClass) element).getName())) {
        System.out.println(new Scanner(Marker.class.getResourceAsStream("ru/" + ((ParserClass) element).getName().toLowerCase() + "class.htm")).useDelimiter("\\Z").next());
        return new Scanner(Marker.class.getResourceAsStream("ru/" + ((ParserClass) element).getName().toLowerCase() + "class.htm")).useDelimiter("\\Z").next();
      }
    }
//    if(element instanceof ParserMethod) {
//      return ((ParserMethod) element).getParserDoc();
//    }
    return null;
  }

  @Override
  public String documentationUrl(PsiElement element, PsiElement originalElement) {
    return null;
  }
}
