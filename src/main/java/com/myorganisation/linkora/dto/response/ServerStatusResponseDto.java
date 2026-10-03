package com.myorganisation.linkora.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ServerStatusResponseDto {

    private String status;
    private String message;

    private ServerInfo server;
    private OperatingSystem os;
    private RuntimeInfo runtime;
    private ServerTime time;
    private ResourceInfo resources;

    @Data
    public static class ServerInfo {
        private String hostname;
        private String ipAddress;
        private String architecture;
    }

    @Data
    public static class OperatingSystem {
        private String name;
        private String version;
        private String architecture;
    }

    @Data
    public static class RuntimeInfo {
        private String javaVersion;
        private String javaVendor;
        private String jvm;
    }

    @Data
    public static class ServerTime {
        private OffsetDateTime dateTime;
        private String timezone;
        private String zoneOffset;
    }

    @Data
    public static class ResourceInfo {
        private String uptime;
        private long availableProcessors;
        private String memoryUsed;
        private String memoryAvailable;
        private String memoryTotal;
    }
}

