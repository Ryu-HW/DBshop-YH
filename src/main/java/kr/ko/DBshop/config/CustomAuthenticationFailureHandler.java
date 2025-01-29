package kr.ko.DBshop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//로그인실패시 spring security설정
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        //여기서 에러메시지를 삭제하면 UsernameNotFoundException를 받아도 삭제됨
//        request.getSession().removeAttribute("error");


        // 예외에 따라 다른 에러 메시지를 세션에 저장
        if (exception instanceof BadCredentialsException) {
            request.getSession().setAttribute("error", "아이디 혹은 비밀번호가 틀렸습니다.");
        } else {
            // 그 외의 경우 일반적인 오류 메시지
            request.getSession().setAttribute("error", "로그인 실패. 다시 시도해주세요.");
        }

        // 로그인 페이지로 리다이렉트
        response.sendRedirect("/login");
    }
}