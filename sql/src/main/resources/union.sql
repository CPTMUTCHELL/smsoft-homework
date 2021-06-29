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
-- Оператор-> возвращает json объект как ключ
-- Оператор ->> возвращает json объект как текст
--Происходит возврат объекта
SELECT info -> 'customer' AS customer
FROM orders;
SELECT info ->> 'customer' AS customer
FROM orders;

--Так как -> возвращает ключ, то его можно использовать вместе с ->>, чтоб вытащить какое-то конкретное значение
select info -> 'items' ->>'product' as items from orders;
--Вытаскиваем всех, у кого есть пивас customer-items
select info ->'customer' as customer , info -> 'items' ->>'product' as items from orders where  info -> 'items' ->>'product'  ='Beer';
-- xmlcomment(text). Данная функция создаёт xml-значение с текстом-контентом. Однако текст не может содержать символы -- и -

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
                       WHEN json_typeof(doc_key_and_value_recursive.value) != 'object' THEN '{}' :: JSON
                       ELSE doc_key_and_value_recursive.value
             END) AS t
)
-- :: каст в json
SELECT *
FROM doc_key_and_value_recursive
WHERE json_typeof(doc_key_and_value_recursive.value) <> 'object';

CREATE TABLE XMLTEST (id serial,Name XML);
insert into XMLTEST(Name)
values ('<?xml version="1.0"?>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>hello</body>
</html>');
SELECT * from XMLTEST;
--xmlconcat(xml[, ...]). Функция конкатенирует
SELECT xmlconcat('<abc/>', '<bar>foo</bar>');
--xmlelement(name name [, xmlattributes(value [AS attname] [, ... ])] [, content, ...]) создаёт xml-element
SELECT xmlelement(name foo, xmlattributes('xyz' as bar));
--Приведение типа xml в text
select XMLSERIALIZE(DOCUMENT query_to_xml('select name from XMLTEST', false, false,'') as text);
--Xpath — это язык запросов к элементам xml
--xpath(xpath, xml [, nsarray])  возвращает массив xml, Первый параметр - xpath выражение. Второй параметр -xml документ, Третий параметр- optional массив нэймспейсов
insert into XMLTEST (Name) values ('<promotions xmlns="http://www.demandware.com/xml/impex/promotion/2008-01-31">
    <campaign campaign-id="2013-1st-semester-jet-giveaways">
        <description>2013 1st Semester Jet Giveaways</description>
        <enabled-flag>true</enabled-flag>
        <start-date>2013-01-01T05:00:00.000Z</start-date>
        <end-date>2013-07-01T04:00:00.000Z</end-date>
        <customer-groups>
            <customer-group group-id="Everyone"/>
        </customer-groups>
    </campaign>
</promotions>');
-- Видим, что
select xpath('/n:promotions/n:campaign/n:description/text()', (select name from XMLTEST where id =2), '{{n,http://www.demandware.com/xml/impex/promotion/2008-01-31}}');

--Если удалить неймспейс, то не нужны нэймспейс алиас n и сам неймспейс. n необходим, так как третий параметр 2d array
--Сейчас поиск стал проще
update XMLTEST set name='<promotions>
    <campaign campaign-id="2013-1st-semester-jet-giveaways">
        <description>2013 1st Semester Jet Giveaways</description>
        <enabled-flag>true</enabled-flag>
        <start-date>2013-01-01T05:00:00.000Z</start-date>
        <end-date>2013-07-01T04:00:00.000Z</end-date>
        <customer-groups>
            <customer-group group-id="Everyone"/>
        </customer-groups>
    </campaign>
</promotions>' where id =2;

select xpath('/promotions/campaign/description/text()', (select name from XMLTEST where id =2));


--Функции – это объекты базы данных, которые используются для автоматизации и упрощения расчетов

CREATE OR REPLACE FUNCTION название функции (типы передаваемых данных через запятую)
    RETURNS тип возвращаемого значения AS
$BODY$
        DECLARE
        Объявление переменных
        BEGIN
        Тело программы
        RETURN возвращаемый результат;
        END;
   $BODY$
    LANGUAGE язык, на котором написана функция
--Тип возвращаемого значения может быть разный, например, numeric, integer, text или, например void это тип, который не возвращает значение, а функция просто отрабатывает (например, добавляет новые строки).

--Здесь объявляется процедура, генерирующая строку заданной длины. Помимо объявления процедуры также демонстрируется использование цикла while.

CREATE OR REPLACE FUNCTION gen_long_string(len INT) RETURNS TEXT AS $$
DECLARE
    res TEXT := 'abcdefghijklmnopqrstuvwxyz';
BEGIN
    WHILE LENGTH(res) <= len
        LOOP

            res := res || res;
        END LOOP;

    RETURN SUBSTRING(res, 1, len);
END
$$ LANGUAGE 'plpgsql';

select * from gen_long_string(22);

--Вставка в табличку
create table func_table(id serial, number int, name text);
CREATE OR REPLACE FUNCTION insert_into_func_table (num int, n text) returns void AS $$
DECLARE
    i int :=0;
BEGIN

    WHILE i<3
    LOOP
        i:=i+1;
   insert into func_table(number, name) values (num+i, concat(n,i::text));
   END LOOP ;

END
$$ LANGUAGE 'plpgsql';
select * from insert_into_func_table(1,'name');

--Триггеры
--1. Создаём функцию-триггер через create function
--2. Связываем триггер с таблицей через create trigger

-- Создадим таблички
CREATE TABLE employees(
  id INT GENERATED ALWAYS AS IDENTITY,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL,
  PRIMARY KEY(id)
);
--таблича для логирования изменений фамилии
CREATE TABLE employee_audits (
id INT GENERATED ALWAYS AS IDENTITY,
employee_id INT NOT NULL,
last_name VARCHAR(40) NOT NULL,
changed_on TIMESTAMP(6) NOT NULL
);

--Создадим функцию-триггер
--Триггер дал нам новые возможности, такие как new и old
--OLD возвращает значение до апдейта, а NEW - будущее значение.

CREATE OR REPLACE FUNCTION log_last_name_changes()
    RETURNS TRIGGER

AS
$$
BEGIN
    IF new.last_name <> OLD.last_name THEN
        INSERT INTO employee_audits(employee_id,last_name,changed_on)
        VALUES(OLD.id,OLD.last_name,now());
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

--Создадим триггер, который свяжет функцию-триггер с табличкой. Перед изменением last_name функция-триггер вызовется и залогирует изменения.
--Триггер с пометкой FOR EACH ROW вызывается один раз для каждой строки, изменяемой в процессе операции.
--Триггер с пометкой FOR EACH STATEMENT, напротив, вызывается только один раз для конкретной операции, вне зависимости от того, как много строк она изменила
--В определении триггера можно указать логическое условие WHEN, которое определит, вызывать триггер или нет.
--В триггерах на уровне строк условия WHEN могут проверять старые и/или новые значения столбцов в строке.
--Триггеры INSTEAD OF вызываются вместо события.  Не поддерживают условия WHEN.
-- В триггере AFTER условие WHEN проверяется сразу после изменения строки, и если оно выполняется, событие запоминается, чтобы вызвать триггер в конце оператора.
-- Если же для триггера AFTER условие WHEN не выполняется, нет необходимости запоминать событие для последующей обработки или заново перечитывать строку в конце оператора.
-- Это приводит к значительному ускорению операторов, изменяющих множество строк, когда триггер должен срабатывать только для некоторых из них.
CREATE  TRIGGER last_name_changes
    BEFORE UPDATE
    ON employees
    FOR EACH ROW
--     when ( )
EXECUTE PROCEDURE log_last_name_changes();
--тестовые данные
INSERT INTO employees (first_name, last_name)
VALUES ('John', 'Doe'),('Lily', 'Bush');

UPDATE employees
SET last_name = 'Brown2222'
WHERE ID = 2;

--https://toto-school.ru/sql-2/postgresql-funkczii-postgresql-dokumentacziya-9-4-funkczii-na-yazyke-zaprosov-sql-kompaniya-postgres-professional.html