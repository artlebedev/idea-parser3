package ru.artlebedev.jreformator.language.abc;

import ru.artlebedev.jreformator.language.type.LanguageTypes;

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

public interface LanguageAbcs {
  public static final LanguageAbc RUSSIAN = new LanguageAbc("ru", LanguageTypes.CYR, "ЁИЙЦЩЪЫЬЭЮЯ", "ёийцщъыьэюя", null);
  public static final LanguageAbc ENGLISH = new LanguageAbc("en", LanguageTypes.LAT, "CFJKQVWXYZ", "cfjkqvwxyz", null);
  public static final LanguageAbc SPANISH = new LanguageAbc("es", LanguageTypes.LAT, "CFJKQVWXYZÁÉÍÑÓÚÜ", "cfjkqvwxyzáéíñóúü", null);
  public static final LanguageAbc FRENCH = new LanguageAbc("fr", LanguageTypes.LAT, "CFJKQVWXYZÀÂÆÇÈÉÊËÎÏÔŒÙÛÜŸ", "cfjkqvwxyzàâæçèéêëîïôœùûüÿ", null);
  public static final LanguageAbc GERMAN = new LanguageAbc("de", LanguageTypes.LAT, "CFJKQVWXYZÄÖßÜ", "cfjkqvwxyzäößü", new String[]{
          "/(\\s|^)(und|zu|von|ein(e[rnm]?)?|dem|nicht|sich|auch|ist|durch|nach|hatte|noch|wie|mein|aus)(\\s|$)/gi",
          "/\\S{2,}(cht|eit|hte|gte|sch|fen|hre|tig|tzt|ieb|bau|bei|auf|von|ehe|erd|bst)(\\s|$)/gi"
  });
  public static final LanguageAbc PORTUGUES = new LanguageAbc("pt", LanguageTypes.LAT, "CFJQVXZÀÁÂÃÇÉÊÍÓÔÕÚÜ", "cfjqvxzàáâãçéêíóôõúü", null);
  public static final LanguageAbc ITALIAN = new LanguageAbc("it", LanguageTypes.LAT, "CFQVZÀÈÉÌÒÙ", "cfqvzàèéìòù", null);
  public static final LanguageAbc TURKISH = new LanguageAbc("tr", LanguageTypes.LAT, "CFJKVYZÂÇĞİÖŞÜ", "cfjkvyzâçğıöşü", null);
  public static final LanguageAbc POLISH = new LanguageAbc("pl", LanguageTypes.LAT, "CFJKWYZĄĆĘŁŃÓŚŹŻ", "cfjkwyząćęłńóśźż", null);
  public static final LanguageAbc NETHERLAND = new LanguageAbc("nl", LanguageTypes.LAT, "CFJKQVWXYZÄÈÉËÏĲÖÜ", "cfjkqvwxyzäèéëïĳöü", null);
  public static final LanguageAbc ROMANIAN = new LanguageAbc("ro", LanguageTypes.LAT, "CFJKVXZÂĂÎȘŢ", "cfjkvxzâăîșţ", null);
  public static final LanguageAbc SWEDISH = new LanguageAbc("sv", LanguageTypes.LAT, "CFJKQVWXYZÄÅÖÜ", "cfjkqvwxyzäåöü", null);
  public static final LanguageAbc UKRAINIAN = new LanguageAbc("uk", LanguageTypes.CYR, "ҐЄІЇИЙЦЩЬЮЯ", "ґєіїийцщьюя", null);
  public static final LanguageAbc CZECH = new LanguageAbc("cs", LanguageTypes.LAT, "CFJKVYZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ", "cfjkvyzáčďéěíňóřšťúůýž", null);
  public static final LanguageAbc HUNGARIAN = new LanguageAbc("hu", LanguageTypes.LAT, "CFJKVZÁÉÍÓÖŐÚÜŰ", "cfjkvzáéíóöőúüű", null);
  public static final LanguageAbc NORWEGIAN = new LanguageAbc("no", LanguageTypes.LAT, "CFJKQVWXYZÅÆØ", "cfjkqvwxyzåæø", null);
  public static final LanguageAbc BULGARIAN = new LanguageAbc("bg", LanguageTypes.CYR, "ИЙЦЩЪЬЮЯ", "ийцщъьюя", null);
  public static final LanguageAbc FINNISH = new LanguageAbc("fi", LanguageTypes.LAT, "CFJKQVWXYZÄÅÖŠ", "cfjkqvwxyzäåöš", null);
  public static final LanguageAbc BELARUSIAN = new LanguageAbc("be", LanguageTypes.CYR, "ЁІЙЎЦЫЬЭЮЯ", "ёійўцыьэюя", null);
  public static final LanguageAbc SLOVAK = new LanguageAbc("sk", LanguageTypes.LAT, "CFJKQVWXYZÁÄČĎÉÍŇÓÔŔŠŤÚÝŽ", "cfjkqvwxyzáäčďéíňóôŕšťúýž", null);
  public static final LanguageAbc IRISH = new LanguageAbc("ga", LanguageTypes.LAT, "CFÁÉÍÓÚ", "cfáéíóú", null);
  public static final LanguageAbc CROATIAN = new LanguageAbc("hr", LanguageTypes.LAT, "CFJKVZĆČĐŠŽ", "cfjkvzćčđšž", null);
  public static final LanguageAbc KAZAKH = new LanguageAbc("kk", LanguageTypes.CYR, "ӘҒЁІИЙҚҢӨҰҮҺЦЩЪЫЬЭЮЯ", "әғёіийқңөұүһцщъыьэюя", null);
  public static final LanguageAbc SERBIAN = new LanguageAbc("sr", LanguageTypes.CYR, "ЂИЈЉЊЋЦЏ", "ђијљњћцџ", null);
  public static final LanguageAbc LITHUANIAN = new LanguageAbc("lt", LanguageTypes.LAT, "CFJKXYZĄČĖĘĮŠŪŲŽ", "cfjkxyząčėęįšūųž", null);
  public static final LanguageAbc LATVIAN = new LanguageAbc("lv", LanguageTypes.LAT, "CFJKVZĀČĒĢĪĶĻŅŠŪŽ", "cfjkvzāčēģīķļņšūž", null);
  public static final LanguageAbc DANISH = new LanguageAbc("da", LanguageTypes.LAT, "CFJKQVWXYZÅÆØ", "cfjkqvwxyzåæø", null);
  public static final LanguageAbc SLOVENE = new LanguageAbc("sl", LanguageTypes.LAT, "CFJKVZČŠŽ", "cfjkvzčšž", null);
  public static final LanguageAbc ESTONIA = new LanguageAbc("et", LanguageTypes.LAT, "FJKVZÄÕÖŠÜ", "fjkvzäõöšü", null);
  public static final LanguageAbc MOLDAVIAN = new LanguageAbc("mo", LanguageTypes.CYR, "ӁИЙЦЫЬЭЮЯ", "ӂийцыьэюя", null);
  public static final LanguageAbc MACEDONIAN = new LanguageAbc("mk", LanguageTypes.CYR, "ЃЅИЈЌЉЊЦЏ", "ѓѕијќљњцџ", null);
  public static final LanguageAbc ALBANIAN = new LanguageAbc("sq", LanguageTypes.LAT, "CFJKQVXYZÇË", "cfjkqvxyzçë", null);
  public static final LanguageAbc ICELANDIC = new LanguageAbc("is", LanguageTypes.LAT, "FJKVXYÁÆÐÉÍÓÖÞÚ", "fjkvxyáæðéíóöþú", null);
  public static final LanguageAbc SCOTTISH = new LanguageAbc("sc", LanguageTypes.LAT, "CFÀÈÌÒÙ", "cfàèìòù", null);
  public static final LanguageAbc ESPERANTO = new LanguageAbc("eo", LanguageTypes.LAT, "CFJKVZĈĜĤĴŜŬ", "cfjkvzĉĝĥĵŝŭ", null);
  public static final LanguageAbc GREEK = new LanguageAbc("el", LanguageTypes.LAT, "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩϜϚϘϠ", "αβγδεζηθικλμνξοπρσςτυφχψωϝϛϙϡ", null);
  public static final LanguageAbc ABKHAZIAN = new LanguageAbc("ab", LanguageTypes.CYR, "ҔЏҼҾҘӠИҞҨҦҪҬҲЦҴҶЫ", "ҕџҽҿҙӡиҟҩҧҫҭҳцҵҷы", null);
  public static final LanguageAbc AZERBAIJANI = new LanguageAbc("az", LanguageTypes.LAT, "CFJKQVXYZÇƏĞİÖŞÜ", "cfjkqvxyzçəğıöşü", null);
  public static final LanguageAbc CHECHEN = new LanguageAbc("ce", LanguageTypes.LAT, "CFJKQVXYZÄÇƏĠÖŞÜ", "cfjkqvxyzäçəġöşü", null);
  public static final LanguageAbc KIRGHIZ = new LanguageAbc("ky", LanguageTypes.CYR, "ЁИЙҢӨҮЦЩЪЫЬЭЮЯ", "ёийңөүцщъыьэюя", null);
  public static final LanguageAbc OSSETIC = new LanguageAbc("os", LanguageTypes.CYR, "ӔЁИЙЦЩЪЫЬЭЮЯ", "ӕёийцщъыьэюя", null);
  public static final LanguageAbc TATAR = new LanguageAbc("tt", LanguageTypes.LAT, "CFJKQVWXYZÁÂÄÇÉĞÍİÑÓÖŞÚÜ", "cfjkqvwxyzáâäçéğíıñóöşúü", null);
  public static final LanguageAbc TURKMEN = new LanguageAbc("tk", LanguageTypes.LAT, "FJKWYZÄÇŇÖŞÜÝ", "fjkwyzäçňöşüý", null);
  public static final LanguageAbc TAJIK = new LanguageAbc("tg", LanguageTypes.CYR, "ҒЁИЙӢҚӮҲҶЪЭЮЯ", "ғёийӣқӯҳҷъэюя", null);
  public static final LanguageAbc UZBEK = new LanguageAbc("uz", LanguageTypes.LAT, "CFJKQVXYZ", "cfjkqvxyz", null);
  public static final LanguageAbc VIETNAMESE = new LanguageAbc("vi", LanguageTypes.LAT, "CKQVXYÂĂĐÊÔƠƯ", "ckqvxyâăđêôơư", null);

  public static final LanguageAbc[] allLanguageAbcs = new LanguageAbc[]{
    RUSSIAN, ENGLISH, SPANISH, FRENCH, GERMAN, PORTUGUES, ITALIAN, TURKISH, POLISH,
    NETHERLAND, ROMANIAN, SWEDISH, UKRAINIAN, CZECH, HUNGARIAN, NORWEGIAN, BULGARIAN,
    FINNISH, BELARUSIAN, SLOVAK, IRISH, CROATIAN, KAZAKH, SERBIAN, LITHUANIAN,
    LATVIAN, DANISH, SLOVENE, ESTONIA, MOLDAVIAN, MACEDONIAN, ALBANIAN, ICELANDIC,
    SCOTTISH, ESPERANTO, GREEK, ABKHAZIAN, AZERBAIJANI, CHECHEN, KIRGHIZ, OSSETIC,
    TATAR, TURKMEN, TAJIK, UZBEK, VIETNAMESE
  };
}
