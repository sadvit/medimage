CREATE TYPE user_role AS ENUM ('ADMIN', 'USER');

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id INT PRIMARY KEY,
  login VARCHAR(45),
  hashpwd CHAR(60),
  role user_role
);

INSERT INTO users (id, login, hashpwd, role) VALUES (2, 'sadvit', 'sadvit', 'USER');

SELECT * FROM users;

/*DROP TYPE user_role;*/
