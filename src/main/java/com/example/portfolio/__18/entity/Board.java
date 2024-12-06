package com.example.portfolio.__18.entity;

import com.example.portfolio.__18.dto.BoardForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@Entity
@ToString
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    @Column(name = "board_count")
    private Long count;


    public static Board boardCreate(BoardForm boardForm){
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        return board;
    }
}
