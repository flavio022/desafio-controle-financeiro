package com.itau.controleFinanceiroApi.controllers;

import com.itau.controleFinanceiroApi.dto.LancamentoDTO;
import com.itau.controleFinanceiroApi.services.LancamentoService;
import com.itau.controleFinanceiroApi.services.ValidationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "v1/lancamentos")
public class LancamentoController {
    Logger logger = Logger.getLogger(LancamentoController.class.getName());

    private LancamentoService lancamentoService;
    public ValidationTokenService validationTokenService;

    public LancamentoController(LancamentoService lancamentoService,
                                ValidationTokenService validationTokenService){
        this.lancamentoService = lancamentoService;
        this.validationTokenService = validationTokenService;
    }
    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> findAll(
            @RequestHeader("Authorization")
            String token) {
        try {
            validationTokenService.validation(token);
            logger.info("Token validado, listando lançamentos");
            List<LancamentoDTO> list = lancamentoService.listAll();

            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<LancamentoDTO> insert(
            @RequestBody LancamentoDTO dto,
            @RequestHeader("Authorization")
            String token) throws Exception {
        try {
            validationTokenService.validation(token);
            logger.info("Token validado, inserindo um novo lançamento");
            dto = lancamentoService.insert(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
