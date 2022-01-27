package com.jaystar.moneyflow.code.service;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.repository.CodeRepository;
import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Transactional(readOnly = true)
    public List<CodeResponse> findAllCodes() {
        return Collections.unmodifiableList(CodeResponse.listOf(codeRepository.findAll()));
    }

    @Transactional(readOnly = true)
    public CodeResponse findCode(Long id) {
        return CodeResponse.of(findById(id));
    }

    private Code findById(Long id) {
        return codeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CODE_NOT_FOUND));
    }
}
