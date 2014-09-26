package com.eheiker.appdirect.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eheiker.appdirect.domain.appdirect.User;
import com.eheiker.appdirect.domain.myapp.Profile;
import com.eheiker.appdirect.repository.BaseRepository;
import com.eheiker.appdirect.repository.ProfileRepository;

@Service
public class ProfileService extends BaseCrudService<Profile, Long> {

    @Autowired
    private ProfileRepository repository;

    public Profile getByOpenID(String openId) {
        return repository.findByOpenId(openId);
    }

    public Profile getByEmail(String email) {
        return repository.findByEmail(email);
    }



    @Override
    protected BaseRepository<Profile, Serializable> getRepository() {
        return repository;
    }
}
