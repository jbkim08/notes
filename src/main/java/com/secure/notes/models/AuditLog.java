package com.secure.notes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;  // Instant 는 utc 기준이고 local는 내 위치

@Entity
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                //자동증가 id
    private String action;          //액션
    private String username;        //사용자
    private Long noteId;            //노트id
    private String noteContent;     //내용
    private LocalDateTime timestamp;//날짜시간
}
