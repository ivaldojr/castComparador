package com.castcomparador.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.castcomparador.dto.error.ErrorDetail;
import com.castcomparador.exception.MissingBinaryDataException;
import com.castcomparador.exception.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ivaldo
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Recurso não encontrado.");
        errorDetail.setDetail(rnfe.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingBinaryDataException.class)
    public ResponseEntity<?> handleMissingBinaryDataException(MissingBinaryDataException mbde, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("BinaryData não encontrado.");
        errorDetail.setDetail(mbde.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ile, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("BinaryData não encontrado.");
        errorDetail.setDetail(ile.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Falha na validação.");
        errorDetail.setDetail(manve.getMessage());

        return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Messagem não legivel");
        errorDetail.setDetail(ex.getMessage());
        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

}
