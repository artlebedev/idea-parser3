package ru.artlebedev.jreformator.html.tag;

/**
 * jreformator
 * <p/>
 * Based on code originally written by Vladimir Tokmakov <vlalek@design.ru>
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

public interface HtmlTags {
  public static final HtmlTag a = new HtmlTag("a", false);
  public static final HtmlTag abbr = new HtmlTag("abbr", false);
  public static final HtmlTag acronym = new HtmlTag("acronym", false);
  public static final HtmlTag address = new HtmlTag("address", true);
  public static final HtmlTag applet = new HtmlTag("applet", false);
  public static final HtmlTag area = new HtmlTag("area", false);
  public static final HtmlTag audio = new HtmlTag("audio", true);
  public static final HtmlTag b = new HtmlTag("b", false);
  public static final HtmlTag base = new HtmlTag("base", false);
  public static final HtmlTag bdo = new HtmlTag("bdo", false);
  public static final HtmlTag basefont = new HtmlTag("basefont", false);
  public static final HtmlTag big = new HtmlTag("big", false);
  public static final HtmlTag blockquote = new HtmlTag("blockquote", false);
  public static final HtmlTag body = new HtmlTag("body", true);
  public static final HtmlTag br = new HtmlTag("br", false);
  public static final HtmlTag button = new HtmlTag("button", false);
  public static final HtmlTag caption = new HtmlTag("caption", true);
  public static final HtmlTag center = new HtmlTag("center", true);
  public static final HtmlTag cite = new HtmlTag("cite", false);
  public static final HtmlTag code = new HtmlTag("code", false);
  public static final HtmlTag col = new HtmlTag("col", false);
  public static final HtmlTag colgroup = new HtmlTag("colgroup", true);
  public static final HtmlTag dd = new HtmlTag("dd", true);
  public static final HtmlTag del = new HtmlTag("del", false);
  public static final HtmlTag dfn = new HtmlTag("dfn", false);
  public static final HtmlTag dir = new HtmlTag("dir", true);
  public static final HtmlTag div = new HtmlTag("div", true);
  public static final HtmlTag dl = new HtmlTag("dl", true);
  public static final HtmlTag dt = new HtmlTag("dt", true);
  public static final HtmlTag em = new HtmlTag("em", false);
  public static final HtmlTag embed = new HtmlTag("embed", true);
  public static final HtmlTag fieldset = new HtmlTag("fieldset", true);
  public static final HtmlTag font = new HtmlTag("font", false);
  public static final HtmlTag form = new HtmlTag("form", true);
  public static final HtmlTag frame = new HtmlTag("frame", true);
  public static final HtmlTag frameset = new HtmlTag("frameset", true);
  public static final HtmlTag h1 = new HtmlTag("h1", true);
  public static final HtmlTag h2 = new HtmlTag("h2", true);
  public static final HtmlTag h3 = new HtmlTag("h3", true);
  public static final HtmlTag h4 = new HtmlTag("h4", true);
  public static final HtmlTag h5 = new HtmlTag("h5", true);
  public static final HtmlTag h6 = new HtmlTag("h6", true);
  public static final HtmlTag head = new HtmlTag("head", true);
  public static final HtmlTag hr = new HtmlTag("hr", true);
  public static final HtmlTag html = new HtmlTag("html", true);
  public static final HtmlTag i = new HtmlTag("i", false);
  public static final HtmlTag iframe = new HtmlTag("iframe", true);
  public static final HtmlTag img = new HtmlTag("img", false);
  public static final HtmlTag input = new HtmlTag("input", false);
  public static final HtmlTag ins = new HtmlTag("ins", true);
  public static final HtmlTag isindex = new HtmlTag("isindex", true);
  public static final HtmlTag kbd = new HtmlTag("kbd", false);
  public static final HtmlTag label = new HtmlTag("label", false);
  public static final HtmlTag legend = new HtmlTag("legend", true);
  public static final HtmlTag li = new HtmlTag("li", true);
  public static final HtmlTag link = new HtmlTag("link", false);
  public static final HtmlTag map = new HtmlTag("map", false);
  public static final HtmlTag menu = new HtmlTag("menu", true);
  public static final HtmlTag meta = new HtmlTag("meta", false);
  public static final HtmlTag nobr = new HtmlTag("nobr", false);
  public static final HtmlTag noframes = new HtmlTag("noframes", true);
  public static final HtmlTag noscript = new HtmlTag("noscript", true);
  public static final HtmlTag object = new HtmlTag("object", true);
  public static final HtmlTag ol = new HtmlTag("ol", true);
  public static final HtmlTag optgroup = new HtmlTag("optgroup", true);
  public static final HtmlTag option = new HtmlTag("option", true);
  public static final HtmlTag p = new HtmlTag("p", true);
  public static final HtmlTag param = new HtmlTag("param", false);
  public static final HtmlTag pre = new HtmlTag("pre", true);
  public static final HtmlTag q = new HtmlTag("q", false);
  public static final HtmlTag s = new HtmlTag("s", false);
  public static final HtmlTag samp = new HtmlTag("samp", false);
  public static final HtmlTag script = new HtmlTag("script", true);
  public static final HtmlTag select = new HtmlTag("select", true);
  public static final HtmlTag small = new HtmlTag("small", false);
  public static final HtmlTag span = new HtmlTag("span", false);
  public static final HtmlTag strike = new HtmlTag("strike", false);
  public static final HtmlTag strong = new HtmlTag("strong", false);
  public static final HtmlTag style = new HtmlTag("style", true);
  public static final HtmlTag sub = new HtmlTag("sub", false);
  public static final HtmlTag sup = new HtmlTag("sup", false);
  public static final HtmlTag table = new HtmlTag("table", true);
  public static final HtmlTag tbody = new HtmlTag("tbody", true);
  public static final HtmlTag td = new HtmlTag("td", true);
  public static final HtmlTag textarea = new HtmlTag("textarea", false);
  public static final HtmlTag tfoot = new HtmlTag("tfoot", true);
  public static final HtmlTag th = new HtmlTag("th", true);
  public static final HtmlTag thead = new HtmlTag("thead", true);
  public static final HtmlTag title = new HtmlTag("title", false);
  public static final HtmlTag tr = new HtmlTag("tr", true);
  public static final HtmlTag tt = new HtmlTag("tt", false);
  public static final HtmlTag u = new HtmlTag("u", false);
  public static final HtmlTag ul = new HtmlTag("ul", true);
  public static final HtmlTag wbr = new HtmlTag("wbr", false);
  public static final HtmlTag var = new HtmlTag("var", false);
  public static final HtmlTag video = new HtmlTag("video", true);

  public static final HtmlTag[] allTags = new HtmlTag[]{
          a, abbr, acronym, address, applet, area, audio, b, base, bdo, basefont, big, blockquote,
          body, br, button, caption, center, cite, code, col, colgroup, dd, del, dfn, dir, div,
          dl, dt, em, embed, fieldset, font, form, frame, frameset, h1, h2, h3, h4, h5, h6, head,
          hr, html, i, iframe, img, input, ins, isindex, kbd, label, legend, li, link, map,
          menu, meta, nobr, noframes, noscript, object, ol, optgroup, option, p,  param, pre,
          q, s, samp, script, select, small, span, strike, strong, style, sub, sup, table, tbody,
          td, textarea, tfoot, th, thead, title, tr, tt, u, ul, wbr, var, video
  };
}