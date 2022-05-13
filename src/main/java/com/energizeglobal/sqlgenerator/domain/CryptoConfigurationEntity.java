package com.energizeglobal.sqlgenerator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "cryptoconfig")
public class CryptoConfigurationEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "protocolOne")
    private String protocolOne;

    @Column(name = "protocolTwo")
    private String protocolTwo;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "cryptoConfigEntity")
    @Lazy
    @JsonIgnore
    private List<SubIssuer> linkedSubIssuers = new ArrayList<>();


    @OneToMany(mappedBy = "cryptoConfigurationEntity")
    @JsonIgnore
    private List<BinRangeEntity> linkedBinRanges = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolOne() {
        return protocolOne;
    }

    public void setProtocolOne(String protocolOne) {
        this.protocolOne = protocolOne;
    }

    public String getProtocolTwo() {
        return protocolTwo;
    }

    public void setProtocolTwo(String protocolTwo) {
        this.protocolTwo = protocolTwo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubIssuer> getLinkedSubIssuers() {
        return linkedSubIssuers;
    }

    public void setLinkedSubIssuers(List<SubIssuer> linkedSubIssuers) {
        this.linkedSubIssuers = linkedSubIssuers;
    }

    public List<BinRangeEntity> getLinkedBinRanges() {
        return linkedBinRanges;
    }

    public void setLinkedBinRanges(List<BinRangeEntity> linkedBinRanges) {
        this.linkedBinRanges = linkedBinRanges;
    }
}
