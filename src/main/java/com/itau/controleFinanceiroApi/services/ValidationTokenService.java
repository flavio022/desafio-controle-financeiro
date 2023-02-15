package com.itau.controleFinanceiroApi.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ValidationTokenService {

    public HttpStatus validation(String token){
        if(!token.trim().equals("aXRhw7o=.")){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        return HttpStatus.OK;
    }
}
