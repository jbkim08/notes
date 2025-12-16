package com.secure.notes.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //id 자동증가

    @Lob
    private String content; //노트 내용 (긴글)

    private String ownerUsername; //글쓴이
}
