package ru.artlebedev.idea.plugins.parser.lang.psi.api;

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

public interface ParserCallingReference extends ParserElement {
  /**
   * @return class reference if it is present or null
   */
  public ParserClassReference getReferenceClass();

  /**
   * @return array of object references in order of their appearance or empty array
   */
  public ParserObjectReference[] getReferenceObjects();

  /**
   * @return a method reference or null
   */
  public ParserMethodReference getReferenceMethod();

  /**
   * @return true if a constructor was invoked and so we have a new instance of some class
   */
  public boolean isConstructorInvoked();
}
