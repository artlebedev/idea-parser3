package ru.artlebedev.idea.plugins.parser.editor.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.ParserIcons;

import javax.swing.*;
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

public class ParserProjectConfiguration implements ProjectComponent, Configurable, JDOMExternalizable {
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
        //CompletionUtil.registerCompletionData(ParserFileType.PARSER_FILE_TYPE, new ParserCompletionData());
        /*ShowParameterInfoHandler.register(ParserFileType.PARSER_FILE_TYPE.getLanguage(), new MethodParameterInfoHandler() {
          @NotNull
          public ParameterInfoHandler[] getHandlers() {
            return new ParameterInfoHandler[]{new ParserParameterInfoHandler()};
          }
        });*/
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

  public void readExternal(Element element) throws InvalidDataException {
    Element classPathContainer = element.getChild("class-path");
    if (classPathContainer == null) return;
    List paths = classPathContainer.getChildren("path");
    for (Object path : paths) {
      Element o = (Element) path;
      classPaths.add(o.getAttributeValue("value"));
    }
  }

  public void writeExternal(Element element) throws WriteExternalException {
    Element classPathContainer = new Element("class-path");
    element.addContent(classPathContainer);
    for (String path : classPaths) {
      Element pathElement = new Element("path");
      pathElement.setAttribute("value", path);
      classPathContainer.addContent(pathElement);
    }
  }
}
