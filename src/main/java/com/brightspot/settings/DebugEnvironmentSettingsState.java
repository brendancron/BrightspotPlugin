package com.brightspot.settings;

import java.util.List;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
    name = "com.brightspot.settings.AppSettingsState",
    storages = @Storage("SdkSettingsPlugin.xml")
)
public class DebugEnvironmentSettingsState implements PersistentStateComponent<DebugEnvironmentSettingsState> {

    public static DebugEnvironmentSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(DebugEnvironmentSettingsState.class);
    }

    @Nullable
    @Override
    public DebugEnvironmentSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull DebugEnvironmentSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    private List<DebugEnvironment> savedFields;

    public List<DebugEnvironment> getSavedFields() {
        return savedFields;
    }

    public void setSavedFields(List<DebugEnvironment> fields) {
        savedFields = fields;
    }

}
