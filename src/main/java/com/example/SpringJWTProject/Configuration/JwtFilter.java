package com.example.SpringJWTProject.Configuration;

import com.example.SpringJWTProject.Services.TokenService;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtFilter extends GenericFilterBean {
    private TokenService tokenService;
    public  JwtFilter(TokenService tokenservice){
        this.tokenService=tokenservice;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletrequest=(HttpServletRequest) req;
        HttpServletResponse httpServletResponse=(HttpServletResponse) res;


        String token=httpServletrequest.getHeader("Authorization");

        if("OPTIONS".equalsIgnoreCase(httpServletrequest.getMethod())){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK,"success");
            return;
        }
        //speific apis without token
        if(allowRequestWithoutToken(httpServletrequest)){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK,"success");
            filterChain.doFilter(req,res);
        }
        else{
            ObjectId userId= new ObjectId((tokenService.getUserIdToken(token)));
            httpServletrequest.setAttribute("userId",userId);
            filterChain.doFilter(req,res);
        }

        }
        public  boolean allowRequestWithoutToken(HttpServletRequest httpServletrequest){
            System.out.println(httpServletrequest.getRequestURI());
            if(httpServletrequest.getRequestURI().contains("/user"))return true;
            return false;
        }

}
