package com.castcomparador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.castcomparador.service.DiffService;


/**
 * @author ivaldo
 */

@RestController("getDiffControllerV1")
@RequestMapping("/v1/diff/{id}")
public class DiffController {

    private static final Logger logger = LoggerFactory.getLogger(DiffController.class);

    @Autowired
    DiffService diffService;

    /**
     * Método responsável por comparar os BinaryData
     *
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDiff(@PathVariable Long id) {

        String message = diffService.getDiff(id);

        return new ResponseEntity<String>(message, HttpStatus.OK);
    }


}
