package com.repl.repl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    private Long idx;

    private String survey_id;

    private String ip;

    private LocalDateTime reply_date;


}
