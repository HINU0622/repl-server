package com.repl.repl.dto;

import com.repl.repl.entity.SurveyEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    private String Survey_id;

    private String user_id;

    private String content;

    public SurveyEntity toEntity() {
        return SurveyEntity.builder()
                .Survey_id(Survey_id)
                .user_id(user_id)
                .content(content)
                .build();
    }

}
