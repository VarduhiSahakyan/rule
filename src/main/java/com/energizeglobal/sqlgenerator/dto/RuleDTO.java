package com.energizeglobal.sqlgenerator.dto;

import com.energizeglobal.sqlgenerator.domain.EntityData;
import com.energizeglobal.sqlgenerator.domain.Profile;

import java.time.Instant;


public class RuleDTO {

    private Long id;
    private String name;
    private Integer orderRule;
    private String createdBy;
    private String lastUpdateBy;
    private String description;
    private Instant creationDate;
    private Instant lastUpdateDate;
    private EntityData.UpdateState updateState;
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(Integer orderRule) {
        this.orderRule = orderRule;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public EntityData.UpdateState getUpdateState() {
        return updateState;
    }

    public void setUpdateState(EntityData.UpdateState updateState) {
        this.updateState = updateState;
    }

    public Instant getCreationDate() { return creationDate; }

    public void setCreationDate(Instant creationDate) { this.creationDate = creationDate; }

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }
}
