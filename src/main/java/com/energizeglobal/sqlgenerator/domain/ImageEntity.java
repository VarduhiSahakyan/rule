package com.energizeglobal.sqlgenerator.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Image")
public class ImageEntity {

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "image")
    @JsonBackReference
    private List<CustomImageEntity> customImages;

    @Lob
    @Column(columnDefinition = "longblob")
    private String binaryData;

    @Column
    private String relativePath;

    @Column
    private String name;

    @Column
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityData.UpdateState updateState;

    @CreatedDate
    @Column(name = "lastUpdateDate", updatable = false)
    private Instant lastUpdateDate = Instant.now();

    @Column
    private String lastUpdateBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate != null ? (Date) creationDate.clone() : null;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate != null) {
            this.creationDate = (Date) creationDate.clone();
        } else {
            this.creationDate = null;
        }
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public EntityData.UpdateState getUpdateState() {
        return updateState;
    }

    public void setUpdateState(EntityData.UpdateState updateState) {
        this.updateState = updateState;
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

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(String binaryData) {
        this.binaryData = binaryData;
    }

    public List<CustomImageEntity> getCustomImages() {
        return customImages;
    }

    public void setCustomImages(List<CustomImageEntity> customImages) {
        this.customImages = customImages;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}