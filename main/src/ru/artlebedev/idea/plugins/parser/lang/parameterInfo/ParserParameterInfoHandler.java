package ru.artlebedev.idea.plugins.parser.lang.parameterInfo;

import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;
import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import com.intellij.lang.parameterInfo.ParameterInfoUtils;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserElement;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethodReference;

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

public class ParserParameterInfoHandler implements ParameterInfoHandler<ParserElement, Object> {
  public boolean couldShowInLookup() {
    return true;
  }

  @Override
  public Object[] getParametersForLookup(LookupElement lookupElement, ParameterInfoContext parameterInfoContext) {
    return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
  }

  public ParserElement findElementForParameterInfo(CreateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("findElementForParameterInfo");
//		System.out.println("args :: " + context);

    ParserCallingReference callingReference = ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), ParserCallingReference.class);
    if (callingReference != null) {
      ParserMethodReference methodReference = callingReference.getReferenceMethod();
      if (methodReference != null) {
        PsiElement psiElement = methodReference.getReference().resolve();
        if (psiElement == null)
          return null;
        String name = ((PsiNamedElement) psiElement).getName();
        LookupElement[] variants = (LookupElement[]) methodReference.getReference().getVariants();
        List<PsiElement> list = new ArrayList<PsiElement>();
        for (LookupElement variant : variants) {
          Object o = variant.getObject();
          if (o instanceof ParserMethod && ((ParserMethod) o).getName().equals(name)) {
            list.add((PsiElement) o);
          }
        }
        //System.out.println(Arrays.deepToString(list.toArray(new Object[0])));
        context.setItemsToShow(list.toArray(new Object[0]));
      }
    }
    return callingReference;
  }

  public void showParameterInfo(@NotNull ParserElement element, CreateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("showParameterInfo");
//		System.out.println("args :: " + element + " | " + context);
//		System.out.println("------------------");
    showParameterInfo((ParserCallingReference) element, context);
  }

  public void showParameterInfo(@NotNull ParserCallingReference callingReference, CreateParameterInfoContext context) {
    if (callingReference.getReferenceMethod() != null)
      context.showHint(callingReference, context.getEditor().getCaretModel().getOffset(), this);
  }

  public ParserElement findElementForUpdatingParameterInfo(UpdateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("updateParameterInfo");
//		System.out.println("------------------");
    return ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), ParserCallingReference.class);
  }

  public void updateParameterInfo(@NotNull ParserElement element, UpdateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("updateParameterInfo");
//		System.out.println("args :: " + element + " | " + context);
//		System.out.println("------------------");
    context.setCurrentParameter(1);
    context.setUIComponentEnabled(0, true);
  }

  public void updateUI(Object o, ParameterInfoUIContext parameterInfoUIContext) {
    updateUI((ParserMethod) o, parameterInfoUIContext);
  }

  public void updateUI(ParserMethod method, ParameterInfoUIContext context) {
//		System.out.println("------------------");
//		System.out.println("updateUI");
//		System.out.println("args :: " + method + " | " + context);
//		String noParams = CodeInsightBundle.message("parameter.info.no.parameters");
    String params = method.getParameterList().getText();
    String toShow = params.substring(1, params.length() - 1);
    if (toShow.trim().equals("")) {
      toShow = CodeInsightBundle.message("parameter.info.no.parameters");
    }
//		System.out.println(toShow);
//		System.out.println("------------------");
    context.setUIComponentEnabled(false);
    context.setupUIComponentPresentation(toShow, 0, 0, false, false, false, context.getDefaultParameterColor());
  }
}
