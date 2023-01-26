package com.brightspot.debug;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class DebugCodeToolWindowFactory implements ToolWindowFactory {

    private final String label;

    public DebugCodeToolWindowFactory(String label) {
        this.label = label;
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        JTextPane jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        jTextPane.setText("<html>"+this.label+"</html>");
        jTextPane.setEditable(false);
        jTextPane.setBackground(null);
        jTextPane.setBorder(null);
        jTextPane.setPreferredSize(new Dimension(800, 600));

        JScrollPane jScrollPane = new JBScrollPane(jTextPane);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(jScrollPane, "", false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.setTitle("Debug Code Response");
    }

}
