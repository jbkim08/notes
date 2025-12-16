package com.secure.notes.services;

import com.secure.notes.models.Note;

import java.util.List;

public interface NoteService {
    //새 노트 만들기
    Note createNoteForUser(String username, String content);
    //노트 업데이트
    Note updateNoteForUser(Long noteId, String content, String username);
    //노트 삭제
    void deleteNoteForUser(Long noteId, String username);
    //모든 노트리스트 가져오기
    List<Note> getNotesForUser(String username);

}
