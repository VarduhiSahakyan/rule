package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.Issuer;
import com.energizeglobal.sqlgenerator.dto.IssuerDTO;
import com.energizeglobal.sqlgenerator.repository.IssuerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class IssuerService {

    private final Logger log = LoggerFactory.getLogger(IssuerService.class);

    private final String FILE_PATH = "src/main/resources/sql_scripts/";
    private final String DATA_FILE_NAME = "data.sql";
    private final String DATA_ROLLBACK_FILE_NAME = "rollback_data.sql";

    private final IssuerRepository issuerRepository;

    public IssuerService(IssuerRepository issuerRepository) {
        this.issuerRepository = issuerRepository;
    }

    @Transactional(readOnly = true)
    public List<Issuer> getAllIssuer() {
        List<Issuer> issuerList = issuerRepository.findAll();
        return issuerList;
    }

    public Issuer findByIssuerByCode(String code) {
        Issuer issuer = issuerRepository.getIssuerByCode(code);
        return issuer;
    }

    public String generateInsertSqlScript(IssuerDTO dto) {
        LocalDateTime date = LocalDateTime.ofInstant(dto.getCreationDate(), ZoneId.of(ZoneOffset.UTC.getId()));
        String Insert = "INSERT INTO Issuer (code, createdBy,creationDate, name, updateState,label, availaibleAuthentMeans) VALUES ('" + dto.getCode() + "', '" + dto.getCreatedBy() + "','" + date + "','" + dto.getName() + "','" + dto.getUpdateState() + "','" + dto.getLabel() + "','" + dto.getAvailaibleAuthentMeans() + "');";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, Insert);
    }

    public String generateInsertSqlScriptWithRollback(IssuerDTO dto) {
        String delete = "DELETE FROM Issuer  WHERE code='" + dto.getCode() + "';";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, delete);
    }

    public String generateEditSqlScript(IssuerDTO dto, String code) {
        String sql = "UPDATE Issuer SET " + "createdBy='" + dto.getCreatedBy() + "', " + "creationDate='" + dto.getCreationDate() + "' ," + "name='" + dto.getName() + "', " + "updateState='" + dto.getUpdateState() + "', " + "label='" + dto.getLabel() + "' , " + "availaibleAuthentMeans='" + dto.getAvailaibleAuthentMeans() + "'" + " WHERE code='" + dto.getCode() + "';";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateEditSqlScriptWithRollback(IssuerDTO dto, String code) {
        Issuer issuer = this.issuerRepository.getIssuerByCode(code);
        String sql = "UPDATE Issuer SET " + "createdBy='" + issuer.getCreatedBy() + "', " + "creationDate='" + issuer.getCreationDate() + "' ," + "name='" + issuer.getName() + "', " + "updateState='" + issuer.getUpdateState() + "', " + "label='" + issuer.getLabel() + "' , " + "availaibleAuthentMeans='" + issuer.getAvailaibleAuthentMeans() + "'" + " WHERE code='" + issuer.getCode() + "';";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScript(String code) {
        String sql = "DELETE FROM Issuer  WHERE code='" + code + "';";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScriptWithRollback(String code) {
        Issuer issuer = this.issuerRepository.getIssuerByCode(code);
        LocalDateTime date = LocalDateTime.ofInstant(issuer.getCreationDate(), ZoneId.of(ZoneOffset.UTC.getId()));
        String sql = "INSERT INTO Issuer (code, createdBy,creationDate, name, updateState,label,availaibleAuthentMeans) VALUES ('" + issuer.getCode() + "', '" + issuer.getCreatedBy() + "','" + date + "','" + issuer.getName() + "','" + issuer.getUpdateState() + "','" + issuer.getLabel() + "','" + issuer.getAvailaibleAuthentMeans() + "');";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    private String storeQueryInFile(String path, String sql) {

        Path newFilePath = Paths.get(path);
        try {
            if (Files.exists(newFilePath)) {
                sql = System.getProperty("line.separator") + sql;
                Files.write(newFilePath, sql.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            } else {
                Path fileDirectory = Paths.get(FILE_PATH);
                Files.createDirectories(fileDirectory);
                Files.write(newFilePath, sql.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DATA_FILE_NAME;
    }
}
