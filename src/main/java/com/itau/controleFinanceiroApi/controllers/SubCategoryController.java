package com.itau.controleFinanceiroApi.controllers;

import com.itau.controleFinanceiroApi.dto.SubCategoryDTO;
import com.itau.controleFinanceiroApi.services.SubCategoryService;
import com.itau.controleFinanceiroApi.services.ValidationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/subcategories")
public class SubCategoryController {
    private SubCategoryService subCategoryService;
    public ValidationTokenService validationTokenService;

    public SubCategoryController(
            SubCategoryService subCategoryService,
            ValidationTokenService validationTokenService){

        this.subCategoryService = subCategoryService;
        this.validationTokenService = validationTokenService;
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>> findAll(
            @RequestHeader("Authorization")
            String token ) {
        try {
            validationTokenService.validation(token);
            List<SubCategoryDTO> list = subCategoryService.listAll();

            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<SubCategoryDTO> insert(
            @RequestBody SubCategoryDTO dto,
            @RequestHeader("Authorization")
            String token) throws Exception {
        try {
            validationTokenService.validation(token);
            dto = subCategoryService.insert(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
