package ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements;

import com.intellij.codeInsight.lookup.LookupElementPresentation;
import ru.artlebedev.idea.plugins.parser.ParserIcons;

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
 * Completion type for superglobals like MAIN:SQL.connect-string
 * <p/>
 * TODO: there might be more than one sql string, should fix this
 *
 * @author <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * @version 1.0
 */
public class ParserStaticVariableLookupElement extends ParserLookupElement {
  public ParserStaticVariableLookupElement(String keyword) {
    super(keyword);
  }

  @Override
  public void renderElement(LookupElementPresentation presentation) {
    presentation.setIcon(ParserIcons.PARSER_PROPERTY_ICON);
    super.renderElement(presentation);
  }
}
