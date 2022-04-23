package com.aso.examplatform.config;

import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.UserRepository;
import com.aso.examplatform.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {

        // if the request that create tokens ignore them from filtering
        if(request.getRequestURI().equals("/api/auth/login") || request.getRequestURI().equals("/api/auth/setTenant")){
            filterChain.doFilter(request, response);
            return;
        }

        String tokenHeader = request.getHeader("Authorization");
        String token;
        String username = null;
        String tenantId = null;

        if(tokenHeader != null && tokenHeader.startsWith("Bearer")){
            token = tokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
                tenantId = jwtTokenUtil.getTenantFromToken(token);
            }catch(IllegalArgumentException e){
                response.getWriter().write("Invalid Token");
            }catch(ExpiredJwtException e){
                response.getWriter().write("Expired Token");
            }catch(Exception e){
                response.getWriter().write("Something went wrong");
            }
        }

        if(username != null){
            if(tenantId == null || tenantId.equals("")){
                response.getWriter().write("Tenant Not Found");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if(optionalUser.isPresent()){
                request.setAttribute("USER", optionalUser.get());
                request.setAttribute("TENANT", tenantId);
                filterChain.doFilter(request, response);
            }else{
                response.getWriter().write("Unauthorized");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }else{
            response.getWriter().write("Unauthorized");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
