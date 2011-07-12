package ru.artlebedev.idea.plugins.parser.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import ru.artlebedev.idea.plugins.parser.editor.settings.ParserProjectConfiguration;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;

import java.util.ArrayList;
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

public class ParserFilesUtil {

  public static List<String> getClassPaths(Project project) {
    return project.getComponent(ParserProjectConfiguration.class).getClassPaths();
  }

  public static VirtualFile findInClassPaths(Project project, String relativePath) {
    List<String> classPaths = getClassPaths(project);
    for (String classPath : classPaths) {
      String url = VfsUtil.pathToUrl(classPath);
      VirtualFile fileByUrl = VirtualFileManager.getInstance().findFileByUrl(url + "/" + relativePath);
      if (fileByUrl != null) {
        return fileByUrl;
      }
    }
    return null;
  }

  public static String[] findVariants(Project project) {
    List<String> paths = new ArrayList<String>();
    List<String> classPaths = getClassPaths(project);
    for (String classPath : classPaths) {
      String url = VfsUtil.pathToUrl(classPath);
      VirtualFile directory = VirtualFileManager.getInstance().findFileByUrl(url);
      if (directory != null && directory.isDirectory()) {
        paths.addAll(getChildren(directory, directory));
      }
    }
    return paths.toArray(new String[0]);
  }

  private static List<String> getChildren(VirtualFile directory, VirtualFile relativeTo) {
    List<String> result = new ArrayList<String>();
    VirtualFile[] children = directory.getChildren();
    for (VirtualFile virtualFile : children) {
      if (virtualFile.isDirectory()) {
        result.addAll(getChildren(virtualFile, relativeTo));
      } else {
        result.add(VfsUtil.getRelativePath(virtualFile, relativeTo, "/".toCharArray()[0]));
      }
    }
    return result;
  }

  public static ParserClass containsClass(PsiFile file) {
    return PsiTreeUtil.getChildOfType(file, ParserClass.class);
  }

}
