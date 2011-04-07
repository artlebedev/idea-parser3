package ru.artlebedev.jreformator.typograf;

import ru.artlebedev.jreformator.html.HtmlUtil;
import ru.artlebedev.jreformator.html.entity.HtmlEntities;
import ru.artlebedev.jreformator.html.entity.HtmlEntity;
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

  List<String> tags = new ArrayList<String>();

  public String process(String text) {
    tags.clear();

    this.text = String.valueOf((char) 0x02) + text + String.valueOf((char) 0x02);

    cutHtml();

    initParamsByLanguage();

    preProcess();

    openNobr();

    placeNbsp();

    postProcess();

    returnHtml();

    closeNobr();

    if(!"".equals(params.getQuotationMarksA())) {
      placeQuotation(params.getQuotationMarksA(), params.getQuotationMarksB());
    }

    return this.text;
  }

  public String process(String text, TypographParams params) {
    this.params = params;

    return process(text);
  }

  private void cutHtml() {
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
      text = Pattern.compile("\\xC2\\x92‘’′", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("'");
    }

    // replace nbsp
    if(!params.isPreserveOriginalNbsp()) {
      text = Pattern.compile(HtmlEntities.nbsp.getVariant1(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(String.valueOf((char) 0x20));
    }

    // replace dash
    if(!params.isPreserveOriginalMinus()) {
      text = Pattern.compile("([\\xC2\\x96\\xC2\\x97–—]|(^|[^-])--(?!\\s*-))", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$2-");
    }

    // replace spaces
    if(params.isCollapse09()) {
      text = Pattern.compile("\\x09+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(String.valueOf((char) 0x20));
    }
    if(params.isCollapse20()) {
      text = Pattern.compile("\\x20{2}", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(String.valueOf((char) 0x20));
      text = Pattern.compile("((^|\\n)" + TypographPatterns.tag + ")\\x20+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1");
    }

    // pseudo code replaces
    if(params.isReplacePlusmn()) {
      text = Pattern.compile("\\+\\-", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.plusmn.getVariant1());
    }
    if(params.isReplaceCopy()) {
      text = Pattern.compile("\\([cс]\\)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.copy.getVariant1());
    }
    if(params.isReplaceReg()) {
      text = Pattern.compile("\\(r\\)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.reg.getVariant1());
    }
    if(params.isReplaceTrade()) {
      text = Pattern.compile("(\\S)\\(tm\\)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll(HtmlEntities.trade.getVariant1());
    }
    if(params.isReplaceHellip()) {
      text = Pattern.compile("([^\\.]|^)\\.{3,3}(?=[^\\.]|$)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.hellip.getVariant1());
    } else {
      text = Pattern.compile(HtmlEntities.hellip.getVariant1(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("...");
    }
    if(params.isReplaceTimes()) {
      text = Pattern.compile("(\\d" + TypographPatterns.tag + ")\\x20?(" + TypographPatterns.tag + ")[xх](" + TypographPatterns.tag + ")\\x20?(" + TypographPatterns.tag + "\\d)",
              Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1$2" + HtmlEntities.times.getVariant1() + "$3$4");
    }

    // place apostrophe
    text = Pattern.compile("(" + TypographPatterns.letters + "{2})(\')(?=" + TypographPatterns.letters + "{0,2}" + TypographPatterns.wordEnd0S + ")",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.rsquo.getVariant1());

    // place mdash
    // -_
    text = Pattern.compile("(\\n" + TypographPatterns.tag + "\\s*" + TypographPatterns.tag + "\\s*|" + TypographPatterns.sentenceEnd +
            "\\x20" + TypographPatterns.tag + ")[\\-\\—](" + TypographPatterns.tag + ")\\x20",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.mdash.getVariant1() + "$2" + HtmlEntities.nbsp.getVariant1());

    // _-
    text = Pattern.compile("(" + TypographPatterns.lettersDigits + TypographPatterns.wordEnd0 + ")\\x20(" + TypographPatterns.tag + ")[\\-\\—](?=" + TypographPatterns.tag + "\\x20)",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.nbsp.getVariant1() + "$2" + HtmlEntities.mdash.getVariant1());
  }

  private void openNobr() {
    Pattern pattern;
    Matcher matcher;

    if(params.isNobr() && !params.isNoTags()) {
      tags.add("<nobr>");

      //  ranges
      pattern = Pattern.compile("(\\s|^)(" + TypographPatterns.wordBegin0 + "(?:" +
                  TypographPatterns.number + "[\\-\\—]" + TypographPatterns.number + "|" +
                  TypographPatterns.romanNumber + "\\—" + TypographPatterns.romanNumber + "))(?=" +
                  TypographPatterns.wordEnd0S + ")", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

      matcher = pattern.matcher(text);

      while(matcher.find()) {
        String str = matcher.group(0);
        String s1 = matcher.group(1);
        String s2 = matcher.group(2);

        text = text.replaceAll(matcher.group(), s1 + TypographPatterns.tagBegin + (tags.size() - 1) + TypographPatterns.tagEnd + s2);
      }

      //  numerical
      pattern = Pattern.compile("(\\s|^)(" + TypographPatterns.wordBegin0 + "(?:" + TypographPatterns.number + "|" +
                TypographPatterns.romanNumber + ")-" + TypographPatterns.letters + "+)(?=" + TypographPatterns.wordEnd0S + ")",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

      matcher = pattern.matcher(text);

      while(matcher.find()) {
        String str = matcher.group(0);
        String s1 = matcher.group(1);
        String s2 = matcher.group(2);

        text = text.replaceAll(matcher.group(), s1 + TypographPatterns.tagBegin + (tags.size() - 1) + TypographPatterns.tagEnd + s2);
      }

      if(params.isNobrPhone() && !params.isNoTags()) {
        tags.add("<nobr class=\"phone\">");

        Pattern r = Pattern.compile("(\\d\\-\\d+\\-\\d|\\+(?:\\d\\x20?){11})");

        pattern = Pattern.compile("(\\s|^)(" + TypographPatterns.wordBegin0 + "\\+?(?:\\d(?:[\\-\\x28\\x29\\x20]|" +
                  TypographPatterns.tag + ")*){5,11})(?=" + TypographPatterns.wordEnd0S + ")",
                  Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        matcher = pattern.matcher(text);

        while(matcher.find()) {
          String str = matcher.group(0);
          String s1 = matcher.group(1);
          String s2 = matcher.group(2);

          String s = s1;

          if(r.matcher(s2).find()) {
            s += TypographPatterns.tagBegin;
            s += (tags.size() - 1);
            s += TypographPatterns.tagEnd;

            s += s2.replaceAll(String.valueOf((char) 0x20), HtmlEntities.nbsp.getVariant1());
          } else {
            s = s2;
          }

          text = text.replaceAll(matcher.group(), s);
        }
      }
    }
  }

  private void placeNbsp() {
    // 5_000_000
    // to do: limit on length
    text = Pattern.compile("(\\d" + TypographPatterns.tag + ")\\x20(?=" + TypographPatterns.tag + "\\d{3}" + TypographPatterns.wordEnd0S + ")",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.nbsp.getVariant1());

    // exceptions
    text = Pattern.compile("(\\S)\\x20(?=" + TypographPatterns.wordBegin0 + TypographPatterns.exceptionsLeft + TypographPatterns.wordEnd0S + ")",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1&_;");

    // No_1
    text = Pattern.compile("([№§]" + TypographPatterns.tag + ")\\x20?(?=" + TypographPatterns.tag + "(?:" + TypographPatterns.number + "|" + TypographPatterns.romanNumber + "))",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + HtmlEntities.nbsp.getVariant1());

    // bla_/ bla
    text = Pattern.compile("\\x20([\\/\\|])\\x20",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" + String.valueOf((char) 0x20));

    // 600_rubley, 500_GHz or 200_km or 60_km/h
    text = Pattern.compile("((?:" + TypographPatterns.number + "|" + TypographPatterns.romanNumber + ")" +
                           TypographPatterns.nulls + ")\\x20(?=" + TypographPatterns.tag + "(" +
                           TypographPatterns.letters + "+" + TypographPatterns.wordEnd1S + "|" +
                           TypographPatterns.lettersUpper + "{2}|" + TypographPatterns.letters + "+\\/|(?!" +
                           TypographPatterns.exceptionsRight + TypographPatterns.wordEnd0S + ")" +
                           TypographPatterns.letters + "{1," + params.getSymbolsNumberForNbsp() + "}" +
                           TypographPatterns.wordEnd0S + "))",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1&_;");

    // Usome 1, Usome 1 Usome
    text = Pattern.compile("(" + TypographPatterns.wordBegin0S + TypographPatterns.lettersUpper +
                           TypographPatterns.lettersLower + "*" + TypographPatterns.nulls + ")\\x20(?=" +
                           TypographPatterns.nulls + "\\d{1," + params.getSymbolsNumberForNbsp() + "}(?:" +
                           TypographPatterns.wordEnd1 + "|" + TypographPatterns.wordBegin0S +
                           TypographPatterns.lettersUpper + "))",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1&_;");

    // lower_U lower or lower_l.
    text = Pattern.compile("(" + TypographPatterns.wordBegin0S + TypographPatterns.lettersLower + "+" +
                           TypographPatterns.nulls + ")\\x20(?=" + TypographPatterns.nulls + "(?:" +
                           TypographPatterns.lettersUpper + TypographPatterns.letters + "{0," +
                           (params.getSymbolsNumberForNbsp() - 1) + "}" + TypographPatterns.wordEndS +
                           "(?=" + TypographPatterns.tag + TypographPatterns.lettersLower + ")|" +
                           TypographPatterns.lettersLower + "{1," + params.getSymbolsNumberForNbsp() +
                           "}(?:" + TypographPatterns.wordEnd1S + "|" + TypographPatterns.wordBegin2S + ")))",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1&_;");

    // some_s ( or some_s, lower
    text = Pattern.compile("(" + TypographPatterns.wordBegin0S + TypographPatterns.lettersDigits + "+" +
                           TypographPatterns.nulls + ")\\x20(?=" + TypographPatterns.nulls + "(?:" +
                           TypographPatterns.lettersDigits + "{1," + params.getSymbolsNumberForNbsp() + "}(?:" +
                           TypographPatterns.wordEnd0S + "$|" + TypographPatterns.wordBegin2S + "|" +
                           TypographPatterns.wordEnd1S + TypographPatterns.wordBegin0 +
                           TypographPatterns.lettersLower + ")))",
           Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1&_;");

    Pattern pattern;
    Matcher matcher;

    // Lastname_F._M.
    pattern = Pattern.compile("(" + TypographPatterns.lettersUpper + TypographPatterns.lettersLower + "+" +
                              TypographPatterns.nulls + ")\\x20(" + TypographPatterns.tag +
                              TypographPatterns.lettersUpper + TypographPatterns.nulls + "\\." +
                              TypographPatterns.nulls + ")(?:\\x20(" + TypographPatterns.tag +
                              TypographPatterns.lettersUpper + TypographPatterns.nulls + "\\." +
                              TypographPatterns.nulls + "))?(?=" + TypographPatterns.wordEnd1S + "|" +
                              TypographPatterns.tag + "(?:\\s*\\n|\\s" + TypographPatterns.wordBegin0 +
                              TypographPatterns.lettersLower + "))", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    matcher = pattern.matcher(text);

    while(matcher.find()) {
      String str = matcher.group(0);
      String s1 = matcher.group(1);
      String s2 = matcher.group(2);
      String s3 = matcher.group(3);

      text = matcher.replaceAll(s1 + "&_;" + s2 +(((s3 != null) && !"".equals(s3)) ? "&_;" + s3: ""));
    }

    // common nbsp
    text = Pattern.compile("(" + TypographPatterns.wordBegin0S + TypographPatterns.lettersDigits + "{0," +
                    (params.getSymbolsNumberForNbsp() - 1) + "}(?!\\d" + TypographPatterns.nulls + "\\x20" +
                    TypographPatterns.wordBegin0 + "\\d)" + TypographPatterns.lettersDigits +
                    TypographPatterns.nulls + ")\\x20(?=" + TypographPatterns.wordBegin0 +
                    TypographPatterns.lettersDigits + ")",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" +
                    HtmlEntities.nbsp.getVariant1());

    // F._M._Lastname
    pattern = Pattern.compile("((?:\\n\\s*|" + TypographPatterns.notLetterUpper + TypographPatterns.wordEnd1S + "|" +
                              TypographPatterns.wordBegin1 + "|" + TypographPatterns.wordBegin0S + TypographPatterns.lettersLower + "+" +
                              TypographPatterns.wordEnd0S + ")" + TypographPatterns.wordBegin0 + TypographPatterns.lettersUpper + TypographPatterns.nulls + "\\." +
                              TypographPatterns.nulls + ")\\x20(?:(" + TypographPatterns.tag + TypographPatterns.lettersUpper + TypographPatterns.nulls + "\\." +
                              TypographPatterns.nulls + ")\\x20)?(" + TypographPatterns.tag + TypographPatterns.lettersUpper + TypographPatterns.lettersLower + "+)",
                              Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    matcher = pattern.matcher(text);

    while(matcher.find()) {
      String str = matcher.group(0);
      String s1 = matcher.group(1);
      String s2 = matcher.group(2);
      String s3 = matcher.group(3);

      text = matcher.replaceAll(s1 + HtmlEntities.nbsp.getVariant1() + ("".equals(s2) ? s2 + HtmlEntities.nbsp.getVariant1() : "") + s3);
    }

    // others
    text = text.replaceAll("&_;", HtmlEntities.nbsp.getVariant1());

    // L._L. or L._LL.
    text = Pattern.compile("(" + TypographPatterns.lettersUpper + TypographPatterns.nulls + "\\." +
                    TypographPatterns.nulls + ")\\x20(?=" + TypographPatterns.tag + TypographPatterns.lettersUpper + "{1," +
                    params.getSymbolsNumberForNbsp() + "}\\.)",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" +
                    HtmlEntities.nbsp.getVariant1());

    // l._l, or l._l. or l. 1.
    text = Pattern.compile("(" + TypographPatterns.lettersLower + TypographPatterns.nulls + "\\." +
                    TypographPatterns.nulls + ")\\x20(?=" + TypographPatterns.tag + "(?:" +
                    TypographPatterns.lettersLower + "{1," + params.getSymbolsNumberForNbsp() + "}|" +
                    TypographPatterns.number + ")(?:" + TypographPatterns.wordEnd1 + "|" +
                    TypographPatterns.nulls + "(?:\\s|$)))",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text).replaceAll("$1" +
                    HtmlEntities.nbsp.getVariant1());
  }

  private void placeQuotation(String quotationMarksA, String quotationMarksB) {
    HtmlEntity LQ = HtmlUtil.getEntityByName(quotationMarksA.split(" ")[0]);
    HtmlEntity RQ = HtmlUtil.getEntityByName(quotationMarksA.split(" ")[1]);

    HtmlEntity Lq = HtmlUtil.getEntityByName(quotationMarksB.split(" ")[0]);
    HtmlEntity Rq = HtmlUtil.getEntityByName(quotationMarksB.split(" ")[0]);

    if((LQ == null) || (RQ == null) || (Lq == null) || (Rq == null)) {
      return;
    }

    if(!LQ.getName().equals("quot") && !RQ.getName().equals("quot")) {
      text = Pattern.compile("(" + TypographPatterns.wordBegin0S + "|[-\\.])(\")((?!" +
                      TypographPatterns.tag + "(?:\\s|" + HtmlEntities.nbsp.getVariant1() + "))[^\"]{1,1900}?(?!(?:\\s|" +
                      HtmlEntities.nbsp.getVariant1() + ')' + TypographPatterns.tag + ")[^\"]{0,100})\\2(?=" +
                      TypographPatterns.wordEnd1S + "|" + TypographPatterns.wordEnd0S + "|-)",
                      Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                      .replaceAll("$1" + Lq.getVariant1() + "$2" + Rq.getVariant1());
    }

    if(!LQ.equals(Lq) && !RQ.equals(Rq)) {
      text = Pattern.compile("(" + LQ.getVariant1() + "[^" + LQ.getVariant1() + RQ.getVariant1() +
                      "]{0,2000})" + LQ.getVariant1() + "([^" + LQ.getVariant1() + RQ.getVariant1() +
                      "]{0,2000})" + RQ.getVariant1(),
                      Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                      .replaceAll("$1" + Lq.getVariant1() + "$2" + Rq.getVariant1());
    }

    text = Pattern.compile("\"(\\S[^\"" + LQ.getVariant1() + Lq.getVariant1() +
                    RQ.getVariant1() + Rq.getVariant1() + "]*)([" + LQ.getVariant1() + Lq.getVariant1() + "])",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$2$1$2");
  }

  private void postProcess() {
    // place ndash in numbers range
    text = Pattern.compile("((?:^|\\s|" + HtmlEntities.nbsp.getVariant1() + ")" +
                    TypographPatterns.wordBegin0 + "\\$?" + TypographPatterns.number +
                    TypographPatterns.tag + ")\\—(?=" + TypographPatterns.tag +
                    TypographPatterns.number + "(?:" + TypographPatterns.wordEnd0S + "|-" +
                    TypographPatterns.letters + "{1,2}" + TypographPatterns.wordEnd0S + "))",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$1" + HtmlEntities.nbsp.getVariant1());

    text = Pattern.compile("((?:^|\\s|" + HtmlEntities.nbsp.getVariant1() + ")" +
                    TypographPatterns.wordBegin0 + TypographPatterns.romanNumber + TypographPatterns.tag + ")\\—(?=" +
                    TypographPatterns.tag + TypographPatterns.romanNumber + "(?:" +
                    TypographPatterns.wordEnd0S + "|-" + TypographPatterns.letters + "{1,2}" + TypographPatterns.wordEnd0S + "))",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$1" + HtmlEntities.nbsp.getVariant1());

    // place minus
    text = Pattern.compile("(\\x20|" + HtmlEntities.nbsp.getVariant1() + ")(?:" + TypographPatterns.tag + ")-(\\d)",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$1" + HtmlEntities.minus.getVariant1() + "$2");

    // to do:
    // place prime
    text = Pattern.compile("(\\d\\s*)(')(?=" + TypographPatterns.wordEnd0S + ")",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$1" + HtmlEntities.prime.getVariant1());
    text = Pattern.compile("(\\d\\s*)(\")(?=" + TypographPatterns.wordEnd0S + ")",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$1" + HtmlEntities.prime.getVariant1());

    text = Pattern.compile("\\\'",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll(HtmlEntities.rsquo.getVariant1());

    // &nbsp;&mdash;&nbsp;as -> &nbsp;&mdash; as&nbsp;
    text = Pattern.compile("(" + HtmlEntities.nbsp.getVariant1() + HtmlEntities.mdash.getVariant1() + ")",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(text)
                    .replaceAll("$1" + String.valueOf((char) 0x20));
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
