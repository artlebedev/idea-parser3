package ru.artlebedev.jreformator.html;

import ru.artlebedev.jreformator.html.entity.HtmlEntities;
import ru.artlebedev.jreformator.html.entity.HtmlEntity;

/**
 * jreformator
 * <p/>
 * Based on code originally written by Vladimir Tokmakov <vlalek@design.ru>
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

public class HtmlUtil {
  public static String replaceEntities(String input, HtmlEntity[] htmlEntities) {
    return replaceEntitiesWithType(input, htmlEntities, 0);
  }

  public static String replaceEntitiesWithType(String input, HtmlEntity[] htmlEntities, int type) {
    if(type == 3) {
      for(int i = 0; i < htmlEntities.length; i++) {
        input = input.replaceAll(htmlEntities[i].getVariant1(), htmlEntities[i].getVariant4());
        input = input.replaceAll(htmlEntities[i].getVariant2(), htmlEntities[i].getVariant4());
        input = input.replaceAll(htmlEntities[i].getVariant3(), htmlEntities[i].getVariant4());
      }
    } else if(type == 2) {
      for(int i = 0; i < htmlEntities.length; i++) {
        input = input.replaceAll(htmlEntities[i].getVariant1(), htmlEntities[i].getVariant3());
        input = input.replaceAll(htmlEntities[i].getVariant2(), htmlEntities[i].getVariant3());
        input = input.replaceAll(htmlEntities[i].getVariant4(), htmlEntities[i].getVariant3());
      }
    } else if(type == 1) {
      for(int i = 0; i < htmlEntities.length; i++) {
        input = input.replaceAll(htmlEntities[i].getVariant1(), htmlEntities[i].getVariant2());
        input = input.replaceAll(htmlEntities[i].getVariant3(), htmlEntities[i].getVariant2());
        input = input.replaceAll(htmlEntities[i].getVariant4(), htmlEntities[i].getVariant2());
      }
    } else {
      for(int i = 0; i < htmlEntities.length; i++) {
        input = input.replaceAll(htmlEntities[i].getVariant2(), htmlEntities[i].getVariant1());
        input = input.replaceAll(htmlEntities[i].getVariant3(), htmlEntities[i].getVariant1());
        input = input.replaceAll(htmlEntities[i].getVariant4(), htmlEntities[i].getVariant1());
      }
    }
    return input;
  }

  public static HtmlEntity getEntityByName(String name) {
    for(HtmlEntity entity : HtmlEntities.allEntities) {
      if(entity.getName().equals(name)) {
        return entity;
      }
    }

    return null;
  }
}
