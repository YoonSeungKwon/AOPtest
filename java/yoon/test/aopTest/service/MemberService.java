package yoon.test.aopTest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.test.aopTest.config.jwt.JwtProvider;
import yoon.test.aopTest.domain.Members;
import yoon.test.aopTest.enums.Role;
import yoon.test.aopTest.repository.MemberRepository;
import yoon.test.aopTest.request.LoginDto;
import yoon.test.aopTest.request.MemberDto;
import yoon.test.aopTest.response.MemberResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private MemberResponse toResponse(Members member){
        return new MemberResponse(member.getEmail(), member.getName(), member.getRoleKey(), member.getRegdate());
    }

    public MemberResponse join(MemberDto dto){
        Members member = Members.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .role(Role.USER)
                .build();
        return toResponse(memberRepository.save(member));
    }

    public MemberResponse login(LoginDto dto)throws UsernameNotFoundException, BadCredentialsException{
        String username = dto.getEmail();
        String password = dto.getPassword();

        Members member = memberRepository.findMembersByEmail(username);

        if(member == null)
            throw new UsernameNotFoundException(username);
        if(!passwordEncoder.matches(password, member.getPassword()))
            throw new BadCredentialsException(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return toResponse(member);
    }
}
