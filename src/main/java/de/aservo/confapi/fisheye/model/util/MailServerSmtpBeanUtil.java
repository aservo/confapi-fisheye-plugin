package de.aservo.confapi.fisheye.model.util;

import com.cenqua.fisheye.config1.SmtpType;
import de.aservo.confapi.commons.model.MailServerSmtpBean;

import javax.annotation.Nullable;

public class MailServerSmtpBeanUtil {

    @Nullable
    public static MailServerSmtpBean toMailServerSmtpBean(
            @Nullable final SmtpType smtpMailServer) {

        if (smtpMailServer == null) {
            return null;
        }

        final MailServerSmtpBean mailServerSmtpBean = new MailServerSmtpBean();
        mailServerSmtpBean.setName("SMTP mail server");
        mailServerSmtpBean.setFrom(smtpMailServer.getFrom());
        mailServerSmtpBean.setHost(smtpMailServer.getHost());
        mailServerSmtpBean.setPort(smtpMailServer.getPort() == null ? null : String.valueOf(smtpMailServer.getPort()));
        mailServerSmtpBean.setUseTls(smtpMailServer.getStarttls());
        mailServerSmtpBean.setTimeout(smtpMailServer.getTimeout() == null ? null : smtpMailServer.getTimeout().longValue());
        mailServerSmtpBean.setUsername(smtpMailServer.getUsername());
        return mailServerSmtpBean;
    }

    private MailServerSmtpBeanUtil() {
    }

}
