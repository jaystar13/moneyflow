package com.jaystar.moneyflow.code.controller;

import com.jaystar.moneyflow.code.dto.CodeTypeRequest;
import com.jaystar.moneyflow.code.dto.CodeTypeResponse;
import com.jaystar.moneyflow.code.service.CodeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/code-types")
public class CodeTypeController {
    private final CodeTypeService codeTypeService;

    @GetMapping
    public ResponseEntity<List<CodeTypeResponse>> findAllCodeTypes() {
        return ResponseEntity.ok(codeTypeService.findAllCodeTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeTypeResponse> findCodeType(@PathVariable Long id) {
        return ResponseEntity.ok(codeTypeService.findCodeType(id));
    }

    @GetMapping("/contain-word")
    public ResponseEntity<List<CodeTypeResponse>> findByNameContaining(@RequestParam(defaultValue = "") String contain) {
        return ResponseEntity.ok(codeTypeService.findByNameContaining(contain));
    }

    @PostMapping
    public ResponseEntity<Void> addCodeType(@RequestBody @Valid CodeTypeRequest codeTypeRequest) {
        Long saveId = codeTypeService.save(codeTypeRequest);
        return ResponseEntity.created(URI.create("/api/code-types/" + saveId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCodeType(@PathVariable Long id, @RequestBody @Valid CodeTypeRequest codeTypeRequest) {
        codeTypeService.update(id, codeTypeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodeType(@PathVariable Long id) {
        codeTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
