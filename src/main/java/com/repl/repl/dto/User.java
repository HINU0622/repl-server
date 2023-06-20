package com.repl.repl.dto;

import com.repl.repl.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long idx;

    private String id;

    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .idx(idx)
                .id(id)
                .password(password)
                .build();
    }

}
