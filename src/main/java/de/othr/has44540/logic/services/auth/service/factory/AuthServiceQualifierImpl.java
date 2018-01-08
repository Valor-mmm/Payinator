package de.othr.has44540.logic.services.auth.service.factory;

import javax.enterprise.util.AnnotationLiteral;

public class AuthServiceQualifierImpl extends AnnotationLiteral<AuthServiceQualifier> implements AuthServiceQualifier{

    private final AuthServiceCase serviceCase;

    public AuthServiceQualifierImpl(final AuthServiceCase serviceCase) {
        this.serviceCase = serviceCase;
    }

    @Override
    public AuthServiceCase value() {
        return serviceCase;
    }
}
