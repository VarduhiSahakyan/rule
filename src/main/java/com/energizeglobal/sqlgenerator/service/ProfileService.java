package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.Profile;
import com.energizeglobal.sqlgenerator.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public List<Profile> getAllProfiles(){
        List<Profile> profiles = this.repository.findAll();
        return profiles;
    }
}
