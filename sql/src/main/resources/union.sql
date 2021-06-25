-- Union позволяет объединять результаты нескольких запросов в один результат. Чтоб объединить результаты с помощью union
-- запросы должны иметь одинаковые типы данных, а также кол-во и порядок параметров (колонок) в селектах должен быть одинаковым
-- Union удаляет все дубликаты, если же их нужно оставить, то используют Union all
create table customers(id serial primary key , name text, code int);
create table suppliers(id serial primary key , name text, code int);
insert into customers (name, code) VALUES ('name1',1), ('name2',22),('name3',333);
insert into suppliers (name, code) VALUES ('sname1',11), ('sname4',22),('name3',333);
SELECT * FROM Customers where name='name2'
UNION
SELECT * FROM Suppliers where code=333
ORDER BY code desc ;
--Type это алиас. Он существует только во время запроса, в этом примере создаём временную колонку
SELECT 'Customer' AS Type, name, code
FROM Customers
UNION
SELECT 'Supplier', name, code
FROM Suppliers;

--Допустим, мы хотим найти кастомера по имени, но мы его не полностью помним, поэтому используем like
SELECT name, code FROM customers
WHERE name LIKE '%2' union all
select name,code from suppliers where name not like '%3%';
SELECT name, code FROM customers
WHERE name LIKE '%a__2';
--case-insensitive
SELECT name, code FROM customers
WHERE name iLIKE '%A__2';
--Если недостаточно возможностей оператора LIKE, то можно использовать SIMILAR TO. Он расширяет возможности LIKE, с помощью некоторых операторов регулярных выражений.
-- | означает выбор (одного из двух вариантов).
-- * означает повторение предыдущего элемента 0 и более раз.
-- + означает повторение предыдущего элемента 1 и более раз.
-- ? означает вхождение предыдущего элемента 0 или 1 раз.
-- {m} означает повторяет предыдущего элемента ровно m раз.
-- {m,} означает повторение предыдущего элемента m или более раз.
-- {m,n} означает повторение предыдущего элемента не менее чем m и не более чем n раз.
-- Скобки () объединяют несколько элементов в одну логическую группу.
SELECT 'search' SIMILAR TO 's(e|a)arch';
SELECT 'searchhва' SIMILAR TO 'searc(h{2})%';
--Returning позволяет возвращать значения, которые были добавлены или изменены (insert into or update)
insert into suppliers(name, code) values ('newInsert',66) returning name;
delete from suppliers where name like '%sert' returning code,name;

--with позволяет разбивать сложные запросы на простые. Такой запрос должен быть определен до вызова.
-- With- это Common Table Expression (CTE). Такие выражения временные, т.е существуют во время выполнения.
-- WITH cte_name (column_list) AS (
--     CTE_query_definition
-- )
-- statement;
--1. Задаём имя
--2. Внутри тела задаём список колонок
--3. Используем with как таблицу или view с select/update/delete/create
with cte_customers as (
    select code,name as n from customers

), cte_suppliers as(select code,name as n from suppliers)
select * from cte_customers union select * from cte_suppliers;

-- view нужны для более наглядного отображения данных, но не хранения, а materialized view хранят. View -виртуальная таблица

--Показать view


-- Materialized view кэшируют сложных запросов и периодически обновляют его
-- CREATE MATERIALIZED VIEW view_name
-- AS
--     query
-- WITH [NO] DATA;

--query -запрос на получения данных из таблиц.
--With data загружает данные во view во время создания
--With no data- view is unreadable, т.е нельзя сделать запрос на получение данных из view
--Чтоб загрузить данные используется команда REFRESH MATERIALIZED VIEW view_name; Когда происходит рефреш,то таблицв, с которой грузятся данные блокируется,
-- но можно использовать CONCURRENTLY

--Показать mat view

--Экспорт DDL. Выбираем SQL Generator. Показать разные форматы А также импорт
--\i c:/Users/1/Desktop/new.sql


--arrays
-- insert into customers (name, code, phones) VALUES ('new',12,ARRAY ['sss','fff','ww']);
--select phones[3] from customers;
-- insert into customers (name, code, phones) VALUES ('new',12,ARRAY [3,4,'5']);
-- update customers set phones ='{2,3}' where id=4;
-- SELECT
--     *
-- FROM
--     customers
-- WHERE
--         5 = ANY (phones);
--unnest -разбивает массив в список
SELECT
    name,
    unnest(phones)
FROM
    contacts;

--json info

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
-- Оператор-> возвращает поле по ключу
-- Оператор ->> возвращает поле по значению
--Происходит возврат объекта
SELECT info -> 'customer' AS customer
FROM orders;