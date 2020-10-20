package de.aservo.confapi.fisheye.service;

import com.atlassian.fecru.user.FecruUser;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.cenqua.fisheye.AppConfig;
import com.cenqua.fisheye.user.UserManager;
import de.aservo.confapi.commons.exception.NotFoundException;
import de.aservo.confapi.commons.model.UserBean;
import de.aservo.confapi.commons.service.api.UsersService;
import de.aservo.confapi.fisheye.model.util.UserBeanUtil;

import javax.inject.Named;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Named
@ExportAsService(UsersService.class)
public class UsersServiceImpl implements UsersService {

    private final UserManager userManager;

    public UsersServiceImpl() {
        userManager = AppConfig.getsConfig().getUserManager();
    }

    public UsersServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserBean getUser(
            final String username) {
        FecruUser user = findUser(username);
        return UserBeanUtil.toUserBean(user);
    }

    @Override
    public UserBean updateUser(
            final String username,
            final UserBean userBean) {

        //verify user exists
        findUser(username);

        //update user
        userManager.updateUser(userBean.getUsername(), userBean.getFullName(), userBean.getEmail());
        if (isNotBlank(userBean.getPassword())) {
            updatePassword(username, userBean.getPassword());
        }

        return getUser(username);
    }

    @Override
    public UserBean updatePassword(
            final String username,
            final String password) {

        //verify user exists
        findUser(username);

        //update user
        userManager.changePassword(username, password);

        return getUser(username);
    }

    private FecruUser findUser(String username) {
        FecruUser user = userManager.getUser(username);
        if (user == null) {
            throw new NotFoundException(String.format("user %s does not exist", username));
        }
        return user;
    }

}
