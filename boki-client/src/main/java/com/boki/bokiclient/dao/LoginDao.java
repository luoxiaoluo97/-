package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.po.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends MongoRepository<User, ObjectId> {
    public User findByUserName(String name);
}
