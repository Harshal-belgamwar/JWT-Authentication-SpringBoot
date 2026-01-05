package SpringSecurity.Filter;



import SpringSecurity.Service.CustomUserDetails;
import SpringSecurity.Service.CustomUserDetailsService;
import SpringSecurity.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {




//        1. fetch Authorization header
        String authHeader=request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

//        2.extract username
        String jwt=authHeader.substring(7);
        String username=jwtService.getUserName(jwt);

        if(username!=null|| SecurityContextHolder.getContext().getAuthentication()==null){
            CustomUserDetails customUserDetails=(CustomUserDetails) customUserDetailsService.loadUserByUsername(
                    username
            );

            if(jwtService.isValid(jwt,customUserDetails)){

                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 6. Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

            filterChain.doFilter(request,response);

        }

    }
}
