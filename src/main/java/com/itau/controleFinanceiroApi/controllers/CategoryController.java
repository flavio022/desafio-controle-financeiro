package com.itau.controleFinanceiroApi.controllers;

import com.itau.controleFinanceiroApi.dto.CategoryDTO;
import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.services.CategoryService;
import com.itau.controleFinanceiroApi.services.ValidationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "v1/categories")
public class CategoryController {
    private CategoryService categoryService;
    public ValidationTokenService validationTokenService;
    Logger logger = Logger.getLogger(LancamentoController.class.getName());

    public CategoryController(CategoryService categoryService,
                              ValidationTokenService validationTokenService){

        this.categoryService = categoryService;
        this.validationTokenService = validationTokenService;

    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(
            @RequestHeader("Authorization")
            String token) {
        try {
            validationTokenService.validation(token);
            logger.info("Token validado, listando categorias");
            List<CategoryDTO> list = categoryService.listAll();

            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<Category> insert(
            @RequestBody CategoryDTO dto,
            @RequestHeader("Authorization")
            String token) {
        try {
            validationTokenService.validation(token);
            logger.info("Token validado, inserindo dados da categoria");
            Category category = categoryService.insert(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(category);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
