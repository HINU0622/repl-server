package com.repl.repl.entity;

import com.repl.repl.dto.Survey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "Survey")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyEntity {

    @Id
    private String Survey_id;

    private String user_id;

    private String content;

    public Survey toDTO() {
        return Survey.builder()
                .Survey_id(Survey_id)
                .user_id(user_id)
                .content(content)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SurveyEntity that = (SurveyEntity) o;
        return Objects.equals(Survey_id, that.Survey_id) && Objects.equals(user_id, that.user_id);
    }

}
