package com.secure.note.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id
    @Lob
    private String content; // 노트 내용 Lob : 문자열 많이 사용할 때
    private String ownerUsername; //글쓴이 이름

}
