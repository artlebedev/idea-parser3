package ru.artlebedev.idea.plugins.parser.editor.codecompletion.collections;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
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

public interface ParserKeywordLookupElements {
  public final static ParserKeywordLookupElement TRUE = new ParserKeywordLookupElement("true");
  public final static ParserKeywordLookupElement FALSE = new ParserKeywordLookupElement("false");
  public final static ParserKeywordLookupElement DEF = new ParserKeywordLookupElement("def");
  public final static ParserKeywordLookupElement IN = new ParserKeywordLookupElement("in");
  public final static ParserKeywordLookupElement SLASH_F = new ParserKeywordLookupElement("-f");
  public final static ParserKeywordLookupElement SLASH_D = new ParserKeywordLookupElement("-d");
  public final static ParserKeywordLookupElement IS = new ParserKeywordLookupElement("is");
  public final static ParserKeywordLookupElement EQ = new ParserKeywordLookupElement("eq");
  public final static ParserKeywordLookupElement NE = new ParserKeywordLookupElement("ne");
  public final static ParserKeywordLookupElement LT = new ParserKeywordLookupElement("lt");
  public final static ParserKeywordLookupElement GT = new ParserKeywordLookupElement("gt");
  public final static ParserKeywordLookupElement LE = new ParserKeywordLookupElement("le");
  public final static ParserKeywordLookupElement GE = new ParserKeywordLookupElement("ge");

  public final static ParserKeywordLookupElement CALLER = new ParserKeywordLookupElement("caller");
  public final static ParserKeywordLookupElement RESULT = new ParserKeywordLookupElement("result");

  public final static ParserKeywordLookupElement UNHANDLED_EXCEPTION = new ParserMethodLookupElement("unhandled_exception");
}
