package de.aservo.confapi.fisheye.service;

import com.cenqua.fisheye.config1.HttpServerType;
import com.cenqua.fisheye.config1.WebServerType;
import de.aservo.confapi.fisheye.model.SettingsHttpBean;
import de.aservo.confapi.fisheye.service.api.SettingsHttpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SettingsHttpServiceTest {

    @Mock
    private WebServerType server;

    @Mock
    private HttpServerType http;

    private SettingsHttpService settingsHttpService;

    @Before
    public void setup() {
        settingsHttpService = new SettingsHttpServiceImpl(server, http);
    }

    @Test
    public void testGetSettingsHttp() {
        doReturn("fisheye").when(server).getContext();

        doReturn("localhost").when(http).getProxyHost();
        doReturn(BigInteger.ONE).when(http).getProxyPort();
        doReturn("https").when(http).getProxyScheme();
        doReturn("1234").when(http).getBind();

        assertNotNull(settingsHttpService.getSettings());
    }

    @Test
    public void testSetSettingsHttp() {
        doReturn("fisheye").when(server).getContext();

        doReturn("localhost").when(http).getProxyHost();
        doReturn(BigInteger.ONE).when(http).getProxyPort();
        doReturn("https").when(http).getProxyScheme();
        doReturn("1234").when(http).getBind();

        assertNotNull(settingsHttpService.setSettings(SettingsHttpBean.EXAMPLE_1));
    }
}
