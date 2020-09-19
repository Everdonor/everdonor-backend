CREATE TABLE user
(
    id            bigint       NOT NULL AUTO_INCREMENT,
    address       varchar(255) DEFAULT NULL,
    donation_type varchar(255) DEFAULT NULL,
    email         varchar(255) DEFAULT NULL,
    image         text,
    latitude      float        NOT NULL,
    longitude     float        NOT NULL,
    name          varchar(500) NOT NULL,
    password      varchar(255) NOT NULL,
    phone_number  int          NOT NULL,
    report_quantity int        NOT NULL,
    PRIMARY KEY (id)
)