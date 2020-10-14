package de.aservo.confapi.fisheye.model.util;

import com.atlassian.fecru.user.FecruUser;
import de.aservo.confapi.commons.model.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanUtilTest {

    @Test
    public void testToUserBean() {

        FecruUser user = new FecruUser("username");

        UserBean userBean = UserBeanUtil.toUserBean(user);

        assertEquals(user.getUsername(), userBean.getUsername());
    }
}