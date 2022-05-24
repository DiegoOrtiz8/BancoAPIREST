package com.ibm.academia.apirest.exceptions.handler;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TarjetasRESTException {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> formatoInvalidoException(BadRequestException exception) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("Error", exception.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> noExisteException(NotFoundException exception) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("Error", exception.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }
}
