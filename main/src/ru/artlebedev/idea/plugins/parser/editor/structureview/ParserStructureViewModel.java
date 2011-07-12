package ru.artlebedev.idea.plugins.parser.editor.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObject;

import java.util.Arrays;
import java.util.List;

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

public class ParserStructureViewModel extends TextEditorBasedStructureViewModel {
  private PsiElement root;
  public static List<Class> suitableClasses = Arrays.asList(new Class[]{
          ParserFile.class,
          ParserClass.class,
          ParserMethod.class,
          ParserObject.class
  });

  public ParserStructureViewModel(PsiElement psiFile) {
    super(psiFile.getContainingFile());
    root = psiFile;
  }

  protected PsiFile getPsiFile() {
    return root.getContainingFile();
  }

  @NotNull
  protected Class[] getSuitableClasses() {
    return suitableClasses.toArray(new Class[suitableClasses.size()]);
  }

  @NotNull
  public StructureViewTreeElement getRoot() {
    return new ParserStructureViewElement(root);
  }

  @NotNull
  public Grouper[] getGroupers() {
    return new Grouper[0];
  }

  @NotNull
  public Sorter[] getSorters() {
    return new Sorter[]{Sorter.ALPHA_SORTER};
  }

  @NotNull
  public Filter[] getFilters() {
    return new Filter[0];
  }
}
