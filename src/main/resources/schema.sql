
-- CUSTOMERS TABLE DEFINITION

CREATE TABLE customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(16) NOT NULL,
    email VARCHAR(32) NOT NULL,
    first_name VARCHAR(16) NOT NULL,
    last_name VARCHAR(16) DEFAULT '',
    birth_date DATE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,

    contact_id BIGINT DEFAULT NULL
);

CREATE TABLE contacts (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    phone_type VARCHAR(16) NOT NULL,
    phone_number VARCHAR(32) NOT NULL,

    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE 
    -- customer_id REFERENCES customers (id) ON DELETE cascade ON UPDATE cascade
);