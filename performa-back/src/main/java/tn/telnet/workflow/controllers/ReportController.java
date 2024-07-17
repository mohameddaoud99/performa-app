package tn.telnet.workflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.services.ReportService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/excel")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable long id) {
        String filename = "tutorials.xlsx";
        InputStreamResource file = new InputStreamResource(reportService.load(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }
}
