package com.energizeglobal.sqlgenerator.mapper;

import com.energizeglobal.sqlgenerator.domain.Profile;
import com.energizeglobal.sqlgenerator.domain.RuleEntity;
import com.energizeglobal.sqlgenerator.dto.RuleDTO;

public class RuleMapper {
    public static RuleEntity dtoToEntity(RuleDTO ruleDto) {
        RuleEntity ruleEntity = new RuleEntity();
        Profile profile = new Profile();
        profile.setId(175L);

        ruleEntity.setId(ruleDto.getId());
        ruleEntity.setName(ruleDto.getName());
        ruleEntity.setOrderRule(ruleDto.getOrderRule());
        ruleEntity.setCreatedBy(ruleDto.getCreatedBy());
        ruleEntity.setLastUpdateBy(ruleDto.getLastUpdateBy());
        ruleEntity.setDescription(ruleDto.getDescription());
        ruleEntity.setLastUpdateDate(ruleDto.getLastUpdateDate());
        ruleEntity.setUpdateState(ruleDto.getUpdateState());
        ruleEntity.setCreationDate(ruleDto.getCreationDate());
        ruleEntity.setProfile(profile);

        return ruleEntity;
    }

    public static RuleDTO entityToDto(RuleEntity ruleEntity) {
        RuleDTO ruleDto = new RuleDTO();

        ruleDto.setId(ruleEntity.getId());
        ruleDto.setName(ruleEntity.getName());
        ruleDto.setOrderRule(ruleEntity.getOrderRule());
        ruleDto.setCreatedBy(ruleEntity.getCreatedBy());
        ruleDto.setLastUpdateBy(ruleEntity.getLastUpdateBy());
        ruleDto.setDescription(ruleEntity.getDescription());
        ruleDto.setLastUpdateDate(ruleEntity.getLastUpdateDate());
        ruleDto.setUpdateState(ruleEntity.getUpdateState());
        ruleDto.setCreationDate(ruleEntity.getCreationDate());

        return ruleDto;
    }
}
