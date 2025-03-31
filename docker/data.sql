CREATE DATABASE IF NOT EXISTS user_app;

USE user_app;

CREATE TABLE user{
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
      age int NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY(userID)
};

INSERT INTO users(name,age,address)VALUES ("u1",12,"abcdasdasd"),("u2",12,"abcdasdasd"),("u3",12,"abcdasdasd");