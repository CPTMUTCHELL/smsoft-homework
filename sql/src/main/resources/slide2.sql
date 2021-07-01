
create table customers(id serial primary key , name text, code int);
create table suppliers(id serial primary key , name text, code int);
insert into customers (name, code) VALUES ('name1',1), ('name2',22),('name3',333);
insert into suppliers (name, code) VALUES ('sname1',11), ('sname4',22),('name3',333);
--одинаковые типы+ порядок параметров
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

