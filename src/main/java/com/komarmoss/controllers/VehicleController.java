package com.komarmoss.controllers;

import com.komarmoss.model.vo.VehicleVO;
import com.komarmoss.model.vo.WebResponseVO;
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

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public WebResponseVO findVehicle(@PathVariable(required = false) Integer id) {
        return new WebResponseVO(id != null ? vehicleService.findVehicle(id) : vehicleService.findVehicles());
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public WebResponseVO saveOrUpdateVehicle(@RequestBody VehicleVO vehicleVO) {
        return new WebResponseVO(vehicleService.saveOrUpdateVehicle(vehicleVO));
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public WebResponseVO removeVehicle(@PathVariable Integer id) {
        return new WebResponseVO(vehicleService.removeVehicle(id));
    }

    @GetMapping(value = "/types", produces = {"application/json", "application/xml"})
    public WebResponseVO getTypesOfVehicle() {
        return new WebResponseVO(vehicleService.getTypesOfVehicles());
    }
}
