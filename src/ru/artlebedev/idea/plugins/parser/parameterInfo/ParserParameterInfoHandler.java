package ru.artlebedev.idea.plugins.parser.parameterInfo;

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
import ru.artlebedev.idea.plugins.parser.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethodReference;
import ru.artlebedev.idea.plugins.parser.psi.lookup.ParserSmartLookupItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2006 Jay Bird <a4blank@yahoo.com>
 * Copyright 2006-2011 ArtLebedev Studio
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

public class ParserParameterInfoHandler implements ParameterInfoHandler {
  public boolean couldShowInLookup() {
    return true;
  }

  @Override
  public Object[] getParametersForLookup(LookupElement lookupElement, ParameterInfoContext parameterInfoContext) {
    return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
  }

  public Object[] getParametersForDocumentation(Object o, ParameterInfoContext parameterInfoContext) {
//		System.out.println("------------------");
//		System.out.println("getParametersForDocumentation");
//		System.out.println("args :: " + o + " | " + parameterInfoContext);
//		System.out.println("------------------");
    return new Object[0];
  }

  public Object findElementForParameterInfo(CreateParameterInfoContext context) {
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
        Object[] variants = methodReference.getReference().getVariants();
        List<PsiElement> list = new ArrayList<PsiElement>();
        for (Object variant : variants) {
//					System.out.println(variant);
          if (variant instanceof ParserSmartLookupItem)
            variant = ((ParserSmartLookupItem) variant).getElement();
          if (variant instanceof ParserMethod && ((ParserMethod) variant).getName().equals(name)) {
            list.add((PsiElement) variant);
          }
        }
//				System.out.println("added items to show");
        context.setItemsToShow(list.toArray(new Object[0]));
      }
    }
    /*	JSCallExpression parent = (JSCallExpression) psiElement;
        com.intellij.lang.javascript.psi.JSExpression methodExpression = parent.getMethodExpression();
        if (methodExpression instanceof JSReferenceExpression) {
          ResolveResult resolveResults[] = ((JSReferenceExpression) methodExpression).multiResolve(true);
          if (resolveResults.length > 0) {
            List items = new ArrayList(resolveResults.length);
            Set availableSignatures = new HashSet();
            ResolveResult arr$[] = resolveResults;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
              ResolveResult r = arr$[i$];
              PsiElement element = r.getElement();
              if (element instanceof JSNamedElementProxy)
                element = ((JSNamedElementProxy) element).getElement();
              if (element instanceof JSProperty)
                element = ((JSProperty) element).getValue();
              if (!(element instanceof JSFunction))
                continue;
              JSFunction o = (JSFunction) element;
              JSParameterList parameterList = o.getParameterList();
              if (parameterList == null)
                continue;
              String text = parameterList.getText();
              if (!availableSignatures.contains(text)) {
                items.add(o);
                availableSignatures.add(text);
              }
            }

            context.setItemsToShow(items.toArray(new Object[items.size()]));
          }
        }
      }*/
//		System.out.println("------------------");
    return callingReference;
  }

  public void showParameterInfo(@NotNull Object o, CreateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("showParameterInfo");
//		System.out.println("args :: " + o + " | " + context);
//		System.out.println("------------------");
    showParameterInfo((ParserCallingReference) o, context);
  }

  public void showParameterInfo(@NotNull ParserCallingReference callingReference, CreateParameterInfoContext context) {
    if (callingReference.getReferenceMethod() != null)
      context.showHint(callingReference, context.getEditor().getCaretModel().getOffset(), this);
  }

  public Object findElementForUpdatingParameterInfo(UpdateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("updateParameterInfo");
//		System.out.println("------------------");
    return ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), ParserCallingReference.class);
  }

  public void updateParameterInfo(@NotNull Object o, UpdateParameterInfoContext context) {
//		System.out.println("------------------");
//		System.out.println("updateParameterInfo");
//		System.out.println("args :: " + o + " | " + context);
//		System.out.println("------------------");
    context.setCurrentParameter(1);
    context.setUIComponentEnabled(0, true);
  }

  public String getParameterCloseChars() {
    return ";";
  }

  public boolean tracksParameterIndex() {
    return true;
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
