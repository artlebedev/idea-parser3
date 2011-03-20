package ru.artlebedev.idea.plugins.parser;

import org.apache.commons.collections.ListUtils;
import ru.artlebedev.idea.plugins.parser.lang.ParserLoader;

import javax.swing.*;
import java.util.ArrayList;
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

public class ParserGeneralConfiguration {
  private JCheckBox actionsEnabledBox;

  private JPanel rootComponent;
  private JTextField constructorNames;
  private JCheckBox htmlFilesAsParser;

  public JPanel getRootComponent() {
    return rootComponent;
  }

  public void setData(ParserLoader data) {
    actionsEnabledBox.setSelected(data.isEnableActions());
    htmlFilesAsParser.setSelected(data.isHtmlFilesAsParser());
    setConstructorNames(data.getConstructorNames());
  }

  private void setConstructorNames(List<String> names) {
    StringBuilder list = new StringBuilder();
    for (String s : names) {
      list.append(", ").append(s);
    }
    constructorNames.setText(list.toString().substring(2));
  }

  public void getData(ParserLoader data) {
    data.setEnableActions(actionsEnabledBox.isSelected());
    data.setHtmlFilesAsParser(htmlFilesAsParser.isSelected());
    List<String> names = getConstructorNames();
    data.setConstructorNames(names);
  }

  private List<String> getConstructorNames() {
    List<String> names = new ArrayList<String>();
    String[] strings = constructorNames.getText().split(",");
    for (String s : strings) {
      names.add(s.trim());
    }
    return names;
  }

  public boolean isModified(ParserLoader data) {
    return (data.isEnableActions() != actionsEnabledBox.isSelected()) || !ListUtils.isEqualList(getConstructorNames(), data.getConstructorNames()) || data.isHtmlFilesAsParser() != htmlFilesAsParser.isSelected();
  }
}
