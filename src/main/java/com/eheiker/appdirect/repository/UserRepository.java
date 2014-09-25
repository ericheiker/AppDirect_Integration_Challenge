package com.eheiker.appdirect.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.eheiker.appdirect.domain.myapp.Profile;

@Repository
public interface UserRepository extends BaseRepository<Profile, Serializable> {

}
