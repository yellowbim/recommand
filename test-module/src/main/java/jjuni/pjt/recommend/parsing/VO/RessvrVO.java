package jjuni.pjt.recommend.parsing.VO;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlRootElement(name = "RCP")
@XmlAccessorType(XmlAccessType.FIELD)
public class RessvrVO {

    @XmlAttribute(name = "method")
    private String method;

    @XmlElement(name = "Result")
    private String result;

    @XmlElement(name = "Resource")
    private Resources resources;

    @Data
    @ToString
    @XmlRootElement(name = "Resource")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Resources {
        @XmlElement(name = "Category")
        private String category;

        @XmlElement(name = "ResourceID")
        private String resourceID;

        @XmlElement(name = "Address")
        private String address;

        @XmlElement(name = "Port")
        private String port;
    }
}
