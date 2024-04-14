
INSERT INTO users (name) VALUES ('User1');
INSERT INTO users (name) VALUES ('User2');

INSERT INTO account (user_id, balance) VALUES (1, 100.50);
INSERT INTO account (user_id, balance) VALUES (2, 200.51);

INSERT INTO account_operations (account_id, operation_type, amount, timestamp) VALUES (1, 'DEPOSIT', 500, '2023-01-01 12:00:00');
INSERT INTO account_operations (account_id, operation_type, amount, timestamp) VALUES (1, 'WITHDRAWAL', 200, '2023-01-02 14:00:00');
INSERT INTO account_operations (account_id, operation_type, amount, timestamp) VALUES (2, 'DEPOSIT', 100, '2023-01-03 10:00:00');
INSERT INTO account_operations (account_id, operation_type, amount, timestamp) VALUES (2, 'WITHDRAWAL', 50, '2023-01-04 16:00:00');
