package de.othr.external.services.oauth;

import javax.xml.ws.BindingProvider;

public class ChangeServiceHost <T> {

    protected T changeServiceHost(T service, String newHost) {
        BindingProvider bindingProvider = (BindingProvider) service;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newHost);
        return service;
    }
}
