-- Insert customers
INSERT INTO customer (id, name, surname) VALUES (1, 'Pepe', 'Hidalgo');
INSERT INTO customer (id, name, surname) VALUES (2, 'Maria', 'Garcia');

-- Insert accounts
INSERT INTO account (account_id, balance, customer_id) VALUES (1, 1000, 1);
INSERT INTO account (account_id, balance, customer_id) VALUES (2, 1500, 2);

-- Insert transactions
INSERT INTO "transaction" (transaction_id, amount, timestamp, account_id) VALUES (1, 1000, '2024-11-17 10:00:00', 1);
INSERT INTO "transaction" (transaction_id, amount, timestamp, account_id) VALUES (2, 1500, '2024-11-16 10:00:00', 2);