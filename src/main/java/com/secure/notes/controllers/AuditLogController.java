package com.secure.notes.controllers;

import com.secure.notes.models.AuditLog;
import com.secure.notes.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {
    @Autowired
    AuditService auditService;

    @GetMapping
    public List<AuditLog> getAuditLogs(){
        return auditService.getAllAuditLogs();
    }

    @GetMapping("/note/{id}")
    public List<AuditLog> getNoteAuditLogs(@PathVariable Long id){
        return auditService.getAuditLogsForNoteId(id);
    }
}
