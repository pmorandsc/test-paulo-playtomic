DROP TABLE WALLET;

CREATE TABLE WALLET (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL NOT NULL
);

INSERT INTO WALLET VALUES (
    1,
    5.0
);