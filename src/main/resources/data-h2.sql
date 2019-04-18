INSERT INTO users (username, name, surname, email, password, salt, backend) VALUES ('kuba0102','jakub', 'chruslicki', 'kuba0102@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', true);
INSERT INTO users (username, name, surname, email, password, salt, backend) VALUES ('BasicUser','Basic', 'User', 'basic@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', true);
INSERT INTO users (username, name, surname, email, password, salt, backend) VALUES ('Customer','Customer', 'User', 'customer@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', false);

INSERT INTO user_types (type) VALUES ('Admin');
INSERT INTO user_types (type) VALUES ('Basic');

INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (1,1,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (2,2,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);

INSERT INTO products (name, description, approved, user_id, image) VALUES ('Test', 'Test Description', 'yes', 1, 'src/products_img/1555438511170Camila_web.png');
INSERT INTO products (name, description, approved, user_id, image) VALUES ('Test2', 'As BigInt: There is no theoretical limit. The BigInteger class allocates as much ', 'yes', 1, 'src/products_img/1555442938992Barnali_web.png');
INSERT INTO products (name, description, approved, user_id, image) VALUES ('Test3', 'Test3 Description', 'no', 1, 'src/products_img/1555438511170Camila_web.png');
INSERT INTO products (name, description, approved, user_id, image) VALUES ('Test4', 'Test4 Description', 'no', 1, 'src/products_img/1555438511170Camila_web.png');
INSERT INTO products (name, description, approved, user_id, image) VALUES ('Test5', 'Test5 Description', 'no', 1, 'src/products_img/1555438511170Camila_web.png');

