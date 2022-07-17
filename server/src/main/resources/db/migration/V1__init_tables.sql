CREATE TABLE IF NOT EXISTS users(
                                    id BIGSERIAL PRIMARY KEY,
                                    login TEXT NOT NULL UNIQUE ,
                                    password TEXT NOT NULL,
                                    salt TEXT NOT NULL
);
CREATE TABLE product
(
    id              BIGSERIAL PRIMARY KEY,
    name            text             NOT NULL,
    creationDate    DATE             NOT NULL,
    price           DOUBLE PRECISION NOT NULL CHECK (price > 0),
    partNumber      TEXT,
    manufactureCost BIGINT           NOT NULL,
    unitOfMeasure   TEXT,
    user_id         BIGINT REFERENCES users (id)
);
CREATE TABLE coordinates
(
    id         BIGSERIAL PRIMARY KEY,
    x          INT    NOT NULL,
    y          BIGINT NOT NULL,
    product_id BIGINT REFERENCES product (id) ON DELETE CASCADE
);
CREATE TABLE person
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    passportId  TEXT NOT NULL,
    eyeColor    TEXT NOT NULL,
    hairColor   TEXT NOT NULL,
    nationality TEXT,
    product_id  BIGINT REFERENCES product (id) ON DELETE CASCADE
);
CREATE TABLE location
(
    id        BIGSERIAL PRIMARY KEY,
    x         FLOAT,
    y         DOUBLE PRECISION,
    z         DOUBLE PRECISION,
    person_id BIGINT REFERENCES person (id) ON DELETE CASCADE
);
