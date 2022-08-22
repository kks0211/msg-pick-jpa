package com.msgpick.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AppConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Value("${file.upload-path}")
    private String FILE_UPLOAD_PATH;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        File dir = new File(FILE_UPLOAD_PATH);
        if (!dir.exists())
            dir.mkdirs();
        factory.setDocumentRoot(dir);
    }

}
