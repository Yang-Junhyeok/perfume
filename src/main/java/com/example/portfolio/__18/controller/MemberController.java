package com.example.portfolio.__18.controller;

import com.example.portfolio.__18.dto.MemberFindDto;
import com.example.portfolio.__18.dto.MemberFormDto;
import com.example.portfolio.__18.entity.Member;
import com.example.portfolio.__18.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "signup_form";
    }

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }
        if (!memberFormDto.getPassword1().equals(memberFormDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try{
            Member member = Member.createMember(memberFormDto,passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String loginMember(){
        return "login_form";
    }
    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "login_form";
    }

    @GetMapping("/find")
    public String showMemberFindForm(Model model) {
        model.addAttribute("memberFindDto", new MemberFindDto());
        return "memberFind";
    }

    @PostMapping("/find")
    public String handleMemberFind(
            @Valid @ModelAttribute("memberFindDto") MemberFindDto memberFindDto,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "memberFind"; // 폼 유효성 검증 오류
        }

        String foundMember = memberService.MemberFind(memberFindDto.getEmail(), memberFindDto.getName());

        if (foundMember != null) {
            model.addAttribute("findIdExist", foundMember);
        }

        return "memberFind";
    }

}
