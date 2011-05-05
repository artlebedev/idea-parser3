package ru.artlebedev.idea.plugins.parser.editor.templates;

import com.intellij.codeInsight.template.impl.TemplateSettings;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;

import java.io.IOException;
import java.io.InputStream;

/**
 * idea-parser3: the most advanced parser3 ide.
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

public class ParserTemplatesLoader implements ApplicationComponent {

  private static final Logger LOG = Logger.getInstance(ParserTemplatesLoader.class.getName());
  private static final String TEMPLATES_FILE = "templates.xml";

  private FileTemplateManager templateManager;

  public ParserTemplatesLoader(final FileTemplateManager fileTemplateManager,
                               final TemplateSettings templateSettings) {
    templateManager = fileTemplateManager;
  }

  public void initComponent() {
    loadFileTemplates();
  }

  public void disposeComponent() {
  }

  @NotNull
  public String getComponentName() {
    return "ParserSupport.ParserTemplatesLoader";
  }

  private static final String TYPE_PATTERN = "pattern";
  private static final String TYPE_TEMPLATE = "template";

  private void registerPattern(final String name, final String text) {
    FileTemplate pattern = templateManager.getPattern(name);
    if (pattern != null) {
      return;
    }

    pattern = templateManager.addTemplate(name, ParserFileType.DEFAULT_EXTENSION);
    pattern.setText(text);
  }

  private void registerTemplate(final String name, final String text) {
    //FileTemplate pattern = templateManager.getPattern(name);
    FileTemplate pattern = templateManager.getTemplate(name);
    if (pattern != null) {
      return;
    }

    pattern = templateManager.addTemplate(name, ParserFileType.DEFAULT_EXTENSION);
    pattern.setText(text);
  }

  private void loadFileTemplates() {
    final InputStream inputStream = ParserTemplatesLoader.class.getResourceAsStream(TEMPLATES_FILE);
    if (inputStream != null) {
      final SAXBuilder parser = new SAXBuilder();
      try {
        final Document doc = parser.build(inputStream);
        final Element root = doc.getRootElement();
        for (Object o : root.getChildren()) {
          if (o instanceof Element) {
            Element e = (Element) o;
            final String name = e.getAttributeValue("name");
            final String type = e.getAttributeValue("type");
            final String text = e.getText();
            if (type.equals(TYPE_PATTERN)) {
              registerPattern(name, text);
            }
            if (type.equals(TYPE_TEMPLATE)) {
              registerTemplate(name, text);
            }
          }
        }
        inputStream.close();
      } catch (JDOMException e) {
        LOG.warn(e);
      } catch (IOException e) {
        LOG.warn(e);
      }
    } else {
      LOG.warn("File " + TEMPLATES_FILE + " wasn't found");
    }
  }
}