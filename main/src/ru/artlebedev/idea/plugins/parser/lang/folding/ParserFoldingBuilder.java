package ru.artlebedev.idea.plugins.parser.lang.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.util.ParserFilesUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexandr Pozdeev</a>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011-2020 ArtLebedev Studio
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

public class ParserFoldingBuilder implements FoldingBuilder {
  @NotNull
  @Override
  public FoldingDescriptor[] buildFoldRegions(@NotNull ASTNode node, @NotNull Document document) {
    List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
    appendDescriptors(node.getPsi(), descriptors, new HashSet<PsiElement>());
    return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
  }

  private static void appendDescriptors(PsiElement element, List<FoldingDescriptor> descriptors, Set<PsiElement> usedComments) {
    if(element instanceof ParserFile) {
      ParserClass klass = ParserFilesUtil.containsClass((ParserFile) element);

      ParserMethod[] methods = PsiTreeUtil.getChildrenOfType(klass, ParserMethod.class);

      if(methods != null) {
        for(ParserMethod method : methods) {
          if(method.getTextRange() != null) {
            try {
              descriptors.add(new FoldingDescriptor(method, method.getTextRange()){
                @Override
                public String getPlaceholderText() {
                  if(getElement().getText() != null) {
                    if(getElement().getText().length() > 50) {
                      return getElement().getText().substring(0, 50) + "...";
                    } else {
                      return getElement().getText() + "...";
                    }
                  } else {
                    return "...";
                  }
                }
              });
            } catch(Exception ignored) {

            }
            appendDescriptorsToCallingReference(PsiTreeUtil.getChildrenOfType(method, ParserCallingReference.class), descriptors);
          }
        }
      }
    }
  }

  private static void appendDescriptorsToCallingReference(@Nullable ParserCallingReference[] references, List<FoldingDescriptor> descriptors) {
    if(references != null) {
      for(ParserCallingReference reference : references) {
        ParserPassedParameter[] parameters = PsiTreeUtil.getChildrenOfType(reference, ParserPassedParameter.class);
        if (parameters != null) {
          for(ParserPassedParameter parameter : parameters) {
            appendDescriptorsToCallingReference(PsiTreeUtil.getChildrenOfType(parameter, ParserCallingReference.class), descriptors);
          }
        }

        if(reference.getTextRange() != null) {
          try {
            appendDescriptorsToCallingReference(PsiTreeUtil.getChildrenOfType(reference, ParserCallingReference.class), descriptors);
            String text = reference.getText();
            Pattern p = Pattern.compile("^([^{]+)(\\{)[\\w\\W]+(})$");
            Matcher m = p.matcher(text);

            if (m.matches()) {
              descriptors.add(new FoldingDescriptor(reference, reference.getTextRange()){
                @Override
                public String getPlaceholderText() {
                  return m.group(1) + m.group(2) + "..." + m.group(3);
                }
              });
            }

          } catch(Exception ignored) {
          }
        }
      }
    }
  }

  @Override
  public String getPlaceholderText(@NotNull ASTNode node) {
    final IElementType elemType = node.getElementType();

    System.out.println(elemType);

    if(elemType.equals(ParserElementTypes.METHOD)) {
      return "@...";
    }

    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isCollapsedByDefault(@NotNull ASTNode node) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
