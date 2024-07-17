package tn.telnet.workflow.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.GradeCompetencies;
import tn.telnet.workflow.models.ProfileCompetencies;
import tn.telnet.workflow.repository.ProfileCompetenciesRepository;

import java.util.List;

@Service
public class ProfileCompetenciesService {

    @Autowired
    ProfileCompetenciesRepository profileCompetenciesRepository;

    public void createProfileCompetencies(@RequestBody ProfileCompetencies profileCompetencies)
    {
        profileCompetenciesRepository.save(profileCompetencies);
    }

    public List<ProfileCompetencies> getAllProfileCompetencies()
    {
        List<ProfileCompetencies> profile_competencie = profileCompetenciesRepository.findAll();
        return profile_competencie;
    }

    public List<ProfileCompetencies> getProfileCompetenciesByName(String name)
    {
        List<ProfileCompetencies> profile_competencies = profileCompetenciesRepository.getProfileCompetenciesByName(name);
        return profile_competencies;
    }




}
