package de.aservo.confapi.fisheye.model;

import de.aservo.confapi.commons.constants.ConfAPI;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = ConfAPI.APPLICATION)
public class SettingsHttpBean {

    @XmlElement
    private String webContext;

    @XmlElement
    private String bindAddress;

    @XmlElement
    private String proxyScheme;

    @XmlElement
    private String proxyHost;

    @XmlElement
    private Integer proxyPort;

    public static final SettingsHttpBean EXAMPLE_1;

    static {
        EXAMPLE_1 = new SettingsHttpBean();
        EXAMPLE_1.setBindAddress("3990");
        EXAMPLE_1.setWebContext("fisheye");
        EXAMPLE_1.setProxyScheme("https");
        EXAMPLE_1.setProxyHost("localhost");
        EXAMPLE_1.setProxyPort(443);
    }
}
