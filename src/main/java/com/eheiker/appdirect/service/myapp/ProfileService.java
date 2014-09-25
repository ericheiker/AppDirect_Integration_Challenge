package com.eheiker.appdirect.service.myapp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eheiker.appdirect.domain.myapp.Profile;
import com.eheiker.appdirect.repository.BaseRepository;
import com.eheiker.appdirect.repository.UserRepository;

@Service
public class ProfileService extends BaseCrudService<Profile, Long> {

    @Autowired
    private UserRepository repository;

    @Override
    protected BaseRepository<Profile, Serializable> getRepository() {
        return repository;
    }
}
