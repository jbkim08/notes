package com.secure.notes.services.impl;

import com.secure.notes.models.Note;
import com.secure.notes.repositories.NoteRepository;
import com.secure.notes.services.AuditService;
import com.secure.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository; //노트리포
    @Autowired
    private AuditService auditService;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);   //글내용 저장
        note.setOwnerUsername(username); //글쓴이저장
        Note savedNote = noteRepository.save(note);
        auditService.logNoteCreation(username, savedNote);//새노트 로그
        return savedNote; //새노트를 저장하고 저장된 노트를 리턴
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findById(noteId) //노트가 있으면 가져오고 없으면 에러발생
                .orElseThrow(() -> new RuntimeException("Note not found"));
        note.setContent(content); //내용 저장
        Note updatedNote = noteRepository.save(note); //노트가 이미 id가 있으면 업데이트됨
        auditService.logNoteUpdate(username, updatedNote); //업데이트 로그
        return updatedNote;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        noteRepository.deleteById(noteId);
        auditService.logNoteDeletion(username, noteId); //삭제 로그
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        List<Note> personalNotes = noteRepository.findByOwnerUsername(username);
        return personalNotes;
    }
}
