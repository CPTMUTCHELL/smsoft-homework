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