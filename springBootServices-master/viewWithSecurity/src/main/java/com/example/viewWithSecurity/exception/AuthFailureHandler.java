package com.example.viewWithSecurity.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {


//        response.setStatus(HttpStatus.FORBIDDEN.value());
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", new Date());
        data.put("status", HttpStatus.FORBIDDEN.value());
        data.put("message", "Access Denied:" + ex.getMessage());
        data.put("path", request.getRequestURL().toString());


        //return json
//        OutputStream out = response.getOutputStream();
//        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(out, data);
//        out.flush();

        //or

        request.setAttribute("message", ex.toString());
        request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request,response);
    }


}

