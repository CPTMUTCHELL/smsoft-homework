insert into customers (name, code, phones) VALUES ('new',12,ARRAY ['sss','fff','ww']);
select phones[3] from customers;
insert into customers (name, code, phones) VALUES ('new',12,ARRAY [3,4,'5']);
update customers set phones ='{2,3}' where id=4;
SELECT  *
FROM customers
WHERE 5 = ANY (phones);


CREATE TABLE orders (
    id serial NOT NULL PRIMARY KEY,
    info json NOT NULL
);
INSERT INTO orders (info)
VALUES('{ "customer": "John Doe", "items": {"product": "Beer","qty": 6}}');
--У джона 6 бутылок
INSERT INTO orders (info)
VALUES('{ "customer": "Lily Bush", "items": {"product": "Diaper","qty": 24}}'),
      ('{ "customer": "Josh William", "items": {"product": "Toy Car","qty": 1}}'),
      ('{ "customer": "Mary Clark", "items": {"product": "Toy Train","qty": 2}}');

SELECT info -> 'customer' AS customer
FROM orders;
SELECT info ->> 'customer' AS customer
FROM orders;

select info -> 'items' ->>'product' as items from orders;
--Вытаскиваем всех, у кого есть пивас customer-items
select info ->'customer' as customer , info -> 'items' ->>'product' as items
from orders where  info -> 'items' ->>'product'  ='Beer';

--key-value example
select key,value  from orders, json_each(info);

--Тут можно увидеть, что из recursive можно использовать в from
WITH RECURSIVE doc_key_and_value_recursive(key, value) AS (
    --Не рекурсивный запрос. Нужен для поиска элемента, с которого следует начать рекурсивный запрос
    SELECT
        t.key,
        t.value
    FROM orders, json_each(orders.info) AS t

    UNION ALL

    --рекурсивный запрос. Выполняется в каждой итерации
    SELECT
        t.key,
        t.value
    FROM doc_key_and_value_recursive,
         json_each(CASE
                       WHEN json_typeof(doc_key_and_value_recursive.value) <> 'object' THEN '{}' :: JSON
                       ELSE doc_key_and_value_recursive.value
             END) AS t
)

SELECT *
FROM doc_key_and_value_recursive
WHERE json_typeof(doc_key_and_value_recursive.value) <> 'object';