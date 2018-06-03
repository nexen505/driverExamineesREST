package com.komarmoss.controllers;

import com.komarmoss.model.vo.VehicleVO;
import com.komarmoss.model.vo.WebResponseVO;
import com.komarmoss.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = {"/{id}", "/"}, method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public WebResponseVO findVehicle(@PathVariable("id") Optional<Integer> id) {
        return new WebResponseVO(id.isPresent() ? vehicleService.findVehicle(id.get()) : vehicleService.findVehicles());
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public WebResponseVO saveOrUpdateVehicle(@RequestBody VehicleVO vehicleVO) {
        return new WebResponseVO(vehicleService.saveOrUpdateVehicle(vehicleVO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public WebResponseVO removeVehicle(@PathVariable Integer id) {
        return new WebResponseVO(vehicleService.removeVehicle(id));
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public WebResponseVO getTypesOfVehicle() {
        return new WebResponseVO(vehicleService.getTypesOfVehicles());
    }
}
