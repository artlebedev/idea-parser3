package ru.artlebedev.idea.plugins.parser.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringActionHandler;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.psi.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.ParserObject;

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

public class ParserRefactoringSupport extends RefactoringSupportProvider {
  private RefactoringActionHandler handler = new ParserIntroduceVariableHandler();

  public boolean isSafeDeleteAvailable(PsiElement element) {
    return element instanceof ParserClass || element instanceof ParserMethod || element instanceof ParserObject;
  }

  @Nullable
  public RefactoringActionHandler getIntroduceVariableHandler() {
    return handler;
  }
}
