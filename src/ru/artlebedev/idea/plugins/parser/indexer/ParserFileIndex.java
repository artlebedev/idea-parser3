package ru.artlebedev.idea.plugins.parser.indexer;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiTreeChangeAdapter;
import com.intellij.psi.PsiTreeChangeEvent;
import com.intellij.psi.PsiTreeChangeListener;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.ParserStandardClasses;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserIncludePath;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.psi.impl.ParserIncludePathImpl;
import ru.artlebedev.idea.plugins.parser.psi.impl.ParserMethodReferenceImpl;
import ru.artlebedev.idea.plugins.parser.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.utils.ParserFilesUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

public class ParserFileIndex implements ProjectComponent {
  private Project myProject;
  private VirtualFileAdapter myFileListener;
  public Map<String, ParserFile> loadedClasses = new HashMap<String, ParserFile>();
  private PsiTreeChangeListener myTreeChangeListener;

  /**
   * The return of this method is a list of classes currently in the index.
   * Return is filtered so that each psi element is a valid element (has not null project)
   *
   * @return returns a map of class name - {@link ParserClass} values
   */

  public Map<String, ParserFile> getLoadedClasses() {
    Map<String, ParserFile> toReturn = new java.util.HashMap<String, ParserFile>();
    Collection<ParserFile> parserClasses = loadedClasses.values();
    for (ParserFile parserClass : parserClasses) {
      if (parserClass.getProject() != null) {
        toReturn.put(parserClass.getName(), parserClass);
      }
    }
    return toReturn;
  }

  public ParserFileIndex(Project project) {
    myProject = project;
  }

  public void initComponent() {
  }

  public void disposeComponent() {
  }

  @NotNull
  public String getComponentName() {
    return "ParserFileIndex";
  }

  /**
   * Adds parser class to an index
   *
   * @param clazz class to add
   */
  public void contributeClass(ParserClass clazz) {
    if (clazz == null)
      return;
    if (!loadedClasses.containsValue(clazz.getContainingFile()) && !loadedClasses.containsKey(clazz.getName())) {
      loadedClasses.put(clazz.getName(), (ParserFile) clazz.getContainingFile());
    }
  }

  public void projectOpened() {


    contributeClass(ParserStandardClasses.BOOL);
    contributeClass(ParserStandardClasses.VOID);
    contributeClass(ParserStandardClasses.STRING);
    contributeClass(ParserStandardClasses.INT);
    contributeClass(ParserStandardClasses.HASH);
    contributeClass(ParserStandardClasses.TABLE);
    contributeClass(ParserStandardClasses.COOKIE);
    contributeClass(ParserStandardClasses.CURL);
    contributeClass(ParserStandardClasses.DATE);

    myTreeChangeListener = new ParserTreeChangeListener();
    PsiManager.getInstance(myProject).addPsiTreeChangeListener(myTreeChangeListener);

    FileEditorManager.getInstance(myProject).addFileEditorManagerListener(new FileEditorManagerListener() {
      public void fileOpened(FileEditorManager source, VirtualFile file) {
        if (file.getFileType() == ParserFileType.PARSER_FILE_TYPE) {
          contributeToClasses(file);
        }
      }

      public void fileClosed(FileEditorManager source, VirtualFile file) {
      }

      public void selectionChanged(FileEditorManagerEvent event) {
      }
    });

    myFileListener = new VirtualFileAdapter() {
      public void fileCreated(VirtualFileEvent event) {
        VirtualFile file = event.getFile();
        if (file.getFileType() == ParserFileType.PARSER_FILE_TYPE) {
          processFileAdded((ParserFile) PsiManager.getInstance(myProject).findFile(file));
        }
      }

      public void beforeFileDeleted(VirtualFileEvent event) {
        final VirtualFile fileOrDir = event.getFile();
        if (fileOrDir.getFileType() == ParserFileType.PARSER_FILE_TYPE) {
          processFileRemoved((ParserFile) PsiManager.getInstance(myProject).findFile(fileOrDir));
        }
      }
    };

    VirtualFileManager.getInstance().addVirtualFileListener(myFileListener);
  }

  /**
   * If the given file is a parser class - adds it to the index
   * Also scans the file for other classes includes and adds them also
   *
   * @param fileOrDir parser file
   */
  private void contributeToClasses(VirtualFile fileOrDir) {
    final PsiFile parserFile = PsiManager.getInstance(myProject).findFile(fileOrDir);
    final ParserClass klass = ParserFilesUtil.containsClass(parserFile);
    if (klass != null) {
//			System.out.println("adding class " + klass.getName() + " from within itself.");
      contributeClass(klass);
    }

    final PsiElement[] includeElements = PsiTreeUtil.collectElements(parserFile, new PsiElementFilter() {
      public boolean isAccepted(PsiElement element) {
        return element instanceof ParserIncludePath;
      }
    });
    for (PsiElement element : includeElements) {
      ParserIncludePathImpl include = (ParserIncludePathImpl) element;
      final PsiElement parserFileInclude = include.resolve();
      if (parserFileInclude != null) {
        final ParserClass parserClass = PsiTreeUtil.getChildOfType(parserFileInclude, ParserClass.class);
        contributeClass(parserClass);
      }
    }

    final List<ParserMethodReferenceImpl> methodReferences = ParserResolveUtil.getIncludesViaUse(parserFile);
    for (ParserMethodReferenceImpl methodReference : methodReferences) {
      final ParserPassedParameter parameter = PsiTreeUtil.getNextSiblingOfType(methodReference, ParserPassedParameter.class);
      if (parameter != null) {
        final VirtualFile file = ParserFilesUtil.findInClassPaths(myProject, parameter.getText().trim());
        if (file != null) {
          final PsiFile psiFile = PsiManager.getInstance(myProject).findFile(file);
          if (psiFile != null) {
            final ParserClass parserClass = (ParserClass) PsiTreeUtil.getChildOfAnyType(psiFile, ParserClass.class);
            contributeClass(parserClass);
          }
        }
      }
    }
  }


  public void projectClosed() {
    loadedClasses = null;
  }

  /**
   * Called when new parser file is added
   *
   * @param psiFile file to process for adding to index
   */
//  public void processFileAdded(final ParserFile psiFile) {
  public void processFileAdded(final PsiFile psiFile) {
    if (loadedClasses != null) {
      if (loadedClasses.containsValue(psiFile)) {
        loadedClasses.put(psiFile.toString(), (ParserFile) psiFile);
        return;
      }
    }

    final ProgressIndicator progress = ProgressManager.getInstance().getProgressIndicator();
    if (progress != null) {
      progress.setText2(psiFile.getVirtualFile().getPresentableUrl());
    }
    contributeToClasses(psiFile.getVirtualFile());
  }

  /**
   * Called when a parser file is changed
   *
   * @param parserFile file to reindex
   */
  private void processFileChanged(final ParserFile parserFile) {
    if (!parserFile.isPhysical()) return;
    if (loadedClasses.containsValue(parserFile)) {
      processFileAdded(parserFile);
    }
  }

  /**
   * Called when a parser file is removed
   *
   * @param parserFile file to remove from index
   */
  private void processFileRemoved(final ParserFile parserFile) {
    loadedClasses.remove(parserFile.toString());
  }

  /**
   * Custom implementation of psi tree change listener.
   * It is needed to process files if they are added, changed or removed
   * in order to keep valid psi elements in the index
   */
  private class ParserTreeChangeListener extends PsiTreeChangeAdapter {
    public void childAdded(PsiTreeChangeEvent event) {
      final PsiElement child = event.getChild();
      if (child instanceof ParserFile && child.isPhysical()) {
        processFileAdded((ParserFile) child);
      } else {
        process(event);
      }
    }

    public void childrenChanged(PsiTreeChangeEvent event) {
      process(event);
    }

    public void childRemoved(PsiTreeChangeEvent event) {
      if (event.getChild() instanceof ParserFile) {
        processFileRemoved((ParserFile) event.getChild());
      } else {
        process(event);
      }
    }

    public void childReplaced(PsiTreeChangeEvent event) {
      process(event);
    }

    private void process(final PsiTreeChangeEvent event) {
      if (event.getParent() != null && event.getParent().getContainingFile() instanceof ParserFile) {
        processFileChanged((ParserFile) event.getParent().getContainingFile());
      }
    }
  }
}
