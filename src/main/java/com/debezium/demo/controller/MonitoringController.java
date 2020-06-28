package com.debezium.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/_monitoring")
public class MonitoringController {

    @Value("${spring.application.name}")
    private String application;

    @Value("${version:RELEASE}")
    private String version;

    @Value("${hostname:localhost}")
    private String hostname;

    @Value("${spring.profiles.active}")
    private String profile;

    private Info info;

    @GetMapping("/info")
    public Info info() {
        if (info == null) {
            info = new Info(application, version, hostname, profile);
        }
        info.setDate(new Date());
        return info;
    }

    @GetMapping("/live")
    public String live() {
        return "OK";
    }

    @GetMapping("/ready")
    public String ready() {
        return "OK";
    }

    private static final class Info {

        private Info(String application, String version, String hostname, String profile) {
            this.application = application;
            this.version = version;
            this.hostname = hostname;
            this.profile = profile;
        }

        private String application;
        private String version;
        private String hostname;
        private String profile;
        private Date date;

        public String getApplication() {
            return application;
        }

        public String getVersion() {
            return version;
        }

        public String getHostname() {
            return hostname;
        }

        public String getProfile() {
            return profile;
        }

        public Long getDate() {
            return date.getTime();
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}

