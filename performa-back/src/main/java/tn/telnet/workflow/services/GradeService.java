package tn.telnet.workflow.services;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.CategoryGrade;
import tn.telnet.workflow.models.Grade;
import tn.telnet.workflow.repository.CategoryGradeRepository;
import tn.telnet.workflow.repository.GradeRepository;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private CategoryGradeRepository categoryGradeRepository;


    public List<Grade> getAllGrades()
    {
        List<Grade> roles =gradeRepository.findAll();
        return roles;
    }

    public Grade getGradeById(Long id)
    {
        return gradeRepository.findById(id).get();
    }

    public Grade createGrade(@Valid @RequestBody Grade grade) {
        return gradeRepository.save(grade);
    }
    public List<CategoryGrade> getAllCategoryGrades()
    {
        List<CategoryGrade> category_grades =categoryGradeRepository.findAll();
        /*if(category_grades.isEmpty())
        {
            throw new NotFoundException("No category grade Found");
        }*/
        return category_grades;
    }
    public CategoryGrade getCategoryGradeById(Long id)
    {
        return categoryGradeRepository.findById(id).get();
    }
    public CategoryGrade createCategoryGrade(@Valid @RequestBody CategoryGrade categoryGrade) {
        return categoryGradeRepository.save(categoryGrade);
    }

}
