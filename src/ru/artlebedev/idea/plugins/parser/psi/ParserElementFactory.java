package ru.artlebedev.idea.plugins.parser.psi;

import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.psi.impl.*;

import java.util.HashMap;
import java.util.Map;

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

public class ParserElementFactory {
  private static Map<IElementType, Class<? extends ParserElementImpl>> parsers;

  static {
    parsers = new HashMap<IElementType, Class<? extends ParserElementImpl>>();
    parsers.put(ParserElementTypes.CLASS, ParserClassImpl.class);
    parsers.put(ParserElementTypes.STATIC_CLASS, ParserStaticClassImpl.class);
    parsers.put(ParserElementTypes.DYNAMIC_CLASS, ParserDynamicClassImpl.class);
    parsers.put(ParserElementTypes.STRICT_CLASS, ParserStrictClassImpl.class);
    parsers.put(ParserElementTypes.DYNAMIC_STRICT_CLASS, ParserStrictDynamicClassImpl.class);
    parsers.put(ParserElementTypes.INCLUDE, ParserIncludeImpl.class);
    parsers.put(ParserElementTypes.INCLUDE_PATH, ParserIncludePathImpl.class);
    parsers.put(ParserElementTypes.METHOD, ParserMethodImpl.class);
    parsers.put(ParserElementTypes.STATIC_METHOD, ParserStaticMethodImpl.class);
    parsers.put(ParserElementTypes.PARAMETER_LIST, ParserParameterListImpl.class);
    parsers.put(ParserElementTypes.PARAMETER, ParserParameterImpl.class);
    parsers.put(ParserElementTypes.PASSED_PARAMETER, ParserPassedParameterImpl.class);
    parsers.put(ParserElementTypes.METHOD_REFERENCE, ParserMethodReferenceImpl.class);
    parsers.put(ParserElementTypes.CLASS_REFERENCE, ParserClassReferenceImpl.class);
    parsers.put(ParserElementTypes.OBJECT_REFERENCE, ParserObjectReferenceImpl.class);
    parsers.put(ParserElementTypes.CALLING_REFERENCE, ParserCallingReferenceImpl.class);
    parsers.put(ParserElementTypes.OBJECT, ParserObjectImpl.class);
    parsers.put(ParserElementTypes.UNIVERSAL_REFERENCE, ParserUniversalReferenceImpl.class);
    parsers.put(ParserElementTypes.OBJECT_AND_METHOD_REFERENCE, ParserObjectAndMethodReferenceImpl.class);
    parsers.put(ParserElementTypes.CLASS_PARENT, ParserClassParentImpl.class);
    parsers.put(ParserElementTypes.HASH_KEY, ParserHashKeyImpl.class);
    parsers.put(ParserElementTypes.PARSERDOC, ParserDocImpl.class);
    parsers.put(ParserElementTypes.PARSERDOC_PARAM_INFO, ParserDocParameterInfoImpl.class);
    parsers.put(ParserElementTypes.PARSERDOC_TYPE_INFO, ParserDocTypeInfoImpl.class);
    parsers.put(ParserElementTypes.PARSERDOC_CONSTRUCTOR_INFO, ParserDocConstructorInfoImpl.class);
  }


  public static Class<? extends ParserElementImpl> getElementClass(IElementType type) {
    return parsers.get(type);
  }
}

