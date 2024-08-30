CREATE TABLE IF NOT EXISTS patient (
                                       patientId INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(45) NOT NULL,
                                       username VARCHAR(45) NOT NULL,
                                       birthdate DATETIME NOT NULL,
                                       gender VARCHAR(45) NOT NULL,
                                       phone   VARCHAR(15),
                                       adresse VARCHAR(100)
);