package de.aservo.confapi.fisheye.rest;

import de.aservo.confapi.fisheye.model.SettingsHttpBean;
import de.aservo.confapi.fisheye.rest.api.SettingsHttpResource;
import de.aservo.confapi.fisheye.service.api.SettingsHttpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SettingsHttpResourceTest {

    @Mock
    private SettingsHttpService settingsHttpService;

    private SettingsHttpResource settingsHttpResource;

    @Before
    public void setup() {
        settingsHttpResource = new SettingsHttpResourceImpl(settingsHttpService);
    }

    @Test
    public void testGetSettingsHttp() {
        final SettingsHttpBean settingsHttpBean = SettingsHttpBean.EXAMPLE_1;
        doReturn(settingsHttpBean).when(settingsHttpService).getSettings();

        final Response response = settingsHttpResource.getSettings();
        assertEquals(200, response.getStatus());

        final SettingsHttpBean responseBean = (SettingsHttpBean) response.getEntity();
        assertEquals(settingsHttpBean, responseBean);
    }

    @Test
    public void testGStSettingsHttp() {
        final SettingsHttpBean settingsHttpBean = SettingsHttpBean.EXAMPLE_1;
        doReturn(settingsHttpBean).when(settingsHttpService).setSettings(settingsHttpBean);

        final Response response = settingsHttpResource.setSettings(settingsHttpBean);
        assertEquals(200, response.getStatus());

        final SettingsHttpBean responseBean = (SettingsHttpBean) response.getEntity();
        assertEquals(settingsHttpBean, responseBean);
    }

}
