package ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements;

import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.openapi.util.IconLoader;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011 ArtLebedev Studio
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

/**
 * Completion type for ^taint[PARAM]
 *
 * @author <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * @version 1.0
 */
public class ParserTaintLookupElement extends ParserLookupElement {
  public ParserTaintLookupElement(String keyword) {
    super(keyword);
  }

  @Override
  public void renderElement(LookupElementPresentation presentation) {
    presentation.setIcon(IconLoader.getIcon("/nodes/deploy.png"));
    super.renderElement(presentation);
  }
}
