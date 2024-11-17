select * from customer c ;

-- Insert customers
INSERT INTO customer (id, name, surname) VALUES (1, 'Pepe', 'Hidalgo');
INSERT INTO customer (id, name, surname) VALUES (2, 'Maria', 'Garcia');

-- Insert accounts
INSERT INTO account (balance, customer_id) VALUES (1000, 1);
INSERT INTO account (balance, customer_id) VALUES (1500, 2);

-- Insert transactions
INSERT INTO "transaction" (amount, timestamp, account_id) VALUES (1000, '2024-11-17 10:00:00', 1);
INSERT INTO "transaction" (amount, timestamp, account_id) VALUES (1500, '2024-11-16 10:00:00', 2);