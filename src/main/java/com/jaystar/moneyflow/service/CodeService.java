package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.repository.MasterCodeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class CodeService {

    private final MasterCodeRepository masterCodeRepository;

    public CodeService(MasterCodeRepository masterCodeRepository) {
        this.masterCodeRepository = masterCodeRepository;
    }

    public List<MasterCode> getMasterCodes() {
        return masterCodeRepository.findAll();
    }

    public MasterCode getMasterCodeById(long id) {
        return masterCodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
