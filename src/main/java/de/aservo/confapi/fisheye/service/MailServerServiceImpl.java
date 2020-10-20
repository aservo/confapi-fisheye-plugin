package de.aservo.confapi.fisheye.service;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.cenqua.fisheye.AppConfig;
import com.cenqua.fisheye.config1.ConfigDocument.Config;
import com.cenqua.fisheye.config1.SmtpType;
import de.aservo.confapi.commons.model.MailServerPopBean;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import de.aservo.confapi.commons.service.api.MailServerService;

import javax.inject.Named;
import java.math.BigInteger;

import static de.aservo.confapi.fisheye.model.util.MailServerSmtpBeanUtil.toMailServerSmtpBean;

@Named
@ExportAsService(MailServerService.class)
public class MailServerServiceImpl implements MailServerService {

    private final Config config;

    public MailServerServiceImpl() {
        config = AppConfig.getsConfig().getConfig();
    }

    public MailServerServiceImpl(Config config) {
        this.config = config;
    }

    @Override
    public MailServerSmtpBean getMailServerSmtp() {
        final SmtpType smtpMailServer = config.getSmtp();
        return toMailServerSmtpBean(smtpMailServer);
    }

    @Override
    public MailServerSmtpBean setMailServerSmtp(
            MailServerSmtpBean mailServerSmtpBean) {

        final SmtpType smtpMailServer = config.isSetSmtp() ? config.getSmtp() : config.addNewSmtp();
        smtpMailServer.setFrom(mailServerSmtpBean.getFrom());
        smtpMailServer.setHost(mailServerSmtpBean.getHost());
        smtpMailServer.setPort(BigInteger.valueOf(mailServerSmtpBean.getPort()));
        smtpMailServer.setStarttls(mailServerSmtpBean.getUseTls());
        smtpMailServer.setTimeout(BigInteger.valueOf(mailServerSmtpBean.getTimeout()));
        smtpMailServer.setUsername(mailServerSmtpBean.getUsername());
        smtpMailServer.setPassword(mailServerSmtpBean.getPassword());

        config.setSmtp(smtpMailServer);

        return toMailServerSmtpBean(smtpMailServer);
    }

    @Override
    public MailServerPopBean getMailServerPop() {
        throw new UnsupportedOperationException("Getting POP mail server is not implemented");
    }

    @Override
    public MailServerPopBean setMailServerPop(
            final MailServerPopBean mailServerPopBean) {
        throw new UnsupportedOperationException("Setting POP mail server is not implemented");
    }
}
