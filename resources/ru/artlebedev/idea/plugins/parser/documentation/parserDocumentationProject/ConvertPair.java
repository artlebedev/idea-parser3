package ru.artlebedev.idea.plugins.parser.documentation.parserDocumentationProject;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 ArtLebedev Studio
 * Copyright 2010 Joachim Ansorg <mail@ansorg-it.com>
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

public class ConvertPair {
  private String fromFile;
  private String toFile;

  public ConvertPair(String fromFile, String toFile) {
    this.fromFile = fromFile;
    this.toFile = toFile;
  }

  public String getFromFile() {
    return fromFile;
  }

  public String getToFile() {
    return toFile;
  }
}
