package ru.artlebedev.idea.plugins.parser.lang.stdlib;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.util.LocalTimeCounter;
import ru.artlebedev.idea.plugins.parser.editor.settings.ParserConfigurationProvider;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.util.ParserFilesUtil;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

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

public class ParserStandardClassesHelper {
  private static Project project;
  public static Set<String> loadedStandardClasses = new HashSet<String>();

  public static ParserClass loadStandardClass(String fileName) {
    if (project == null) {
      project = ParserConfigurationProvider.myProject;
    }

    if(fileName.contains(".p")) {
      loadedStandardClasses.add(fileName.split(".p")[0]);
    }

    InputStream asStream = Marker.class.getResourceAsStream(fileName);
    if (asStream != null) {
      String fileText = null;
      final PsiManager psiManager = PsiManager.getInstance(project);
      try {
        fileText = StreamUtil.readText(asStream, StandardCharsets.UTF_8);
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
                ParserFileType.INSTANCE,
                fileText,
                LocalTimeCounter.currentTime(),
                false,
                false
        );
        return ParserFilesUtil.containsClass(parserFile);
      } catch (Exception e) {
        if (fileText != null) {
          FileType fileType = FileTypeRegistry.getInstance().getFileTypeByFileName(fileName);
          //PsiFile psiFile = PsiFileFactory.getInstance(ParserProjectConfiguration._project).createFileFromText(fileName, fileType, fileText);
          PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(fileName, fileType, fileText);
          return ParserFilesUtil.containsClass(psiFile);
        }
      }
    }

    return null;
  }
}