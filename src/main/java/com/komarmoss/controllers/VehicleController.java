package com.komarmoss.controllers;

import com.komarmoss.controllers.xsltWrappers.TypesOfVehicle;
import com.komarmoss.controllers.xsltWrappers.Vehicles;
import com.komarmoss.model.vo.VehicleVO;
import com.komarmoss.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Vehicles findVehicles() {
        return new Vehicles(vehicleService.findVehicles());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public VehicleVO findVehicle(@PathVariable("id") Integer id) {
        return vehicleService.findVehicle(id);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public VehicleVO saveOrUpdateVehicle(@RequestBody VehicleVO vehicleVO) {
        return vehicleService.saveOrUpdateVehicle(vehicleVO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public boolean removeVehicle(@PathVariable Integer id) {
        return vehicleService.removeVehicle(id);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public TypesOfVehicle getTypesOfVehicle() {
        return new TypesOfVehicle(vehicleService.getTypesOfVehicles());
    }
}
