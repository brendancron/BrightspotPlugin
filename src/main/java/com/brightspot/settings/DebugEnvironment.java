package com.brightspot.settings;

import java.util.Objects;

public class DebugEnvironment {

    private String name;
    private String url;
    private String username;
    private String creds;

    public DebugEnvironment() {
        this("Local", "http://localhost/_debug/code", "debug", "");
    }

    public DebugEnvironment(String name, String url, String username, String creds) {
        this.name = name;
        this.url = url;
        this.username = username;
        this.creds = creds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreds() {
        return creds;
    }

    public void setCreds(String creds) {
        this.creds = creds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DebugEnvironment that = (DebugEnvironment) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(url, that.url)) return false;
        if (!Objects.equals(username, that.username)) return false;
        return Objects.equals(creds, that.creds);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (creds != null ? creds.hashCode() : 0);
        return result;
    }
}
