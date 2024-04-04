package org.example.productservice.security;

import org.example.productservice.dto.ValidateTokenRequestDto;
import org.example.productservice.exception.JwtVerificationException;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class TokenValidator {

    private RestTemplateBuilder restTemplateBuilder;
    private String tokenValidationUrl="http://localhost:8080/auth/validate";

    public TokenValidator(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public SessionStatus validateToken(ValidateTokenRequestDto requestDto) throws Exception
    {

        RestTemplate restTemplate  =restTemplateBuilder.build();
        ResponseEntity<SessionStatus> response=
                restTemplate.postForEntity(tokenValidationUrl,requestDto,SessionStatus.class);

        if(response.getBody()==null)
            throw new JwtVerificationException("Authorization failed.");

        return response.getBody();

    }
}
