
CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE TABLE stocks (
   id SERIAL PRIMARY KEY,
   status VARCHAR(255) NOT NULL,
   address VARCHAR(255) NOT NULL,
   open_time TIME NOT NULL,
   close_time TIME NOT NULL
);
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    id_role INT REFERENCES roles(id)
);
CREATE TABLE orders (
     id SERIAL PRIMARY KEY,
     status VARCHAR(255) NOT NULL,
     end_point VARCHAR(255) NOT NULL,
     id_user INT REFERENCES users (id),
     id_courier INT REFERENCES users(id),
     id_stock INT REFERENCES stocks(id)
);

INSERT INTO roles (name)
VALUES ('User'), ('Courier'), ('Admin');

INSERT INTO stocks (status, address, open_time, close_time)
VALUES ('Open', 'Point A', '08:00:00', '18:00:00'),
       ('Closed', 'Point C', '09:00:00', '17:00:00'),
       ('Open', 'Point E', '09:00:00', '17:00:00');

INSERT INTO users (username, phone, password, id_role)
VALUES ('user', '1234567890', '$2a$10$56To3mrRW8EP4sQvCNFa5OrmuMYL9LIqQTBKhPzp5zbSQ75BS41Oy', 1),
       ('courier', '6543217890', '$2a$10$DskUFGL3Ep5A345fMZnuDOABIiXJ9czl/tUOqjOCej5cpfPBV6RPu', 2),
       ('admin', '0987654321', '$2a$10$kGaUEKvburba4odRLP3lOuZEP1NGZKWl.R2WWKplMHcQT9BQrH5I.', 3);

INSERT INTO orders (status, end_point, id_user, id_courier, id_stock)
VALUES ('Pending', 'Point B', 1, 2, 1),
       ('Delivered', 'Point D', 1, 2, 1),
       ('Created', 'Point D', 1, NULL, 2),
       ('Delivered', 'Point D', 1, 2, 2),
       ('Created', 'Point F', 3, NULL, 2);
