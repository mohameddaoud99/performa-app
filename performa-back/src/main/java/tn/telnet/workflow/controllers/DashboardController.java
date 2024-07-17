package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.models.EvaluationCompetencies;
import tn.telnet.workflow.models.ProfileCompetencies;
import tn.telnet.workflow.payload.request.CompetencyCategoryAverageRequest;
import tn.telnet.workflow.services.DashboardService;
import tn.telnet.workflow.services.EmployeeEvaluationService;
import tn.telnet.workflow.services.EvaluationCompetenciesService;
import tn.telnet.workflow.services.ProfileCompetenciesService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/dashboard")
@RestController
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/getEvaluationsCountByEmployee/{id}")
    public Integer getLastEvaluationForUserByEmail(@PathVariable("id") Long id) {
        Integer number = dashboardService.getEvaluationsCountByEmployee(id);
        return number;
    }

    @GetMapping("/getProfileCompetenciesCountById/{id}")
    public Integer getProfileCompetenciesByName(@PathVariable Long id) {
        return dashboardService.getProfileCompetenciesByName(id);
    }

    @GetMapping("/getEvaluationsDataByEmployee/{id}")
    public Map<String, Object> getEvaluationsDataByEmployee(@PathVariable Long id) {
        return dashboardService.getEvaluationsDataByEmployee(id);
    }

    @GetMapping("/getEmployeeNumberByStatusByAppraiser/{id}")
    public Map<String, Object> getEmployeeNumberByStatusByAppraiser(@PathVariable Long id) {
        return dashboardService.getEmployeeNumberByStatusByAppraiser(id);
    }

    @GetMapping("/getEmployeeNumberByStatusByDepartment/{department}")
    public Map<String, Object> getEmployeeNumberByStatusByAppraiser(@PathVariable String department) {
        return dashboardService.getEmployeeNumberByStatusByDepartment(department);
    }

    @GetMapping("/getEmployeeNumberByStatusByRole/{role}")
    public Map<String, Object> getEmployeeNumberByStatusByRole(@PathVariable String role) {
        return dashboardService.getEmployeeNumberByStatusByRole(role);
    }

    @GetMapping("/getAllEmployeeNumberByStatus")
    public Map<String, Object> getAllEmployeeNumberByStatus() {
        return dashboardService.getAllEmployeeNumberByStatus();
    }

}
