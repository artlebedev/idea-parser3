package ru.artlebedev.idea.plugins.parser.lang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserElementImpl;

import java.lang.reflect.InvocationTargetException;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
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

public class ParserPsiCreator implements ParserElementTypes {
  private static final Logger log = Logger.getInstance("#ParserPsiCreator");

  public static PsiElement createElement(ASTNode node) {
    final IElementType type = node.getElementType();
    Class<? extends ParserElementImpl> elementClass = ParserElementFactory.getElementClass(type);
    if (elementClass != null) {
      try {
        return elementClass.getConstructor(ASTNode.class).newInstance(node);
      } catch (InstantiationException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (IllegalAccessException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (InvocationTargetException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (NoSuchMethodException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
    }
    return new ASTWrapperPsiElement(node);
  }
}
