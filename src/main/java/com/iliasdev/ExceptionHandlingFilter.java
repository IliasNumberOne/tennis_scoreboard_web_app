package com.iliasdev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iliasdev.dto.ErrorResponseDTO;
import com.iliasdev.exception.InvalidParameterException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.*;

@WebFilter("/*")
public class ExceptionHandlingFilter extends HttpFilter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try{
            super.doFilter(req, res, chain);
        }
        catch(InvalidParameterException e){
            writeErrorResponse(res, SC_BAD_REQUEST, e);
        }
    }

    private void writeErrorResponse(HttpServletResponse resp, int errorCode, RuntimeException errorMessage) throws IOException {
        resp.setStatus(errorCode);

        objectMapper.writeValue(resp.getWriter(), new ErrorResponseDTO(errorCode, errorMessage.getMessage()));
    }
}