package ru.artlebedev.idea.plugins.parser.documentation.util;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 13.04.11
 * Time: 0:40
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
}
