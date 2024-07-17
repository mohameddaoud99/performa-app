package tn.telnet.workflow.services;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.telnet.workflow.models.Department;
import tn.telnet.workflow.repository.DepartmentRepository;
import java.util.List;

@Service
public class DepartmentService {


    @Autowired
    DepartmentRepository departmentRepository;


    public Department createDepartment(@Valid @RequestBody Department department) {

        return departmentRepository.save(department);

    }

    public List<Department> getAllDepartments()
    {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

}
