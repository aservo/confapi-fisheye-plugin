package de.aservo.confapi.fisheye.rest;

import com.atlassian.plugins.rest.common.security.jersey.SysadminOnlyResourceFilter;
import com.sun.jersey.spi.container.ResourceFilters;
import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.fisheye.model.SettingsHttpBean;
import de.aservo.confapi.fisheye.rest.api.SettingsHttpResource;
import de.aservo.confapi.fisheye.service.api.SettingsHttpService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Named
@Path(ConfAPI.SETTINGS + "/" + ConfAPI.HTTP)
@ResourceFilters(SysadminOnlyResourceFilter.class)
public class SettingsHttpResourceImpl implements SettingsHttpResource {

    private final SettingsHttpService settingsService;

    @Inject
    public SettingsHttpResourceImpl(
            final SettingsHttpService settingsService) {

        this.settingsService = settingsService;
    }

    @Override
    public Response getSettings() {
        return Response.ok(settingsService.getSettings()).build();
    }

    @Override
    public Response setSettings(
            SettingsHttpBean bean) {
        return Response.ok(settingsService.setSettings(bean)).build();
    }
}
