package ru.artlebedev.idea.plugins.parser.file;

import com.intellij.ide.FileIconPatcher;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import ru.artlebedev.idea.plugins.parser.ParserIcons;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.util.ParserFilesUtil;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

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

public class ParserFileIconPatcher implements FileIconPatcher {
  public static final String[] parseredHtmlExtensions = {
          "html"
  };
  public static final List<String> parseredHtmlExtensionList = Arrays.asList(parseredHtmlExtensions);

  public Icon patchIcon(final Icon baseIcon, final VirtualFile file, final int flags, final Project project) {
    if (project == null) {
      return baseIcon;
    }

    return replaceIcon(file, flags, project, baseIcon);
  }

  private static Icon replaceIcon(VirtualFile file, int flags, Project project, Icon baseIcon) {
    final PsiFile parserFile = PsiManager.getInstance(project).findFile(file);
    final ParserClass klass = ParserFilesUtil.containsClass(parserFile);

    if(parseredHtmlExtensionList.contains(file.getExtension())) {
      return ParserIcons.PARSER_HTML_FILE_ICON;
    }

    if (klass != null) {
      return ParserIcons.PARSER_CLASS_FILE_ICON;
    }

    return baseIcon;
  }
}

