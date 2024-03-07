CREATE TABLE user (
    id bigint auto_increment primary key,
    username varchar_ignorecase(50) not null ,
    password varchar_ignorecase(500) not null
);

CREATE TABLE authority (
    id bigint auto_increment primary key,
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references user(username)
);

CREATE UNIQUE INDEX ix_auth_username on authority(username, authority);
