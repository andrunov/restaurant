DELETE FROM users;
DELETE FROM roles;
DELETE FROM restaurants;
DELETE FROM menu_lists;
DELETE FROM orders;
DELETE FROM dishes;
DELETE FROM orders_dishes;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users(name,email,password) VALUES
  ('Алексей Иванов','ivanov.alexey@gmail.com','111'),
  ('Андрей Горбунов','andrunov@gmail.com','222'),
  ('Павел Сидоров','sidor@gmail.com','333'),
  ('Roberto Zanetti','rzanetti@gmail.com','444'),
  ('John Bon Jovi','jbj@gmail.com','555'),
  ('Didier Maoruani','dmauruani@gmail.com','666');

INSERT INTO roles(user_id, role) VALUES
  (100000,'USER'),
  (100001,'USER'),
  (100001,'ADMIN'),
  (100002,'USER'),
  (100003,'USER'),
  (100004,'USER'),
  (100005,'USER');

INSERT INTO restaurants(name, address) VALUES
  ('Ёлки-палки','ул. Некрасова, 14'),
  ('Пиццерия','пл. Пушкина, 6'),
  ('Закусочная','Привокзальная пл, 3'),
  ('Прага','ул. Арбат, 1');

INSERT INTO orders(user_id, restaurant_id, date_time, status) VALUES
  (100000,100006,'2017-01-15 15:47:00','ACCEPTED'),
  (100001,100006,'2017-01-14 18:49:00','ACCEPTED'),
  (100002,100007,'2017-01-15 12:07:00','ACCEPTED'),
  (100003,100007,'2017-01-15 02:09:00','ACCEPTED'),
  (100004,100008,'2017-01-15 14:17:00','ACCEPTED'),
  (100005,100009,'2017-01-15 09:29:00','ACCEPTED');

INSERT INTO menu_lists(restaurant_id, description, date_time) VALUES
  (100006,'Меню на 14.01','2017-01-14 16:30:00'),
  (100007,'Обед комплексный','2017-01-14 15:45:00'),
  (100008,'Обед','2017-01-14 15:40:00'),
  (100009,'Обед','2017-01-14 15:32:00');

INSERT INTO dishes( menu_list_id,description,price) VALUES
  (100016,'Каша овсяная',1.25),
  (100016,'Сырники',3.45),
  (100016,'Блины',2.48),
  (100016,'Суп гороховый',5.57),
  (100016,'Рассольник',6.87),
  (100017,'Жульен с грибами',12.47),
  (100017,'Пельмени',7.96),
  (100017,'Котлета по киевски',14.58),
  (100017,'Чай черный',0.55),
  (100018,'Чай зеленый',0.55),
  (100018,'Кофе черный',0.75),
  (100018,'Кофе белый',0.95),
  (100018,'Котлета по питерски',12.54),
  (100018,'Поросенок под хреном',24.58),
  (100019,'Чебурек',4.62),
  (100019,'Беляш',4.12),
  (100019,'Чай черный с лимоном',1.95),
  (100019,'Борщ',17.58),
  (100019,'Плов узбекский',12.75),
  (100019,'Салат оливье',8.12);

INSERT INTO orders_dishes(order_id, dish_id,dish_quantity) VALUES
  (100010,100020,1),
  (100010,100021,2),
  (100010,100023,1),
  (100011,100020,1),
  (100011,100021,1),
  (100011,100023,2),
  (100011,100024,1),
  (100012,100025,1),
  (100012,100026,2),
  (100012,100027,1),
  (100013,100027,1),
  (100013,100028,2),
  (100014,100029,1),
  (100014,100030,2),
  (100015,100034,1),
  (100015,100035,1),
  (100015,100036,1),
  (100015,100037,2),
  (100015,100038,3),
  (100015,100039,1);
