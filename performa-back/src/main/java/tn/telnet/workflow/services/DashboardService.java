package tn.telnet.workflow.services;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.CategoryGrade;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.Grade;
import tn.telnet.workflow.models.ProfileCompetencies;
import tn.telnet.workflow.payload.request.CompetencyCategoryAverageRequest;
import tn.telnet.workflow.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private EmployeeEvaluationRepository evaluationRepository;
    @Autowired
    private ProfileCompetenciesRepository profileCompetenciesRepository;
    @Autowired
    private EmployeeEvaluationRepository employeeEvaluationRepository;

    public Integer getEvaluationsCountByEmployee(Long id) {
        Integer number = evaluationRepository.getEvaluationsCountByEmployee(id);
        return number;
    }

    public Integer getProfileCompetenciesByName(Long id) {
        Integer number = profileCompetenciesRepository.getCompetenciesCountByEmployee(id);
        if (number == null || number == 0) {
            return 0;
        }
        return number;
    }

    public Map<String, Object> getEvaluationsDataByEmployee(Long id) {
        List<EmployeeEvaluation> evaluations = employeeEvaluationRepository.getEvaluationsByEmployee(id);
        List<String> dateList = new ArrayList<>();
        List<String> marksList = new ArrayList<>();

        for (EmployeeEvaluation e : evaluations) {
            dateList.add(e.getDateAppraiserSignature().toString());
            marksList.add(e.getFinal_mark().toString());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("dateList", dateList);
        data.put("marksList", marksList);
        return data;
    }

    public Map<String, Object> getEmployeeNumberByStatusByAppraiser(Long id) {

        List<String> statusList = employeeEvaluationRepository.getDistinctStatus();
        List<Integer> employeeCountList = new ArrayList<>();
        for (String e : statusList) {
            Integer employeeCount = employeeEvaluationRepository.getEmployeeNumberByStatusByAppraiser(id, e);
            employeeCountList.add(employeeCount);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("statusList", statusList);
        data.put("employeeCountList", employeeCountList);
        return data;
    }

    public Map<String, Object> getEmployeeNumberByStatusByDepartment(String department) {

        List<String> statusList = employeeEvaluationRepository.getDistinctStatus();
        List<Integer> employeeCountList = new ArrayList<>();
        for (String e : statusList) {
            Integer employeeCount = employeeEvaluationRepository.getEmployeeNumberByStatusByDepartment(department, e);
            employeeCountList.add(employeeCount);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("statusList", statusList);
        data.put("employeeCountList", employeeCountList);
        return data;
    }

    public Map<String, Object> getEmployeeNumberByStatusByRole(String role) {

        List<String> statusList = employeeEvaluationRepository.getDistinctStatus();
        List<Integer> employeeCountList = new ArrayList<>();
        for (String e : statusList) {
            Integer employeeCount = employeeEvaluationRepository.getEmployeeNumberByStatusByRole(role, e);
            employeeCountList.add(employeeCount);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("statusList", statusList);
        data.put("employeeCountList", employeeCountList);
        return data;
    }

    public Map<String, Object> getAllEmployeeNumberByStatus() {

        List<String> statusList = employeeEvaluationRepository.getDistinctStatus();
        List<Integer> employeeCountList = new ArrayList<>();
        for (String e : statusList) {
            Integer employeeCount = employeeEvaluationRepository.getAllEmployeeNumberByStatus(e);
            employeeCountList.add(employeeCount);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("statusList", statusList);
        data.put("employeeCountList", employeeCountList);
        return data;
    }


}
