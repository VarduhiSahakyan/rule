package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.AuthentMeansEntity;
import com.energizeglobal.sqlgenerator.dto.AuthentMeansDTO;
import com.energizeglobal.sqlgenerator.mapper.Mapping;
import com.energizeglobal.sqlgenerator.repository.AuthentMeansRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthentMeansService {

    public Logger logger = LoggerFactory.getLogger(AuthentMeansService.class);

    static boolean activeDB = false;

    private final String INSERT_SQL_FILE_NAME = "insert_query.sql";

    private final String ROLLBACK_SQL_FILE_NAME = "rollback_query.sql";

    private final Mapping mappingAuthentMean;

    private final AuthentMeansRepository authentMeansRepository;

    private final GenerateSqlScriptService generateSqlScriptService;

    private final DownloadFileService downloadFileService;

    public AuthentMeansService(Mapping mappingAuthentMean,
                               AuthentMeansRepository authentMeansRepository,
                               GenerateSqlScriptService generateSqlScriptService,
                               DownloadFileService downloadFileService) {
        this.mappingAuthentMean = mappingAuthentMean;
        this.authentMeansRepository = authentMeansRepository;
        this.generateSqlScriptService = generateSqlScriptService;
        this.downloadFileService = downloadFileService;
    }

    @Transactional
    public List<AuthentMeansDTO> getAllAuthentMeans() {

        List<AuthentMeansEntity> authentMeansEntityList = authentMeansRepository.findAll();
        return mappingAuthentMean.mapList(authentMeansEntityList, AuthentMeansDTO.class);
    }

    @Transactional
    public AuthentMeansDTO getByIdAuthentMean(Long id) {

        AuthentMeansEntity authentMeansEntity = authentMeansRepository.getById(id);


        return mappingAuthentMean.convertToDto(authentMeansEntity, AuthentMeansDTO.class);
    }

    @Transactional
    public String deleteAuthentMeanById(Long id) {

        AuthentMeansEntity authentMeansEntity = authentMeansRepository.getById(id);

        String deleteQuery = "DELETE FROM `authentmeans` WHERE id ='" + id + "';";

        String rollbackQuery = String.format("INSERT INTO `authentmeans` (createdBy, creationDate, description, lastUpdateBy, " +
                        "lastUpdateDate, name, updateState) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                authentMeansEntity.getCreatedBy(),
                authentMeansEntity.getCreationDate(),
                authentMeansEntity.getDescription(),
                authentMeansEntity.getLastUpdateBy(),
                authentMeansEntity.getLastUpdateDate(),
                authentMeansEntity.getName(),
                authentMeansEntity.getUpdateState());

        generateSqlScriptService.insertSqlScript(deleteQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(rollbackQuery, ROLLBACK_SQL_FILE_NAME);
        if (activeDB) {
            authentMeansRepository.deleteById(id);
        }
        return INSERT_SQL_FILE_NAME;
    }

    @Transactional
    public String updateAuthentMean(AuthentMeansDTO authentMeansDTO) {

        AuthentMeansEntity authentMeansEntity = mappingAuthentMean.convertToEntity(authentMeansDTO, AuthentMeansEntity.class);
        authentMeansEntity.setLastUpdateDate(LocalDateTime.now());
        authentMeansRepository.getById(authentMeansDTO.getId());
        String updateQuery = String.format("UPDATE `authentmeans` SET createdBy='%s', creationDate='%s', description='%s'," +
                        " lastUpdateBy='%s', lastUpdateDate='%s', name='%s', updateState='%s' WHERE id='%s';",
                authentMeansEntity.getCreatedBy(),
                authentMeansEntity.getCreationDate(),
                authentMeansEntity.getDescription(),
                authentMeansEntity.getLastUpdateBy(),
                authentMeansEntity.getLastUpdateDate(),
                authentMeansEntity.getName(),
                authentMeansEntity.getUpdateState(),
                authentMeansEntity.getId());

        AuthentMeansEntity authentMeansEntityRollback = authentMeansRepository.getById(authentMeansDTO.getId());
        String rollbackUpdateQuery = String.format("UPDATE `authentmeans` SET createdBy='%s', creationDate='%s', description='%s'," +
                        " lastUpdateBy='%s', lastUpdateDate='%s', name='%s', updateState='%s' WHERE id='%s';",
                authentMeansEntityRollback.getCreatedBy(),
                authentMeansEntityRollback.getCreationDate(),
                authentMeansEntityRollback.getDescription(),
                authentMeansEntityRollback.getLastUpdateBy(),
                authentMeansEntityRollback.getLastUpdateDate(),
                authentMeansEntityRollback.getName(),
                authentMeansEntityRollback.getUpdateState(),
                authentMeansEntityRollback.getId());


        generateSqlScriptService.insertSqlScript(updateQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(rollbackUpdateQuery, ROLLBACK_SQL_FILE_NAME);
        if (activeDB) {
            authentMeansRepository.save(authentMeansEntity);
        }
        return INSERT_SQL_FILE_NAME;
    }

    @Transactional
    public String saveAuthentMean(AuthentMeansDTO authentMeansDTO) {

        AuthentMeansEntity authentMeansEntity = mappingAuthentMean.convertToEntity(authentMeansDTO, AuthentMeansEntity.class);
        if (authentMeansEntity.getCreationDate() == null) {
            authentMeansEntity.setCreationDate(LocalDateTime.now());
        }
        Long lastId = authentMeansRepository.getMaxId();

        String insertQuery = String.format("INSERT INTO `authentmeans` (createdBy, creationDate, description, lastUpdateBy, " +
                        "lastUpdateDate, name, updateState) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                authentMeansEntity.getCreatedBy(),
                authentMeansEntity.getCreationDate(),
                authentMeansEntity.getDescription(),
                authentMeansEntity.getLastUpdateBy(),
                authentMeansEntity.getLastUpdateDate(),
                authentMeansEntity.getName(),
                authentMeansEntity.getUpdateState());

        String deleteQuery = "DELETE FROM `authentmeans` WHERE id ='" + lastId + "';";

        generateSqlScriptService.insertSqlScript(insertQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(deleteQuery, ROLLBACK_SQL_FILE_NAME);

        if (activeDB) {
            authentMeansRepository.save(authentMeansEntity);
        }
        return INSERT_SQL_FILE_NAME;
    }

    public Resource getDownloadFile(String FILE_NAME) {
        String FILE_PATH = "src/main/resources/sql_scripts/";
        return downloadFileService.downloadFile(FILE_NAME, FILE_PATH);
    }
}
