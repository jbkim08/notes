package com.secure.notes.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //id
    @Lob
    private String content;     //노트내용
    private String ownerUsername; //글쓴이이름
}
