CREATE TABLE user(
    id VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE user;

CREATE TABLE servey(
	servey_id INT(36) NOT NULL,
    user_id VARCHAR(32) NOT NULL,
    PRIMARY KEY(servey_id)
);

DROP TABLE servey;