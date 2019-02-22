package com.castcomparador.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.castcomparador.entity.BinaryData;

/**
 * @author ivaldo
 */
@Repository
public interface BinaryDataRepository extends CrudRepository<BinaryData, Long> {

}