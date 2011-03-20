package ru.artlebedev.idea.plugins.parser;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 20.03.11
 * Time: 4:40
 */
public class ParserApplication implements ApplicationComponent {
  public ParserApplication() {
  }

  public void initComponent() {
    ApplicationManager.getApplication().runWriteAction(
            new Runnable() {
              @Override
              public void run() {
                FileTypeManager.getInstance().registerFileType(new ParserFileType(), "p");
                FileTypeManager.getInstance().registerFileType(new ParserFileType(), "bp");
                FileTypeManager.getInstance().registerFileType(new ParserFileType(), "pp");
              }
            }
    );
  }

  public void disposeComponent() {
    // TODO: insert component disposal logic here
  }

  @NotNull
  public String getComponentName() {
    return "ParserApplication";
  }
}
