package com.castcomparador.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.castcomparador.dto.BinaryDataDto;
import com.castcomparador.entity.BinaryData;
import com.castcomparador.enums.Side;
import com.castcomparador.service.BinaryDataService;

import javax.validation.Valid;

/**
 * 
 *
 * @author ivaldo
 */

@RestController("binaryDataControllerV1")
@RequestMapping("/v1/diff/{id}")
public class BinaryDataController {

    private static final Logger logger = LoggerFactory.getLogger(BinaryDataController.class);

    @Autowired
    BinaryDataService binaryDataService;


    /**
     * Método utilizado para cadastrar ou atualizar um BinaryData da esquerda a partir de um id. 
     *
     */
    @RequestMapping(value = "/left", method = RequestMethod.POST)
    public ResponseEntity<Void> createLeftBinaryData(@PathVariable Long id, @Valid @RequestBody BinaryDataDto binaryDataDto) {

        BinaryData binaryData = binaryDataService.save(id, binaryDataDto, Side.LEFT);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(binaryData.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    /**
     * Método utilizado para cadastrar ou atualizar um BinaryData da direita a partir de um id. 
     *
     */
    @RequestMapping(value = "/right", method = RequestMethod.POST)
    public ResponseEntity<Void> createRightBinaryData(@PathVariable Long id, @Valid @RequestBody BinaryDataDto binaryDataDto) {

        BinaryData binaryData = binaryDataService.save(id, binaryDataDto, Side.RIGHT);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(binaryData.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Responsavel por seleceionar um BinaryData a partir do lado escolhido.
     *
     */
    @RequestMapping(value = "/{side}", method = RequestMethod.GET)
    public ResponseEntity<String> getBySide(@PathVariable Long id, @PathVariable String side) {
        String data = binaryDataService.getBySide(id, getSide(side));

        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<String>(data, HttpStatus.OK);
    }

    private Side getSide(String side) throws IllegalArgumentException {
        
        if (!side.equalsIgnoreCase("left") && !side.equalsIgnoreCase("right")) {
            throw new IllegalArgumentException("Não foi encontrando um lado.");
        }

        return side.equals(Side.LEFT.getValue()) ? Side.LEFT : Side.RIGHT;

    }


}
