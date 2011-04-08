package org.codingbox.idea.dev.util;

import com.intellij.psi.PsiElement;

/**
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
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

public class PsiDevUtil {
  /**
   * Example usage:
   *  PsiDevUtil.printPsiElements(((ParserCallingReference) getParent()).getReferenceObjects());
   *
   * Note: could be acceptable to use only with Parser 3 PsiTree
   *
   * @param elements array of PsiElements to call their toString()
   */
  public static void printPsiElements(PsiElement[] elements) {
    for(PsiElement element : elements) {
      System.out.println("PsiDevUtil#printPsiElements: " + element);
    }
  }
}
