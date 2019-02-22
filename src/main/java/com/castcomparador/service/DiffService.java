package com.castcomparador.service;

import com.castcomparador.exception.MissingBinaryDataException;
import com.castcomparador.exception.ResourceNotFoundException;

/**
 * @author ivaldo
 */
public interface DiffService {
     String getDiff(Long id) throws ResourceNotFoundException,MissingBinaryDataException;
}
