package com.jaystar.moneyflow.account.domain;

import com.jaystar.moneyflow.code.domain.Code;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @DisplayName("은행명을 가져온다.")
    @Test
    void bankName() {
        //given
        String bankName = "테스트은행";
        Code bankCode = new Code();
        bankCode.setId(1L);
        bankCode.setName(bankName);

        //when
        Account account = new Account();
        account.setId(1L);
        account.setBankCode(bankCode);

        //then
        assertThat(account.bankName()).isEqualTo(bankName);
    }
}
