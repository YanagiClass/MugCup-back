package com.example.mugcupserver.service;


import com.example.mugcupserver.common.exception.MemberException;
import com.example.mugcupserver.dto.request.member.MemberRegisterDto;
import com.example.mugcupserver.dto.response.member.MemberResponseDto;
import com.example.mugcupserver.entity.Member;
import com.example.mugcupserver.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {
    private final PasswordEncoder encoder;
    private final MemberRepository memberRepository;
    private  final AuthenticationManager authenticationManager;


    public HttpStatus checkIdDuplicate(String email) {
        isExistUserEmail(email);
        return HttpStatus.OK;
    }
    public MemberResponseDto register(MemberRegisterDto registerDto){
        isExistUserEmail(registerDto.getEmail());
        checkPassword(registerDto.getPassword(), registerDto.getPasswordCheck());

        // 패스워드 암호화
        String encodePwd = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodePwd);

        Member saveMember = memberRepository.save(
                MemberRegisterDto.ofEntity(registerDto)
        );
        return MemberResponseDto.fromEntity(saveMember);


    }


    /**
     * 아이디(이메일) 중복 체크
     * @param email
     */
    private void isExistUserEmail(String email) {
        if(memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }
    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }
}
