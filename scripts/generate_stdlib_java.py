#!/usr/bin/python2.5 -tt

# idea-parser3: the most advanced parser3 ide.
#
# Copyright 2011 Valeriy Yatsko <dwr@design.ru>
# Copyright 2011 ArtLebedev Studio
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

STDLIB_DIR = '../main/src/ru/artlebedev/idea/plugins/parser/lang/stdlib'
STDLIB_JAVA_CLASS_OUTPUT = '../main/src/ru/artlebedev/idea/plugins/parser/lang/stdlib/ParserStdLib.java'

import os
import re

def file_get_contents(filename):
  with open(filename) as f:
    return f.read()

def main():
  java_class_content  = "package ru.artlebedev.idea.plugins.parser.lang.stdlib;\n"
  java_class_content += "\n"
  java_class_content += "/**\n"
  java_class_content += " * idea-parser3: the most advanced parser3 ide.\n"
  java_class_content += " * <p/>\n"
  java_class_content += " * Copyright 2011 Valeriy Yatsko <dwr@design.ru>\n"
  java_class_content += " * Copyright 2011 ArtLebedev Studio\n"
  java_class_content += " * <p/>\n"
  java_class_content += " * Licensed under the Apache License, Version 2.0 (the \"License\");\n"
  java_class_content += " * you may not use this file except in compliance with the License.\n"
  java_class_content += " * You may obtain a copy of the License at\n"
  java_class_content += " * <p/>\n"
  java_class_content += " * http://www.apache.org/licenses/LICENSE-2.0\n"
  java_class_content += " * <p/>\n"
  java_class_content += " * Unless required by applicable law or agreed to in writing, software\n"
  java_class_content += " * distributed under the License is distributed on an \"AS IS\" BASIS,\n"
  java_class_content += " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
  java_class_content += " * See the License for the specific language governing permissions and\n"
  java_class_content += " * limitations under the License.\n"
  java_class_content += " */\n"
  java_class_content += "\n"
  java_class_content += "public class ParserStdLib {\n"
  
  dirList = os.listdir(STDLIB_DIR)
  for fname in dirList:
    if re.search("(.*)\.p", fname):
      class_name = re.sub('\.p', '', fname)
      current_class_content = "  public static final String parser3_" + class_name + " = \"" + re.sub("\n", "\\\\n", file_get_contents(STDLIB_DIR + '/' + fname)) + "\";\n"
      
      java_class_content += current_class_content
  
  java_class_content += "}"
  
  java_class = open(STDLIB_JAVA_CLASS_OUTPUT, 'w')
  java_class.write(java_class_content)
  java_class.close()

if __name__ == '__main__':
  main()
