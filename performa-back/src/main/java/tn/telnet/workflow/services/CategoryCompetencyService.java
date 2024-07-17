package tn.telnet.workflow.services;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.*;
import tn.telnet.workflow.payload.response.MessageResponse;
import tn.telnet.workflow.repository.CategoryCompetencyRepository;
import tn.telnet.workflow.repository.CompetencyRepository;
import tn.telnet.workflow.repository.UserRepository;

import java.util.List;

@Service
public class CategoryCompetencyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryCompetencyRepository categoryCompetencyRepository;

    public ResponseEntity<?> affectComptenciesToUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Competencies affected to user successfully!"));
    }

    public List<CategoryCompetency> getAllCategoryCompetencies() {
        List<CategoryCompetency> competencies = categoryCompetencyRepository.findAll();
        return competencies;
    }


}
