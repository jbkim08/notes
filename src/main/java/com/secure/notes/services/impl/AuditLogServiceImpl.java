package com.secure.notes.services.impl;

import com.secure.notes.models.AuditLog;
import com.secure.notes.models.Note;
import com.secure.notes.repositories.AuditLogRepository;
import com.secure.notes.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    //노트생성 로그
    @Override
    public void logNoteCreation(String username, Note note){
        AuditLog log = new AuditLog();
        log.setAction("CREATE");
        log.setUsername(username); //글쓴이
        log.setNoteId(note.getId()); //노트번호
        log.setNoteContent(note.getContent()); //노트 내용
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    //노트 업데이트 로그
    @Override
    public void logNoteUpdate(String username, Note note){
        AuditLog log = new AuditLog();
        log.setAction("UPDATE");
        log.setUsername(username); //글쓴이
        log.setNoteId(note.getId()); //노트번호
        log.setNoteContent(note.getContent()); //수정내용
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    //노트 삭제 로그
    @Override
    public void logNoteDeletion(String username, Long noteId){
        AuditLog log = new AuditLog();
        log.setAction("DELETE");
        log.setUsername(username);
        log.setNoteId(noteId);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getAuditLogsForNoteId(Long id) {
        return auditLogRepository.findByNoteId(id);
    }
}
