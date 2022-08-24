package com.jaystar.moneyflow.code.domain;

import com.jaystar.moneyflow.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Code extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "CODE_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CODE_TYPE_ID", nullable = false)
    private CodeType codeType;

    @Builder
    public Code(Long id, String name, CodeType codeType) {
        this.id = id;
        this.name = name;
        setCodeType(codeType);
    }

    public void setCodeType(CodeType codeType) {
        codeType.addCode(this);
        this.codeType = codeType;
    }

    public Code update(Code codeRequest) {
        this.name = codeRequest.getName();
        this.codeType = codeRequest.getCodeType();

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(id, code.id) && Objects.equals(name, code.name) && Objects.equals(codeType, code.codeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, codeType);
    }
}
