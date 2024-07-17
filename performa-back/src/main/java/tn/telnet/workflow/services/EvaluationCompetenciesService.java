package tn.telnet.workflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.EvaluationCompetencies;
import tn.telnet.workflow.models.GradeCompetencies;
import tn.telnet.workflow.repository.EvaluationCompetenciesRepository;
import tn.telnet.workflow.repository.GradeCompetenciesRepository;
import tn.telnet.workflow.response.ResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EvaluationCompetenciesService {

    @Autowired
    EvaluationCompetenciesRepository evaluationCompetenciesRepository;

    public ResponseEntity<Object> createEvaluationCompetencies(@RequestBody EvaluationCompetencies evaluationCompetencies)
    {
        return ResponseHandler.responseBuilder("Evaluations created succefully ",
                HttpStatus.OK, evaluationCompetenciesRepository.save(evaluationCompetencies));
    }

    public List<EvaluationCompetencies> createListEvaluationCompetencies(@RequestBody List<EvaluationCompetencies> listEvaluationCompetencies) {


        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesRepository.saveAll(listEvaluationCompetencies);

        if (savedEvaluationCompetencies.isEmpty()) {
            return savedEvaluationCompetencies;
        } else {
            return savedEvaluationCompetencies;
        }
    }

    public List<EvaluationCompetencies> updateListEvaluationCompetencies(@RequestBody List<EvaluationCompetencies> listEvaluationCompetencies) {
        List<EvaluationCompetencies> updatedEvaluationCompetencies = new ArrayList<>();

        for (EvaluationCompetencies competency : listEvaluationCompetencies) {
            EvaluationCompetencies existingCompetency = evaluationCompetenciesRepository.findById(competency.getId()).orElse(null);
            if (existingCompetency != null) {
                existingCompetency.setSelected(competency.getSelected());
                existingCompetency.setEmployee_comment(competency.getEmployee_comment());
                existingCompetency.setEmployee_mark(competency.getEmployee_mark());
                existingCompetency.setAppraiser_comment(competency.getAppraiser_comment());
                existingCompetency.setAppraiser_mark(competency.getAppraiser_mark());
                existingCompetency.setWeightage(competency.getWeightage());
                existingCompetency.setPoints(competency.getPoints());
                updatedEvaluationCompetencies.add(evaluationCompetenciesRepository.save(existingCompetency));
            }
        }
        return updatedEvaluationCompetencies;
    }




    public List<EvaluationCompetencies> getAllEvaluationCompetencies()
    {
        List<EvaluationCompetencies> evaluations_competencies = evaluationCompetenciesRepository.findAll();
        return evaluations_competencies;
    }

}
