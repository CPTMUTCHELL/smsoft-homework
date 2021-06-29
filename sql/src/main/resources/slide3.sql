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