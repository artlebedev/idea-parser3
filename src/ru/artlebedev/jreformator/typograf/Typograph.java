package ru.artlebedev.jreformator.typograf;

import ru.artlebedev.jreformator.html.HtmlUtil;
import ru.artlebedev.jreformator.html.entity.HtmlEntities;
import ru.artlebedev.jreformator.html.tag.HtmlTags;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class Typograph {
  private static volatile Typograph instance;

  private TypographParams params = new TypographParams();
  private String text;

  public String process(String text) {
    this.text = String.valueOf((char) 0x02) + text + String.valueOf((char) 0x02);

    cutHtml();

    initParamsByLanguage();

    preProcess();

    openNobr();

    placeNbsp();

    postProcess();

    returnHtml();

    closeNobr();

//    if(!params.getQuotationMarksA().equals("")) {
//      placeQuotation(params.getQuotationMarksA(), params.getQuotationMarksB());
//    }

    return this.text;
  }

  public String process(String text, TypographParams params) {
    this.params = params;

    return process(text);
  }

  private void cutHtml() {
    List<String> tags = new ArrayList<String>();

    Pattern pattern;
    Matcher matcher;

    // cut comments
    pattern = Pattern.compile("(<(?:\\!--(?:.|\\n)*?--|%(?:.|\\n)*?%)>)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    matcher = pattern.matcher(text);
    while(matcher.find()) {
      String str = matcher.group(0);
      String s1 = matcher.group(1);

      tags.add(s1);

      String s = TypographPatterns.tagBegin + (tags.size() - 1) + TypographPatterns.tagEnd;

      text = text.replaceAll(matcher.group(), s);
    }

    // cut code tags with content
    pattern = Pattern.compile("(<(style|script|code|var|samp)(\\s[^>]*)?>(?:.|\\n)*?<\\/\\2>)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    matcher = pattern.matcher(text);
    while(matcher.find()) {
      String str = matcher.group(0);
      String s1 = matcher.group(1);

      tags.add(s1);

      String s = TypographPatterns.tagBegin + (tags.size() - 1) + TypographPatterns.tagEnd;

      text = text.replaceAll(matcher.group(), s);
    }

    if(params.isNobr() && !params.isPreserveOriginalNobr()) {
      pattern = Pattern.compile("<nobr(?:\\s+[^>]*)?>", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
      matcher = pattern.matcher(text);
      while(matcher.find()) {
        text = text.replaceAll(matcher.group(), "");
      }
    }

    // cut opened block tags without \n before
    pattern = Pattern.compile("<\\/nobr>", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    matcher = pattern.matcher(text);
    while(matcher.find()) {
      text = text.replaceAll(matcher.group(), "");
    }

    pattern = Pattern.compile("([^\\n])(<([a-z]+)([^>]*)?>)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    matcher = pattern.matcher(text);
    while(matcher.find()) {
      String str = matcher.group(0);
      String s1 = matcher.group(1);
      String s2 = matcher.group(2);
      String s3 = matcher.group(3);

      String s = "";
      for(int i = 0; i < HtmlTags.allTags.length; i++) {
        if(HtmlTags.allTags[i].getName().equals(s3) && HtmlTags.allTags[i].getBlock() || "br".equals(s3)) {
          s = String.valueOf((char) 0x0A);
        }
      }

      tags.add(s + s2);
      s = s1 + s + TypographPatterns.tagBegin + (tags.size() - 1) + TypographPatterns.tagEnd;

      text = text.replaceAll(matcher.group(), s);
    }

    // cut others
    pattern = Pattern.compile("((<[!?\\/a-z][^>]*>)+)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    matcher = pattern.matcher(text);
    while(matcher.find()) {
      String str = matcher.group(0);
      String s1 = matcher.group(1);

      String s = TypographPatterns.tagBegin + (tags.size() - 1) + TypographPatterns.tagEnd;

      text = text.replaceAll(matcher.group(), s);
    }

    text = HtmlUtil.replaceEntities(text, HtmlEntities.commonEntites);
    text = HtmlUtil.replaceEntities(text, HtmlEntities.specialEntities);
  }

  private void initParamsByLanguage() {

  }

  private void preProcess() {

  }

  private void openNobr() {

  }

  private void placeNbsp() {

  }

  private void placeQuotation(String quotationMarksA, String quotationMarksB) {

  }

  private void postProcess() {

  }

  private void returnHtml() {

  }

  private void closeNobr() {

  }

  private Typograph() {

  }

  public static Typograph getInstance() {
    if(instance == null) {
      synchronized (Typograph.class) {
        if(instance == null)
          instance = new Typograph();
      }
    }
    return instance;
  }
}
