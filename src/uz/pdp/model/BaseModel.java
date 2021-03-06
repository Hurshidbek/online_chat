package uz.pdp.model;

import java.util.UUID;

public abstract class BaseModel {
    protected final UUID id;
    protected String name;

    {
        this.id = UUID.randomUUID();
    }

    public BaseModel(String name) {
        this.name = name;
    }

    public BaseModel() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected abstract boolean checkName();
}
