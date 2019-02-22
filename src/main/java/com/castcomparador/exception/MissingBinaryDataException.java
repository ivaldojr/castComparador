package com.castcomparador.exception;


import com.castcomparador.enums.Side;

/**
 * Falta BinaryData Exception.
 * @author ivaldo
 */
public class MissingBinaryDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MissingBinaryDataException() {
    }

    public MissingBinaryDataException(String message) {
        super(message);
    }

    public MissingBinaryDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public static MissingBinaryDataException build(Long id, Side side) {

        if(side.equals(Side.LEFT)){
            return new MissingBinaryDataException("Está faltando o lado esquerdo.");
        }else{
            return new MissingBinaryDataException("Está faltando o lado direito.");
        }
    }
}