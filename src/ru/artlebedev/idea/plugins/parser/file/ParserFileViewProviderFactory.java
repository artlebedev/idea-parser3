package ru.artlebedev.idea.plugins.parser.file;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 26.03.11
 * Time: 23:20
 */
public class ParserFileViewProviderFactory implements FileViewProviderFactory {
  public FileViewProvider createFileViewProvider(final VirtualFile file, final Language language, final PsiManager manager, final boolean physical) {
    return new ParserFileViewProvider(manager, file, physical);
  }
}
