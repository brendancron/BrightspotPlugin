package com.brightspot.settings;

import javax.swing.JComponent;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

/**
 * Provides controller functionality for application settings.
 */
public class DebugEnvironmentSettingsConfigurable implements Configurable {

    private DebugEnvironmentSettingsComponent debugEnvironmentSettingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "SDK: Application Settings Example";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (debugEnvironmentSettingsComponent == null) {
            debugEnvironmentSettingsComponent = new DebugEnvironmentSettingsComponent();
        }
        return debugEnvironmentSettingsComponent.getComponent();
    }

    @Override
    public boolean isModified() {
        return debugEnvironmentSettingsComponent.isModified();
    }

    @Override
    public void apply() {
        debugEnvironmentSettingsComponent.apply();
    }

    @Override
    public void reset() {
        debugEnvironmentSettingsComponent.reset();
    }

    @Override
    public void disposeUIResources() {
        debugEnvironmentSettingsComponent = null;
    }

}
