package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.dto.MasterCodeRequest;
import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.repository.MasterCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MasterCodeService {

    private final MasterCodeRepository masterCodeRepository;

    public MasterCodeService(MasterCodeRepository masterCodeRepository) {
        this.masterCodeRepository = masterCodeRepository;
    }

    public List<MasterCodeResponse> getMasterCodes() {
        return Collections.unmodifiableList(MasterCodeResponse.listOf(masterCodeRepository.findAll()));
    }

    public MasterCode getMasterCodeById(long id) {
        return masterCodeRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public Long addMasterCode(MasterCodeRequest masterCodeRequest) {
        MasterCode masterCode = masterCodeRepository.save(masterCodeRequest.toMasterCode());
        return masterCode.getId();
    }

    public MasterCode updateMasterCodeName(long id, String masterCodeName) {
        MasterCode masterCode = masterCodeRepository.findById(id).orElseThrow();

        masterCode.modifyCodeName(masterCodeName);

        return masterCode;
    }
}
