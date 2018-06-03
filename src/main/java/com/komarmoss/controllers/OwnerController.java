package com.komarmoss.controllers;

import com.komarmoss.model.vo.OwnerVO;
import com.komarmoss.model.vo.WebResponseVO;
import com.komarmoss.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = {"/{id}", "/"}, method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public WebResponseVO findOwner(@PathVariable("id") Optional<Integer> id) {
        return new WebResponseVO(id.isPresent() ? ownerService.findOwner(id.get()) : ownerService.findOwners());
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public WebResponseVO saveOrUpdateOwner(@RequestBody OwnerVO ownerVO) {
        return new WebResponseVO(ownerService.saveOrUpdateOwner(ownerVO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public WebResponseVO removeOwner(@PathVariable Integer id) {
        return new WebResponseVO(ownerService.removeOwner(id));
    }
}
