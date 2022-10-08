package com.maazouz.authentification_service.Security.spring_security;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="yahya@app.net";
    public static final long EXPIRATION=60000*60;//One Hour
    public static final String HEADER_PREFIX="Bearer ";
}
