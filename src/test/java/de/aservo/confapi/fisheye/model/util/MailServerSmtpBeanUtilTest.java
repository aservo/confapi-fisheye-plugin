package de.aservo.confapi.fisheye.model.util;

import com.cenqua.fisheye.config1.SmtpType;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;

import static de.aservo.confapi.fisheye.model.util.MailServerSmtpBeanUtil.toMailServerSmtpBean;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class MailServerSmtpBeanUtilTest {

    @Mock
    private SmtpType dummySmtp;

    @Test
    public void testToMailServerSmtpBean() {

        doReturn("localhost").when(dummySmtp).getHost();
        doReturn("from@test.de").when(dummySmtp).getFrom();
        doReturn(BigInteger.valueOf(123)).when(dummySmtp).getPort();
        doReturn("user").when(dummySmtp).getUsername();
        doReturn(BigInteger.valueOf(1000)).when(dummySmtp).getTimeout();
        doReturn(true).when(dummySmtp).getStarttls();

        final MailServerSmtpBean mailServerSmtpBean = toMailServerSmtpBean(dummySmtp);

        assertNotNull(mailServerSmtpBean);
        assertEquals(dummySmtp.getHost(), mailServerSmtpBean.getHost());
        assertEquals(dummySmtp.getFrom(), mailServerSmtpBean.getFrom());
        assertEquals(dummySmtp.getPort(), BigInteger.valueOf(mailServerSmtpBean.getPort()));
        assertEquals(dummySmtp.getUsername(), mailServerSmtpBean.getUsername());
        assertEquals(dummySmtp.getStarttls(), mailServerSmtpBean.getUseTls());
    }

    @Test
    public void testToMailServerSmtpBeanWithNull() {
        assertNull(toMailServerSmtpBean(null));
    }
}
