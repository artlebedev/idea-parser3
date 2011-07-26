package ru.artlebedev.idea.plugins.parser.documentation;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006-2011 ArtLebedev Studio
 * <p/>
 * Based on code from www.lucenetutorial.com, which had no copyright header
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
/**
 * This terminal application creates an Apache Lucene index in a folder and adds files into this index
 * based on the input of the user.
 */
public class ParserDocumentationFileIndexer {
  private IndexWriter _writer;
  private ArrayList<File> _queue
    = new ArrayList<File>();

  /**
   * Left from original TextFileIndexer,
   * shouldn't be used in idea-parser3 bundle
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    System.out.println("Enter the path where the index will be created: ");

    BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
    String s = br.readLine();

    ParserDocumentationFileIndexer indexer = null;
    try {
      indexer = new ParserDocumentationFileIndexer(s);
    } catch (Exception ex) {
      System.out.println("Cannot create index..." + ex.getMessage());
      System.exit(-1);
    }

    // ===================================================
    // read input from user until he enters q for quit
    // ===================================================
    while (!s.equalsIgnoreCase("q")) {
      try {
        System.out.println("Enter the file or folder name to add into the index (q=quit):");
        System.out.println("[Acceptable file types: .xml, .html, .html, .txt]");
        s = br.readLine();
        if (s.equalsIgnoreCase("q")) {
          break;
        }

        //try to add file into the index
        indexer.indexFileOrDirectory(s);
      } catch (Exception e) {
        System.out.println("Error indexing " + s + " : " + e.getMessage());
      }
    }

    // ===================================================
    // after adding, we always have to call the
    // closeIndex, otherwise the index is not created
    // ===================================================
    indexer.closeIndex();
  }

  /**
   * Constructor
   * @param indexDir the name of the folder in which the index should be created
   * @throws java.io.IOException
   */
  ParserDocumentationFileIndexer(String indexDir) throws IOException {
    // the boolean true parameter means to create a new index everytime,
    // potentially overwriting any existing files there.
    FSDirectory dir = FSDirectory.open(new File(indexDir));
    _writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_CURRENT), true, IndexWriter.MaxFieldLength.LIMITED);
  }

  /**
   * Indexes a file or directory
   * @param fileName the name of a text file or a folder we wish to add to the index
   * @throws java.io.IOException
   */
  public void indexFileOrDirectory(String fileName) throws IOException {
    // ===================================================
    // gets the list of files in a folder (if user has submitted
    // the name of a folder) or gets a single file name (is user
    // has submitted only the file name)
    // ===================================================
    listFiles(new File(fileName));

    int originalNumDocs = _writer.numDocs();
    for (File f : _queue) {
      FileReader fr = null;
      try {
        Document doc = new Document();

        // ===================================================
        // add contents of file
        // ===================================================
        fr = new FileReader(f);
        doc.add(new Field("contents", fr));

        // ===================================================
        // adding second field which contains the path of the file
        // ===================================================
        doc.add(new Field("path", fileName,
                Field.Store.YES,
                Field.Index.NOT_ANALYZED));

        _writer.addDocument(doc);
        System.out.println("Added: " + f);
      } catch (Exception e) {
        System.out.println("Could not add: " + f);
      } finally {
        fr.close();
      }
    }

    int newNumDocs = _writer.numDocs();
    System.out.println("");
    System.out.println("************************");
    System.out.println((newNumDocs - originalNumDocs) + " documents added.");
    System.out.println("************************");

    _queue.clear();
  }

  private void listFiles(File file) {
    if (!file.exists()) {
      System.out.println(file + " does not exist.");
    }
    if (file.isDirectory()) {
      for (File f : file.listFiles()) {
        listFiles(f);
      }
    } else {
      String filename = file.getName().toLowerCase();
      // ===================================================
      // Only index text files
      // ===================================================
      if (filename.endsWith(".htm") || filename.endsWith(".html") ||
              filename.endsWith(".xml") || filename.endsWith(".txt")) {
        _queue.add(file);
      } else {
        System.out.println("Skipped " + filename);
      }
    }
  }

  /**
   * Close the index.
   * @throws java.io.IOException
   */
  public void closeIndex() throws IOException {
    _writer.optimize();
    _writer.close();
  }
}