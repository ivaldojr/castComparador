package com.castcomparador.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castcomparador.dto.BinaryDataDto;
import com.castcomparador.entity.BinaryData;
import com.castcomparador.enums.Side;
import com.castcomparador.exception.MissingBinaryDataException;
import com.castcomparador.exception.ResourceNotFoundException;
import com.castcomparador.repository.BinaryDataRepository;
import com.castcomparador.service.BinaryDataService;

import java.util.Optional;

/**
 * @author ivaldo
 */
@Service
public class BinaryDataServiceImp implements BinaryDataService {

    @Autowired
    BinaryDataRepository binaryDataRepository;

    @Override
    public BinaryData save(Long id, BinaryDataDto binaryDataDto, Side side) {


        Optional<BinaryData> binaryData = get(id);

        BinaryData entity = null;

        if (!binaryData.isPresent()) {
            entity = new BinaryData();
            entity.setId(id);
        } else {
            entity = binaryData.get();
        }

        if (side.equals(Side.LEFT)) {
            entity.setLeft(binaryDataDto.getData());
        } else {
            entity.setRight(binaryDataDto.getData());
        }

        return binaryDataRepository.save(entity);

    }

    @Override
    public Optional<BinaryData> get(Long id) {
        return binaryDataRepository.findById(id);
    }

    @Override
    public String getBySide(Long id, Side side) throws ResourceNotFoundException, MissingBinaryDataException{


        BinaryData binaryData = get(id).orElseThrow(()
                -> ResourceNotFoundException.build(id));


        String message = "";

        if (side.equals(Side.LEFT)) {
            message = binaryData.getLeft();
        } else {
            message = binaryData.getRight();
        }

        if (message.isEmpty()) {
            throw MissingBinaryDataException.build(id, side);
        }

        return message;
    }
}
