/*CREATE TYPE user_role AS ENUM ('admin', 'user');*/

CREATE TABLE IF NOT EXISTS users
(
  login INT,
  hashpwd VARCHAR(45),
  role user_role
)