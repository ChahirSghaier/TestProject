package tn.esprit.idev.Services;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
@Getter
public class MessageService {
    @Resource
private MessageSource message ;
    private Locale locale = LocaleContextHolder.getLocale();


    public String getMessage(String code) {
        return message.getMessage(code, null, locale);
    }

}
