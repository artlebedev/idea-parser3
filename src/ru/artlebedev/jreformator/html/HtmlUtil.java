package ru.artlebedev.jreformator.html;

import ru.artlebedev.jreformator.html.entity.HtmlEntities;
import ru.artlebedev.jreformator.html.entity.HtmlEntity;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 07.04.11
 * Time: 4:14
 */
public class HtmlUtil {
  public static String replaceEntities(String input, HtmlEntity[] htmlEntities) {
    for(int i = 0; i < htmlEntities.length; i++) {
      input = input.replaceAll(htmlEntities[i].getVariant2(), htmlEntities[i].getVariant1());
      input = input.replaceAll(htmlEntities[i].getVariant3(), htmlEntities[i].getVariant1());
      input = input.replaceAll(htmlEntities[i].getVariant4(), htmlEntities[i].getVariant1());
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
