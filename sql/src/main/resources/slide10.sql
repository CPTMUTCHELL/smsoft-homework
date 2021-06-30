
--4 - length
select upper(
               ( select substring(
                                (select initcap(
                                                (select name || ' ' || customers.name from customers where id = 1))
                                )
                                from 1 for 4)));
