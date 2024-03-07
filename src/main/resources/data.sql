INSERT into user (username, password) values ('user1', '{bcrypt}$2a$12$RKP6l.lOavIpsYhLZ6wwTeW/G6ilNswSezsOlCovZ9.s2NgnLyo56');
INSERT into user (username, password) values ('user2', '{bcrypt}$2a$12$.VB8obMprJUuWgZlHe9H5.1ff8FiEZYP/FDaqNeaXQSyzXEVSQEie');
INSERT into user (username, password) values ('admin', '{bcrypt}$2a$12$M3B61v9j/f44HVUB22fydefy.CIUncKKScL92WlOahXulYbeZFWwG');

INSERT into authority (authority, username) values ('VIEW_INFO', 'user1');
INSERT into authority (authority, username) values ('VIEW_INFO', 'user2');
INSERT into authority (authority, username) values ('VIEW_ADMIN', 'user2');
INSERT into authority (authority, username) values ('VIEW_ADMIN', 'admin');
