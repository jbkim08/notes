package com.secure.notes.services;

import com.secure.notes.models.Note;

import java.util.List;

public interface NoteService {
    //유저가 새 노트를 만듬
    Note createNoteForUser(String username, String content);
    //유저가 id 노트를 수정
    Note updateNoteForUser(Long noteId, String content, String username);
    //노트를 삭제
    void deleteNoteForUser(Long noteId, String username);
    //유저의 모든 노트를 가져오기
    List<Note> getNotesForUser(String username);
}
