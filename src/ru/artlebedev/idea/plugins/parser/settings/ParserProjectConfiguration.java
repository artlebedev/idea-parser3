package ru.artlebedev.idea.plugins.parser.settings;

import com.intellij.codeInsight.completion.CompletionUtil;
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
import ru.artlebedev.idea.plugins.parser.completion.ParserCompletionData;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/*
 * idea4parser: slightly useful plugin
 *
 * Copyright (c) 2011, Valeriy Yatsko
 * Copyright (c) 2006, Markov Nikolaj
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
    return "Parser classpaths";
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
        CompletionUtil.registerCompletionData(ParserFileType.PARSER_FILE_TYPE, new ParserCompletionData());


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
