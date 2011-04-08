package ru.artlebedev.idea.plugins.parser.editor.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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

public class ParserModuleBuilder extends ModuleBuilder implements SourcePathsBuilder {
  private String myContentRootPath;
  private List<Pair<String, String>> mySourcePathes;

  public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
    final String moduleRootPath = getContentEntryPath();

    if (moduleRootPath != null) {
      final VirtualFile moduleContentRoot = LocalFileSystem.getInstance().refreshAndFindFileByPath(moduleRootPath.replace('\\', '/'));

      if (moduleContentRoot != null) {
        final ContentEntry contentEntry = modifiableRootModel.addContentEntry(moduleContentRoot);
        final List<Pair<String, String>> list = getSourcePaths();

        if (list != null) {
          for (Pair<String, String> p : list) {
            final VirtualFile relativeFile = VfsUtil.findRelativeFile(p.first, null);
            if (relativeFile != null) contentEntry.addSourceFolder(relativeFile, false);
          }
        }
      }
    }
  }

  public ModuleType getModuleType() {
    return ParserModuleType.getInstance();
  }

  @Nullable
  public String getContentEntryPath() {
    return myContentRootPath;
  }

  public void setContentEntryPath(String moduleRootPath) {
    myContentRootPath = moduleRootPath;
  }

  public List<Pair<String, String>> getSourcePaths() {
    return mySourcePathes;
  }

  public void setSourcePaths(List<Pair<String, String>> sourcePaths) {
    mySourcePathes = sourcePaths;
  }

  public void addSourcePath(Pair<String, String> sourcePathInfo) {
    if (mySourcePathes == null) mySourcePathes = new ArrayList<Pair<String, String>>();
    mySourcePathes.add(sourcePathInfo);
  }
}
