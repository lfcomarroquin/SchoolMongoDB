package com.api.SchoolMongoDB.dto.auth;
// Este DTO va a manejar la respuesta despues de la autenticación
//Segundo, el servidor me va a responder con este Dto el cual contiene el token
public class AuthDto {

    private String token;

    public AuthDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
