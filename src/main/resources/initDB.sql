# Create new DB on server;
DROP SCHEMA IF EXISTS toleksiv_products;
CREATE SCHEMA toleksiv_products DEFAULT CHARACTER SET utf8 ;
USE toleksiv_products;
# 01. Create table manufacturers;
CREATE TABLE manufacturers (
  id BINARY(16) NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX MANUFACTURER_IDX (name ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
# 02. Create table products (product could be produced only by 1 manufacturer);
CREATE TABLE products (
  id BINARY(16) NOT NULL,
  name varchar(45) NOT NULL,
  manufacturer_id BINARY(16) NOT NULL,
  price DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_MANUFACTURER_idx (manufacturer_id),
  CONSTRAINT FK_PRODUCTS_MANUFACTURER FOREIGN KEY (manufacturer_id) REFERENCES manufacturers (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

