package com.polyquimica.app.domain.user.controller;

import java.util.Locale;
import java.util.Map;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;

import com.polyquimica.app.common.LangUtils;
import com.polyquimica.app.common.exception.ErrorCode;
import com.polyquimica.app.common.exception.ErrorDetails;
import com.polyquimica.app.common.exception.GenericErrorResponse;
import com.polyquimica.app.domain.user.exception.UserException;
import com.polyquimica.app.domain.user.model.User;
import com.polyquimica.app.domain.user.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) throws UserException {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(Locale locale) {
        System.out.println(locale.getLanguage());
        // User user = userService.createUser();
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserExceptions(UserException ex) {
        Map<String, Object> response = null;
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code(ErrorCode.USER_EXCEPTION)
                .detail(ex.getLocalizedMessage()).build();
        response = new GenericErrorResponse(errorDetails, null).mapOf();
        return ResponseEntity.badRequest().body(response);
    }

}
