package com.crackit.SpringSecurityJWT.auth;

public class UserActiveRequest {

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
