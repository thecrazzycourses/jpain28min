/*CREATE TABLE person
(
    id         INTEGER not null,
    name       VARCHAR (255),
    location   VARCHAR(255),
    birth_date TIMESTAMP ,
    PRIMARY KEY (id)
);*/

INSERT INTO person (id, name, location, birth_date) VALUES (1001, 'Rahul Choudhary', 'Gurgoan', SYSDATE());
INSERT INTO person (id, name, location, birth_date) VALUES (1002, 'Shalu Choudhary', 'Meerut', SYSDATE());
INSERT INTO person (id, name, location, birth_date) VALUES (1003, 'Ravi Choudhary', 'Bareilly', SYSDATE());
INSERT INTO person (id, name, location, birth_date) VALUES (1004, 'Kuldeep Choudhary', 'Bulandsher', SYSDATE());


INSERT INTO course (id, name) VALUES (1001, 'Java');
INSERT INTO course (id, name) VALUES (1002, 'JavaScript');
INSERT INTO course (id, name) VALUES (1003, 'Spring');
INSERT INTO course (id, name) VALUES (1004, 'NextJs');
