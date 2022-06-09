CREATE TABLE userApp(
    name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(40) NOT NULL,
    role VARCHAR(15),
    PRIMARY KEY(email)
);

CREATE TABLE walletHistory(
    id SERIAL,
    userApp VARCHAR NOT NULL,
    type VARCHAR,
    fcoins FLOAT,
    registeredAt TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(userApp)
        REFERENCES userApp(email)
);

CREATE TABLE Collection(
     id SERIAL,
     name VARCHAR NOT NULL,
     userApp VARCHAR NOT NULl,
     description VARCHAR,
     category VARCHAR,
     PRIMARY KEY(id),
     FOREIGN KEY(userApp)
         REFERENCES userApp(email)
);


CREATE TABLE Art(
    id SERIAL,
    name VARCHAR,
    price FLOAT,
    imagePath VARCHAR,
    forSale BOOLEAN,
    collection INT,
    PRIMARY KEY(id),
    FOREIGN KEY(collection)
       REFERENCES Collection(id)
);

CREATE TABLE Ownership(
    id SERIAL,
    art INT,
    userApp VARCHAR,
    registeredAt TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(art)
       REFERENCES Art(id),
    FOREIGN KEY(userApp)
       REFERENCES userApp(email)
);

CREATE TABLE LikePart(
    id SERIAL,
    art INT,
    userApp VARCHAR,
    registeredAt TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(art)
       REFERENCES Art(id),
    FOREIGN KEY(userApp)
       REFERENCES userApp(email)
);
