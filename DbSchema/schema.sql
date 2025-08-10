CREATE TABLE UserAuth (
    userId INT PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    projectId INT NOT NULL
);
