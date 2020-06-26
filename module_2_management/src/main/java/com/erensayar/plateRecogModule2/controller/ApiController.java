package com.erensayar.plateRecogModule2.controller;

import com.erensayar.plateRecogModule2.entity.AuthorizedPlate;
import com.erensayar.plateRecogModule2.entity.Plate;
import com.erensayar.plateRecogModule2.service.IAuthorizedPlateService;
import com.erensayar.plateRecogModule2.service.IPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author erensayar
 */
@RestController()
@RequestMapping("/api")
public class ApiController {

    private IAuthorizedPlateService authorityService;
    private IPlateService plateService;

    @Autowired
    public ApiController(IAuthorizedPlateService authorityService, IPlateService plateService) {
        this.authorityService = authorityService;
        this.plateService = plateService;
    }


    //GET ALL
    //<========================================================================>
    @GetMapping(value = "/getPlates")
    public List<Plate> getAllPlates() {
        return plateService.getAll();
    }

    @GetMapping(value = "/getAuthoritys")
    public List<AuthorizedPlate> getAllAuthoritys() {
        return authorityService.getAll();
    }

    //GET BY ID
    //<========================================================================>
    @GetMapping(value = "/plate/{id}")
    public Optional<Plate> getPlateById(@PathVariable("id") Integer id) {
        return plateService.getPlateById(id);
    }

    @GetMapping(value = "/authority/{id}")
    public Optional<AuthorizedPlate> getAuthorityById(@PathVariable("id") Integer id) {
        return authorityService.getAuthorityPlateById(id);
    }

    //ADD
    //<========================================================================>
    @PostMapping(value = "/addPlate")
    public ResponseEntity<String> addPlate(@RequestBody Plate plate) {
        //System.out.println(plate.toString());
        plateService.addPlate(plate);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PostMapping(value = "/addAuthority")
    public ResponseEntity<String> addAuthority(@RequestBody AuthorizedPlate authorizedPlate) {
        //System.out.println(authority.toString());
        authorityService.addAuthorityPlate(authorizedPlate);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    //DELETE
    //<========================================================================>
    @PostMapping(value = "/deletePlate")
    public ResponseEntity<String> deletePlate(@RequestBody Plate plate) {
        plateService.deleteById(plate.getId());
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PostMapping(value = "/deleteAuthority")
    public ResponseEntity<String> deleteAuthority(@RequestBody AuthorizedPlate authorizedPlate) {
        authorityService.deleteById(authorizedPlate.getId());
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }


}
