package de.aservo.confapi.fisheye.model.util;


import com.atlassian.fecru.user.FecruUser;
import de.aservo.confapi.commons.model.UserBean;

public class UserBeanUtil {

    public static UserBean toUserBean(
            final FecruUser user) {

        final UserBean userBean = new UserBean();
        userBean.setUsername(user.getUsername());
        userBean.setFullName(user.getDisplayName());
        userBean.setEmail(user.getEmail());

        return userBean;
    }

    private UserBeanUtil() {
    }

}
