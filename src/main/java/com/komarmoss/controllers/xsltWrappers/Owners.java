package com.komarmoss.controllers.xsltWrappers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.komarmoss.model.vo.OwnerVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "owners")
public class Owners implements Serializable {

    @JacksonXmlElementWrapper(localName = "owners")
    @JacksonXmlProperty(localName = "owner")
    @XmlElement(name = "owner", type = OwnerVO.class)
    private List<OwnerVO> owners = new ArrayList<>();

    public Owners() {
    }

    public Owners(List<OwnerVO> owners) {
        this.owners = owners;
    }

    public List<OwnerVO> getOwners() {
        return owners;
    }
}
