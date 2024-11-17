-- Table for Customer
CREATE TABLE customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
);

-- Table for Account
CREATE TABLE account (
    account_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(19, 4) NOT NULL,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Table for Transaction
CREATE TABLE "transaction" (
    transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(19, 4) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    account_id BIGINT,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account(account_id)
);