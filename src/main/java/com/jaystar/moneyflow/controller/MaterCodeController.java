package com.jaystar.moneyflow.controller;

import com.jaystar.moneyflow.dto.MasterCodeRequest;
import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.service.MasterCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/master-codes")
public class MaterCodeController {

    private final MasterCodeService masterCodeService;

    public MaterCodeController(MasterCodeService masterCodeService) {
        this.masterCodeService = masterCodeService;
    }

    @GetMapping
    public List<MasterCodeResponse> list() {
        return masterCodeService.findAllMasterCodes();
    }

    @PostMapping
    public ResponseEntity<Void> addMasterCode(@RequestBody MasterCodeRequest masterCodeRequest) {
        Long saveId = masterCodeService.add(masterCodeRequest);
        return ResponseEntity.created(URI.create("/master-codes/" + saveId)).build();
    }
}
