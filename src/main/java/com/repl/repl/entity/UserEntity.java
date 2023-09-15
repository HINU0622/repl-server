package com.repl.repl.entity;

import com.repl.repl.dto.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String id;

    private String password;

    public User toDTO() {
        return User.builder()
                .id(id)
                .password(password)
                .build();
    }

}
