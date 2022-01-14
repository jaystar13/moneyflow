package com.jaystar.moneyflow.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CodeTest {

    @DisplayName("자식코드를 추가한다.")
    @Test
    void addChildCodeTest() {
        //given
        Code code = new Code();
        code.setId(1L);
        code.setName("PARENT1");

        Code childCode1 = new Code();
        childCode1.setId(1L);
        childCode1.setName("CHILD1");

        Code childCode2 = new Code();
        childCode2.setId(1L);
        childCode2.setName("CHILD2");

        Code childCode3 = new Code();
        childCode3.setId(1L);
        childCode3.setName("CHILD3");

        //when
        code.addChildCode(childCode1);
        code.addChildCode(childCode2);

        //then
        assertThat(code.getChild()).contains(childCode1, childCode2);
        assertThat(code.getChild()).doesNotContain(childCode3);
    }
}
