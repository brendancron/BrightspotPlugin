package com.brightspot.settings;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.intellij.util.Function;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.NotNull;

public class DebugEnvironmentSettingsComponent {

    private final JPanel panel;
    private final TableModelEditor<DebugEnvironment> tableModelEditor;

    private static final ColumnInfo[] COLUMNS = {
        new TableModelEditor.EditableColumnInfo<DebugEnvironment, String>("Name") {
            @Override
            public String valueOf(DebugEnvironment item) {
                return item.getName();
            }

            @Override
            public void setValue(DebugEnvironment item, String value) {
                item.setName(value);
            }
        },
        new TableModelEditor.EditableColumnInfo<DebugEnvironment, String>("Url") {
            @Override
            public String valueOf(DebugEnvironment item) {
                return item.getUrl();
            }

            @Override
            public void setValue(DebugEnvironment item, String value) {
                item.setUrl(value);
            }
        },
        new TableModelEditor.EditableColumnInfo<DebugEnvironment, String>("Username") {
            @Override
            public String valueOf(DebugEnvironment item) {
                return item.getUsername();
            }

            @Override
            public void setValue(DebugEnvironment item, String value) {
                item.setUsername(value);
            }
        },
        new TableModelEditor.EditableColumnInfo<DebugEnvironment, String>("Creds") {
            @Override
            public String valueOf(DebugEnvironment item) {
                return item.getCreds();
            }

            @Override
            public void setValue(DebugEnvironment item, String value) {
                item.setCreds(value);
            }
        }
    };

    public DebugEnvironmentSettingsComponent() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        TableModelEditor.DialogItemEditor<DebugEnvironment> itemEditor = new TableModelEditor.DialogItemEditor<>() {
            @NotNull
            @Override
            public Class<DebugEnvironment> getItemClass() {
                return DebugEnvironment.class;
            }

            @Override
            public DebugEnvironment clone(@NotNull DebugEnvironment item, boolean forInPlaceEditing) {
                return new DebugEnvironment(item.getName(), item.getUrl(), item.getUsername(), item.getCreds());
            }

            @Override
            public void edit(@NotNull DebugEnvironment environment,
                @NotNull Function<? super DebugEnvironment, ? extends DebugEnvironment> mutator,
                boolean isAdd) {
                mutator.fun(environment);
            }

            @Override
            public void applyEdited(@NotNull DebugEnvironment oldItem, @NotNull DebugEnvironment newItem) {
                //oldItem.setSpecificSettings(newItem.getSpecificSettings());
            }
        };
        tableModelEditor = new TableModelEditor<>(COLUMNS, itemEditor, "Add a debug environment");
        panel.add(tableModelEditor.createComponent());
    }

    public JComponent getComponent() {
        return panel;
    }

    public boolean isModified() {
        return tableModelEditor.isModified();
    }

    public void apply() {
        DebugEnvironmentSettingsState.getInstance().setSavedFields(tableModelEditor.apply());
    }

    public void reset() {
        tableModelEditor.reset(DebugEnvironmentSettingsState.getInstance().getSavedFields());
    }


}