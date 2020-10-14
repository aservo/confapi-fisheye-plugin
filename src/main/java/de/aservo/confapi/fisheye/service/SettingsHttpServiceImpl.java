package de.aservo.confapi.fisheye.service;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.cenqua.fisheye.AppConfig;
import com.cenqua.fisheye.config1.HttpServerType;
import com.cenqua.fisheye.config1.WebServerType;
import de.aservo.confapi.commons.service.api.ApplicationLinksService;
import de.aservo.confapi.fisheye.model.SettingsHttpBean;
import de.aservo.confapi.fisheye.service.api.SettingsHttpService;

import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Named
@ExportAsService(ApplicationLinksService.class)
public class SettingsHttpServiceImpl implements SettingsHttpService {

    private final WebServerType server;
    private final HttpServerType http;

    public SettingsHttpServiceImpl() {
        server = AppConfig.getsConfig().getConfig().getWebServer();
        http = server.getHttp();
    }

    public SettingsHttpServiceImpl(
            @NotNull WebServerType server,
            @NotNull HttpServerType http) {
        this.server = server;
        this.http = http;
    }

    @Override
    public SettingsHttpBean getSettings() {

        SettingsHttpBean settingsBean = new SettingsHttpBean();
        settingsBean.setWebContext(server.getContext());
        settingsBean.setBindAddress(http.getBind());
        settingsBean.setProxyScheme(http.getProxyScheme());
        settingsBean.setProxyHost(http.getProxyHost());
        settingsBean.setProxyPort(http.getProxyPort() == null ? null : Integer.parseInt(http.getProxyPort().toString()));

        return settingsBean;
    }

    @Override
    public SettingsHttpBean setSettings(
            SettingsHttpBean settings) {

        server.setContext(settings.getWebContext());
        http.setBind(settings.getBindAddress());
        http.setProxyScheme(settings.getProxyScheme());
        http.setProxyHost(settings.getProxyHost());
        http.setProxyPort(BigInteger.valueOf(settings.getProxyPort()));

        return getSettings();
    }
}
