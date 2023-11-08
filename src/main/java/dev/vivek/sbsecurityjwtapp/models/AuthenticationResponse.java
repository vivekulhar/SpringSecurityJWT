package dev.vivek.sbsecurityjwtapp.models;

import lombok.Data;

@Data
public class AuthenticationResponse implements java.io.Serializable{
    private final String jwt;
    public AuthenticationResponse(String jwt){
        this.jwt=jwt;
    }
}
