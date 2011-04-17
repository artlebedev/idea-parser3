package ru.artlebedev.idea.plugins.parser.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReferenceExpression;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserCallingReferenceImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserClassImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserClassParentImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserHashKeyImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserIncludeImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserIncludePathImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserMethodImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserMethodReferenceImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserObjectImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserObjectReferenceImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserParameterImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserParameterListImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserPassedParameterImpl;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
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

public class ParserElementVisitor extends PsiElementVisitor {
  public void visitReferenceExpression(PsiReferenceExpression expression) {
    visitElement(expression);
  }

  public void visitElement(PsiElement element) {
  }


  public void visitParserClass(ParserClassImpl parserClass) {
    visitElement(parserClass);
  }

  public void visitParserMethod(ParserMethodImpl parserMethod) {
    visitElement(parserMethod);
  }

  public void visitParserObject(ParserObjectImpl parserObject) {
    visitElement(parserObject);
  }

  public void visitParserParameterList(ParserParameterListImpl parserParameterList) {
    visitElement(parserParameterList);
  }

  public void visitParserParameter(ParserParameterImpl parserParameter) {
    visitElement(parserParameter);
  }

  public void visitParserInclude(ParserIncludeImpl parserInclude) {
    visitElement(parserInclude);
  }

  public void visitParserClassParent(ParserClassParentImpl parserClassParent) {
    visitElement(parserClassParent);
  }

  public void visitParserCallingReference(ParserCallingReferenceImpl parserCallingReference) {
    visitElement(parserCallingReference);
  }

  public void visitParserMethodReference(ParserMethodReferenceImpl parserMethodReference) {
    visitElement(parserMethodReference);
  }

  public void visitParserObjectReference(ParserObjectReferenceImpl parserObjectReference) {
    visitElement(parserObjectReference);
  }

  public void visitParserPassedParameter(ParserPassedParameterImpl parserPassedParameter) {
    visitElement(parserPassedParameter);
  }

  public void visitParserHashKey(ParserHashKeyImpl parserHashKey) {
    visitElement(parserHashKey);
  }

  public void visitParserIncludePath(ParserIncludePathImpl parserIncludePath) {
    visitElement(parserIncludePath);
  }

  public void visitParserClassReference(ParserClassReference parserClassReference) {
    visitElement(parserClassReference);
  }
}
