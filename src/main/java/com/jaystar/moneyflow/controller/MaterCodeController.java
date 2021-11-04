package com.jaystar.moneyflow.controller;

import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.service.MasterCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaterCodeController {

    private final MasterCodeService masterCodeService;

    public MaterCodeController(MasterCodeService masterCodeService) {
        this.masterCodeService = masterCodeService;
    }

    @GetMapping("/master-codes")
    public List<MasterCodeResponse> list() {
        return masterCodeService.getMasterCodes();
    }
}
