package com.jaystar.moneyflow.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Code {

    @Id
    @GeneratedValue
    @Column(name = "CODE_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Code parent;

    @OneToMany(mappedBy = "parent")
    private List<Code> child = new ArrayList<>();

    public void addChildCode(Code child) {
        this.child.add(child);
        child.setParent(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(id, code.id) &&
                Objects.equals(name, code.name) &&
                Objects.equals(parent, code.parent) &&
                Objects.equals(child, code.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parent, child);
    }
}
