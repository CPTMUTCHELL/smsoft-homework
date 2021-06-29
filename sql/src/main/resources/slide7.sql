CREATE TABLE XMLTEST (id serial,Name XML);
insert into XMLTEST(Name)
values ('<?xml version="1.0"?>
<html xmlns="http://www.w3.org/1999/xhtml">
    <body>hello</body>
</html>');
SELECT * from XMLTEST;
--xmlconcat(xml[, ...]). Функция конкатенирует
SELECT xmlconcat('<abc/>', '<bar>foo</bar>');
--xmlelement(name name [, xmlattributes(value [AS attname] [, ... ])] [, content, ...]) создаёт xml-element
SELECT xmlelement(name foo, xmlattributes('xyz' as bar));
--Приведение типа xml в text
select XMLSERIALIZE(DOCUMENT query_to_xml('select name from XMLTEST', false, false,'') as text);

insert into XMLTEST (Name) values
('<promotions xmlns="http://www.demandware.com/xml/impex/promotion/2008-01-31">
    <campaign campaign-id="2013-1st-semester-jet-giveaways">
        <description>2013 1st Semester Jet Giveaways</description>
        <enabled-flag>true</enabled-flag>
        <start-date>2013-01-01T05:00:00.000Z</start-date>
        <end-date>2013-07-01T04:00:00.000Z</end-date>
        <customer-groups>
            <customer-group group-id="Everyone"/>
        </customer-groups>
    </campaign>
</promotions>');

-- Видим, что
select xpath('/n:promotions/n:campaign/n:description/text()', (select name from XMLTEST where id =2),
    '{{n,http://www.demandware.com/xml/impex/promotion/2008-01-31}}');

--Если удалить неймспейс, то не нужны нэймспейс алиас n и сам неймспейс. n необходим, так как третий параметр 2d array
--Сейчас поиск стал проще
update XMLTEST set name=
'<promotions>
    <campaign campaign-id="2013-1st-semester-jet-giveaways">
        <description>2013 1st Semester Jet Giveaways</description>
        <enabled-flag>true</enabled-flag>
        <start-date>2013-01-01T05:00:00.000Z</start-date>
        <end-date>2013-07-01T04:00:00.000Z</end-date>
        <customer-groups>
            <customer-group group-id="Everyone"/>
        </customer-groups>
    </campaign>
</promotions>' where id =2;

select xpath('/promotions/campaign/description/text()', (select name from XMLTEST where id =2));
