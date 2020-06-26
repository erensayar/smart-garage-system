package com.erensayar.plateRecogModule2.controller;


import com.erensayar.plateRecogModule2.entity.AuthorizedPlate;
import com.erensayar.plateRecogModule2.entity.NewAuthorizedPlate;
import com.erensayar.plateRecogModule2.entity.Plate;
import com.erensayar.plateRecogModule2.service.IAdminService;
import com.erensayar.plateRecogModule2.service.IAuthorizedPlateService;
import com.erensayar.plateRecogModule2.service.IPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author erensayar
 */
@Controller
@RequestMapping()
public class MainController {

    //<=====================================================================>
    private IAuthorizedPlateService authorizedPlateService;
    private IPlateService plateService;
    private IAdminService adminService;

    @Autowired
    public MainController(IAuthorizedPlateService authorizedPlateService, IPlateService plateService, IAdminService adminService) {
        this.authorizedPlateService = authorizedPlateService;
        this.plateService = plateService;
        this.adminService = adminService;
    }

    //<=====================================================================>
    @GetMapping(value = "/")
    public String redirect() {
        return "redirect:/login";
    }


    @GetMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("plates", plateService.getAll());
        return "home";
    }

    @GetMapping(value = "/home/{plate}")
    public String homeGetPlateRecords(@PathVariable String plate, Model model) {
        model.addAttribute("plates", plateService.getAll());
        model.addAttribute("specificPlate", plateService.findByPlateContaining(plate));
        model.addAttribute("numberOfRec", plateService.countByPlate(plate));
        return "home";
    }

    @GetMapping(value = "/addplate")
    public String addplate() {
        return "addplate";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    //<=====================================================================>

    @PostMapping(value = "/addplate")
    public ResponseEntity<String> addPlate(@RequestBody NewAuthorizedPlate newPlate) {

        if (newPlate.getPass().equals(adminService.getAdminById(1).get().getPass())) {
            AuthorizedPlate ap = new AuthorizedPlate(null, newPlate.getNewPlate());
            authorizedPlateService.addAuthorityPlate(ap);
            return new ResponseEntity<>("Plate Added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Pass Is Wrong", HttpStatus.CREATED);
        }
    }

    // Take Plate From Module 1
    @PostMapping(value = "/checkplate")
    public ResponseEntity<String> getPlate(@RequestBody Plate plate) {

        //Plate Date Set
        Date timeToNow = new Date();
        plate.setDate(timeToNow);
        //AddPlate
        plateService.addPlate(plate);

        if(authorizedPlateService.findByPlate(plate.getPlate()) == null){
            return new ResponseEntity<>("Unauthorized", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("Authorized", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/deleteplate")
    public ResponseEntity<String> deleteplate(@RequestBody String id) {
        System.out.println(id);
        //plateService.deleteById(id);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }


}
