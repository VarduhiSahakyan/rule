package com.energizeglobal.sqlgenerator.dto;

import java.time.Instant;

public class IssuerDTO {

    private String id;
    private String code;
    private String createdBy;
    private Instant creationDate;
    private String description;
    private String name;
    private String updateState;
    private String label;
    private String availaibleAuthentMeans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateState() {
        return updateState;
    }

    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAvailaibleAuthentMeans() {
        return availaibleAuthentMeans;
    }

    public void setAvailaibleAuthentMeans(String availaibleAuthentMeans) {
        this.availaibleAuthentMeans = availaibleAuthentMeans;
    }
}
