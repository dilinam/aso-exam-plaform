package com.aso.examplatform.config;

import com.aso.examplatform.model.ModuleAction;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.ModuleActionRepository;
import com.aso.examplatform.repository.TenantUserRepository;
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
    private final TenantUserRepository tenantUserRepository;
    private final ModuleActionRepository moduleActionRepository;

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
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }catch(ExpiredJwtException e){
                response.getWriter().write("Expired Token");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }catch(Exception e){
                response.getWriter().write("Something went wrong");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
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
                try{
                    User user = optionalUser.get();
                    Optional<TenantUser> optionalTenantUser = tenantUserRepository.findByTenantIdAndUserId(Long.valueOf(tenantId), user.getUserId());
                    if(optionalTenantUser.isPresent()){
                        Optional<ModuleAction> optionalModuleAction = moduleActionRepository.findByRoleIdAndEndPoint(optionalTenantUser.get().getRole().getRoleId(), request.getRequestURI(), request.getMethod());
                        if(optionalModuleAction.isPresent()){
                            // Authorization successful, adding user and tenant objects to request
                            request.setAttribute("USER", user);
                            request.setAttribute("TENANT", optionalTenantUser.get().getTenant());
                            filterChain.doFilter(request, response);
                        }else{
                            // If user does not have access to endpoint
                            response.getWriter().write("Access Defined");
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        }
                    }else{
                        response.getWriter().write("Unauthorized");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    }
                }catch(Exception e){
                    response.getWriter().write("Unauthorized");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
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
