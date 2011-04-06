package ru.artlebedev.reformator.html.entity;

/**
 * reformator for java
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 Vladimir Tokmakov <vlalek@design.ru>
 * Copyright 2011 ArtLebedev Studio
 * <p/>
 * Commercial use is restricted. Please contact ArtLebedev Studio in order to buy
 * commercial license if you want one.
 */

public interface HtmlEntities {
  public static final HtmlEntity amp = new HtmlEntity("&", "&amp;", "&amp;", "&#38;");
  public static final HtmlEntity gt = new HtmlEntity(">", "&gt;", "&gt;", "&#62;");
  public static final HtmlEntity lt = new HtmlEntity("<", "&lt;", "&lt;", "&#60;");
  public static final HtmlEntity quot = new HtmlEntity("\"", "&quot;", "&quot;", "&#34;");

  public static final HtmlEntity[] specialEntities = new HtmlEntity[]{
          amp, gt, lt, quot
  };
  
  public static final HtmlEntity Aacute = new HtmlEntity("Á", "&Aacute;", "&Aacute;", "&#193;");
  public static final HtmlEntity aacute = new HtmlEntity("á", "&aacute;", "&aacute;", "&#225;");
  public static final HtmlEntity Acirc = new HtmlEntity("Â", "&Acirc;", "&Acirc;", "&#194;");
  public static final HtmlEntity acirc = new HtmlEntity("â", "&acirc;", "&acirc;", "&#226;");
  public static final HtmlEntity acute = new HtmlEntity("´", "&acute;", "&acute;", "&#180;");
  public static final HtmlEntity AElig = new HtmlEntity("Æ", "&AElig;", "&AElig;", "&#198;");
  public static final HtmlEntity aelig = new HtmlEntity("æ", "&aelig;", "&aelig;", "&#230;");
  public static final HtmlEntity Agrave = new HtmlEntity("À", "&Agrave;", "&Agrave;", "&#192;");
  public static final HtmlEntity agrave = new HtmlEntity("à", "&agrave;", "&agrave;", "&#224;");
  public static final HtmlEntity alefsym = new HtmlEntity("ℵ", "&alefsym;","&alefsym;","&#8501;");
  public static final HtmlEntity Alpha = new HtmlEntity("Α", "&Alpha;", "&Alpha;", "&#913;");
  public static final HtmlEntity alpha = new HtmlEntity("α", "&alpha;", "&alpha;", "&#945;");
  public static final HtmlEntity Aring = new HtmlEntity("Å", "&Aring;", "&Aring;", "&#197;");
  public static final HtmlEntity aring = new HtmlEntity("å", "&aring;", "&aring;", "&#229;");
  public static final HtmlEntity asymp = new HtmlEntity("≈", "&asymp;", "&asymp;", "&#8776;");
  public static final HtmlEntity Atilde = new HtmlEntity("Ã", "&Atilde;", "&Atilde;", "&#195;");
  public static final HtmlEntity atilde = new HtmlEntity("ã", "&atilde;", "&atilde;", "&#227;");
  public static final HtmlEntity Auml = new HtmlEntity("Ä", "&Auml;", "&Auml;", "&#196;");
  public static final HtmlEntity auml = new HtmlEntity("ä", "&auml;", "&auml;", "&#228;");
  public static final HtmlEntity bdquo = new HtmlEntity("„", "&#132;", "&bdquo;", "&#8222;");
  public static final HtmlEntity Beta = new HtmlEntity("Β", "&Beta;", "&Beta;", "&#914;");
  public static final HtmlEntity beta = new HtmlEntity("β", "&beta;", "&beta;", "&#946;");
  public static final HtmlEntity brvbar = new HtmlEntity("¦", "&brvbar;", "&brvbar;", "&#166;");
  public static final HtmlEntity bsquo = new HtmlEntity(",", ",",  ",",  ",");
  public static final HtmlEntity bull = new HtmlEntity("•", "&bull;", "&bull;", "&#8226;");
  public static final HtmlEntity cap = new HtmlEntity("∩", "&cap;", "&cap;", "&#8745;");
  public static final HtmlEntity Ccedil = new HtmlEntity("Ç", "&Ccedil;", "&Ccedil;", "&#199;");
  public static final HtmlEntity ccedil = new HtmlEntity("ç", "&ccedil;", "&ccedil;", "&#231;");
  public static final HtmlEntity cedil = new HtmlEntity("¸", "&cedil;", "&cedil;", "&#184;");
  public static final HtmlEntity cent = new HtmlEntity("¢", "&cent;", "&cent;", "&#162;");
  public static final HtmlEntity Chi = new HtmlEntity("Χ", "&Chi;", "&Chi;", "&#935;");
  public static final HtmlEntity chi = new HtmlEntity("χ", "&chi;", "&chi;", "&#967;");
  public static final HtmlEntity circ = new HtmlEntity("ˆ", "&circ;", "&circ;", "&#710;");
  public static final HtmlEntity clubs = new HtmlEntity("♣", "&clubs;", "&clubs;", "&#9827;");
  public static final HtmlEntity cong = new HtmlEntity("≅", "&cong;", "&cong;", "&#8773;");
  public static final HtmlEntity copy = new HtmlEntity("©", "&copy;", "&copy;", "&#169;");
  public static final HtmlEntity crarr = new HtmlEntity("↵", "&crarr;", "&crarr;", "&#8629;");
  public static final HtmlEntity curren = new HtmlEntity("¤", "&curren;", "&curren;", "&#164;");
  public static final HtmlEntity dagger = new HtmlEntity("†", "&dagger;", "&dagger;", "&#8224;");
  public static final HtmlEntity Dagger = new HtmlEntity("‡", "&Dagger;", "&Dagger;", "&#8225;");
  public static final HtmlEntity darr = new HtmlEntity("↓", "&darr;", "&darr;", "&#8595;");
  public static final HtmlEntity dArr = new HtmlEntity("⇓", "&dArr;", "&dArr;", "&#8659;");
  public static final HtmlEntity deg = new HtmlEntity("°", "&deg;", "&deg;", "&#176;");
  public static final HtmlEntity Delta = new HtmlEntity("Δ", "&Delta;", "&Delta;", "&#916;");
  public static final HtmlEntity delta = new HtmlEntity("δ", "&delta;", "&delta;", "&#948;");
  public static final HtmlEntity diams = new HtmlEntity("♦", "&diams;", "&diams;", "&#9830;");
  public static final HtmlEntity divide = new HtmlEntity("÷", "&divide;", "&divide;", "&#247;");
  public static final HtmlEntity Eacute = new HtmlEntity("É", "&Eacute;", "&Eacute;", "&#201;");
  public static final HtmlEntity eacute = new HtmlEntity("é", "&eacute;", "&eacute;", "&#233;");
  public static final HtmlEntity Ecirc = new HtmlEntity("Ê", "&Ecirc;", "&Ecirc;", "&#202;");
  public static final HtmlEntity ecirc = new HtmlEntity("ê", "&ecirc;", "&ecirc;", "&#234;");
  public static final HtmlEntity Egrave = new HtmlEntity("È", "&Egrave;", "&Egrave;", "&#200;");
  public static final HtmlEntity egrave = new HtmlEntity("è", "&egrave;", "&egrave;", "&#232;");
  public static final HtmlEntity empty = new HtmlEntity("∅", "&empty;", "&empty;", "&#8709;");
  public static final HtmlEntity Epsilon = new HtmlEntity("Ε", "&Epsilon;","&Epsilon;","&#917;");
  public static final HtmlEntity epsilon = new HtmlEntity("ε", "&epsilon;","&epsilon;","&#949;");
  public static final HtmlEntity equiv = new HtmlEntity("≡", "&equiv;", "&equiv;", "&#8801;");
  public static final HtmlEntity Eta = new HtmlEntity("Η", "&Eta;", "&Eta;", "&#919;");
  public static final HtmlEntity eta = new HtmlEntity("η", "&eta;", "&eta;", "&#951;");
  public static final HtmlEntity ETH = new HtmlEntity("Ð", "&ETH;", "&ETH;", "&#208;");
  public static final HtmlEntity eth = new HtmlEntity("ð", "&eth;", "&eth;", "&#240;");
  public static final HtmlEntity Euml = new HtmlEntity("Ë", "&Euml;", "&Euml;", "&#203;");
  public static final HtmlEntity euml = new HtmlEntity("ë", "&euml;", "&euml;", "&#235;");
  public static final HtmlEntity euro = new HtmlEntity("€", "&euro;", "&euro;", "&#8364;");
  public static final HtmlEntity fnof = new HtmlEntity("ƒ", "&fnof;", "&fnof;", "&#402;");
  public static final HtmlEntity frac12 = new HtmlEntity("½", "&frac12;", "&frac12;", "&#189;");
  public static final HtmlEntity frac14 = new HtmlEntity("¼", "&frac14;", "&frac14;", "&#188;");
  public static final HtmlEntity frac34 = new HtmlEntity("¾", "&frac34;", "&frac34;", "&#190;");
  public static final HtmlEntity frasl = new HtmlEntity("⁄", "&frasl;", "&frasl;", "&#8260;");
  public static final HtmlEntity Gamma = new HtmlEntity("Γ", "&Gamma;", "&Gamma;", "&#915;");
  public static final HtmlEntity gamma = new HtmlEntity("γ", "&gamma;", "&gamma;", "&#947;");
  public static final HtmlEntity ge = new HtmlEntity("≥", "&ge;",  "&ge;",  "&#8805;");
  public static final HtmlEntity harr = new HtmlEntity("↔", "&harr;", "&harr;", "&#8596;");
  public static final HtmlEntity hearts = new HtmlEntity("♥", "&hearts;", "&hearts;", "&#9829;");
  public static final HtmlEntity hellip = new HtmlEntity("…", "&#133;", "&hellip;", "&#133;");
  public static final HtmlEntity Iacute = new HtmlEntity("Í", "&Iacute;", "&Iacute;", "&#205;");
  public static final HtmlEntity iacute = new HtmlEntity("í", "&iacute;", "&iacute;", "&#237;");
  public static final HtmlEntity Icirc = new HtmlEntity("Î", "&Icirc;", "&Icirc;", "&#206;");
  public static final HtmlEntity icirc = new HtmlEntity("î", "&icirc;", "&icirc;", "&#238;");
  public static final HtmlEntity iexcl = new HtmlEntity("¡", "&iexcl;", "&iexcl;", "&#161;");
  public static final HtmlEntity Igrave = new HtmlEntity("Ì", "&Igrave;", "&Igrave;", "&#204;");
  public static final HtmlEntity igrave = new HtmlEntity("ì", "&igrave;", "&igrave;", "&#236;");
  public static final HtmlEntity image = new HtmlEntity("ℑ", "&image;", "&image;", "&#8465;");
  public static final HtmlEntity infin = new HtmlEntity("∞", "&infin;", "&infin;", "&#8734;");
  public static final HtmlEntity integral = new HtmlEntity("∫", "&int;", "&int;", "&#8747;");
  public static final HtmlEntity Iota = new HtmlEntity("Ι", "&Iota;", "&Iota;", "&#921;");
  public static final HtmlEntity iota = new HtmlEntity("ι", "&iota;", "&iota;", "&#953;");
  public static final HtmlEntity iquest = new HtmlEntity("¿", "&iquest;", "&iquest;", "&#191;");
  public static final HtmlEntity Iuml = new HtmlEntity("Ï", "&Iuml;", "&Iuml;", "&#207;");
  public static final HtmlEntity iuml = new HtmlEntity("ï", "&iuml;", "&iuml;", "&#239;");
  public static final HtmlEntity Kappa = new HtmlEntity("Κ", "&Kappa;", "&Kappa;", "&#922;");
  public static final HtmlEntity kappa = new HtmlEntity("κ", "&kappa;", "&kappa;", "&#954;");
  public static final HtmlEntity Lambda = new HtmlEntity("Λ", "&Lambda;", "&Lambda;", "&#923;");
  public static final HtmlEntity lambda = new HtmlEntity("λ", "&lambda;", "&lambda;", "&#955;");
  public static final HtmlEntity laquo = new HtmlEntity("«", "&laquo;", "&laquo;", "&#171;");
  public static final HtmlEntity lArr = new HtmlEntity("⇐", "&lArr;", "&lArr;", "&#8656;");
  public static final HtmlEntity larr = new HtmlEntity("←", "&larr;", "&larr;", "&#8592;");
  public static final HtmlEntity lceil = new HtmlEntity("⌈", "&lceil;", "&lceil;", "&#8968;");
  public static final HtmlEntity ldquo = new HtmlEntity("“", "&#147;", "&ldquo;", "&#147;");
  public static final HtmlEntity le = new HtmlEntity("≤", "&le;",  "&le;",  "&#8804;");
  public static final HtmlEntity lfloor = new HtmlEntity("⌊", "&lfloor;", "&lfloor;", "&#8970;");
  public static final HtmlEntity lowast = new HtmlEntity("∗", "&lowast;", "&lowast;", "&#8727;");
  public static final HtmlEntity loz = new HtmlEntity("◊", "&loz;", "&loz;", "&#9674;");
  public static final HtmlEntity lsaquo = new HtmlEntity("‹", "&lsaquo;", "&lsaquo;", "&#8249;");
  public static final HtmlEntity lsquo = new HtmlEntity("‘", "&#145;", "&lsquo;", "&#145;");
  public static final HtmlEntity macr = new HtmlEntity("¯", "&macr;", "&macr;", "&#175;");
  public static final HtmlEntity mdash = new HtmlEntity("—", "&#151;", "&mdash;", "&#8212;");
  public static final HtmlEntity micro = new HtmlEntity("µ", "&micro;", "&micro;", "&#181;");
  public static final HtmlEntity middot = new HtmlEntity("·", "&middot;", "&middot;", "&#183;");
  public static final HtmlEntity minus = new HtmlEntity("−", "&minus;", "&minus;", "&#8722;");
  public static final HtmlEntity Mu = new HtmlEntity("Μ", "&Mu;", "&Mu;", "&#924;");
  public static final HtmlEntity mu = new HtmlEntity("μ", "&mu;", "&mu;", "&#956;");
  public static final HtmlEntity nbsp = new HtmlEntity(String.valueOf((char) 160), "&nbsp;", "&nbsp;", "&#160;");
  public static final HtmlEntity ndash = new HtmlEntity("–", "&#150;", "&ndash;", "&#8211;");
  public static final HtmlEntity ne = new HtmlEntity("≠", "&ne;",  "&ne;",  "&#8800;");
  public static final HtmlEntity not = new HtmlEntity("¬", "&not;", "&not;", "&#172;");
  public static final HtmlEntity notin = new HtmlEntity("∉", "&notin;", "&notin;", "&#8713;");
  public static final HtmlEntity nsub = new HtmlEntity("⊄", "&nsub;", "&nsub;", "&#8836;");
  public static final HtmlEntity Ntilde = new HtmlEntity("Ñ", "&Ntilde;", "&Ntilde;", "&#209;");
  public static final HtmlEntity ntilde = new HtmlEntity("ñ", "&ntilde;", "&ntilde;", "&#241;");
  public static final HtmlEntity Nu = new HtmlEntity("Ν", "&Nu;",  "&Nu;",  "&#925;");
  public static final HtmlEntity nu = new HtmlEntity("ν", "&nu;",  "&nu;",  "&#957;");
  public static final HtmlEntity num = new HtmlEntity("№", "&#8470;", "&#8470;", "&#8470;");
  public static final HtmlEntity Oacute = new HtmlEntity("Ó", "&Oacute;", "&Oacute;", "&#211;");
  public static final HtmlEntity oacute = new HtmlEntity("ó", "&oacute;", "&oacute;", "&#243;");
  public static final HtmlEntity Ocirc = new HtmlEntity("Ô", "&Ocirc;", "&Ocirc;", "&#212;");
  public static final HtmlEntity ocirc = new HtmlEntity("ô", "&ocirc;", "&ocirc;", "&#244;");
  public static final HtmlEntity OElig = new HtmlEntity("Œ", "&OElig;", "&OElig;", "&#338;");
  public static final HtmlEntity oelig = new HtmlEntity("œ", "&oelig;", "&oelig;", "&#339;");
  public static final HtmlEntity Ograve = new HtmlEntity("Ò", "&Ograve;", "&Ograve;", "&#210;");
  public static final HtmlEntity ograve = new HtmlEntity("ò", "&ograve;", "&ograve;", "&#242;");
  public static final HtmlEntity oline = new HtmlEntity("‾", "&oline;", "&oline;", "&#8254;");
  public static final HtmlEntity Omega = new HtmlEntity("Ω", "&Omega;", "&Omega;", "&#937;");
  public static final HtmlEntity omega = new HtmlEntity("ω", "&omega;", "&omega;", "&#969;");
  public static final HtmlEntity Omicron = new HtmlEntity("Ο", "&Omicron;","&Omicron;","&#927;");
  public static final HtmlEntity omicron = new HtmlEntity("ο", "&omicron;","&omicron;","&#959;");
  public static final HtmlEntity ordf = new HtmlEntity("ª", "&ordf;", "&ordf;", "&#170;");
  public static final HtmlEntity ordm = new HtmlEntity("º", "&ordm;", "&ordm;", "&#186;");
  public static final HtmlEntity Oslash = new HtmlEntity("Ø", "&Oslash;", "&Oslash;", "&#216;");
  public static final HtmlEntity oslash = new HtmlEntity("ø", "&oslash;", "&oslash;", "&#248;");
  public static final HtmlEntity Otilde = new HtmlEntity("Õ", "&Otilde;", "&Otilde;", "&#213;");
  public static final HtmlEntity otilde = new HtmlEntity("õ", "&otilde;", "&otilde;", "&#245;");
  public static final HtmlEntity otimes = new HtmlEntity("⊗", "&otimes;", "&otimes;", "&#8855;");
  public static final HtmlEntity Ouml = new HtmlEntity("Ö", "&Ouml;", "&Ouml;", "&#214;");
  public static final HtmlEntity ouml = new HtmlEntity("ö", "&ouml;", "&ouml;", "&#246;");
  public static final HtmlEntity para = new HtmlEntity("¶", "&para;", "&para;", "&#182;");
  public static final HtmlEntity part = new HtmlEntity("∂", "&part;", "&part;", "&#8706;");
  public static final HtmlEntity permil = new HtmlEntity("‰", "&permil;", "&permil;", "&#8240;");
  public static final HtmlEntity Phi = new HtmlEntity("Φ", "&Phi;", "&Phi;", "&#934;");
  public static final HtmlEntity phi = new HtmlEntity("φ", "&phi;", "&phi;", "&#966;");
  public static final HtmlEntity Pi = new HtmlEntity("Π", "&Pi;",  "&Pi;",  "&#928;");
  public static final HtmlEntity pi = new HtmlEntity("π", "&pi;",  "&pi;",  "&#960;");
  public static final HtmlEntity piv = new HtmlEntity("ϖ", "&piv;", "&piv;", "&#982;");
  public static final HtmlEntity plusmn = new HtmlEntity("±", "&plusmn;", "&plusmn;", "&#177;");
  public static final HtmlEntity pound = new HtmlEntity("£", "&pound;", "&pound;", "&#163;");
  public static final HtmlEntity prime = new HtmlEntity("′", "&prime;", "&prime;", "&#8242;");
  public static final HtmlEntity Prime = new HtmlEntity("″", "&Prime;", "&Prime;", "&#8243;");
  public static final HtmlEntity prod = new HtmlEntity("∏", "&prod;", "&prod;", "&#8719;");
  public static final HtmlEntity Psi = new HtmlEntity("Ψ", "&Psi;", "&Psi;", "&#936;");
  public static final HtmlEntity psi = new HtmlEntity("ψ", "&psi;", "&psi;", "&#968;");
  public static final HtmlEntity radic = new HtmlEntity("√", "&radic;", "&radic;", "&#8730;");
  public static final HtmlEntity raquo = new HtmlEntity("»", "&raquo;", "&raquo;", "&#187;");
  public static final HtmlEntity rarr = new HtmlEntity("→", "&rarr;", "&rarr;", "&#8594;");
  public static final HtmlEntity rceil = new HtmlEntity("⌉", "&rceil;", "&rceil;", "&#8969;");
  public static final HtmlEntity rdquo = new HtmlEntity("”", "&#148;", "&rdquo;", "&#8221;");
  public static final HtmlEntity real = new HtmlEntity("ℜ", "&real;", "&real;", "&#8476;");
  public static final HtmlEntity reg = new HtmlEntity("®", "&reg;", "&reg;", "&#174;");
  public static final HtmlEntity rfloor = new HtmlEntity("⌋", "&rfloor;", "&rfloor;", "&#8971;");
  public static final HtmlEntity Rho = new HtmlEntity("Ρ", "&Rho;", "&Rho;", "&#929;");
  public static final HtmlEntity rho = new HtmlEntity("ρ", "&rho;", "&rho;", "&#961;");
  public static final HtmlEntity rsaquo = new HtmlEntity("›", "&rsaquo;", "&rsaquo;", "&#8250;");
  public static final HtmlEntity rsquo = new HtmlEntity("’", "&#146;", "&rsquo;", "&#146;");
  public static final HtmlEntity sbquo = new HtmlEntity("‚", "&sbquo;", "&sbquo;", "&#8218;");
  public static final HtmlEntity Scaron = new HtmlEntity("Š", "&Scaron;", "&Scaron;", "&#352;");
  public static final HtmlEntity scaron = new HtmlEntity("š", "&scaron;", "&scaron;", "&#353;");
  public static final HtmlEntity sdot = new HtmlEntity("⋅", "&sdot;", "&sdot;", "&#8901;");
  public static final HtmlEntity sect = new HtmlEntity("§", "&sect;", "&sect;", "&#167;");
  public static final HtmlEntity shy = new HtmlEntity(String.valueOf((char) 173), "&shy;", "&shy;", "&#173;");
  public static final HtmlEntity Sigma = new HtmlEntity("Σ", "&Sigma;", "&Sigma;", "&#931;");
  public static final HtmlEntity sigma = new HtmlEntity("σ", "&sigma;", "&sigma;", "&#963;");
  public static final HtmlEntity sigmaf = new HtmlEntity("ς", "&sigmaf;", "&sigmaf;", "&#962;");
  public static final HtmlEntity spades = new HtmlEntity("♠", "&spades;", "&spades;", "&#9824;");
  public static final HtmlEntity sum = new HtmlEntity("∑", "&sum;", "&sum;", "&#8721;");
  public static final HtmlEntity sup1 = new HtmlEntity("¹", "&sup1;", "&sup1;", "&#185;");
  public static final HtmlEntity sup2 = new HtmlEntity("²", "&sup2;", "&sup2;", "&#178;");
  public static final HtmlEntity sup3 = new HtmlEntity("³", "&sup3;", "&sup3;", "&#179;");
  public static final HtmlEntity szlig = new HtmlEntity("ß", "&szlig;", "&szlig;", "&#223;");
  public static final HtmlEntity Tau = new HtmlEntity("Τ", "&Tau;", "&Tau;", "&#932;");
  public static final HtmlEntity tau = new HtmlEntity("τ", "&tau;", "&tau;", "&#964;");
  public static final HtmlEntity Theta = new HtmlEntity("Θ", "&Theta;", "&Theta;", "&#920;");
  public static final HtmlEntity theta = new HtmlEntity("θ", "&theta;", "&theta;", "&#952;");
  public static final HtmlEntity thetasym = new HtmlEntity("ϑ", "&thetasym;", "&thetasym;", "&#977;");
  public static final HtmlEntity THORN = new HtmlEntity("Þ", "&THORN;", "&THORN;", "&#222;");
  public static final HtmlEntity thorn = new HtmlEntity("þ", "&thorn;", "&thorn;", "&#254;");
  public static final HtmlEntity tilde = new HtmlEntity("˜", "&tilde;", "&tilde;", "&#732;");
  public static final HtmlEntity times = new HtmlEntity("×", "&times;", "&times;", "&#215;");
  public static final HtmlEntity trade = new HtmlEntity("™", "&trade;", "&trade;", "&#8482;");
  public static final HtmlEntity Uacute = new HtmlEntity("Ú", "&Uacute;", "&Uacute;", "&#218;");
  public static final HtmlEntity uacute = new HtmlEntity("ú", "&uacute;", "&uacute;", "&#250;");
  public static final HtmlEntity uarr = new HtmlEntity("↑", "&uarr;", "&uarr;", "&#8593;");
  public static final HtmlEntity uArr = new HtmlEntity("⇑", "&uArr;", "&uArr;", "&#8657;");
  public static final HtmlEntity Ucirc = new HtmlEntity("Û", "&Ucirc;", "&Ucirc;", "&#219;");
  public static final HtmlEntity ucirc = new HtmlEntity("û", "&ucirc;", "&ucirc;", "&#251;");
  public static final HtmlEntity Ugrave = new HtmlEntity("Ù", "&Ugrave;", "&Ugrave;", "&#217;");
  public static final HtmlEntity ugrave = new HtmlEntity("ù", "&ugrave;", "&ugrave;", "&#249;");
  public static final HtmlEntity uml = new HtmlEntity("¨", "&uml;", "&uml;", "&#168;");
  public static final HtmlEntity upsih = new HtmlEntity("ϒ", "&upsih;", "&upsih;", "&#978;");
  public static final HtmlEntity Upsilon = new HtmlEntity("Υ", "&Upsilon;","&Upsilon;","&#933;");
  public static final HtmlEntity upsilon = new HtmlEntity("υ", "&upsilon;","&upsilon;","&#965;");
  public static final HtmlEntity Uuml = new HtmlEntity("Ü", "&Uuml;", "&Uuml;", "&#220;");
  public static final HtmlEntity uuml = new HtmlEntity("ü", "&uuml;", "&uuml;", "&#252;");
  public static final HtmlEntity weierp = new HtmlEntity("℘", "&weierp;", "&weierp;", "&#8472;");
  public static final HtmlEntity Xi = new HtmlEntity("Ξ", "&Xi;",  "&Xi;",  "&#926;");
  public static final HtmlEntity xi = new HtmlEntity("ξ", "&xi;",  "&xi;",  "&#958;");
  public static final HtmlEntity Yacute = new HtmlEntity("Ý", "&Yacute;", "&Yacute;", "&#221;");
  public static final HtmlEntity yacute = new HtmlEntity("ý", "&yacute;", "&yacute;", "&#253;");
  public static final HtmlEntity yen = new HtmlEntity("¥", "&yen;", "&yen;", "&#165;");
  public static final HtmlEntity yuml = new HtmlEntity("ÿ", "&yuml;", "&yuml;", "&#255;");
  public static final HtmlEntity Yuml = new HtmlEntity("Ÿ", "&Yuml;", "&Yuml;", "&#376;");
  public static final HtmlEntity Zeta = new HtmlEntity("Ζ", "&Zeta;", "&Zeta;", "&#918;");
  public static final HtmlEntity zeta = new HtmlEntity("ζ", "&zeta;", "&zeta;", "&#950;");

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
}
