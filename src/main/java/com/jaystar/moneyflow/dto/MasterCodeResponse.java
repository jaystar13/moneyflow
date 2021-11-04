package com.jaystar.moneyflow.dto;

import com.jaystar.moneyflow.domain.MasterCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MasterCodeResponse {
    private Long id;

    private String code;

    private String codeName;

    public static MasterCodeResponse of(MasterCode masterCode) {
        return new MasterCodeResponse(masterCode.getId(), masterCode.getCode(), masterCode.getCodeName());
    }

    public static List<MasterCodeResponse> listOf(List<MasterCode> masterCodes) {
        List<MasterCodeResponse> masterCodeResponses = new ArrayList<>();

        for (MasterCode masterCode : masterCodes) {
            masterCodeResponses.add(of(masterCode));
        }

        return masterCodeResponses;
    }
}
