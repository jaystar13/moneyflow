package com.jaystar.moneyflow.code.service;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.dto.CodeRequest;
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

    @Transactional
    public Long save(CodeRequest codeRequest) {
        Code code = codeRequest.toCode();
        codeRepository.save(code);

        return code.getId();
    }

    @Transactional
    public CodeResponse updateCode(Long id, CodeRequest codeRequest) {
        Code code = findById(id);
        code.update(codeRequest.toCode());

        return CodeResponse.of(code);
    }

    @Transactional
    public void deleteCode(Long id) {
        codeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CodeResponse> findByNameContaining(String name) {
        List<Code> codesContainingName = codeRepository.findByNameContaining(name);
        return CodeResponse.listOf(codesContainingName);
    }
}
