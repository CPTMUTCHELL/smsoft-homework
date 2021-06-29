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
