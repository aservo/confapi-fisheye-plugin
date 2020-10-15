package de.aservo.confapi.fisheye.rest;

import com.sun.jersey.spi.container.ResourceFilters;
import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import de.aservo.confapi.commons.rest.api.MailServerSmtpResource;
import de.aservo.confapi.commons.service.api.MailServerService;
import de.aservo.confapi.fisheye.filter.SysAdminOnlyResourceFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Named
@Path(ConfAPI.MAIL_SERVER)
@ResourceFilters(SysAdminOnlyResourceFilter.class)
public class MailServerResourceImpl implements MailServerSmtpResource {

    private final MailServerService mailServerService;

    @Inject
    public MailServerResourceImpl(
            final MailServerService mailServerService) {
        this.mailServerService = mailServerService;
    }

    @Override
    public Response getMailServerSmtp() {
        final MailServerSmtpBean smtpBean = mailServerService.getMailServerSmtp();

        if (smtpBean == null) {
            return Response.noContent().build();
        }

        return Response.ok(smtpBean).build();
    }

    @Override
    public Response setMailServerSmtp(MailServerSmtpBean bean) {
        final MailServerSmtpBean updatedSmtpBean = mailServerService.setMailServerSmtp(bean);
        return Response.ok(updatedSmtpBean).build();
    }
}

