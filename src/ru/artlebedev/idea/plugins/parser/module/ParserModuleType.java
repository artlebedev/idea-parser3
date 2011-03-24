package ru.artlebedev.idea.plugins.parser.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectWizardStepFactory;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.ParserIcons;

import javax.swing.*;

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

public class ParserModuleType extends ModuleType<ParserModuleBuilder> implements ApplicationComponent {
  public ParserModuleType() {
    super("Parser");
  }

  public ParserModuleBuilder createModuleBuilder() {
    return new ParserModuleBuilder();
  }

  public String getName() {
    return ParserBundle.message("module.type.parser.name");
  }

  public String getDescription() {
    return ParserBundle.message("module.type.parser.description");
  }

  public Icon getBigIcon() {
    return ParserIcons.PARSER_LARGE_ICON;
  }

  public Icon getNodeIcon(boolean isOpened) {
    return ParserIcons.PARSER_FILE_ICON;
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ParserModuleBuilder moduleBuilder, ModulesProvider modulesProvider) {
    final ModuleWizardStep chooseSourceFolder = ProjectWizardStepFactory.getInstance().createSourcePathsStep(wizardContext, moduleBuilder, null, null);
    return new ModuleWizardStep[]{
            chooseSourceFolder
    };
  }

  @NonNls
  @NotNull
  public String getComponentName() {
    return "ParserSupport.ModuleType";
  }

  public void initComponent() {
    ModuleTypeManager.getInstance().registerModuleType(this);
  }

  public void disposeComponent() {
  }

  public static ModuleType getInstance() {
    return ApplicationManager.getApplication().getComponent(ParserModuleType.class);
  }
}