
psql -U admin -d delivery_db -h localhost -p 5432

INSERT INTO roles (name)
VALUES ('User'), ('Courier'), ('Admin');

INSERT INTO stocks (status, address, open_time, close_time)
VALUES ('Open', 'Point A', '08:00:00', '18:00:00'),
       ('Closed', 'Point C', '09:00:00', '17:00:00'),
       ('Open', 'Point E', '09:00:00', '17:00:00');

INSERT INTO users (username, phone, password, id_role)
VALUES ('user1', '1234567890', 'password123', 1),
       ('user2', '6543217890', 'pass123', 1),
       ('user3', '0987654321', 'qwerty456', 2),
       ('user4', '9876543210', 'abc123xyz', 3);

INSERT INTO orders (status, end_point, id_user, id_courier, id_stock)
VALUES ('Pending', 'Point B', 1, NULL, 1),
       ('Delivered', 'Point D', 1, 3, 1),
       ('Pending', 'Point F', 3, NULL, 2);
