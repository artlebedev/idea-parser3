package ru.artlebedev.jreformator.typograf;

import ru.artlebedev.jreformator.html.HtmlUtil;
import ru.artlebedev.jreformator.html.entity.HtmlEntities;
import ru.artlebedev.jreformator.html.tag.HtmlTags;
import ru.artlebedev.jreformator.language.Language;

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
    String language = !"".equals(params.getLanguage()) ? params.getLanguage() : Language.getInstance().get(text);

    if(language.equals("cyr") || language.equals("ru") || language.equals("be") || language.equals("uk") || language.equals("bg")) {
      /* Russian Belarusian Ukrainian Bulgarian */
      params.setQuotationMarksA("laquo raquo");
      params.setQuotationMarksB("bdquo ldquo");
    } else if(language.equals("fr") || language.equals("pt") || language.equals("es") || language.equals("el") || language.equals("ca")) {
      /* French Portuguese Spanish Greek Catalan */
      params.setQuotationMarksA("laquo raquo");
      params.setQuotationMarksB("ldquo rdquo");
    } else if(language.equals("sq") || language.equals("it") || language.equals("tr")) {
      /* Albanian Italian Turkish */
      params.setQuotationMarksA("laquo raquo");
      params.setQuotationMarksB("lsaquo rsaquo");
    } else if(language.equals("cs") || language.equals("lt") || language.equals("sk") || language.equals("sl") || language.equals("ro") || language.equals("pl") || language.equals("nl")) {
      /* Czech Lithuanian Slovak Slovene Romanian Polish Netherland */
      params.setQuotationMarksA("bdquo rdquo");
      params.setQuotationMarksB("bsquo rsquo");
    } else if(language.equals("de") || language.equals("da")) {
      /* German Danish */
      params.setQuotationMarksA("raquo laquo");
      params.setQuotationMarksB("rsaquo lsaquo");
    } else if(language.equals("fi") || language.equals("sv")) {
      /* Finnish Swedish */
      params.setQuotationMarksA("rdquo rdquo");
      params.setQuotationMarksB("rsquo rsquo");
    } else {
      /* English Esperanto Irish Indonesian Korean Thai Chinese */
      params.setQuotationMarksA("ldquo rdquo");
      params.setQuotationMarksB("lsquo rsquo");
    }

    // to do:
    // en
    //  double space after ! and ?
    //  . and , inside quotes

    // fr
    //  La, Le, L if next in upper case

    // fr
    //  space before : ; ! ?
    //  elki with space
  }

  private void preProcess() {
    // replace quots
    if(!"".equals(params.getQuotationMarksA()) && !params.isPreserveOriginalQuotation()) {
      Pattern.compile("\\xC2\\x92‘’′", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("'");
    }

    // replace nbsp
    if(!params.isPreserveOriginalNbsp()) {
      Pattern.compile(HtmlEntities.nbsp.getVariant1(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(String.valueOf((char) 0x20));
    }

    // replace dash
    if(!params.isPreserveOriginalMinus()) {
      Pattern.compile("([\\xC2\\x96\\xC2\\x97–—]|(^|[^-])--(?!\\s*-))", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$2-");
    }

    // replace spaces
    if(params.isCollapse09()) {
      Pattern.compile("\\x09+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(String.valueOf((char) 0x20));
    }
    if(params.isCollapse20()) {
      Pattern.compile("\\x20{2}", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(String.valueOf((char) 0x20));
      Pattern.compile("((^|\\n)" + TypographPatterns.tag + ")\\x20+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1");
    }

    // pseudo code replaces
    if(params.isReplacePlusmn()) {
      Pattern.compile("\\+\\-", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.plusmn.getVariant1());
    }
    if(params.isReplaceCopy()) {
      Pattern.compile("\\([cс]\\)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.copy.getVariant1());
    }
    if(params.isReplaceReg()) {
      Pattern.compile("\\(r\\)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.reg.getVariant1());
    }
    if(params.isReplaceTrade()) {
      Pattern.compile("(\\S)\\(tm\\)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.trade.getVariant1());
    }
    if(params.isReplaceHellip()) {
      Pattern.compile("([^\\.]|^)\\.{3,3}(?=[^\\.]|$)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.hellip.getVariant1());
    } else {
      Pattern.compile(HtmlEntities.hellip.getVariant1(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("...");
    }
    if(params.isReplaceTimes()) {
      Pattern.compile("(\\d" + TypographPatterns.tag + ")\\x20?(" + TypographPatterns.tag + ")[xх](" + TypographPatterns.tag + ")\\x20?(" + TypographPatterns.tag + "\\d)",
              Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1$2" + HtmlEntities.times.getVariant1() + "$3$4");
    }

    // place apostrophe
    Pattern.compile("(" + TypographPatterns.letters + "{2})(\')(?=" + TypographPatterns.letters + "{0,2}" + TypographPatterns.wordEnd0S + ")",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.rsquo.getVariant1());

    // place mdash
    // -_
    Pattern.compile("(\\n" + TypographPatterns.tag + "\\s*" + TypographPatterns.tag + "\\s*|" + TypographPatterns.sentenceEnd +
            "\\x20" + TypographPatterns.tag + ")[\\-\\—](" + TypographPatterns.tag + ")\\x20",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.mdash.getVariant1() + "$2" + HtmlEntities.nbsp.getVariant1());

    // _-
    Pattern.compile("(" + TypographPatterns.lettersDigits + TypographPatterns.wordEnd0 + ")\\x20(" + TypographPatterns.tag + ")[\\-\\—](?=" + TypographPatterns.tag + "\\x20)",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.nbsp.getVariant1() + "$2" + HtmlEntities.mdash.getVariant1());
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
