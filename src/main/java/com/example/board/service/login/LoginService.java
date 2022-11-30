package com.example.board.service.login;

import com.example.board.domain.login.Member;
import com.example.board.domain.login.MemberRepository;
import com.example.board.web.dto.MemberDto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public static final Map<String,String> sessionStore = new HashMap<>();
    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private final HttpSession httpSession;

    @Transactional
    public String signUp(@RequestBody MemberRequestDto memberRequestDto){

        if (memberRepository.existsByMemberId(memberRequestDto.getMemberId())) {  // 아이디 중복확인
            throw new RuntimeException("중복된 아이디입니다.");
        } else {    // 중복되지 않은 아이디

            // 암호화
            String encodePw = passwordEncoder.encode(memberRequestDto.getMemberPw());
            memberRequestDto.setMemberPw(encodePw);

            Member member = Member.builder()
                    .memberName(memberRequestDto.getMemberName())
                    .memberId(memberRequestDto.getMemberId())
                    .memberPw(encodePw)
                    .build();

            memberRepository.save(member);

            return "SUCCESS";
        }
    }

    @Transactional
    public String login(String memberId, HttpServletResponse response){

        // 예측불가한 랜덤한 sessionId값 생성(예측가능할 경우 해커가 악의로 접근 가능)
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId,memberId);


        httpSession.setAttribute(sessionId, memberId);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME,sessionId);
        cookie.setMaxAge(24*60*60); // 유효시간(1day)
        response.addCookie(cookie);

        return cookie.getValue();
    }

    public Cookie findCookies(HttpServletRequest request, String cookieName){
        if(request.getCookies()==null){
            //throw new Exception("쿠키에 값이 없습니다.");
        }
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    @Transactional
    public void logout(HttpServletRequest request){
        Cookie cookie = findCookies(request,SESSION_COOKIE_NAME);
        if(cookie != null){
            sessionStore.remove(cookie.getValue());
        }
    }
}
