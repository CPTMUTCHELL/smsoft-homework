
--4 - length
select upper
               (  substring(
                                ( initcap(
                                                (select name || ' ' || customers.name from customers where id = 1))
                                )
                                from 1 for 4));


select name || ' ' || customers.name from customers where id = 1;
select initcap(
               (select name || ' ' || customers.name from customers where id = 1));

select substring(
               (select initcap(
                               (select name || ' ' || customers.name from customers where id = 1))
               )
               from 1 for 5);
