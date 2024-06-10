package com.example.mugcupserver.controller;


import com.example.mugcupserver.dto.request.member.MemberRegisterDto;
import com.example.mugcupserver.dto.response.member.MemberResponseDto;
import com.example.mugcupserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/checkId")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email){
        memberService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterDto memberRegisterDto){
        MemberResponseDto successMember = memberService.register(memberRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }
}
