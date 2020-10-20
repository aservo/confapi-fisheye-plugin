package de.aservo.confapi.fisheye.service;

import com.cenqua.fisheye.config1.ConfigDocument;
import com.cenqua.fisheye.config1.SmtpType;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import de.aservo.confapi.commons.service.api.MailServerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MailServerServiceTest {

    @Mock
    private ConfigDocument.Config config;

    private MailServerService mailServerService;

    @Before
    public void setup() {
        mailServerService = new MailServerServiceImpl(config);
    }

    @Test
    public void testGetMailServerSmtp() {
        final SmtpType dummySmtp = createSmtpType();

        doReturn(true).when(config).isSetSmtp();
        doReturn(dummySmtp).when(config).getSmtp();

        assertNotNull(mailServerService.getMailServerSmtp());
    }

    @Test
    public void testSetMailServerSmtp() {
        final SmtpType dummySmtp = createSmtpType();

        doReturn(true).when(config).isSetSmtp();
        doReturn(dummySmtp).when(config).getSmtp();

        assertNotNull(mailServerService.setMailServerSmtp(MailServerSmtpBean.EXAMPLE_2));
    }

    private SmtpType createSmtpType() {
        final SmtpType dummySmtp = mock(SmtpType.class);
        doReturn("localhost").when(dummySmtp).getHost();
        doReturn("from@test.de").when(dummySmtp).getFrom();
        doReturn(BigInteger.valueOf(123)).when(dummySmtp).getPort();
        doReturn("user").when(dummySmtp).getUsername();
        doReturn(BigInteger.valueOf(1000)).when(dummySmtp).getTimeout();
        doReturn(true).when(dummySmtp).getStarttls();
        return dummySmtp;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetMailServerPop() {
        mailServerService.getMailServerPop();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetMailServerPop() {
        mailServerService.setMailServerPop(null);
    }
}
