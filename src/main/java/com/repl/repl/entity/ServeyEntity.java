package com.repl.repl.entity;

import com.repl.repl.dto.Servey;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "servey")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServeyEntity {

    @Id
    private String servey_id;

    private String user_id;

    private String content;

    public Servey toDTO() {
        return Servey.builder()
                .servey_id(servey_id)
                .user_id(user_id)
                .content(content)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServeyEntity that = (ServeyEntity) o;
        return Objects.equals(servey_id, that.servey_id) && Objects.equals(user_id, that.user_id);
    }

}
