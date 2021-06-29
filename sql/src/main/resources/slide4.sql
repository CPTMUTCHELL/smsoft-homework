with cte_customers as (
    select code,name as n from customers

), cte_suppliers as(select code,name as n from suppliers)
select * from cte_customers union select * from cte_suppliers;
