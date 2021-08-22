package com.guliz.bookstore.customer.data;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
@NoArgsConstructor
public class CustomerEntity {

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}