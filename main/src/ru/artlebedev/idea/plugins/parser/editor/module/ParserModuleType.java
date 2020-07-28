package ru.artlebedev.idea.plugins.parser.editor.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectWizardStepFactory;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.ParserIcons;

import javax.swing.*;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexander Pozdeev</a>
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

@Service
public class ParserModuleType extends ModuleType<ParserModuleBuilder> {
  public ParserModuleType() {
    super("Parser 3");
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
    return ParserIcons.PARSER_MODULE_ICON;
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
}