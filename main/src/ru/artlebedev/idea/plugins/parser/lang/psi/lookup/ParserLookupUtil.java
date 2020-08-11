package ru.artlebedev.idea.plugins.parser.lang.psi.lookup;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserNamedElement;

import java.util.ArrayList;
import java.util.List;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexandr Pozdeev</a>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
 * Copyright 2006-2020 ArtLebedev Studio
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

public class ParserLookupUtil {
  public static LookupElement[] createSmartLookupItems(List<PsiElement> elements) {
    List<LookupElement> result = new ArrayList<LookupElement>();
    for (PsiElement element : elements) {
      if (element instanceof ParserNamedElement) {
        if ((((ParserNamedElement) element).getName() != null) &&
                !ParserLanguageConstants.HAS_NO_CONSTRUCTOR.equals(((ParserNamedElement) element).getName()) &&
                !ParserLanguageConstants.AUTO_METHOD_NAME.equals(((ParserNamedElement) element).getName()) &&
                !ParserLanguageConstants.CONF_METHOD_NAME.equals(((ParserNamedElement) element).getName()))
          result.add(createLookupItem((ParserNamedElement) element));
      }
    }

    return result.toArray(new LookupElement[0]);
  }

  private static LookupElement createLookupItem(ParserNamedElement element) {
    LookupElementBuilder lookupElement;
    if(element instanceof ParserMethod) {
      String params = ((ParserMethod) element).getParameterList().getText();
      String tailText = "[" + params.substring(1, params.length() - 1) + "]";
      lookupElement = LookupElementBuilder.createWithIcon(element).withTailText(tailText);
    } else {
      lookupElement = LookupElementBuilder.createWithIcon(element);
    }

    return lookupElement;
  }
}
