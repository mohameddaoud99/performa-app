package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.telnet.workflow.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


}
