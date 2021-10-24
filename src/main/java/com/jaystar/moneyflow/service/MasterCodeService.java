package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.repository.MasterCodeRepository;

import java.util.List;

public class MasterCodeService {

    private final MasterCodeRepository masterCodeRepository;

    public MasterCodeService(MasterCodeRepository masterCodeRepository) {
        this.masterCodeRepository = masterCodeRepository;
    }

    public List<MasterCode> getMasterCodes() {
        return masterCodeRepository.findAll();
    }

    public MasterCode getMasterCodeById(long id) {
        return masterCodeRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public MasterCode addMasterCode(MasterCode masterCode) {
        return masterCodeRepository.save(masterCode);
    }

    public MasterCode updateMasterCodeName(long id, String masterCodeName) {
        MasterCode masterCode = masterCodeRepository.findById(id).orElseThrow();

        masterCode.modifyCodeName(masterCodeName);

        return masterCode;
    }
}
