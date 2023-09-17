package com.repl.repl.repository;

import com.repl.repl.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<SurveyEntity, String> {
}
