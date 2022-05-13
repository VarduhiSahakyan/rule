package com.energizeglobal.sqlgenerator.domain;

import java.time.Instant;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "Rule")
public class RuleEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String createdBy;

    @Column(name = "creationDate", nullable = false)
    private Instant creationDate;

    @Column
    private String description;

    @Column
    private String lastUpdateBy;

    @CreatedDate
    @Column
    private Instant lastUpdateDate = Instant.now();

    @Column
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityData.UpdateState updateState;

    @Column(nullable = false)
    private Integer orderRule;

    @ManyToOne
    @JoinColumn(name = "fk_id_profile", referencedColumnName = "id", updatable = false)
    @JsonIgnore
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

    public Instant getCreationDate() { return creationDate; }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityData.UpdateState getUpdateState() {
        return updateState;
    }

    public void setUpdateState(EntityData.UpdateState updateState) {
        this.updateState = updateState;
    }
}

