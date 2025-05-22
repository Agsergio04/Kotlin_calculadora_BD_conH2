DROP TABLE IF EXISTS Calculadora;

CREATE TABLE Calculadora (
                             fecha VARCHAR(14) PRIMARY KEY,
                             num_1 DECIMAL,
                             operador CHAR,
                             num_2 DECIMAL,
                             resultado VARCHAR(225)
);
