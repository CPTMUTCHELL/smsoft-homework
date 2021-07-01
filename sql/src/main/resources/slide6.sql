alter table customers add column phones text[];

insert into customers (name, code, phones) VALUES ('new',12,ARRAY ['sss','fff','ww']);
select phones[3] from customers;

update customers set phones[3]='upd' ;
insert into customers (name, code, phones) VALUES ('new',12,ARRAY [3,4,'5']);
update customers set phones ='{2,3}' where id=4;
SELECT  *
FROM customers
WHERE '5' = ANY (phones);


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

ALTER TABLE orders
    ALTER COLUMN info
        SET DATA TYPE jsonb using info::jsonb;

UPDATE orders SET info = jsonb_set(info, '{customer}', '"Cashi"') where id =1;
update orders
set info = info - 'customer' || jsonb_build_object('customer!!!', info->'customer')
where id =1
returning *;
--returning для наглядности

--вложенность
UPDATE orders
SET info = jsonb_set(info #- '{items,qty}',
                                '{items,qt111y}',
                                info#>'{items,qty}')
WHERE id=1;


--Сделать импорт sql-файлов с создать view:
-- has_etp_process - true/false - для репы имеется как минимум 1 значение с label ETP_PROCESS
-- gu_service_codes - значения с label GU_SERVICE_CODE в виде массива для данного репозитория
-- java_files_count - значениес label STAT_JAVA
-- кол-во ЕФП-адаптеров
-- кол-во sql файлов
-- Почитать что такое процедура, с помощью неё что-то добавить во view
-- Сделать триггер, который будет смотреть на изменения во вью и удалять каждый 5й элемент во вью,
-- а также добавлять значение счетчика к названию каждой чётной метрики а удалённые записи будут записывать в отдельную таблицу, которую тоже надо вам создать
-- Сделать export через pg dump, чтоб можно было посмотреть на ваши таблицы/view, триггеры, процедуры, функции
-- Сделать и прикрепить скрины import/export