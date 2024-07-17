package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.models.ProfileCompetencies;
import tn.telnet.workflow.services.ProfileCompetenciesService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/profileCompetencies")
@RestController
public class ProfileCompetenciesController {

    @Autowired
    ProfileCompetenciesService profileCompetenciesService;


    @GetMapping("/getAllProfileCompetencies")
    public List<ProfileCompetencies> getAllProfileCompetencies() {
        return profileCompetenciesService.getAllProfileCompetencies();
    }

    @GetMapping("/getProfileCompetenciesByName/{name}")
    public List<ProfileCompetencies> getProfileCompetenciesByName(@PathVariable String name) {
        return profileCompetenciesService.getProfileCompetenciesByName(name);
    }


}
