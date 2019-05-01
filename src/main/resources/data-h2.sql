INSERT INTO users (votes, username, name, surname, email, password, salt, backend) VALUES (10, 'kuba0102','jakub', 'chruslicki', 'kuba0102@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', true);
INSERT INTO users (votes, username, name, surname, email, password, salt, backend) VALUES (10, 'BasicUser','Basic', 'User', 'basic@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', true);
INSERT INTO users (votes, username, name, surname, email, password, salt, backend) VALUES (10, 'Customer','Customer', 'User', 'customer@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', false);
INSERT INTO users (votes, username, name, surname, email, password, salt, backend) VALUES (10, 'User','User', 'User', 'customer2@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', false);
INSERT INTO users (votes, username, name, surname, email, password, salt, backend) VALUES (10, 'test@gmail.com','test', 'test', 'test@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', false);


INSERT INTO user_types (type) VALUES ('Admin');
INSERT INTO user_types (type) VALUES ('Basic');

INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (1,1,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (2,2,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (3,2,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (4,2,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);



INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES (23, 'Test', 'Test Description', 'yes', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES (12, 'Test2', 'As BigInt: There is no theoretical limit. The BigInteger class allocates as much ', 'yes', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES (27, 'Test3', 'Test3 Description', 'no', true, 2, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES (8, 'Test4', 'Test4 Description', 'no', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES (32 ,'Test5', 'Test5 Description', 'no', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 23, 'Tes6', 'Test Description', 'yes', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 23, 'Tes7', 'Test Description', 'yes', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 43, 'Tes8', 'Test Description', 'yes', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 31, 'Tes9', 'Test Description', 'yes', true, 4, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 65, 'Test10', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 78, 'Test11', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 19, 'Test12', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 98, 'Test13', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 12, 'Test14', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 34, 'Test15', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 19, 'Test16', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 25, 'Test17', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 75, 'Test18', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (up_votes, name, description, approved, user_approved, user_id, image, date_submitted) VALUES ( 23, 'Test19', 'Test Description', 'yes', true, 3, 'src/products_img/1555438511170Camila_web.png', '2019-04-27 14:42:21.017');
INSERT INTO products (APPROVED, DATE_SUBMITTED,	DESCRIPTION,	IMAGE,	NAME,	UP_VOTES,	USER_APPROVED,	USER_ID) VALUES ('no',	'2019-04-30 19:02:55.305',	'This is final year project product submission test.',	'src/products_img/1556647375314paul_web.png',	'Final Year Project Test',	0,	TRUE,	5);

INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	67.0,	77.0, 1,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	43.0,	44.0, 2,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	34.0,	59.0, 3,	2);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	234.0,	333.0, 4,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	45.0,	46.0, 5,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	65.0,	67.0, 6,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	76.0,	79, 7,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	45.0,	54.0, 8,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	4.0,	6.0, 9,	4);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	2.0,	5.0, 10,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	4.0,	7.0, 11,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	12.0,	22.0, 12,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	43.0,	76.0, 13,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	213.0,	266.0, 14,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	33.0,	54.0, 15,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	43.0,	72.0, 16,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	12.0,	20.0, 17,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	43.0,	49.0, 18,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	76.0,	85.0, 19,	3);
INSERT INTO offers (COMMENT,DATE,MARKET_PRICE,SOURCE_PRICE,PRODUCT_ID,USER_ID) VALUES ('New Submission',	'2019-04-30 19:02:55.31',	87.0,	95.0, 20,	5);
