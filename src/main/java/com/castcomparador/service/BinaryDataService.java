package com.castcomparador.service;

import java.util.Optional;

import com.castcomparador.dto.BinaryDataDto;
import com.castcomparador.entity.BinaryData;
import com.castcomparador.enums.Side;
import com.castcomparador.exception.MissingBinaryDataException;
import com.castcomparador.exception.ResourceNotFoundException;

/**
 * @author ivaldo
 */
public interface BinaryDataService {
	
     BinaryData save(Long id, BinaryDataDto dto, Side side);
     Optional<BinaryData> get(Long id);
     String getBySide(Long id,Side side) throws ResourceNotFoundException, MissingBinaryDataException;

}
