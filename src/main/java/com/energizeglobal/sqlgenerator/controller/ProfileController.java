package com.energizeglobal.sqlgenerator.controller;

import com.energizeglobal.sqlgenerator.domain.Profile;
import com.energizeglobal.sqlgenerator.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> index() {
        List<Profile> profiles = this.service.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }
}
