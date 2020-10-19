package de.aservo.confapi.fisheye.model.util;

import com.atlassian.extras.api.Product;
import com.atlassian.extras.api.ProductLicense;
import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.fisheye.service.LicensesServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class LicenseBeanUtilTest {

    @Test
    public void testToLicenseBean() {

        ProductLicense license = LicensesServiceTest.createTestLicense(Product.FISHEYE);

        final LicenseBean bean = LicenseBeanUtil.toLicenseBean(license);

        assertNotNull(bean);
        assertEquals(bean.getProducts().iterator().next(), license.getProduct().getName());
        assertEquals(bean.getOrganization(), license.getOrganisation().getName());
        assertEquals(bean.getType(), license.getLicenseType().toString());
        assertEquals(bean.getDescription(), license.getDescription());
        assertEquals(bean.getExpiryDate(), license.getMaintenanceExpiryDate());
        assertEquals(bean.getMaxUsers(), license.getMaximumNumberOfUsers());
    }

}
