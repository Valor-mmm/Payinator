package de.othr.has44540.logic.services.auth.service.factory;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, TYPE, PARAMETER})
public @interface AuthServiceQualifier {
    AuthServiceCase value();
}
