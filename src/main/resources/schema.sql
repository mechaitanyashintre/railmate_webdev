CREATE SCHEMA infyrailcapstone;
USE infyrailcapstone;

CREATE TABLE routes (
    route_id INT PRIMARY KEY,
    destination VARCHAR(255),
    source VARCHAR(255)
);

CREATE TABLE trains (
    train_id INT PRIMARY KEY,
    arrival_time VARCHAR(255),
    departure_time VARCHAR(255),
    fare DOUBLE,
    train_name VARCHAR(255),
    route_id INT,
    FOREIGN KEY (route_id) REFERENCES routes(route_id)
);
