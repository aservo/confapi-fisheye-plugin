package de.aservo.confapi.fisheye.service.api;

import de.aservo.confapi.fisheye.model.SettingsHttpBean;

import javax.validation.constraints.NotNull;

public interface SettingsHttpService {

    SettingsHttpBean getSettings();

    SettingsHttpBean setSettings(
            @NotNull SettingsHttpBean settings);
}
