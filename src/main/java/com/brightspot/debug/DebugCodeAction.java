package com.brightspot.debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.brightspot.settings.DebugEnvironment;
import com.brightspot.settings.DebugEnvironmentSettingsState;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import icons.BrightspotIcons;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

public class DebugCodeAction extends AnAction {

    private final String environmentName;

    public DebugCodeAction(String environmentName) {
        super(environmentName);
        this.environmentName = environmentName;
        getTemplatePresentation().setIcon(BrightspotIcons.BrightspotOIcon);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        e.getPresentation().setEnabledAndVisible(true);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {

        PsiFile psiFile = e.getRequiredData(CommonDataKeys.PSI_FILE);
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        String code = psiFile.getText();

        DebugEnvironment environment = null;
        for (DebugEnvironment env : DebugEnvironmentSettingsState.getInstance().getSavedFields()) {
            if (env.getName().equals(environmentName)) {
                environment = env;
            }
        }

        if(!code.isEmpty() && environment != null && project != null) {
            sendRequest(environment, code, project);
        }

    }

    public static void sendRequest(DebugEnvironment environment, String code, @NotNull Project project) {

        String url = environment.getUrl();
        String username = environment.getUsername();
        String creds = environment.getCreds();

        try {

            final HttpPost httpPost = new HttpPost(url);
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("action", "run"));
            params.add(new BasicNameValuePair("type", "Java"));
            params.add(new BasicNameValuePair("code", code));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            httpPost.addHeader("Content-Type", " application/x-www-form-urlencoded");

            if(!creds.isEmpty()) {
                httpPost.addHeader("Authorization", "Basic " +
                    Base64.getEncoder().encodeToString((username + ":" + creds).getBytes()));
            }

            try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost)) {

                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                new DebugCodeToolWindowFactory(responseString).createToolWindowContent(project, null);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




}
