package de.aservo.confapi.fisheye.service;

import com.atlassian.extras.api.Product;
import com.atlassian.extras.api.ProductLicense;
import com.atlassian.extras.common.util.ProductLicenseProperties;
import com.atlassian.extras.core.crucible.CrucibleProductLicenseFactory;
import com.atlassian.extras.core.fisheye.FisheyeProductLicenseFactory;
import com.atlassian.fecru.util.LicenseInfoService;
import com.atlassian.sal.api.license.LicenseHandler;
import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.commons.model.LicensesBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static com.atlassian.extras.common.LicensePropertiesConstants.DEFAULT_CREATION_DATE;
import static com.atlassian.extras.common.LicensePropertiesConstants.DEFAULT_EXPIRY_DATE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class LicensesServiceTest {

    @Mock
    private LicenseHandler licenseHandler;

    @Mock
    private LicenseInfoService licenseInfoService;

    @InjectMocks
    private LicensesServiceImpl licenseService;

    @Test
    public void testGetLicense() {

        ProductLicense testLicenseFe = createTestLicense(Product.FISHEYE);
        ProductLicense testLicenseCru = createTestLicense(Product.CRUCIBLE);

        doReturn(testLicenseFe).when(licenseInfoService).getFisheyeLicense();
        doReturn(testLicenseCru).when(licenseInfoService).getCrucibleLicense();

        LicensesBean licenses = licenseService.getLicenses();

        assertNotNull(licenses);

        LicenseBean licenseBean = licenses.getLicenses().iterator().next();

        assertTrue(licenseBean.getProducts().contains(Product.FISHEYE.getName()));
        assertTrue(licenseBean.getProducts().contains(Product.CRUCIBLE.getName()));
    }

    @Test
    public void testAddLicense() {
        ProductLicense testLicenseFe = createTestLicense(Product.FISHEYE);
        ProductLicense testLicenseCru = createTestLicense(Product.CRUCIBLE);

        doReturn(testLicenseFe).when(licenseInfoService).getFisheyeLicense();
        doReturn(testLicenseCru).when(licenseInfoService).getCrucibleLicense();

        LicenseBean licenseBean = licenseService.addLicense(LicenseBean.EXAMPLE_1);

        assertTrue(licenseBean.getProducts().contains(Product.FISHEYE.getName()));
        assertTrue(licenseBean.getProducts().contains(Product.CRUCIBLE.getName()));
    }

    public static ProductLicense createTestLicense(Product product) {

        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");

        Properties properties = new Properties();
        properties.setProperty("licenseVersion", String.valueOf(0));
        properties.setProperty("Evaluation", "true");
        properties.setProperty("Subscription", "false");
        properties.setProperty("Description", "blah");
        properties.setProperty("Organisation", "aservo");
        properties.setProperty("CreationDate", dateFormat.format(DEFAULT_CREATION_DATE));
        properties.setProperty("PurchaseDate", dateFormat.format(DEFAULT_CREATION_DATE));
        properties.setProperty("LicenseExpiryDate", dateFormat.format(DEFAULT_EXPIRY_DATE));
        properties.setProperty("MaintenanceExpiryDate", dateFormat.format(DEFAULT_EXPIRY_DATE));
        properties.setProperty("SEN", "123");
        properties.setProperty("NumberOfUsers", "10");
        properties.setProperty("LicenseTypeName", "TESTING");

        ProductLicenseProperties licenseProperties = new ProductLicenseProperties(product, properties);

        if (Product.FISHEYE.equals(product)) {
            return new FisheyeProductLicenseFactory().getLicense(product, licenseProperties);
        } else {
            return new CrucibleProductLicenseFactory().getLicense(product, licenseProperties);
        }
    }
}
