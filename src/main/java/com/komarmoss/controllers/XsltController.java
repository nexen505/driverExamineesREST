package com.komarmoss.controllers;

import com.komarmoss.controllers.xsltWrappers.Owners;
import com.komarmoss.controllers.xsltWrappers.Vehicles;
import com.komarmoss.service.OwnerService;
import com.komarmoss.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

@Controller
@RequestMapping("/xslt")
public class XsltController {

    private final OwnerService ownerService;

    private final VehicleService vehicleService;

    @Autowired
    public XsltController(OwnerService ownerService, VehicleService vehicleService) {
        this.ownerService = ownerService;
        this.vehicleService = vehicleService;
    }

    private Source createXsltSource(Object obj) throws Exception {
        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final JAXBContext jc = JAXBContext.newInstance(obj.getClass());
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(obj, document);
        return new DOMSource(document);
    }

    @RequestMapping(value = "/owners")
    public ModelAndView findOwners() throws Exception {
        final ModelAndView modelAndView = new ModelAndView("XSLTView");
        modelAndView.addObject("xmlSource", createXsltSource(
                new Owners(ownerService.findOwners())
        ));
        return modelAndView;
    }

    @RequestMapping(value = "/vehicles")
    public ModelAndView findVehicles() throws Exception {
        final ModelAndView modelAndView = new ModelAndView("XSLTView");
        modelAndView.addObject("xmlSource", createXsltSource(
                new Vehicles(vehicleService.findVehicles()))
        );
        return modelAndView;
    }
}
