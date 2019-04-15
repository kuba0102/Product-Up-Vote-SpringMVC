INSERT INTO users (username, name, surname, email, password, salt, backend) VALUES ('kuba0102','jakub', 'chruslicki', 'kuba0102@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', true);
INSERT INTO users (username, name, surname, email, password, salt, backend) VALUES ('BasicUser','Basic', 'User', 'basic@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', true);
INSERT INTO users (username, name, surname, email, password, salt, backend) VALUES ('Customer','Customer', 'User', 'customer@gmail.com', 'rFpnSq9b1qZb6SvalA0Z1EPqtZbyL1ajWWY27p5SzX8=', 'zVtX4KvmgxLb2ZwRhKAy3WHjl4h9j9', false);

INSERT INTO user_types (type) VALUES ('Admin');
INSERT INTO user_types (type) VALUES ('Basic');

INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (1,1,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
INSERT INTO permissions (user_id, user_type_id, user_edit, user_view,user_delete, user_add, product_approve, product_edit, product_view, product_add, product_delete, order_edit, order_view, order_delete, order_add, customer_edit, customer_view, customer_delete, customer_add) VALUES (2,2,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
