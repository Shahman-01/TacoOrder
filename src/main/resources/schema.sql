CREATE TABLE IF NOT EXISTS Taco_Order
(
    id              SERIAL PRIMARY KEY,
    delivery_Name   VARCHAR(50) NOT NULL,
    delivery_Street VARCHAR(50) NOT NULL,
    delivery_City   VARCHAR(50) NOT NULL,
    delivery_State  VARCHAR(2)  NOT NULL,
    delivery_Zip    VARCHAR(10) NOT NULL,
    cc_number       VARCHAR(16) NOT NULL,
    cc_expiration   VARCHAR(5)  NOT NULL,
    cc_cvv          VARCHAR(3)  NOT NULL,
    placed_at       TIMESTAMP   NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco
(
    id             SERIAL PRIMARY KEY NOT NULL,
    name           VARCHAR(50)        NOT NULL,
    taco_order     BIGINT             NOT NULL,
    created_at     TIMESTAMP          NOT NULL,
    FOREIGN KEY (taco_order) REFERENCES Taco_Order (id)
);

CREATE TABLE IF NOT EXISTS Ingredient
(
    id   VARCHAR(4) PRIMARY KEY NOT NULL,
    name VARCHAR(25)            NOT NULL,
    type VARCHAR(10)            NOT NULL
);

CREATE TABLE IF NOT EXISTS usr
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    fullname    VARCHAR(255) NOT NULL,
    street      VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    state       VARCHAR(255) NOT NULL,
    zip         VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(255) NOT NULL
);
