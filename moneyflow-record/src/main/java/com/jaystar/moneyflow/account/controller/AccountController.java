package com.jaystar.moneyflow.account.controller;

import com.jaystar.moneyflow.account.dto.AccountRequest;
import com.jaystar.moneyflow.account.dto.AccountResponse;
import com.jaystar.moneyflow.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findAccount(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AccountRequest accountRequest) {
        Long id = accountService.saveAccount(accountRequest);
        return ResponseEntity.created(URI.create("/api/accounts/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid AccountRequest accountRequest) {
        accountService.update(id, accountRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
