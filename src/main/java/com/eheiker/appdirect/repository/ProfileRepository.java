package com.eheiker.appdirect.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.eheiker.appdirect.domain.myapp.Profile;

@Repository
public interface ProfileRepository extends BaseRepository<Profile, Serializable> {
    public Profile findByEmail(String email);
    public Profile findByOpenId(String openId);
}
