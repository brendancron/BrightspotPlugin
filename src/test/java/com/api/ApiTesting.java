package com.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

public class ApiTesting {

    @Test
    public void DebugCodeTest() {
        String code = ""
            + "import com.psddev.dari.db.*;\n"
            + "import com.psddev.dari.util.*;\n"
            + "public class GenerateValuesForLocalDev {\n"
            + "    public static Object main() throws Throwable {\n"
            + "        return \"hehe23\";\n"
            + "    }\n"
            + "}";

        System.out.println("Running code: " + code);
        try {

            final HttpPost httpPost = new HttpPost("http://localhost/_debug/code");
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("action", "run"));
            params.add(new BasicNameValuePair("type", "Java"));
            params.add(new BasicNameValuePair("code", code));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            httpPost.addHeader("Content-Type", " application/x-www-form-urlencoded");

            try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost)) {

                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                System.out.println(responseString);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
