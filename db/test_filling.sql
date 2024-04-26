
psql -U admin -d delivery_db -h localhost -p 5432

INSERT INTO roles (name)
VALUES ('User'), ('Courier'), ('Admin');

INSERT INTO stocks (status, address, open_time, close_time)
VALUES ('Open', 'Point A', '2024-04-26 08:00:00', '2024-04-26 18:00:00'),
       ('Closed', 'Point C', '2024-04-26 09:00:00', '2024-04-26 17:00:00'),
       ('Open', 'Point E', '2024-04-26 09:00:00', '2024-04-26 17:00:00');

INSERT INTO users (status, phone, password, id_role, id_stock)
VALUES ('Active', '1234567890', 'password123', 1, 1),
       ('Inactive', '6543217890', 'pass123', 1, 1),
       ('Active', '0987654321', 'qwerty456', 2, 2),
       ('Active', '9876543210', 'abc123xyz', 3, 1);

INSERT INTO orders (status, end_point, id_user, id_courier, id_stock)
VALUES ('Pending', 'Point B', 1, NULL, 1),
       ('Delivered', 'Point D', 1, 3, 1),
       ('Pending', 'Point F', 3, NULL, 2);
