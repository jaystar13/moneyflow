package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.dto.MasterCodeRequest;
import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.repository.MasterCodeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class MasterCodeService {

    private final MasterCodeRepository masterCodeRepository;

    public MasterCodeService(MasterCodeRepository masterCodeRepository) {
        this.masterCodeRepository = masterCodeRepository;
    }

    public List<MasterCodeResponse> findAllMasterCodes() {
        return Collections.unmodifiableList(MasterCodeResponse.listOf(masterCodeRepository.findAll()));
    }

    public MasterCodeResponse findMasterCode(long id) {
        return MasterCodeResponse.of(findById(id));
    }

    public Long add(MasterCodeRequest masterCodeRequest) {
        MasterCode masterCode = masterCodeRepository.save(masterCodeRequest.toMasterCode());
        return masterCode.getId();
    }

    public MasterCode update(long id, MasterCodeRequest masterCodeRequest) {
        MasterCode masterCode = findById(id);

        masterCode.update(masterCodeRequest.toMasterCode());

        return masterCode;
    }

    private MasterCode findById(Long id) {
        return masterCodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("마스터 코드를 찾을 수 없습니다."));
    }
}
