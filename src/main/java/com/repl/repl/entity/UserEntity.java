package com.repl.repl.entity;

import com.repl.repl.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String id;

    private String password;

    public User toDTO() {
        return User.builder()
                .idx(idx)
                .id(id)
                .password(password)
                .build();
    }

}
