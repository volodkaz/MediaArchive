package org.vladimir.homeArchive.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vladimir.homeArchive.security.token.TokenService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorisationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Value("${auth.enabled}")
    private boolean enable;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(!enable){
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || authHeader.isBlank()){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else if(!checkAuthorization(authHeader)){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            filterChain.doFilter(request, response);
        }
    }

    private boolean checkAuthorization(String authHeader) {
        if(!authHeader.startsWith("Noga ")){
            return false;
        }

        String token = authHeader.substring(5);
        return tokenService.checkToken(token);
    }
}
