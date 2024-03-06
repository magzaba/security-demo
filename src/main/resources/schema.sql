CREATE TABLE user (
    id bigint auto_increment primary key,
    username varchar_ignorecase(50) not null ,
    password varchar_ignorecase(500) not null
);