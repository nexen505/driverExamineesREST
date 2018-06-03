package com.komarmoss.controllers;

import com.komarmoss.controllers.xsltWrappers.Vehicles;
import com.komarmoss.model.vo.VehicleVO;
import com.komarmoss.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Vehicles findVehicles() {
        return new Vehicles(vehicleService.findVehicles());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public VehicleVO findVehicle(@PathVariable("id") Integer id) {
        return vehicleService.findVehicle(id);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public VehicleVO saveOrUpdateVehicle(@RequestBody VehicleVO vehicleVO) {
        return vehicleService.saveOrUpdateVehicle(vehicleVO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public boolean removeVehicle(@PathVariable Integer id) {
        return vehicleService.removeVehicle(id);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Object getTypesOfVehicle() {
        return vehicleService.getTypesOfVehicles();
    }
}
