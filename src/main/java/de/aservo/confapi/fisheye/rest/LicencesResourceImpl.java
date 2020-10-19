package de.aservo.confapi.fisheye.rest;

import com.sun.jersey.spi.container.ResourceFilters;
import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.rest.AbstractLicensesResourceImpl;
import de.aservo.confapi.commons.service.api.LicensesService;
import de.aservo.confapi.fisheye.filter.SysAdminOnlyResourceFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

@Named
@Path(ConfAPI.LICENSES)
@ResourceFilters(SysAdminOnlyResourceFilter.class)
public class LicencesResourceImpl extends AbstractLicensesResourceImpl {

    @Inject
    public LicencesResourceImpl(LicensesService licensesService) {
        super(licensesService);
    }

    // Completely inhering the implementation of AbstractLicensesResourceImpl
}
