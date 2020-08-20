package com.example.kpsc.model;
import com.example.kpsc.model.Operation;

import java.io.Serializable;
import java.util.List;


public class Route implements Serializable {
    private String name;
    private List<Operation> operations;
    private long id;

    public Route() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
