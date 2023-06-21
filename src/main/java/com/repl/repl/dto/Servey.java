package com.repl.repl.dto;

import com.repl.repl.entity.ServeyEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Servey {

    private String servey_id;

    private String user_id;

    public ServeyEntity toEntity() {
        return ServeyEntity.builder()
                .servey_id(servey_id)
                .user_id(user_id)
                .build();
    }

}
