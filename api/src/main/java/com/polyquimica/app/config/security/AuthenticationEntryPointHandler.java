package com.polyquimica.app.config.security;

import java.io.IOException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.polyquimica.app.common.JsonUtils;
import com.polyquimica.app.common.exception.ErrorCode;
import com.polyquimica.app.common.exception.ErrorDetails;
import com.polyquimica.app.common.exception.GenericErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.getWriter().write(getErrorBody());
    }

    private static String getErrorBody() throws JsonProcessingException {
        ErrorDetails details = ErrorDetails.builder()
        .code(ErrorCode.UNAUTHORIZED)
        .detail(ErrorCode.UNAUTHORIZED.getDefaultMessage())
        .build();
        Map<String, Object> bodyMap = new GenericErrorResponse(details, null).mapOf();
        return JsonUtils.objectToJson(bodyMap);
    }
}
