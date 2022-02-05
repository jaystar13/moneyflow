package com.jaystar.moneyflow.code.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class CodeType {
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeType codeType = (CodeType) o;
        return Objects.equals(id, codeType.id) && Objects.equals(name, codeType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
