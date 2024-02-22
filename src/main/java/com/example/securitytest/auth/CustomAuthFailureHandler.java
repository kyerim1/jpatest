package com.example.securitytest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String loginid = request.getParameter("id");
        String errormsg="aaa";

        if( exception instanceof BadCredentialsException){
            errormsg="아이디나 비밀번호가 맞지 않습니다. 다시확인해주세요";
        }else if(exception instanceof InternalAuthenticationServiceException){
            errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시확인해주세요";

        }else if(exception instanceof DisabledException){
            errormsg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요";
        }else if(exception instanceof CredentialsExpiredException){
            errormsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요";
        }

//        errormsg = URLEncoder.encode(errormsg, "UTF-8");
//        System.out.println("aa "+errormsg);
//        setDefaultFailureUrl("/login?error=true&exception="+ errormsg);
//        super.onAuthenticationFailure(request,response,exception);

        request.getRequestDispatcher("/login?error=true&error_message="+errormsg)
                .forward(request,response);
    }
}






