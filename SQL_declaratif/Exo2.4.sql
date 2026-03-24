-- 7 
SELECT AVG(year_result) 
FROM Student;

-- 8
SELECT MAX(year_result)
FROM Student;

-- 9
SELECT SUM(year_result)
FROM Student;

-- 10
SELECT MIN(year_result)
FROM Student;

-- 11
SELECT count(*)
FROM Student;

-- 12
SELECT login, birth_date
FROM Student
WHERE birth_date > '1970-12-31';

-- 13
SELECT login, last_name
FROM Student
WHERE LENGTH(last_name) > 7; 

-- 14
SELECT UPPER(last_name) AS "NOM DE FAMILLE", first_name, year_result
FROM Student
WHERE year_resul





