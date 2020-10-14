package de.aservo.confapi.fisheye.filter;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AuthenticationRequiredException;
import com.atlassian.plugins.rest.common.security.AuthorisationException;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ext.Provider;

@Provider
@Named
public class SysAdminOnlyResourceFilter implements ResourceFilter, ContainerRequestFilter {


    private final UserManager userManager;

    /**
     * Instantiates a new Sysadmin only resource filter.
     *
     * @param userManager           the user manager
     */
    @Inject
    public SysAdminOnlyResourceFilter(
            @ComponentImport final UserManager userManager) {
        this.userManager = userManager;
    }

    public ContainerRequest filter(
            final ContainerRequest containerRequest) {

        final String remoteUsername = userManager.getRemoteUsername();
        final UserProfile loggedInUser = userManager.getUserProfile(remoteUsername);

        if (loggedInUser == null) {
            throw new AuthenticationRequiredException();
        } else if (!userManager.isSystemAdmin(loggedInUser.getUsername())) {
            throw new AuthorisationException("Client must be authenticated as an system administrator to access this resource.");
        }

        return containerRequest;
    }

    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    public ContainerResponseFilter getResponseFilter() {
        return null;
    }
}
