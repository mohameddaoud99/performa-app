package tn.telnet.workflow.services;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telnet.workflow.helper.ExcelHelper;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.User;
import tn.telnet.workflow.repository.EmployeeEvaluationRepository;

///////////////////////

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telnet.workflow.repository.UserRepository;


@Service
public class ReportService {

    @Autowired
    private EmployeeEvaluationRepository evaluationRepo;

    @Autowired
    private UserRepository userRepo;


    public ByteArrayInputStream load(long id) {
        Optional<EmployeeEvaluation> evaluation = evaluationRepo.findById(id);
        Optional<User> appraiser = userRepo.findById(evaluation.get().getId_appraiser());
        ByteArrayInputStream in = ExcelHelper.generateEmployeeEvaluationExcelFile(evaluation, appraiser);
        return in;
    }


}
