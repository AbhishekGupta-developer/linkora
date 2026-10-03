package com.myorganisation.linkora.service;

import com.myorganisation.linkora.dto.response.ServerStatusResponseDto;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.time.Duration;
import java.time.ZonedDateTime;

@Service
public class ServerStatusServiceImpl implements ServerStatusService{

    private final OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();

    private final MemoryMXBean memory = ManagementFactory.getMemoryMXBean();

    @Override
    public ServerStatusResponseDto getServerStatus() {
        ServerStatusResponseDto response = new ServerStatusResponseDto();
        response.setStatus("UP");
        response.setMessage("Server is healthy and running");
        response.setServer(buildServerInfo());
        response.setOs(buildOperatingSystemInfo());
        response.setRuntime(buildRuntimeInfo());
        response.setTime(buildServerTime());
        response.setResources(buildResourceInfo());

        return response;
    }

    private ServerStatusResponseDto.ServerInfo buildServerInfo() {
        ServerStatusResponseDto.ServerInfo info = new ServerStatusResponseDto.ServerInfo();

        try {
            InetAddress localHost = InetAddress.getLocalHost();

            info.setHostname(localHost.getHostName());
            info.setIpAddress(localHost.getHostAddress());

        } catch (Exception e) {
            info.setHostname("unknown");
            info.setIpAddress("unknown");
        }

        info.setArchitecture(System.getProperty("os.arch"));

        return info;
    }

    private ServerStatusResponseDto.OperatingSystem buildOperatingSystemInfo() {

        ServerStatusResponseDto.OperatingSystem info = new ServerStatusResponseDto.OperatingSystem();

        info.setName(System.getProperty("os.name"));
        info.setVersion(System.getProperty("os.version"));
        info.setArchitecture(System.getProperty("os.arch"));

        return info;
    }

    private ServerStatusResponseDto.RuntimeInfo buildRuntimeInfo() {

        ServerStatusResponseDto.RuntimeInfo info = new ServerStatusResponseDto.RuntimeInfo();

        info.setJavaVersion(System.getProperty("java.version"));
        info.setJavaVendor(System.getProperty("java.vendor"));
        info.setJvm(System.getProperty("java.vm.name"));

        return info;
    }

    private ServerStatusResponseDto.ServerTime buildServerTime() {
        ServerStatusResponseDto.ServerTime info = new ServerStatusResponseDto.ServerTime();
        ZonedDateTime now = ZonedDateTime.now();
        info.setDateTime(now.toOffsetDateTime());
        info.setTimezone(now.getZone().getId());
        info.setZoneOffset(now.getOffset().toString());

        return info;
    }

    private ServerStatusResponseDto.ResourceInfo buildResourceInfo() {
        ServerStatusResponseDto.ResourceInfo info = new ServerStatusResponseDto.ResourceInfo();

        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        info.setAvailableProcessors(
                runtime.availableProcessors()
        );

        info.setMemoryUsed(formatBytes(usedMemory));
        info.setMemoryAvailable(formatBytes(freeMemory));
        info.setMemoryTotal(formatBytes(totalMemory));

        info.setUptime(formatUptime(
                ManagementFactory.getRuntimeMXBean().getUptime()
        ));

        return info;
    }

    private String formatUptime(long uptimeMillis) {
        Duration duration = Duration.ofMillis(uptimeMillis);

        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format(
                "%dd %02dh %02dm %02ds",
                days,
                hours,
                minutes,
                seconds
        );
    }

    private String formatBytes(long bytes) {
        double gb = bytes / (1024.0 * 1024.0 * 1024.0);
        return String.format("%.2f GB", gb);
    }
}
