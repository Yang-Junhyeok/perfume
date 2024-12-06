package com.example.portfolio.__18.service;

import com.example.portfolio.__18.dto.BoardForm;
import com.example.portfolio.__18.entity.Board;
import com.example.portfolio.__18.repository.BoardRepository;
import com.example.portfolio.__18.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Board createBoard(Board board){
        board.setTitle(board.getTitle());
        board.setContent(board.getContent());
        boardRepository.save(board);
        return board;
    }
}
