package de.aservo.confapi.fisheye.model.util;

import com.atlassian.crowd.directory.RemoteCrowdDirectory;
import com.atlassian.crowd.embedded.api.Directory;
import com.atlassian.crowd.embedded.api.DirectoryType;
import com.atlassian.crowd.model.directory.DirectoryImpl;
import de.aservo.confapi.commons.model.AbstractDirectoryBean;
import de.aservo.confapi.commons.model.DirectoryCrowdBean;
import de.aservo.confapi.commons.model.DirectoryCrowdBean.DirectoryCrowdAdvanced;
import de.aservo.confapi.commons.model.DirectoryCrowdBean.DirectoryCrowdServer;
import de.aservo.confapi.commons.model.DirectoryCrowdBean.DirectoryCrowdServer.DirectoryCrowdServerProxy;
import de.aservo.confapi.commons.model.DirectoryGenericBean;
import de.aservo.confapi.commons.model.DirectoryInternalBean;
import de.aservo.confapi.commons.model.DirectoryLdapBean;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.atlassian.crowd.directory.RemoteCrowdDirectory.*;
import static com.atlassian.crowd.directory.SynchronisableDirectoryProperties.*;
import static com.atlassian.crowd.model.directory.DirectoryImpl.ATTRIBUTE_KEY_USE_NESTED_GROUPS;
import static de.aservo.confapi.commons.util.ConversionUtil.*;
import static java.lang.Boolean.TRUE;
import static java.lang.String.valueOf;

public class DirectoryBeanUtil {

    /**
     * Build directory directory.
     *
     * @return the directory
     */
    @NotNull
    public static Directory toDirectory(
            @NotNull final DirectoryCrowdBean directoryBean) {

        final Map<String, String> attributes = new HashMap<>();
        DirectoryCrowdServer server = directoryBean.getServer();
        if (server != null) {
            attributes.put(CROWD_SERVER_URL, server.getUrl() == null ? null : valueOf(server.getUrl()));
            attributes.put(APPLICATION_NAME, server.getAppUsername());
            attributes.put(APPLICATION_PASSWORD, server.getAppPassword());
            DirectoryCrowdServerProxy proxy = server.getProxy();
            if (proxy != null) {
                attributes.put(CROWD_HTTP_PROXY_HOST, proxy.getHost());
                if (proxy.getPort() != null) {
                    attributes.put(CROWD_HTTP_PROXY_PORT, proxy.getPort().toString());
                }
                attributes.put(CROWD_HTTP_PROXY_USERNAME, proxy.getUsername());
                attributes.put(CROWD_HTTP_PROXY_PASSWORD, proxy.getPassword());
            }
        }
        DirectoryCrowdAdvanced advanced = directoryBean.getAdvanced();
        if (advanced != null) {
            attributes.put(CACHE_SYNCHRONISE_INTERVAL, advanced.getUpdateSyncIntervalInMinutes() != 0 ?
                    valueOf(advanced.getUpdateSyncIntervalInMinutes()) : "3600");
            attributes.put(ATTRIBUTE_KEY_USE_NESTED_GROUPS, valueOf(TRUE.equals(advanced.getEnableNestedGroups())));
            attributes.put(INCREMENTAL_SYNC_ENABLED, valueOf(TRUE.equals(advanced.getEnableIncrementalSync())));
            attributes.put(SYNC_GROUP_MEMBERSHIP_AFTER_SUCCESSFUL_USER_AUTH_ENABLED, advanced.getUpdateGroupMembershipMethod());
        }

        DirectoryImpl directory = new DirectoryImpl(directoryBean.getName(), getDirectoryType(directoryBean), RemoteCrowdDirectory.class.getName());
        directory.setActive(directoryBean.getActive() != null && directoryBean.getActive());
        directory.setDescription(directoryBean.getDescription());
        directory.setAttributes(attributes);

        return directory;
    }

    /**
     * Build user directory bean user directory bean.
     *
     * @param directory the directory
     * @return the user directory bean
     */
    @NotNull
    public static AbstractDirectoryBean toDirectoryBean(
            @NotNull final Directory directory) {

        final Map<String, String> attributes = directory.getAttributes();
        final AbstractDirectoryBean directoryBean;

        if (DirectoryType.CROWD.equals(directory.getType())) {

            DirectoryCrowdServer serverBean = new DirectoryCrowdServer();
            serverBean.setUrl(URI.create(attributes.get(CROWD_SERVER_URL)));
            if (attributes.containsKey(CROWD_HTTP_PROXY_HOST)) {
                DirectoryCrowdServerProxy proxy = new DirectoryCrowdServerProxy();
                proxy.setUsername(attributes.get(CROWD_HTTP_PROXY_USERNAME));
                proxy.setHost(attributes.get(CROWD_HTTP_PROXY_HOST));
                if (attributes.get(CROWD_HTTP_PROXY_PORT) != null) {
                    proxy.setPort(Integer.valueOf(attributes.get(CROWD_HTTP_PROXY_PORT)));
                }
                serverBean.setProxy(proxy);
            }
            serverBean.setConnectionTimeoutInMillis(toLong(attributes.get(CROWD_HTTP_TIMEOUT)));
            serverBean.setMaxConnections(toInt(attributes.get(CROWD_HTTP_MAX_CONNECTIONS)));
            serverBean.setAppUsername(attributes.get(APPLICATION_NAME));

            DirectoryCrowdAdvanced advanced = new DirectoryCrowdAdvanced();
            advanced.setEnableIncrementalSync(toBoolean(attributes.get(INCREMENTAL_SYNC_ENABLED)));
            advanced.setEnableNestedGroups(toBoolean(attributes.get(ATTRIBUTE_KEY_USE_NESTED_GROUPS)));
            advanced.setUpdateSyncIntervalInMinutes(toInt(attributes.get(CACHE_SYNCHRONISE_INTERVAL)));

            DirectoryCrowdBean directoryCrowdBean = new DirectoryCrowdBean();
            directoryCrowdBean.setServer(serverBean);
            directoryCrowdBean.setAdvanced(advanced);
            directoryBean = directoryCrowdBean;

        } else  {
            directoryBean = new DirectoryGenericBean();
        }

        directoryBean.setId(directory.getId());
        directoryBean.setName(directory.getName());
        directoryBean.setActive(directory.isActive());
        directoryBean.setDescription(directory.getDescription());
        return directoryBean;
    }

    public static DirectoryType getDirectoryType(
            @NotNull final AbstractDirectoryBean directoryBean) {
        if (directoryBean instanceof DirectoryInternalBean) {
            return DirectoryType.INTERNAL;
        } else if (directoryBean instanceof DirectoryCrowdBean) {
            return DirectoryType.CROWD;
        } else if (directoryBean instanceof DirectoryLdapBean) {
            return DirectoryType.DELEGATING;
        } else {
            return DirectoryType.UNKNOWN;
        }
    }

    private DirectoryBeanUtil() {
    }

}
