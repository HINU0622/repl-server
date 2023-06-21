package com.repl.repl.entity;

import com.repl.repl.dto.Servey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servey")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServeyEntity {

    @Id
    private String servey_id;

    private String user_id;

    public Servey toDTO() {
        return Servey.builder()
                .servey_id(servey_id)
                .user_id(user_id)
                .build();
    }

}
