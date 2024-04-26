
CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE TABLE stocks (
   id SERIAL PRIMARY KEY,
   status VARCHAR(255) NOT NULL,
   address VARCHAR(255) NOT NULL,
   open_time TIMESTAMP NOT NULL,
   close_time TIMESTAMP NOT NULL
);
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    status VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    id_role INT REFERENCES roles(id),
    id_stock INT REFERENCES stocks(id)
);
CREATE TABLE orders (
     id SERIAL PRIMARY KEY,
     status VARCHAR(255) NOT NULL,
     end_point VARCHAR(255) NOT NULL,
     id_user INT REFERENCES users (id),
     id_courier INT REFERENCES users(id),
     id_stock INT REFERENCES stocks(id)
);
