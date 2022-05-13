package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    List<Profile> findAll();

}
