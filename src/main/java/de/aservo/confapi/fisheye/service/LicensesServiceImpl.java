package de.aservo.confapi.fisheye.service;

import com.atlassian.fecru.util.LicenseInfoService;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.license.LicenseHandler;
import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.commons.model.LicensesBean;
import de.aservo.confapi.commons.service.api.LicensesService;
import de.aservo.confapi.fisheye.model.util.LicenseBeanUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Named
@ExportAsService(LicensesService.class)
public class LicensesServiceImpl implements LicensesService {

    private final LicenseHandler licenseHandler;
    private final LicenseInfoService licenseInfoService;

    @Inject
    public LicensesServiceImpl(
            @ComponentImport final LicenseHandler licenseHandler,
            @ComponentImport final LicenseInfoService licenseInfoService) {
        this.licenseHandler = licenseHandler;
        this.licenseInfoService = licenseInfoService;
    }

    @Override
    public LicensesBean getLicenses() {
        LicenseBean licenseBeanFe = LicenseBeanUtil.toLicenseBean(licenseInfoService.getFisheyeLicense());
        LicenseBean licenseBeanCru = LicenseBeanUtil.toLicenseBean(licenseInfoService.getCrucibleLicense());

        //merge license details (merge products) as both products were historically separated but are now driven by a single license
        Set<String> products = new HashSet<>();
        products.addAll(licenseBeanFe.getProducts());
        products.addAll(licenseBeanCru.getProducts());
        licenseBeanFe.setProducts(products);

        return new LicensesBean(Collections.singleton(licenseBeanFe));
    }

    @Override
    public LicenseBean addLicense(LicenseBean licenseBean) {
        licenseHandler.setLicense(licenseBean.getKey());
        return getLicenses().getLicenses().iterator().next();
    }
}
