package de.aservo.confapi.fisheye.model.util;

import com.atlassian.extras.api.ProductLicense;
import de.aservo.confapi.commons.model.LicenseBean;

import javax.annotation.Nonnull;
import java.util.Collections;

public class LicenseBeanUtil {

    /**
     * Instantiates a new License bean.
     *
     * @param productLicense the product license
     */
    @Nonnull
    public static LicenseBean toLicenseBean(
            @Nonnull final ProductLicense productLicense) {

        final LicenseBean licenseBean = new LicenseBean();
        licenseBean.setProducts(Collections.singletonList(productLicense.getProduct().getName()));
        licenseBean.setType(productLicense.getLicenseType().toString());
        licenseBean.setOrganization(productLicense.getOrganisation().getName());
        licenseBean.setDescription(productLicense.getDescription());
        licenseBean.setExpiryDate(productLicense.getMaintenanceExpiryDate());
        licenseBean.setMaxUsers(productLicense.getMaximumNumberOfUsers());
        return licenseBean;
    }

    private LicenseBeanUtil() {
    }

}
