package ru.artlebedev.idea.plugins.parser.editor.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.ParserIcons;

import javax.swing.*;
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


@State(name = "ParserProjectState")

public class ParserProjectConfiguration implements ProjectComponent, Configurable, PersistentStateComponent<ParserProjectConfiguration.State> {
  public List<String> classPaths = new ArrayList<String>();
  public static Project _project;
  private ParserProjectConfigurationForm configuration;

  public List<String> getClassPaths() {
    return classPaths;
  }

  public void setClassPaths(List<String> classPaths) {
    this.classPaths = classPaths;
  }

  public ParserProjectConfiguration(Project project) {
    _project = project;
  }

  public String getDisplayName() {
    return "Parser Classpaths";
  }

  public Icon getIcon() {
    return ParserIcons.CONFIGURATION;
  }

  @Nullable
  @NonNls
  public String getHelpTopic() {
    return null;
  }

  public JComponent createComponent() {
    if (configuration == null) {
      configuration = new ParserProjectConfigurationForm();
      configuration.setUp(_project);

    }
    return configuration.getRootComponent();
  }

  public boolean isModified() {
    return configuration != null && configuration.isModified(this);
  }

  public void apply() throws ConfigurationException {
    if (configuration != null)
      configuration.getData(this);
  }

  public void reset() {
    if (configuration != null)
      configuration.setData(this);
  }

  public void disposeUIResources() {
    configuration = null;
  }

  public void projectOpened() {
    ApplicationManager.getApplication().runWriteAction(new Runnable() {
      public void run() {
      }
    });
  }

  public void projectClosed() {
  }

  @NotNull
  @NonNls
  public String getComponentName() {
    return "ParserProjectConfiguration";
  }

  public void initComponent() {
  }

  public void disposeComponent() {
  }

  class State {
    public String value;
  }

  State myState;

  public State getState() {
    return myState;
  }

  public void loadState(State state) {
    myState = state;
  }
}
