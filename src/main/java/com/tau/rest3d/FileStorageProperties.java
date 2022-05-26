package com.tau.rest3d;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "/home/agirnob/Documents/files")
public class FileStorageProperties {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
