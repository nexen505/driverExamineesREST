package com.komarmoss.controllers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.komarmoss.controllers.xsltWrappers.Owners;
import com.komarmoss.controllers.xsltWrappers.Vehicles;
import com.komarmoss.service.OwnerService;
import com.komarmoss.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import java.io.StringReader;
import java.util.Collections;

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
        final DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        final InputSource is = new InputSource(new StringReader(new XmlMapper().writeValueAsString(obj)));
        final Document document = documentBuilder.parse(is);
        return new DOMSource(document);
    }

    @RequestMapping(value = "/owners")
    public ModelAndView findOwners(@RequestParam(name = "id", required = false) Integer id) throws Exception {
        final ModelAndView modelAndView = new ModelAndView("XSLTView");
        modelAndView.addObject("xmlSource", createXsltSource(
                new Owners(id == null ? ownerService.findOwners() : Collections.singletonList(ownerService.findOwner(id)))
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
