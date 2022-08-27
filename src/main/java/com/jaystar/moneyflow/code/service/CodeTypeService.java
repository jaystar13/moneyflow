package com.jaystar.moneyflow.code.service;

import com.jaystar.moneyflow.code.domain.CodeType;
import com.jaystar.moneyflow.code.dto.CodeTypeRequest;
import com.jaystar.moneyflow.code.dto.CodeTypeResponse;
import com.jaystar.moneyflow.code.repository.CodeTypeRepository;
import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeTypeService {

    private final CodeTypeRepository codeTypeRepository;

    @Transactional(readOnly = true)
    public CodeTypeResponse findCodeType(Long id) {
        return CodeTypeResponse.of(findById(id));
    }

    private CodeType findById(Long id) {
        return codeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CODE_TYPE_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<CodeTypeResponse> findAllCodeTypes() {
        return Collections.unmodifiableList(CodeTypeResponse.listOf(codeTypeRepository.findAll()));
    }

    @Transactional
    public Long save(CodeTypeRequest request) {
        CodeType codeType = codeTypeRepository.save(request.toCodeType());
        return codeType.getId();
    }

    @Transactional
    public CodeTypeResponse update(Long id, CodeTypeRequest request) {
        CodeType codeType = findById(id);
        codeType.update(request.toCodeType());

        return CodeTypeResponse.of(codeType);
    }

    @Transactional
    public void delete(Long id) {
        codeTypeRepository.deleteById(id);
    }

    @Transactional
    public List<CodeTypeResponse> findByNameContaining(String name) {
        return CodeTypeResponse.listOf(codeTypeRepository.findByNameContaining(name));
    }
}
