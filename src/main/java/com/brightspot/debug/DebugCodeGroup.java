package com.brightspot.debug;

import java.util.List;

import com.brightspot.settings.DebugEnvironment;
import com.brightspot.settings.DebugEnvironmentSettingsState;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DebugCodeGroup extends ActionGroup {

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        List<DebugEnvironment> environments = DebugEnvironmentSettingsState.getInstance().getSavedFields();
        AnAction[] actions = new AnAction[environments.size()];
        for (int i = 0; i < actions.length; i++) {
            actions[i] = new DebugCodeAction(environments.get(i).getName());
        }
        return actions;
    }
}
