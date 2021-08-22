package com.guliz.bookstore.security.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credentials")
@Data
@AllArgsConstructor
public class CredentialEntity {

    @Id
    private String username;
    private String password;
}