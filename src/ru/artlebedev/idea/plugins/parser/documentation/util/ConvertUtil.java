package ru.artlebedev.idea.plugins.parser.documentation.util;

/**
 * idea-parser3: slightly useful plugin.
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

public class ConvertUtil {
  public static String prettifyContent(String content) {
    content = content.replaceAll("<script(.*?)>([\\w\\W]*?)</script>", "");
    content = content.replaceAll("<hr(.*?)>([\\w\\W]*?)</body>", "</body>");
    content = content.replaceAll("<!--([\\w\\W]*?)-->", "");
    content = content.replaceAll("<(m|M)(e|E)(t|T)(a|A)(.*)>", "");
    content = content.replaceAll("<span style=\"font-family:Monospace; font-size:10pt; color:#0000FF\">([\\w\\W]*?)</span>", "<monoblue>$1</monoblue>");
    content = content.replaceAll("<span style=\"font-family:Monospace; font-size:10pt; color:#000000\">([\\w\\W]*?)</span>", "<mono>$1</mono>");
    content = content.replaceAll("<span style=\"font-family:FreeSetC,Arial; font-size:10pt; color:#0000FF\">([\\w\\W]*?)</span>", "<blue>$1</blue>");
    content = content.replaceAll("<span style=\"font-family:FreeSetC,Arial; font-size:12pt; color:#000000\">([\\w\\W]*?)</span>", "<h1>$1</h1>");
    content = content.replaceAll("<table(.*?)>([\\w\\W]*?)<h1>", "<h1>");
    content = content.replaceAll("</h1>([\\w\\W]*?)</table>", "</h1>");
    content = content.replaceAll("<br>", "<br />");
    content = content.replaceAll("<span(.*?)>", "<normal>");
    content = content.replaceAll("</span>", "</normal>");
    content = content.replaceAll("\\r", "");
    content = content.replaceAll("<body(.*?)>", "<body>");
    content = content.replaceAll("<a href=(.*?)\\.htm>", "<a href=\"$1.htm\">");

    return content;
  }

  public static String prettifyContent2(String content) {
    content = content.replaceAll("<script(.*?)>([\\w\\W]*?)</script>", "");
    content = content.replaceAll("<hr(.*?)>([\\w\\W]*?)</body>", "</body>");
    content = content.replaceAll("<!--([\\w\\W]*?)-->", "");
    content = content.replaceAll("<(m|M)(e|E)(t|T)(a|A)(.*)>", "");
    content = content.replaceAll("</title>", "</title><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\">");
    content = content.replaceAll("<span style=\"font-family:Monospace; font-size:10pt; color:#0000FF\">", "<span class=\"monoblue\">");
    content = content.replaceAll("<span style=\"font-family:Monospace; font-size:10pt; color:#000000\">", "<span class=\"mono\">");
    content = content.replaceAll("<span style=\"font-family:FreeSetC,Arial; font-size:10pt; color:#0000FF\">", "<span class=\"blue\">");
    content = content.replaceAll("<span style=\"font-family:FreeSetC,Arial; font-size:12pt; color:#000000\">", "<span class=\"h1\">");
    content = content.replaceAll("<td bgcolor=\"#99D228\" height=\"3\"></td>", "<td><div class=\"greenline\"></div></td>");
    content = content.replaceAll("<br>", "<br />");
    content = content.replaceAll("<span style=\"(.*?)10pt(.*?)\">", "<span>");
    content = content.replaceAll("\\r", "");
    content = content.replaceAll("<body(.*?)>", "<body>");
    content = content.replaceAll("<a href=(.*?)\\.htm>", "<a href=\"$1.htm\">");

    return content;
  }
}
