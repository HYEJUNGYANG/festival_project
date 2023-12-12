package com.festival.festival.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, jakarta.servlet.ServletException {
        // Authentication 객체를 이용해서 사용자가 가진 모든 권한을 체크
        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority->{
            roleNames.add(authority.getAuthority());
        });
        //log.warn("ROLE NAMES : "+roleNames);
        if(roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin_page/admin_main");
            return;
        }
        if(roleNames.contains("ROLE_USER")) {
            String prevPage = (String) request.getSession().getAttribute("prevPage");
            response.sendRedirect(prevPage != null ? prevPage : "/");
            return;
        }
        response.sendRedirect("/");
    }
}
