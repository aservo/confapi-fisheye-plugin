package de.aservo.confapi.fisheye.service;

import com.atlassian.fecru.user.FecruUser;
import com.cenqua.fisheye.user.UserManager;
import de.aservo.confapi.commons.exception.NotFoundException;
import de.aservo.confapi.commons.model.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserManager userManager;

    @Test
    public void testGetUser() {
        UsersServiceImpl userService = new UsersServiceImpl(userManager);
        FecruUser user = createDummyUser();

        doReturn(user).when(userManager).getUser(user.getUsername());

        UserBean userBean = userService.getUser(user.getUsername());

        assertNotNull(userBean);
        assertEquals(user.getUsername(), userBean.getUsername());
        assertNull(userBean.getPassword());
    }

    @Test(expected = NotFoundException.class)
    public void testGetUserNotFound() {
        UsersServiceImpl userService = new UsersServiceImpl(userManager);
        FecruUser user = createDummyUser();

        userService.getUser(user.getUsername());
    }

    @Test
    public void testUpdateUser() {
        UsersServiceImpl userService = new UsersServiceImpl(userManager);
        FecruUser user = createDummyUser();

        doReturn(user).when(userManager).getUser(user.getUsername());

        UserBean userBean = userService.updateUser(user.getUsername(), UserBean.EXAMPLE_1);

        assertNotNull(userBean);
        assertNull(userBean.getPassword());
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateUserNotFound() {
        UsersServiceImpl userService = new UsersServiceImpl(userManager);
        FecruUser user = createDummyUser();

        userService.updateUser(user.getUsername(), UserBean.EXAMPLE_1);
    }

    @Test
    public void testUpdateUserPassword() {
        UsersServiceImpl userService = new UsersServiceImpl(userManager);
        FecruUser user = createDummyUser();

        doReturn(user).when(userManager).getUser(user.getUsername());

        UserBean userBean = userService.updatePassword(user.getUsername(), "newPassword");

        assertNotNull(userBean);
        assertNull(userBean.getPassword());
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateUserPasswordNotFound() {
        UsersServiceImpl userService = new UsersServiceImpl(userManager);
        FecruUser user = createDummyUser();

        userService.updatePassword(user.getUsername(), "newPassword");
    }

    private FecruUser createDummyUser() {
        return new FecruUser("username");
    }
}
