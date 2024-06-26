
psql -U admin -d delivery_db -h localhost -p 5432

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
VALUES ('Pending', 'Point B', 1, NULL, 1),
       ('Delivered', 'Point D', 1, 2, 1),
       ('Pending', 'Point F', 3, NULL, 2);
