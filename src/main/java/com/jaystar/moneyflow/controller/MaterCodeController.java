package com.jaystar.moneyflow.controller;

import com.jaystar.moneyflow.domain.MasterCode;
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
    public List<MasterCode> list() {
        return masterCodeService.getMasterCodes();
    }
}
