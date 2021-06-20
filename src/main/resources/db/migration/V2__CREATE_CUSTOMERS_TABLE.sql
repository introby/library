CREATE TABLE customers (
    id bigint auto_increment,
    customer_name varchar(255) NOT NULL,
    address_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);