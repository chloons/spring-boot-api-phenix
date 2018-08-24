CREATE TABLE Team (
    id   INTEGER      NOT NULL,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Player (
    id        INTEGER      NOT NULL,
    team_id   INTEGER      NOT NULL,
    name      VARCHAR(128) ,    
    num 	  VARCHAR(128) NOT NULL,
    Position  INTEGER      NOT NULL,    
    PRIMARY KEY (id)
);
