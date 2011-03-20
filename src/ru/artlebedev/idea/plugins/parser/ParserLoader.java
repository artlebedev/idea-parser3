package ru.artlebedev.idea.plugins.parser;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

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

public class ParserLoader implements ApplicationComponent {
  public List<String> constructorNames = Arrays.asList(new String[]{"new", "init", "create"});

  public boolean enableActions = true;

	public boolean isEnableActions() {
		return enableActions;
	}

	public void setEnableActions(boolean enableActions) {
		this.enableActions = enableActions;
	}

	public boolean htmlFilesAsParser = false;

	public boolean isHtmlFilesAsParser() {
		return htmlFilesAsParser;
	}

	public void setHtmlFilesAsParser(boolean htmlFilesAsParser) {
		this.htmlFilesAsParser = htmlFilesAsParser;
	}

  /**
   * You can get constructor names which user has defined. (since every method in a class could be a constructor it's useful to have a filter)
   *
   * @return list of constuctor names
   */
  public List<String> getConstructorNames() {
    return constructorNames;
  }

  public void setConstructorNames(List<String> constructorNames) {
    this.constructorNames = constructorNames;
  }

  public static ParserLoader getInstance() {
    return ApplicationManager.getApplication().getComponent(ParserLoader.class);
  }

  public ParserLoader() {
  }

  public void initComponent() {
    ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerAdapter() {
      public void projectOpened(final Project project) {
      }
    });
  }

  public void disposeComponent() {
  }

  @NotNull
  public String getComponentName() {
    return ParserComponents.PARSER_LOADER;
  }
}
