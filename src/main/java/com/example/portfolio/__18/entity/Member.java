package com.example.portfolio.__18.entity;

import com.example.portfolio.__18.constant.Role;
import com.example.portfolio.__18.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Table(name = "member")
@ToString
public class Member extends BaseEntity{

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    //enum 타입을 엔티티의 속성으로 지정, enum의 순서가 바뀔경우 문제가 발생할 수 있으므로 EnumType.STRING 옵션을 사용해서 String 으로 저장권장
    @Enumerated(EnumType.STRING)
    private Role role;
    
    //Member엔티티를 생성하는 메서드/ 관리 하기편안하게 해줌
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setUsername(memberFormDto.getUsername());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
       //스프링 시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호를 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword1());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}
