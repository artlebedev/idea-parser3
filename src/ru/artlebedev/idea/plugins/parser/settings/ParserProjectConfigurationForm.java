package ru.artlebedev.idea.plugins.parser.settings;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
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

public class ParserProjectConfigurationForm implements ActionListener, ListSelectionListener {
  private JPanel rootComponent;
  private JList pathList;
  private JButton addButton;
  private JButton removeButton;
  private JPanel listPlace;
  DefaultListModel model = new DefaultListModel();

  public JPanel getRootComponent() {
    return rootComponent;
  }

  public void setData(ParserProjectConfiguration data) {
    List<String> paths = data.getClassPaths();
    for (String path : paths) {
      model.addElement(path);
    }
  }

  public void getData(ParserProjectConfiguration data) {
    data.setClassPaths(getListValues());
  }

  private List<String> getListValues() {
    List<String> result = new ArrayList<String>();
    Object[] paths = model.toArray();
    for (Object path : paths) {
      result.add((String) path);
    }
    return result;
  }

  public boolean isModified(ParserProjectConfiguration data) {
    return !data.getClassPaths().equals(getListValues());
  }

  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getActionCommand().equals("remove")) {
      int index = pathList.getSelectedIndex();
      model.remove(index);

      int size = model.getSize();

      if (size == 0) {
        removeButton.setEnabled(false);

      } else {
        if (index == model.getSize()) {
          index--;
        }

        pathList.setSelectedIndex(index);
        pathList.ensureIndexIsVisible(index);
      }
    }
  }

  public void setUp(final Project project) {
    pathList = new JList(model);
    pathList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    pathList.addListSelectionListener(this);

    JScrollPane pane = new JScrollPane(pathList);
    GridConstraints myConstraints = new GridConstraints();
    myConstraints.setFill(GridConstraints.FILL_BOTH);
    listPlace.add(pane, myConstraints);
//		pane.set

    removeButton.setActionCommand("remove");
    removeButton.addActionListener(this);

    MouseListener addClickListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent mouseEvent) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        descriptor.setTitle("Parser class path");
        descriptor.setDescription("Pick up a directory to add it to parser class path");
        VirtualFile[] files = FileChooser.chooseFiles(project, descriptor);
        if (files.length != 0) {
          model.addElement(files[0].getPath());
        }
      }
    };
    addButton.addMouseListener(addClickListener);
  }

  public void valueChanged(ListSelectionEvent listSelectionEvent) {
    if (pathList.getSelectedIndex() == -1) {
      removeButton.setEnabled(false);
    } else {
      removeButton.setEnabled(true);
    }
  }
}
