package com.guliz.bookstore.security.data;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<CredentialEntity, String> {
}