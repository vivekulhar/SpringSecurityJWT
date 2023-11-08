package dev.vivek.sbsecurityjwtapp.rest;

import dev.vivek.sbsecurityjwtapp.models.AuthenticationRequest;
import dev.vivek.sbsecurityjwtapp.models.AuthenticationResponse;
import dev.vivek.sbsecurityjwtapp.security.MyUserDetailsService;
import dev.vivek.sbsecurityjwtapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloRestController {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
