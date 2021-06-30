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

--Ещё пример instead of. В основном он предназначен для скипа DML.
--Мы хотим добавить какой-то бренд в таблицу, но есть промежуточная таблица approvals, через которую проходят бренды.
--Для наглядности создадим view. Когда мы будем добавлять брэнды во view, будет срабатывать instead of.
CREATE TABLE CUSTOMER_DETAILS ( CUSTOMER_ID INT PRIMARY KEY
    , CUSTOMER_NAME VARCHAR(20)
    , COUNTRY VARCHAR(20)
);
CREATE TABLE PROJECTS_DETAILS ( PROJECT_ID INT PRIMARY KEY
    , PROJECT_NAME VARCHAR(30)
    , PROJECT_START_DATE DATE
    , CUSTOMER_ID INT REFERENCES CUSTOMER_DETAILS(CUSTOMER_ID)
);
CREATE OR REPLACE VIEW customer_projects_view AS
SELECT cust.customer_id, cust.customer_name, cust.country,
       projectdtls.project_id, projectdtls.project_name,
       projectdtls.project_start_Date
FROM customer_details cust, projects_details projectdtls
WHERE cust.customer_id = projectdtls.customer_id;

CREATE OR REPLACE FUNCTION cust_proj_view_insert_proc() RETURNS trigger AS $$
BEGIN

    INSERT INTO customer_details (customer_id,customer_name,country)
    VALUES (NEW.customer_id, NEW.customer_name, NEW.country);

    INSERT INTO projects_details (project_id, project_name, project_start_Date, customer_id)
    VALUES (
               NEW.project_id,
               NEW.project_name,
               NEW.project_start_Date,
               NEW.customer_id);

    RETURN NEW;
EXCEPTION WHEN unique_violation THEN
    RAISE EXCEPTION 'Duplicate customer or project id';
END;
$$ LANGUAGE plpgsql;
create trigger cust_proj_view_insert_trg
    instead of insert on customer_projects_view for each row EXECUTE procedure cust_proj_view_insert_proc();
INSERT INTO customer_projects_view VALUES (1,'XYZ Enterprise','Japan',101,'Library management',now());
INSERT INTO customer_projects_view VALUES (2,'ABC Infotech','India',202,'HR management',now());