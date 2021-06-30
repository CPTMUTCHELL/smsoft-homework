
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

SELECT 'search' SIMILAR TO 's(e|a)arch';
SELECT 'searchhва' SIMILAR TO 'searc(h{2})%';
--Returning позволяет возвращать значения, которые были добавлены или изменены (insert into or update)
insert into suppliers(name, code) values ('newInsert',66) returning name;
delete from suppliers where name like '%sert' returning code,name;
