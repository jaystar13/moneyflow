package com.jaystar.moneyflow.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Code {

    private Long id;

    private String name;

    private Code parent;

    private List<Code> child = new ArrayList<>();

    public void addChildCode(Code child) {
        this.child.add(child);
        child.setParent(this);
    }
}
