package dev.vivek.sbsecurityjwtapp.models;

import lombok.Data;

@Data
public class AuthenticationRequest implements java.io.Serializable{
    private String username;
    private String password;
    public AuthenticationRequest(String username, String password){
        this.username=username;
        this.password=password;
    }
    public AuthenticationRequest(){
    }
}
