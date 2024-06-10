package com.whistle6.webfluxdemo.security.domain;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class BearerToken extends AbstractAuthenticationToken {
    private final String token;
    private final UserDetails principal;

    BearerToken(String token, UserDetails principal) {
        super(principal.getAuthorities());
        this.token = token;
        this.principal = principal;
    }

    public Authentication withAuthenticated(boolean isAuthenticated) {
        BearerToken cloned = new BearerToken(token, principal);
        cloned.setAuthenticated(isAuthenticated);
        return cloned;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BearerToken test)) {
            return false;
        }
        if (this.getToken() == null && test.getToken() != null) {
            return false;
        }
        if (this.getToken() != null && !this.getToken().equals(test.getToken())) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int code = super.hashCode();
        if (this.getToken() != null) {
            code ^= this.getToken().hashCode();
        }
        return code;
    }

    
}
