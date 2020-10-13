package de.aservo.confapi.fisheye.rest;

import com.sun.jersey.spi.container.ResourceFilters;
import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.rest.AbstractDirectoriesResourceImpl;
import de.aservo.confapi.commons.service.api.DirectoriesService;
import de.aservo.confapi.fisheye.filter.SysAdminOnlyResourceFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

@Path(ConfAPI.DIRECTORIES)
@ResourceFilters(SysAdminOnlyResourceFilter.class)
@Named
public class DirectoriesResourceImpl extends AbstractDirectoriesResourceImpl {

    @Inject
    public DirectoriesResourceImpl(DirectoriesService directoryService) {
        super(directoryService);
    }

    // Completely inhering the implementation of AbstractDirectoriesResourceImpl

}
