package com.energizeglobal.sqlgenerator.domain;

import com.energizeglobal.sqlgenerator.enums.UpdateState;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Table(name = "authentmeans")
public class AuthentMeansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "createdBy", nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "creationDate", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "lastUpdateBy")
    private String lastUpdateBy;

    @CreatedDate
    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate;

    @Column(name = "name")
    private String name;

    @Column(name = "updateState", nullable = false)
    @Enumerated(EnumType.STRING)
    private UpdateState updateState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
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

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateState getUpdateState() {
        return updateState;
    }

    public void setUpdateState(UpdateState updateState) {
        this.updateState = updateState;
    }
}
