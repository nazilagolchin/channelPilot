DROP TABLE IF EXISTS PRODUCT;
 
CREATE TABLE PRODUCT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  x VARCHAR(250) NOT NULL,
  y VARCHAR(250) NOT NULL,
  z VARCHAR(250) DEFAULT NULL,
  a VARCHAR(250),
  b VARCHAR(250)
);