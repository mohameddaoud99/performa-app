package tn.telnet.workflow.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.GradeCompetencies;
import tn.telnet.workflow.models.ProfileCompetencies;
import tn.telnet.workflow.repository.GradeCompetenciesRepository;
import tn.telnet.workflow.repository.ProfileCompetenciesRepository;

import java.util.List;

@Service
public class GradeCompetenciesService {

    @Autowired
    GradeCompetenciesRepository gradeCompetenciesRepository;


    public void createGradeCompetencies(@RequestBody GradeCompetencies gradeCompetencies)
    {
        gradeCompetenciesRepository.save(gradeCompetencies);
    }

    public List<GradeCompetencies> getAllGradeCompetencies()
    {
        List<GradeCompetencies> grade_competencies = gradeCompetenciesRepository.findAll();
        return grade_competencies;
    }

    public List<GradeCompetencies> getGradeCompetenciesByGradeName(String grade_name)
    {
        List<GradeCompetencies> grade_competencies = gradeCompetenciesRepository.getGradeCompetenciesByGradeName(grade_name);
        return grade_competencies;
    }




}
