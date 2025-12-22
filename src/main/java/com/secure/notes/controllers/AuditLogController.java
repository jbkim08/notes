package com.secure.notes.controllers;

import com.secure.notes.models.AuditLog;
import com.secure.notes.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 관리자가 모든 로그를 가져옴
     * @return 로그 리스트
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AuditLog> getAuditLogs(){
        return auditService.getAllAuditLogs();
    }

    /**
     * 특정 노트에 대한 로그 가져옴
     * @param id
     * @return 로그 리스트
     */
    @GetMapping("/note/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AuditLog> getNoteAuditLogs(@PathVariable Long id){
        return auditService.getAuditLogsForNoteId(id);
    }
}
