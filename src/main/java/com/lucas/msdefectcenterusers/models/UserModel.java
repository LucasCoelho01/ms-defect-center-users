package com.lucas.msdefectcenterusers.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;
import java.io.Serializable;

@Entity
@Table(name = "TB_USERS")
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
}
