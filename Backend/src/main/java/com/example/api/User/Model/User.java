package com.example.api.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "Users")
public class User {

    @Id
    private ObjectId id;

    private String username;
    private String password;
    private String role;
}