package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.DetailCode;
import com.jaystar.moneyflow.repository.DetailCodeRepository;

import java.util.List;

public class DetailCodeService {
    private DetailCodeRepository detailCodeRepository;

    public DetailCodeService(DetailCodeRepository detailCodeRepository) {
        this.detailCodeRepository = detailCodeRepository;
    }

    public List<DetailCode> getDetailCodes(String masterCode) {
        return detailCodeRepository.findByMasterCode(masterCode);
    }

    public DetailCode modifyDetailCodeName(long id, String detailCodeName) {
        DetailCode detailCode = detailCodeRepository.findById(id);

        //detailCode.modifyDetailCodeName(detailCodeName);

        return detailCode;
    }
}
