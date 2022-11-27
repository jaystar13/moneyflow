package com.jaystar.moneyflow.account.service;

import com.jaystar.moneyflow.account.domain.Account;
import com.jaystar.moneyflow.account.dto.AccountRequest;
import com.jaystar.moneyflow.account.dto.AccountResponse;
import com.jaystar.moneyflow.account.repository.AccountRepository;
import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final FinancialCompanyRepository financialCompanyRepository;

    @Transactional(readOnly = true)
    public AccountResponse findAccount(Long id) {
        return AccountResponse.of(findAccountById(id));
    }

    private Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> findAllAccounts() {
        return AccountResponse.listOf(accountRepository.findAll());
    }

    @Transactional
    public Long saveAccount(AccountRequest request) {
        Account account = request.toAccount(findFinancialCompany(request.getFinancialCompanyId()));
        accountRepository.save(account);
        return account.getId();
    }

    @Transactional
    public AccountResponse update(Long id, AccountRequest request) {
        Account account = findAccountById(id);
        FinancialCompany financialCompany = findFinancialCompany(request.getFinancialCompanyId());
        account.update(request.toAccount(financialCompany));
        return AccountResponse.of(account);
    }

    @Transactional(readOnly = true)
    private FinancialCompany findFinancialCompany(Long id) {
        return financialCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.FINANCIAL_COMPANY_NOT_FOUND));
    }

    @Transactional
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
