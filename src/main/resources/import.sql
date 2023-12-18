-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into customer (firstname, lastname, email, usertype, createdat) values('David', 'Hamsel', 'david@gmail.com', 'CUSTOMER', CURRENT_TIMESTAMP);
insert into customer (firstname, lastname, email, usertype, createdat) values('James', 'Boot', 'james@gmail.com', 'AFFILIATE', CURRENT_TIMESTAMP);
insert into customer (firstname, lastname, email, usertype, createdat) values('Frederick', 'Obum', 'fred@gmail.com', 'EMPLOYEE', CURRENT_TIMESTAMP);
insert into customer (firstname, lastname, email, usertype, createdat) values('Kofi', 'Gabbs', 'kofi@gmail.com', 'CUSTOMER', CURRENT_TIMESTAMP);

insert into category (name) values('grocery');
insert into category (name) values('furniture');
insert into category (name) values('electronics');

insert into product (name, description, price, category_id) values ('product 1', 'product description', 1250.00, 1);
insert into product (name, description, price, category_id) values('product 2', 'product 2 description', 1500.00, 2);
insert into product (name, description, price, category_id) values('product 3', 'product 3 description', 1000.00, 3);

INSERT INTO public.sale(customer_id, totalcost, soldat) VALUES (1, 3000.00, CURRENT_TIMESTAMP);
INSERT INTO public.sale(customer_id, totalcost, soldat) VALUES (2, 2250.00, CURRENT_TIMESTAMP);

INSERT INTO public.sale_product(product_id, sale_id) VALUES (1, 2);
INSERT INTO public.sale_product(product_id, sale_id) VALUES (2, 1);
INSERT INTO public.sale_product(product_id, sale_id) VALUES (2, 1);
INSERT INTO public.sale_product(product_id, sale_id) VALUES (3, 2);