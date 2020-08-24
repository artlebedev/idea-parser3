package ru.artlebedev.idea.plugins.parser.editor.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

public class ParserProjectConfiguration implements Configurable {
  public List<String> classPaths = new ArrayList<String>();
  public static Project myProject;
  private ParserProjectConfigurationForm configuration;

  private void initClassPaths() {
    ParserConfigurationProvider configurationProvider = ParserConfigurationProvider.getInstance(myProject);
    classPaths = configurationProvider.getClassPaths();
  }

  public List<String> getClassPaths() {
    return classPaths;
  }

  public void setClassPaths(List<String> classPaths) {
    this.classPaths = classPaths;
  }

  public ParserProjectConfiguration(Project project) {
    myProject = project;
    initClassPaths();
  }

  public String getDisplayName() {
    return "Parser Classpaths";
  }

  public JComponent createComponent() {
    if (configuration == null) {
      configuration = new ParserProjectConfigurationForm();
      configuration.setUp(myProject);
    }
    return configuration.getRootComponent();
  }

  public boolean isModified() {
    return configuration != null && configuration.isModified(this);
  }

  public void apply() throws ConfigurationException {
    if (configuration != null) {
      configuration.getData(this);
      ParserConfigurationProvider configurationProvider = ParserConfigurationProvider.getInstance(myProject);
      configurationProvider.setClassPaths(classPaths);
    }
  }

  public void reset() {
    if (configuration != null)
      configuration.setData(this);
  }

  public void disposeUIResources() {
    configuration = null;
  }
}
