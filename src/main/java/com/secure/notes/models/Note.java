package com.secure.notes.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant; //mysql timestamp 와 같음

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //id 자동증가

    @Lob
    private String content; //노트 내용 (긴글)

    private String ownerUsername; //글쓴이

    @CreationTimestamp         //자동생성
    private Instant createdAt; //노트 생성날짜
}
