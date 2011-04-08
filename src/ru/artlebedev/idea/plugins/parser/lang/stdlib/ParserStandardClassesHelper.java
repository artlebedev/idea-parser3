package ru.artlebedev.idea.plugins.parser.lang.stdlib;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.util.LocalTimeCounter;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.editor.settings.ParserProjectConfiguration;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.util.ParserFilesUtil;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * idea-parser3: slightly useful plugin.
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

public class ParserStandardClassesHelper {
  private static Project project;

  public static ParserClass loadStandardClass(String fileName) {
    if (project == null) {
      project = ParserProjectConfiguration._project;
    }

    InputStream asStream = Marker.class.getResourceAsStream(fileName);
    if (asStream != null) {
      String file = null;
      final PsiManager psiManager = PsiManager.getInstance(project);
      try {
        file = StreamUtil.readText(asStream);
        // This method does not expand tree
        final Method method = psiManager.getClass().getMethod(
                "createFileFromText",
                String.class,
                FileType.class,
                CharSequence.class,
                Long.TYPE,
                Boolean.TYPE,
                Boolean.TYPE
        );
        ParserFile parserFile = (ParserFile) method.invoke(
                psiManager,
                fileName,
                ParserFileType.PARSER_FILE_TYPE,
                file,
                LocalTimeCounter.currentTime(),
                false,
                false
        );
        return ParserFilesUtil.containsClass(parserFile);
      } catch (Exception e) {
        PsiFile psiFile = PsiFileFactory.getInstance(ParserProjectConfiguration._project).createFileFromText(fileName, file);
        return ParserFilesUtil.containsClass(psiFile);
      }
    }

    return null;
  }
}