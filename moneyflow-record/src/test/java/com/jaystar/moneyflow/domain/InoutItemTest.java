package com.jaystar.moneyflow.domain;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.domain.CodeType;
import com.jaystar.moneyflow.inoutitem.domain.InoutItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InoutItemTest {

    @DisplayName("클래스명을 가져온다.")
    @Test
    void classNameTest() {
        //given
        String className = "테스트클래스";
        Code classCode = Code.builder()
                .name(className)
                .codeType(CodeType.builder()
                        .name("코드타입")
                        .build())
                .build();

        //when
        InoutItem inoutItem = new InoutItem();
        inoutItem.setId(1L);
        inoutItem.setClassCode(classCode);

        //then
        assertThat(inoutItem.className()).isEqualTo(className);
    }

    @DisplayName("카테고리명을 가져온다.")
    @Test
    void categoryNameTest() {
        //given
        String categoryName = "테스트카테고리";
        Code categoryCode = Code.builder()
                .name(categoryName)
                .codeType(CodeType.builder()
                        .name("코드타입")
                        .build())
                .build();

        //when
        InoutItem inoutItem = new InoutItem();
        inoutItem.setId(1L);
        inoutItem.setCategoryCode(categoryCode);

        //then
        assertThat(inoutItem.categoryName()).isEqualTo(categoryName);
    }
}
