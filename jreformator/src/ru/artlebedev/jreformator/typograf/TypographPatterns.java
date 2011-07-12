package ru.artlebedev.jreformator.typograf;

import ru.artlebedev.jreformator.html.entity.HtmlEntities;
import ru.artlebedev.jreformator.language.Language;

/**
 * jreformator
 * <p/>
 * Based on code originally written by <a href="mailto:vlalek@design.ru">Vladimir Tokmakov</a>
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

public interface TypographPatterns {
  // tags
  public final String tagBegin = String.valueOf((char) 0x02);
  public final String tagEnd = String.valueOf((char) 0x02);
  public final String tag = "(?:" + tagBegin + "\\d+" + tagEnd + ")*";

  // null symbols
  public final String nulls = tag + "(?:[ª®°¹²³´¶™+−±\\*]" + tag + "){0,3}";

  // sentence encloses
  public final String sentenceEndSymbols = "\\.\\:\\;\\!\\?…";
  public final String sentenceEnd = "(?:" + tag + "[" + sentenceEndSymbols + "]+" + tag + ")";

  // words encloses
  public final String wordEndS = nulls + "(?:[\\s" + HtmlEntities.nbsp.getVariant1() + "]+\\$)";

  public final String wordEnd = nulls + "(?:[%\\x29\\x5d\\x7d>\\,\"" + sentenceEndSymbols + "]{1,6}" + nulls + ")";
  public final String wordEnd1 = wordEnd + "{1,6}";
  public final String wordEnd1S = wordEnd1 + wordEndS;
  public final String wordEnd0 = wordEnd + "{0,6}";
  public final String wordEnd0S = wordEnd0 + wordEndS;

  public final String wordBeginS = "[\\s" + HtmlEntities.nbsp.getVariant1() + "]";
  public final String wordBegin1 = "(?:" + nulls + "[\\x28\\x5b\\x7b<\"\\°\\+]{1,6}" + nulls + ")";
  public final String wordBegin1S = wordBeginS + wordBegin1 + "{1,6}";
  public final String wordBegin2 = "(?:" + nulls + "[\\x28\\x5b\\x7b<]{1,6}" + nulls + ")";
  public final String wordBegin2S = wordBeginS + wordBegin2 + "{1,6}";
  public final String wordBegin0 = nulls + wordBegin1 + "?" + nulls;
  public final String wordBegin0S = wordBeginS + wordBegin0;

  // letters
  public final String lettersLower = "[" + Language.getInstance().getLowerLetters() + "&%©\\$€£¥¤" + String.valueOf((char) 769) + "]";
  public final String lettersUpper = "[" + Language.getInstance().getUpperLetters() + "]";
  public final String notLetterUpper = "[^" + Language.getInstance().getUpperLetters() + "]";
  public final String letters = "[" + Language.getInstance().getLowerLetters() + "&%©\\$€£¥¤" + String.valueOf((char) 769) + Language.getInstance().getUpperLetters() + "]";
  public final String lettersDigits = "[\\d" + Language.getInstance().getLowerLetters() + "&%©\\$€£¥¤" + String.valueOf((char) 769) + Language.getInstance().getUpperLetters() + "]";

  // numbers
  public final String number = "(?:[#\\.\\,\\+\\-±−]?(?:\\d+(?:[\\.\\,\\:]\\d+)*|[¼½¾]))";
  public final String romanNumber = "(?:[IXCMVLD]+(?![ABEFGHJKNOPQRSTUWYZ]))";
  //public final String romanNumber = "(?:(?<![ABEFGHJKNOPQRSTUWYZ])[IXCMVLD]+(?![ABEFGHJKNOPQRSTUWYZ]))";

  public final String exceptionsLeft = "(?:бы|б|же|ж|ли|ль|us)";
  public final String exceptionsRight = "(?:и|до|по|от|за|из|to|by|in|of)";
}
