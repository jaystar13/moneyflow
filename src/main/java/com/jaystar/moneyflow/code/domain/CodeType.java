package com.jaystar.moneyflow.code.domain;

import com.jaystar.moneyflow.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class CodeType extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "CODE_TYPE_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "codeType", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Code> codes = new ArrayList<>();

    @Builder
    public CodeType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCode(Code code) {
        this.codes.add(code);
    }

    public void update(CodeType requestCodeType) {
        this.name = requestCodeType.name;
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
