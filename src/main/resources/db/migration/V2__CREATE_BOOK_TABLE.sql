CREATE TABLE books (
    id bigint auto_increment,
    author varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    customer_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
