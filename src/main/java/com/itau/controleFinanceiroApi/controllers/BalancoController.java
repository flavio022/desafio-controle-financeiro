package com.itau.controleFinanceiroApi.controllers;

import com.itau.controleFinanceiroApi.dto.BalancoDTO;
import com.itau.controleFinanceiroApi.services.BalancoService;
import com.itau.controleFinanceiroApi.services.ValidationTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "v1/balanco")
@Api(value = "Greeting")
public class BalancoController {
    private BalancoService balancoService;
    public ValidationTokenService validationTokenService;
    Logger logger = Logger.getLogger(LancamentoController.class.getName());

    public BalancoController(BalancoService balancoService,
                             ValidationTokenService validationTokenService){
        this.balancoService = balancoService;
        this.validationTokenService = validationTokenService;
    }
    @ApiOperation(value = "Greets the world or Niteroi")
    @GetMapping()
    public ResponseEntity<List<BalancoDTO>> findAll(@RequestParam String data_inico,
                                                    @RequestParam String data_fim,
                                                    @RequestParam Long id_category,
                                                    @RequestHeader("Authorization")
                                                    String token
                                                    ) {
        try{
            LocalDate dt_ini = stringToDate(data_inico);
            LocalDate dt_fim = stringToDate(data_fim);
            validationTokenService.validation(token);
            logger.info("Token validado, listando balanço");
            List<BalancoDTO> list = balancoService.listAll(dt_fim, dt_ini, id_category);

            return ResponseEntity.ok().body(list);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private LocalDate stringToDate(String dataString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada =  LocalDate.parse(dataString, formatter);

        return dataFormatada;
    }
}
