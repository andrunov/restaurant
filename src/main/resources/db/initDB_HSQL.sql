DROP TABLE IF EXISTS orders_dishes ;
DROP TABLE IF EXISTS dishes ;
DROP TABLE IF EXISTS orders ;
DROP TABLE IF EXISTS menu_lists ;
DROP TABLE IF EXISTS restaurants ;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users ;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name        VARCHAR(255),
  email       VARCHAR(255),
  password    VARCHAR(255)
);

CREATE TABLE roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name       VARCHAR(255),
  address    VARCHAR(255)
);

CREATE TABLE orders(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  user_id         INTEGER NOT NULL ,
  restaurant_id  INTEGER NOT NULL ,
  date_time       TIMESTAMP NOT NULL ,
  FOREIGN KEY     (user_id) REFERENCES users(id) ON DELETE CASCADE ,
  FOREIGN KEY     (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE menu_lists(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  description    VARCHAR(255),
  date_time      TIMESTAMP NOT NULL ,
  restaurant_id  INTEGER NOT NULL ,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  description      VARCHAR(255),
  price             DOUBLE PRECISION,
  menu_list_id     INTEGER NOT NULL ,
  FOREIGN KEY (menu_list_id) REFERENCES menu_lists (id) ON DELETE CASCADE
);

CREATE TABLE orders_dishes
(
  order_id          INTEGER NOT NULL ,
  dish_id           INTEGER NOT NULL ,
  dish_quantity     INTEGER NOT NULL,
  PRIMARY KEY (order_id,dish_id),
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (dish_id) REFERENCES dishes(id) ON DELETE CASCADE
);