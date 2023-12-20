package com.polyquimica.app.domain.user.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.polyquimica.app.common.LangUtils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class LocalizedExc extends RuntimeException {

    private final LangUtils langUtils;

    public LocalizedExc(String messageKey) {
        super(messageKey);
    }

    @Override
    public String getLocalizedMessage() {
        String exMessage = super.getMessage();
        return langUtils.getLocalizedMessage(exMessage, LocaleContextHolder.getLocale());
    }
}

public class UserException extends LocalizedExc {

    public UserException(String messageKey) {
        super(messageKey);
    }


}
