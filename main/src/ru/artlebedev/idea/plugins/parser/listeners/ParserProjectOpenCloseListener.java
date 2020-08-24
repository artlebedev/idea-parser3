package ru.artlebedev.idea.plugins.parser.listeners;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.settings.ParserConfigurationProvider;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexander Pozdeev</a>
 * Copyright 2020 ArtLebedev Studio
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
 * Listener to detect project open and close.
 */
public class ParserProjectOpenCloseListener implements ProjectManagerListener {
    /**
     * Invoked on project open.
     *
     * @param project opening project
     */
    @Override
    public void projectOpened(@NotNull Project project) {
        ParserConfigurationProvider configurationProvider = ParserConfigurationProvider.getInstance(project);
        ParserFileIndex parserFileIndex = project.getService(ParserFileIndex.class);
        parserFileIndex.projectOpened(project);
        //System.out.println("project opened");
    }

    /**
     * Invoked on project close.
     *
     * @param project closing project
     */
    @Override
    public void projectClosed(@NotNull Project project) {
        ParserFileIndex parserFileIndex = project.getService(ParserFileIndex.class);
        parserFileIndex.projectClosed();
        //System.out.println("project closed");
    }
}