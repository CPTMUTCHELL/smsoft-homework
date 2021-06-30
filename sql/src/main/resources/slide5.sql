
DO $$
DECLARE myvar integer;
BEGIN
SELECT 5 INTO myvar;

DROP TABLE IF EXISTS tmp_table;
CREATE TABLE tmp_table AS
SELECT * FROM yourtable WHERE  id = myvar;
END $$;

SELECT * FROM tmp_table;