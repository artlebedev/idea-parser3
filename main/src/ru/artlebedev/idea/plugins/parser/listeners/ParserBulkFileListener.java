package ru.artlebedev.idea.plugins.parser.listeners;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectLocator;
import com.intellij.openapi.vfs.*;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.openapi.vfs.newvfs.events.VFileCreateEvent;
import com.intellij.openapi.vfs.newvfs.events.VFileDeleteEvent;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;

import java.util.List;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexandr Pozdeev</a>
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

public class ParserBulkFileListener implements BulkFileListener {
    public void before(@NotNull List<? extends VFileEvent> events) {
        VFileEvent event = events.get(0);
        if (event instanceof VFileDeleteEvent) {
            VirtualFile file = event.getFile();
            Project project = ProjectLocator.getInstance().guessProjectForFile(file);
            try {
                project.getService(ParserFileIndex.class).beforeFileDeletion(project, file);
            } catch(Exception ignored) {
            }
        }
    }

    public void after(@NotNull List<? extends VFileEvent> events) {
        VFileEvent event = events.get(0);
        if (event instanceof VFileCreateEvent) {
            VirtualFile file = event.getFile();
            Project project = ProjectLocator.getInstance().guessProjectForFile(file);
            try {
                project.getService(ParserFileIndex.class).fileCreated(project, file);
            } catch(Exception ignored) {
            }
        }
    }
}
