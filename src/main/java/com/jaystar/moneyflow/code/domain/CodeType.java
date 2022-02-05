package com.jaystar.moneyflow.code.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
public class CodeType {
    @Id
    @GeneratedValue
    @Column(name = "CODE_TYPE_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "codeType")
    private List<Code> codes = new ArrayList<>();

    public void update(CodeType requestCodeType) {
        this.name = requestCodeType.name;
    }

    public void removeCode(Code code) {
        codes.remove(code);
    }

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
