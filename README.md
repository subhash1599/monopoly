Schema of the database

CREATE TABLE Player (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    cash INT,
    position INT
);


CREATE TABLE Place (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    buy_price DECIMAL(10, 2),
    rent DECIMAL(10, 2)
);


CREATE TABLE Game (
    id SERIAL PRIMARY KEY,
    host_player_id INT,
    FOREIGN KEY (host_player_id) REFERENCES Player(id)
);



