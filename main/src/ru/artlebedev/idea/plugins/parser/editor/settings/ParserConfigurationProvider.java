package ru.artlebedev.idea.plugins.parser.editor.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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

@State(
        name = "ParserConfigurationProvider",
        storages = {
                @Storage("parser3-settings.xml")
        }
)
public class ParserConfigurationProvider implements PersistentStateComponent<ParserConfigurationProvider.State> {
    public static final Logger LOG = Logger.getInstance(ParserConfigurationProvider.class);
    public static Project myProject;
    private State myState = new State();

    public static ParserConfigurationProvider getInstance(Project project) {
        if (
                myProject == null
                && project != null
        ) {
            myProject = project;
        }
        return ServiceManager.getService(project, ParserConfigurationProvider.class);
    }

    public List<String> getClassPaths() {
        return myState.classPaths;
    }

    public void setClassPaths(List<String> classPaths) {
        myState.classPaths = classPaths;
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(State state) {
        myState.classPaths = state.classPaths;
    }

    public static class State {
        public List<String> classPaths = new ArrayList<String>();
    }
}