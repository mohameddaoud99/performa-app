package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.models.GradeCompetencies;
import tn.telnet.workflow.models.ProfileCompetencies;
import tn.telnet.workflow.services.GradeCompetenciesService;
import tn.telnet.workflow.services.ProfileCompetenciesService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/gradeCompetencies")
@RestController
public class GradeCompetenciesController {

    @Autowired
    GradeCompetenciesService gradeCompetenciesService;

    @GetMapping("/getAllGradeCompetencies")
    public List<GradeCompetencies> getAllProfileCompetencies()
    {
       return gradeCompetenciesService.getAllGradeCompetencies();
    }

    @GetMapping("/getGradeCompetenciesByGradeName/{grade_name}")
    public List<GradeCompetencies> getGradeCompetenciesByGradeName(@PathVariable String grade_name)
    {
        return gradeCompetenciesService.getGradeCompetenciesByGradeName(grade_name);
    }

}
