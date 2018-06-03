package com.komarmoss.controllers;

import com.komarmoss.controllers.xsltWrappers.Owners;
import com.komarmoss.model.vo.OwnerVO;
import com.komarmoss.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Owners findOwners() {
        return new Owners(ownerService.findOwners());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public OwnerVO findOwner(@PathVariable("id") Integer id) {
        return ownerService.findOwner(id);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public OwnerVO saveOrUpdateOwner(@RequestBody OwnerVO ownerVO) {
        return ownerService.saveOrUpdateOwner(ownerVO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public boolean removeOwner(@PathVariable Integer id) {
        return ownerService.removeOwner(id);
    }
}
