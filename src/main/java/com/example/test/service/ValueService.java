package com.example.test.service;

import com.example.test.entity.ValueEntity;
import com.example.test.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ValueService {
    private final ValueRepository valueRepository;

    @Autowired
    public ValueService(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public int getValue() {
        return valueRepository.findById(1L)
                .map(ValueEntity::getCountedValue)
                .orElse(0);
    }

    public void setValue(int value) {
        ValueEntity valueEntity = valueRepository.findById(1L)
                .orElseGet(ValueEntity::new);
        valueEntity.setCountedValue(value);
        valueRepository.save(valueEntity);
    }
}
