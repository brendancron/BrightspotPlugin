package com.brightspot.debug;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;

public class DebugCodeDialogWrapper extends DialogWrapper {

    private final String label;

    public DebugCodeDialogWrapper(String label) {
        super(true);
        this.label = label;
        setTitle("Debug Code Response");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

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
        dialogPanel.add(jScrollPane, BorderLayout.CENTER);

        return dialogPanel;
    }

}
