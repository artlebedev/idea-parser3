package ru.artlebedev.jreformator.html.entity;

import org.codingbox.utils.ArraysUtil;

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

public interface HtmlEntities {
  public static final HtmlEntity amp = new HtmlEntity("amp", "&", "&amp;", "&amp;", "&#38;");
  public static final HtmlEntity gt = new HtmlEntity("gt", ">", "&gt;", "&gt;", "&#62;");
  public static final HtmlEntity lt = new HtmlEntity("lt", "<", "&lt;", "&lt;", "&#60;");
  public static final HtmlEntity quot = new HtmlEntity("quot", "\"", "&quot;", "&quot;", "&#34;");

  public static final HtmlEntity[] specialEntities = new HtmlEntity[]{
          amp, gt, lt, quot
  };
  
  public static final HtmlEntity Aacute = new HtmlEntity("Aacute", "Á", "&Aacute;", "&Aacute;", "&#193;");
  public static final HtmlEntity aacute = new HtmlEntity("aacute", "á", "&aacute;", "&aacute;", "&#225;");
  public static final HtmlEntity Acirc = new HtmlEntity("Acirc", "Â", "&Acirc;", "&Acirc;", "&#194;");
  public static final HtmlEntity acirc = new HtmlEntity("acirc", "â", "&acirc;", "&acirc;", "&#226;");
  public static final HtmlEntity acute = new HtmlEntity("acute", "´", "&acute;", "&acute;", "&#180;");
  public static final HtmlEntity AElig = new HtmlEntity("AElig", "Æ", "&AElig;", "&AElig;", "&#198;");
  public static final HtmlEntity aelig = new HtmlEntity("aelig", "æ", "&aelig;", "&aelig;", "&#230;");
  public static final HtmlEntity Agrave = new HtmlEntity("Agrave", "À", "&Agrave;", "&Agrave;", "&#192;");
  public static final HtmlEntity agrave = new HtmlEntity("agrave", "à", "&agrave;", "&agrave;", "&#224;");
  public static final HtmlEntity alefsym = new HtmlEntity("alefsym", "ℵ", "&alefsym;","&alefsym;","&#8501;");
  public static final HtmlEntity Alpha = new HtmlEntity("Alpha", "Α", "&Alpha;", "&Alpha;", "&#913;");
  public static final HtmlEntity alpha = new HtmlEntity("alpha", "α", "&alpha;", "&alpha;", "&#945;");
  public static final HtmlEntity Aring = new HtmlEntity("Aring", "Å", "&Aring;", "&Aring;", "&#197;");
  public static final HtmlEntity aring = new HtmlEntity("aring", "å", "&aring;", "&aring;", "&#229;");
  public static final HtmlEntity asymp = new HtmlEntity("asymp", "≈", "&asymp;", "&asymp;", "&#8776;");
  public static final HtmlEntity Atilde = new HtmlEntity("Atilde", "Ã", "&Atilde;", "&Atilde;", "&#195;");
  public static final HtmlEntity atilde = new HtmlEntity("atilde", "ã", "&atilde;", "&atilde;", "&#227;");
  public static final HtmlEntity Auml = new HtmlEntity("Auml", "Ä", "&Auml;", "&Auml;", "&#196;");
  public static final HtmlEntity auml = new HtmlEntity("auml", "ä", "&auml;", "&auml;", "&#228;");
  public static final HtmlEntity bdquo = new HtmlEntity("bdquo", "„", "&#132;", "&bdquo;", "&#8222;");
  public static final HtmlEntity Beta = new HtmlEntity("Beta", "Β", "&Beta;", "&Beta;", "&#914;");
  public static final HtmlEntity beta = new HtmlEntity("beta", "β", "&beta;", "&beta;", "&#946;");
  public static final HtmlEntity brvbar = new HtmlEntity("brvbar", "¦", "&brvbar;", "&brvbar;", "&#166;");
  public static final HtmlEntity bsquo = new HtmlEntity("bsquo", ",", ",",  ",",  ",");
  public static final HtmlEntity bull = new HtmlEntity("bull", "•", "&bull;", "&bull;", "&#8226;");
  public static final HtmlEntity cap = new HtmlEntity("cap", "∩", "&cap;", "&cap;", "&#8745;");
  public static final HtmlEntity Ccedil = new HtmlEntity("Ccedil", "Ç", "&Ccedil;", "&Ccedil;", "&#199;");
  public static final HtmlEntity ccedil = new HtmlEntity("ccedil", "ç", "&ccedil;", "&ccedil;", "&#231;");
  public static final HtmlEntity cedil = new HtmlEntity("cedil", "¸", "&cedil;", "&cedil;", "&#184;");
  public static final HtmlEntity cent = new HtmlEntity("cent", "¢", "&cent;", "&cent;", "&#162;");
  public static final HtmlEntity Chi = new HtmlEntity("Chi", "Χ", "&Chi;", "&Chi;", "&#935;");
  public static final HtmlEntity chi = new HtmlEntity("chi", "χ", "&chi;", "&chi;", "&#967;");
  public static final HtmlEntity circ = new HtmlEntity("circ", "ˆ", "&circ;", "&circ;", "&#710;");
  public static final HtmlEntity clubs = new HtmlEntity("clubs", "♣", "&clubs;", "&clubs;", "&#9827;");
  public static final HtmlEntity cong = new HtmlEntity("cong", "≅", "&cong;", "&cong;", "&#8773;");
  public static final HtmlEntity copy = new HtmlEntity("copy", "©", "&copy;", "&copy;", "&#169;");
  public static final HtmlEntity crarr = new HtmlEntity("crarr", "↵", "&crarr;", "&crarr;", "&#8629;");
  public static final HtmlEntity curren = new HtmlEntity("curren", "¤", "&curren;", "&curren;", "&#164;");
  public static final HtmlEntity dagger = new HtmlEntity("dagger", "†", "&dagger;", "&dagger;", "&#8224;");
  public static final HtmlEntity Dagger = new HtmlEntity("Dagger", "‡", "&Dagger;", "&Dagger;", "&#8225;");
  public static final HtmlEntity darr = new HtmlEntity("darr", "↓", "&darr;", "&darr;", "&#8595;");
  public static final HtmlEntity dArr = new HtmlEntity("dArr", "⇓", "&dArr;", "&dArr;", "&#8659;");
  public static final HtmlEntity deg = new HtmlEntity("deg", "°", "&deg;", "&deg;", "&#176;");
  public static final HtmlEntity Delta = new HtmlEntity("Delta", "Δ", "&Delta;", "&Delta;", "&#916;");
  public static final HtmlEntity delta = new HtmlEntity("delta", "δ", "&delta;", "&delta;", "&#948;");
  public static final HtmlEntity diams = new HtmlEntity("diams", "♦", "&diams;", "&diams;", "&#9830;");
  public static final HtmlEntity divide = new HtmlEntity("divide", "÷", "&divide;", "&divide;", "&#247;");
  public static final HtmlEntity Eacute = new HtmlEntity("Eacute", "É", "&Eacute;", "&Eacute;", "&#201;");
  public static final HtmlEntity eacute = new HtmlEntity("eacute", "é", "&eacute;", "&eacute;", "&#233;");
  public static final HtmlEntity Ecirc = new HtmlEntity("Ecirc", "Ê", "&Ecirc;", "&Ecirc;", "&#202;");
  public static final HtmlEntity ecirc = new HtmlEntity("ecirc", "ê", "&ecirc;", "&ecirc;", "&#234;");
  public static final HtmlEntity Egrave = new HtmlEntity("Egrave", "È", "&Egrave;", "&Egrave;", "&#200;");
  public static final HtmlEntity egrave = new HtmlEntity("egrave", "è", "&egrave;", "&egrave;", "&#232;");
  public static final HtmlEntity empty = new HtmlEntity("empty", "∅", "&empty;", "&empty;", "&#8709;");
  public static final HtmlEntity Epsilon = new HtmlEntity("Epsilon", "Ε", "&Epsilon;","&Epsilon;","&#917;");
  public static final HtmlEntity epsilon = new HtmlEntity("epsilon", "ε", "&epsilon;","&epsilon;","&#949;");
  public static final HtmlEntity equiv = new HtmlEntity("equiv", "≡", "&equiv;", "&equiv;", "&#8801;");
  public static final HtmlEntity Eta = new HtmlEntity("Eta", "Η", "&Eta;", "&Eta;", "&#919;");
  public static final HtmlEntity eta = new HtmlEntity("eta", "η", "&eta;", "&eta;", "&#951;");
  public static final HtmlEntity ETH = new HtmlEntity("ETH", "Ð", "&ETH;", "&ETH;", "&#208;");
  public static final HtmlEntity eth = new HtmlEntity("eth", "ð", "&eth;", "&eth;", "&#240;");
  public static final HtmlEntity Euml = new HtmlEntity("Euml", "Ë", "&Euml;", "&Euml;", "&#203;");
  public static final HtmlEntity euml = new HtmlEntity("euml", "ë", "&euml;", "&euml;", "&#235;");
  public static final HtmlEntity euro = new HtmlEntity("euro", "€", "&euro;", "&euro;", "&#8364;");
  public static final HtmlEntity fnof = new HtmlEntity("fnof", "ƒ", "&fnof;", "&fnof;", "&#402;");
  public static final HtmlEntity frac12 = new HtmlEntity("frac12", "½", "&frac12;", "&frac12;", "&#189;");
  public static final HtmlEntity frac14 = new HtmlEntity("frac14", "¼", "&frac14;", "&frac14;", "&#188;");
  public static final HtmlEntity frac34 = new HtmlEntity("frac34", "¾", "&frac34;", "&frac34;", "&#190;");
  public static final HtmlEntity frasl = new HtmlEntity("frasl", "⁄", "&frasl;", "&frasl;", "&#8260;");
  public static final HtmlEntity Gamma = new HtmlEntity("Gamma", "Γ", "&Gamma;", "&Gamma;", "&#915;");
  public static final HtmlEntity gamma = new HtmlEntity("gamma", "γ", "&gamma;", "&gamma;", "&#947;");
  public static final HtmlEntity ge = new HtmlEntity("ge", "≥", "&ge;",  "&ge;",  "&#8805;");
  public static final HtmlEntity harr = new HtmlEntity("harr", "↔", "&harr;", "&harr;", "&#8596;");
  public static final HtmlEntity hearts = new HtmlEntity("hearts", "♥", "&hearts;", "&hearts;", "&#9829;");
  public static final HtmlEntity hellip = new HtmlEntity("hellip", "…", "&#133;", "&hellip;", "&#133;");
  public static final HtmlEntity Iacute = new HtmlEntity("Iacute", "Í", "&Iacute;", "&Iacute;", "&#205;");
  public static final HtmlEntity iacute = new HtmlEntity("iacute", "í", "&iacute;", "&iacute;", "&#237;");
  public static final HtmlEntity Icirc = new HtmlEntity("Icirc", "Î", "&Icirc;", "&Icirc;", "&#206;");
  public static final HtmlEntity icirc = new HtmlEntity("icirc", "î", "&icirc;", "&icirc;", "&#238;");
  public static final HtmlEntity iexcl = new HtmlEntity("iexcl", "¡", "&iexcl;", "&iexcl;", "&#161;");
  public static final HtmlEntity Igrave = new HtmlEntity("Igrave", "Ì", "&Igrave;", "&Igrave;", "&#204;");
  public static final HtmlEntity igrave = new HtmlEntity("igrave", "ì", "&igrave;", "&igrave;", "&#236;");
  public static final HtmlEntity image = new HtmlEntity("image", "ℑ", "&image;", "&image;", "&#8465;");
  public static final HtmlEntity infin = new HtmlEntity("infin", "∞", "&infin;", "&infin;", "&#8734;");
  public static final HtmlEntity integral = new HtmlEntity("integral", "∫", "&int;", "&int;", "&#8747;");
  public static final HtmlEntity Iota = new HtmlEntity("Iota", "Ι", "&Iota;", "&Iota;", "&#921;");
  public static final HtmlEntity iota = new HtmlEntity("iota", "ι", "&iota;", "&iota;", "&#953;");
  public static final HtmlEntity iquest = new HtmlEntity("iquest", "¿", "&iquest;", "&iquest;", "&#191;");
  public static final HtmlEntity Iuml = new HtmlEntity("Iuml", "Ï", "&Iuml;", "&Iuml;", "&#207;");
  public static final HtmlEntity iuml = new HtmlEntity("iuml", "ï", "&iuml;", "&iuml;", "&#239;");
  public static final HtmlEntity Kappa = new HtmlEntity("Kappa", "Κ", "&Kappa;", "&Kappa;", "&#922;");
  public static final HtmlEntity kappa = new HtmlEntity("kappa", "κ", "&kappa;", "&kappa;", "&#954;");
  public static final HtmlEntity Lambda = new HtmlEntity("Lambda", "Λ", "&Lambda;", "&Lambda;", "&#923;");
  public static final HtmlEntity lambda = new HtmlEntity("lambda", "λ", "&lambda;", "&lambda;", "&#955;");
  public static final HtmlEntity laquo = new HtmlEntity("laquo", "«", "&laquo;", "&laquo;", "&#171;");
  public static final HtmlEntity lArr = new HtmlEntity("lArr", "⇐", "&lArr;", "&lArr;", "&#8656;");
  public static final HtmlEntity larr = new HtmlEntity("larr", "←", "&larr;", "&larr;", "&#8592;");
  public static final HtmlEntity lceil = new HtmlEntity("lceil", "⌈", "&lceil;", "&lceil;", "&#8968;");
  public static final HtmlEntity ldquo = new HtmlEntity("ldquo", "“", "&#147;", "&ldquo;", "&#147;");
  public static final HtmlEntity le = new HtmlEntity("le", "≤", "&le;",  "&le;",  "&#8804;");
  public static final HtmlEntity lfloor = new HtmlEntity("lfloor", "⌊", "&lfloor;", "&lfloor;", "&#8970;");
  public static final HtmlEntity lowast = new HtmlEntity("lowast", "∗", "&lowast;", "&lowast;", "&#8727;");
  public static final HtmlEntity loz = new HtmlEntity("loz", "◊", "&loz;", "&loz;", "&#9674;");
  public static final HtmlEntity lsaquo = new HtmlEntity("lsaquo", "‹", "&lsaquo;", "&lsaquo;", "&#8249;");
  public static final HtmlEntity lsquo = new HtmlEntity("lsquo", "‘", "&#145;", "&lsquo;", "&#145;");
  public static final HtmlEntity macr = new HtmlEntity("macr", "¯", "&macr;", "&macr;", "&#175;");
  public static final HtmlEntity mdash = new HtmlEntity("mdash", "—", "&#151;", "&mdash;", "&#8212;");
  public static final HtmlEntity micro = new HtmlEntity("micro", "µ", "&micro;", "&micro;", "&#181;");
  public static final HtmlEntity middot = new HtmlEntity("middot", "·", "&middot;", "&middot;", "&#183;");
  public static final HtmlEntity minus = new HtmlEntity("minus", "−", "&minus;", "&minus;", "&#8722;");
  public static final HtmlEntity Mu = new HtmlEntity("Mu", "Μ", "&Mu;", "&Mu;", "&#924;");
  public static final HtmlEntity mu = new HtmlEntity("mu", "μ", "&mu;", "&mu;", "&#956;");
  public static final HtmlEntity nbsp = new HtmlEntity("nbsp", String.valueOf((char) 160), "&nbsp;", "&nbsp;", "&#160;");
  public static final HtmlEntity ndash = new HtmlEntity("ndash", "–", "&#150;", "&ndash;", "&#8211;");
  public static final HtmlEntity ne = new HtmlEntity("ne", "≠", "&ne;",  "&ne;",  "&#8800;");
  public static final HtmlEntity not = new HtmlEntity("not", "¬", "&not;", "&not;", "&#172;");
  public static final HtmlEntity notin = new HtmlEntity("notin", "∉", "&notin;", "&notin;", "&#8713;");
  public static final HtmlEntity nsub = new HtmlEntity("nsub", "⊄", "&nsub;", "&nsub;", "&#8836;");
  public static final HtmlEntity Ntilde = new HtmlEntity("Ntilde", "Ñ", "&Ntilde;", "&Ntilde;", "&#209;");
  public static final HtmlEntity ntilde = new HtmlEntity("ntilde", "ñ", "&ntilde;", "&ntilde;", "&#241;");
  public static final HtmlEntity Nu = new HtmlEntity("Nu", "Ν", "&Nu;",  "&Nu;",  "&#925;");
  public static final HtmlEntity nu = new HtmlEntity("nu", "ν", "&nu;",  "&nu;",  "&#957;");
  public static final HtmlEntity num = new HtmlEntity("num", "№", "&#8470;", "&#8470;", "&#8470;");
  public static final HtmlEntity Oacute = new HtmlEntity("Oacute", "Ó", "&Oacute;", "&Oacute;", "&#211;");
  public static final HtmlEntity oacute = new HtmlEntity("oacute", "ó", "&oacute;", "&oacute;", "&#243;");
  public static final HtmlEntity Ocirc = new HtmlEntity("Ocirc", "Ô", "&Ocirc;", "&Ocirc;", "&#212;");
  public static final HtmlEntity ocirc = new HtmlEntity("ocirc", "ô", "&ocirc;", "&ocirc;", "&#244;");
  public static final HtmlEntity OElig = new HtmlEntity("OElig", "Œ", "&OElig;", "&OElig;", "&#338;");
  public static final HtmlEntity oelig = new HtmlEntity("oelig", "œ", "&oelig;", "&oelig;", "&#339;");
  public static final HtmlEntity Ograve = new HtmlEntity("Ograve", "Ò", "&Ograve;", "&Ograve;", "&#210;");
  public static final HtmlEntity ograve = new HtmlEntity("ograve", "ò", "&ograve;", "&ograve;", "&#242;");
  public static final HtmlEntity oline = new HtmlEntity("oline", "‾", "&oline;", "&oline;", "&#8254;");
  public static final HtmlEntity Omega = new HtmlEntity("Omega", "Ω", "&Omega;", "&Omega;", "&#937;");
  public static final HtmlEntity omega = new HtmlEntity("omega", "ω", "&omega;", "&omega;", "&#969;");
  public static final HtmlEntity Omicron = new HtmlEntity("Omicron", "Ο", "&Omicron;","&Omicron;","&#927;");
  public static final HtmlEntity omicron = new HtmlEntity("omicron", "ο", "&omicron;","&omicron;","&#959;");
  public static final HtmlEntity ordf = new HtmlEntity("ordf", "ª", "&ordf;", "&ordf;", "&#170;");
  public static final HtmlEntity ordm = new HtmlEntity("ordm", "º", "&ordm;", "&ordm;", "&#186;");
  public static final HtmlEntity Oslash = new HtmlEntity("Oslash", "Ø", "&Oslash;", "&Oslash;", "&#216;");
  public static final HtmlEntity oslash = new HtmlEntity("oslash", "ø", "&oslash;", "&oslash;", "&#248;");
  public static final HtmlEntity Otilde = new HtmlEntity("Otilde", "Õ", "&Otilde;", "&Otilde;", "&#213;");
  public static final HtmlEntity otilde = new HtmlEntity("otilde", "õ", "&otilde;", "&otilde;", "&#245;");
  public static final HtmlEntity otimes = new HtmlEntity("otimes", "⊗", "&otimes;", "&otimes;", "&#8855;");
  public static final HtmlEntity Ouml = new HtmlEntity("Ouml", "Ö", "&Ouml;", "&Ouml;", "&#214;");
  public static final HtmlEntity ouml = new HtmlEntity("ouml", "ö", "&ouml;", "&ouml;", "&#246;");
  public static final HtmlEntity para = new HtmlEntity("para", "¶", "&para;", "&para;", "&#182;");
  public static final HtmlEntity part = new HtmlEntity("part", "∂", "&part;", "&part;", "&#8706;");
  public static final HtmlEntity permil = new HtmlEntity("permil", "‰", "&permil;", "&permil;", "&#8240;");
  public static final HtmlEntity Phi = new HtmlEntity("Phi", "Φ", "&Phi;", "&Phi;", "&#934;");
  public static final HtmlEntity phi = new HtmlEntity("phi", "φ", "&phi;", "&phi;", "&#966;");
  public static final HtmlEntity Pi = new HtmlEntity("Pi", "Π", "&Pi;",  "&Pi;",  "&#928;");
  public static final HtmlEntity pi = new HtmlEntity("pi", "π", "&pi;",  "&pi;",  "&#960;");
  public static final HtmlEntity piv = new HtmlEntity("piv", "ϖ", "&piv;", "&piv;", "&#982;");
  public static final HtmlEntity plusmn = new HtmlEntity("plusmn", "±", "&plusmn;", "&plusmn;", "&#177;");
  public static final HtmlEntity pound = new HtmlEntity("pound", "£", "&pound;", "&pound;", "&#163;");
  public static final HtmlEntity prime = new HtmlEntity("prime", "′", "&prime;", "&prime;", "&#8242;");
  public static final HtmlEntity Prime = new HtmlEntity("Prime", "″", "&Prime;", "&Prime;", "&#8243;");
  public static final HtmlEntity prod = new HtmlEntity("prod", "∏", "&prod;", "&prod;", "&#8719;");
  public static final HtmlEntity Psi = new HtmlEntity("Psi", "Ψ", "&Psi;", "&Psi;", "&#936;");
  public static final HtmlEntity psi = new HtmlEntity("psi", "ψ", "&psi;", "&psi;", "&#968;");
  public static final HtmlEntity radic = new HtmlEntity("radic", "√", "&radic;", "&radic;", "&#8730;");
  public static final HtmlEntity raquo = new HtmlEntity("raquo", "»", "&raquo;", "&raquo;", "&#187;");
  public static final HtmlEntity rarr = new HtmlEntity("rarr", "→", "&rarr;", "&rarr;", "&#8594;");
  public static final HtmlEntity rceil = new HtmlEntity("rceil", "⌉", "&rceil;", "&rceil;", "&#8969;");
  public static final HtmlEntity rdquo = new HtmlEntity("rdquo", "”", "&#148;", "&rdquo;", "&#8221;");
  public static final HtmlEntity real = new HtmlEntity("real", "ℜ", "&real;", "&real;", "&#8476;");
  public static final HtmlEntity reg = new HtmlEntity("reg", "®", "&reg;", "&reg;", "&#174;");
  public static final HtmlEntity rfloor = new HtmlEntity("rfloor", "⌋", "&rfloor;", "&rfloor;", "&#8971;");
  public static final HtmlEntity Rho = new HtmlEntity("Rho", "Ρ", "&Rho;", "&Rho;", "&#929;");
  public static final HtmlEntity rho = new HtmlEntity("rho", "ρ", "&rho;", "&rho;", "&#961;");
  public static final HtmlEntity rsaquo = new HtmlEntity("rsaquo", "›", "&rsaquo;", "&rsaquo;", "&#8250;");
  public static final HtmlEntity rsquo = new HtmlEntity("rsquo", "’", "&#146;", "&rsquo;", "&#146;");
  public static final HtmlEntity sbquo = new HtmlEntity("sbquo", "‚", "&sbquo;", "&sbquo;", "&#8218;");
  public static final HtmlEntity Scaron = new HtmlEntity("Scaron", "Š", "&Scaron;", "&Scaron;", "&#352;");
  public static final HtmlEntity scaron = new HtmlEntity("scaron", "š", "&scaron;", "&scaron;", "&#353;");
  public static final HtmlEntity sdot = new HtmlEntity("sdot", "⋅", "&sdot;", "&sdot;", "&#8901;");
  public static final HtmlEntity sect = new HtmlEntity("sect", "§", "&sect;", "&sect;", "&#167;");
  public static final HtmlEntity shy = new HtmlEntity("shy", String.valueOf((char) 173), "&shy;", "&shy;", "&#173;");
  public static final HtmlEntity Sigma = new HtmlEntity("Sigma", "Σ", "&Sigma;", "&Sigma;", "&#931;");
  public static final HtmlEntity sigma = new HtmlEntity("sigma", "σ", "&sigma;", "&sigma;", "&#963;");
  public static final HtmlEntity sigmaf = new HtmlEntity("sigmaf", "ς", "&sigmaf;", "&sigmaf;", "&#962;");
  public static final HtmlEntity spades = new HtmlEntity("spades", "♠", "&spades;", "&spades;", "&#9824;");
  public static final HtmlEntity sum = new HtmlEntity("sum", "∑", "&sum;", "&sum;", "&#8721;");
  public static final HtmlEntity sup1 = new HtmlEntity("sup1", "¹", "&sup1;", "&sup1;", "&#185;");
  public static final HtmlEntity sup2 = new HtmlEntity("sup2", "²", "&sup2;", "&sup2;", "&#178;");
  public static final HtmlEntity sup3 = new HtmlEntity("sup3", "³", "&sup3;", "&sup3;", "&#179;");
  public static final HtmlEntity szlig = new HtmlEntity("szlig", "ß", "&szlig;", "&szlig;", "&#223;");
  public static final HtmlEntity Tau = new HtmlEntity("Tau", "Τ", "&Tau;", "&Tau;", "&#932;");
  public static final HtmlEntity tau = new HtmlEntity("tau", "τ", "&tau;", "&tau;", "&#964;");
  public static final HtmlEntity Theta = new HtmlEntity("Theta", "Θ", "&Theta;", "&Theta;", "&#920;");
  public static final HtmlEntity theta = new HtmlEntity("theta", "θ", "&theta;", "&theta;", "&#952;");
  public static final HtmlEntity thetasym = new HtmlEntity("thetasym", "ϑ", "&thetasym;", "&thetasym;", "&#977;");
  public static final HtmlEntity THORN = new HtmlEntity("THORN", "Þ", "&THORN;", "&THORN;", "&#222;");
  public static final HtmlEntity thorn = new HtmlEntity("thorn", "þ", "&thorn;", "&thorn;", "&#254;");
  public static final HtmlEntity tilde = new HtmlEntity("tilde", "˜", "&tilde;", "&tilde;", "&#732;");
  public static final HtmlEntity times = new HtmlEntity("times", "×", "&times;", "&times;", "&#215;");
  public static final HtmlEntity trade = new HtmlEntity("trade", "™", "&trade;", "&trade;", "&#8482;");
  public static final HtmlEntity Uacute = new HtmlEntity("Uacute", "Ú", "&Uacute;", "&Uacute;", "&#218;");
  public static final HtmlEntity uacute = new HtmlEntity("uacute", "ú", "&uacute;", "&uacute;", "&#250;");
  public static final HtmlEntity uarr = new HtmlEntity("uarr", "↑", "&uarr;", "&uarr;", "&#8593;");
  public static final HtmlEntity uArr = new HtmlEntity("uArr", "⇑", "&uArr;", "&uArr;", "&#8657;");
  public static final HtmlEntity Ucirc = new HtmlEntity("Ucirc", "Û", "&Ucirc;", "&Ucirc;", "&#219;");
  public static final HtmlEntity ucirc = new HtmlEntity("ucirc", "û", "&ucirc;", "&ucirc;", "&#251;");
  public static final HtmlEntity Ugrave = new HtmlEntity("Ugrave", "Ù", "&Ugrave;", "&Ugrave;", "&#217;");
  public static final HtmlEntity ugrave = new HtmlEntity("ugrave", "ù", "&ugrave;", "&ugrave;", "&#249;");
  public static final HtmlEntity uml = new HtmlEntity("uml", "¨", "&uml;", "&uml;", "&#168;");
  public static final HtmlEntity upsih = new HtmlEntity("upsih", "ϒ", "&upsih;", "&upsih;", "&#978;");
  public static final HtmlEntity Upsilon = new HtmlEntity("Upsilon", "Υ", "&Upsilon;","&Upsilon;","&#933;");
  public static final HtmlEntity upsilon = new HtmlEntity("upsilon", "υ", "&upsilon;","&upsilon;","&#965;");
  public static final HtmlEntity Uuml = new HtmlEntity("Uuml", "Ü", "&Uuml;", "&Uuml;", "&#220;");
  public static final HtmlEntity uuml = new HtmlEntity("uuml", "ü", "&uuml;", "&uuml;", "&#252;");
  public static final HtmlEntity weierp = new HtmlEntity("weierp", "℘", "&weierp;", "&weierp;", "&#8472;");
  public static final HtmlEntity Xi = new HtmlEntity("Xi", "Ξ", "&Xi;",  "&Xi;",  "&#926;");
  public static final HtmlEntity xi = new HtmlEntity("xi", "ξ", "&xi;",  "&xi;",  "&#958;");
  public static final HtmlEntity Yacute = new HtmlEntity("Yacute", "Ý", "&Yacute;", "&Yacute;", "&#221;");
  public static final HtmlEntity yacute = new HtmlEntity("yacute", "ý", "&yacute;", "&yacute;", "&#253;");
  public static final HtmlEntity yen = new HtmlEntity("yen", "¥", "&yen;", "&yen;", "&#165;");
  public static final HtmlEntity yuml = new HtmlEntity("yuml", "ÿ", "&yuml;", "&yuml;", "&#255;");
  public static final HtmlEntity Yuml = new HtmlEntity("Yuml", "Ÿ", "&Yuml;", "&Yuml;", "&#376;");
  public static final HtmlEntity Zeta = new HtmlEntity("Zeta", "Ζ", "&Zeta;", "&Zeta;", "&#918;");
  public static final HtmlEntity zeta = new HtmlEntity("zeta", "ζ", "&zeta;", "&zeta;", "&#950;");

  public static final HtmlEntity[] commonEntites = new HtmlEntity[]{
          Aacute, aacute, Acirc, acirc, acute, AElig, aelig, Agrave, agrave, alefsym, Alpha,
          alpha, Aring, aring, asymp, Atilde, atilde, Auml, auml, bdquo, Beta, beta, brvbar,
          bsquo, bull, cap, Ccedil, ccedil, cedil, cent, Chi, chi, circ, clubs, cong, copy,
          crarr, curren, dagger, Dagger, darr, dArr, deg, Delta, delta, diams, divide, Eacute,
          eacute, Ecirc, ecirc, Egrave, egrave, empty, Epsilon, epsilon, equiv, Eta, eta, ETH, eth,
          Euml, euml, euro, fnof, frac12, frac14, frac34, frasl, Gamma, gamma, ge, harr, hearts,
          hellip, Iacute, iacute, Icirc, icirc, iexcl, Igrave, igrave, image, infin, integral,
          Iota, iota, iquest, Iuml, iuml, Kappa, kappa, Lambda, lambda, laquo, lArr, larr,
          lceil, ldquo, le, lfloor, lowast, loz, lsaquo, lsquo, macr, mdash, micro, middot,
          minus, Mu, mu, nbsp, ndash, ne, not, notin, nsub, Ntilde, ntilde, Nu, nu, num, Oacute,
          oacute, Ocirc, ocirc, OElig, oelig, Ograve, ograve, oline, Omega, omega, Omicron, omicron,
          ordf, ordm, Oslash, oslash, Otilde, otilde, otimes, Ouml, ouml, para, part, permil,
          Phi, phi, Pi, pi, piv, plusmn, pound, prime, Prime, prod, Psi, psi, radic, raquo,
          rarr, rceil, rdquo, real, reg, rfloor, Rho, rho, rsaquo, rsquo, sbquo, Scaron, scaron,
          sdot, sect, shy, Sigma, sigma, sigmaf, spades, sum, sup1, sup2, sup3, szlig, Tau, tau, Theta,
          theta, thetasym, THORN, thorn, tilde, times, trade, Uacute, uacute, uarr, uArr, Ucirc, ucirc,
          Ugrave, ugrave, uml, upsih, Upsilon, upsilon, Uuml, uuml, weierp, Xi, xi, Yacute, yacute,
          yen, yuml, Yuml, Zeta, zeta
  };

  public static final HtmlEntity[] allEntities = ArraysUtil.arrayMerge(specialEntities, commonEntites);
}
