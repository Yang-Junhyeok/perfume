package com.example.portfolio.__18.controller;

import com.example.portfolio.__18.dto.BoardForm;
import com.example.portfolio.__18.dto.MemberFormDto;
import com.example.portfolio.__18.entity.Board;
import com.example.portfolio.__18.service.BoardService;
import com.example.portfolio.__18.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/boardCreate")
    public String BoardCreate(BoardForm boardForm){
        return "boardForm";
    }

    @PostMapping("/boardCreate")
    public String BoardCreate(BoardForm boardForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "boardForm";
        }
        try{
            Board board = Board.boardCreate(boardForm);
            boardService.createBoard(board);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
    
}
