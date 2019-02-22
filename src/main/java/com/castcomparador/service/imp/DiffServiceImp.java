package com.castcomparador.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castcomparador.entity.BinaryData;
import com.castcomparador.enums.Side;
import com.castcomparador.exception.MissingBinaryDataException;
import com.castcomparador.exception.ResourceNotFoundException;
import com.castcomparador.service.BinaryDataService;
import com.castcomparador.service.DiffService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivaldo
 */
@Service
public class DiffServiceImp implements DiffService {

   

    @Autowired
    BinaryDataService binaryDataService;

    public String getDiff(Long id) throws ResourceNotFoundException, MissingBinaryDataException {

        BinaryData binaryData = binaryDataService.get(id).orElseThrow(()
                -> ResourceNotFoundException.build(id));

 
        if ((binaryData.getLeft()==null)||(binaryData.getLeft().isEmpty())) {
            throw MissingBinaryDataException.build(id, Side.LEFT);
        }

        if ((binaryData.getRight()==null)||(binaryData.getRight().isEmpty())) {
            throw MissingBinaryDataException.build(id, Side.RIGHT);
        }

        byte[] sideLeft = binaryData.getLeft().getBytes();
        byte[] sideRight = binaryData.getRight().getBytes();

        /**Check lengths of left and  right sides*/
        if (sideLeft.length != sideRight.length) {
            return "Data is not equal";
        }

        int length = sideLeft.length;
        List<Integer> offSetList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if ((sideLeft[i] ^ sideRight[i]) != 0) {
                offSetList.add(i);
            }
        }

        if (offSetList.isEmpty()) {
            return "Data is equal";
        }

        /**Concat for offsetlist*/
        String offsets = offSetList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        return String.format("Data is same size but offsets are different. Offsets:%s", offsets);

    }


}
