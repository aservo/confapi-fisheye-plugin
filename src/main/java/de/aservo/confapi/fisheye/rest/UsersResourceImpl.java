package de.aservo.confapi.fisheye.rest;

import com.sun.jersey.spi.container.ResourceFilters;
import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.rest.AbstractUsersResourceImpl;
import de.aservo.confapi.commons.service.api.UsersService;
import de.aservo.confapi.fisheye.filter.SysAdminOnlyResourceFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

@Named
@Path(ConfAPI.USERS)
@ResourceFilters(SysAdminOnlyResourceFilter.class)
public class UsersResourceImpl extends AbstractUsersResourceImpl {

    @Inject
    public UsersResourceImpl(UsersService userService) {
        super(userService);
    }

    // Completely inhering the implementation of AbstractUserResourceImpl

}
