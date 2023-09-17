CREATE TABLE user(
    id VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE user;

CREATE TABLE survey(
	survey_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(32) NOT NULL,
    content VARCHAR(100) NOT NULL,
    PRIMARY KEY(survey_id)
);

DROP TABLE survey;

CREATE TABLE reply(
	reply_id VARCHAR(36) NOT NULL,
    survey_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(32) NOT NULL,
    content VARCHAR(100) NOT NULL,
    PRIMARY KEY(reply_id)
);

DROP TABLE reply;