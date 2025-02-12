package com.secure.notes.services;

import com.secure.notes.models.AuditLog;
import com.secure.notes.models.Note;

import java.util.List;

public interface AuditLogService {

    void logNoteCreation(String username, Note note);
    void logNoteUpdate(String username, Note note);
    void logNoteDeletion(String username, Long noteId);

    //모든 로그를 가져오기
    List<AuditLog> getAllAuditLogs();
    //노트 한개에 대한 로그들을 가져오기
    List<AuditLog> getAuditLogsForNoteId(Long id);
}
