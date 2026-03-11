-- 1
SELECT login, year_result 
FROM Student
WHERE year_result > 16;


-- 2 
SELECT last_name, section_id 
FROM Student
WHERE first_name = 'Georges';


-- 3
SELECT last_name, year_result
FROM Student
WHERE year_result BETWEEN 12 AND 16;


-- 4
SELECT last_name, section_id, year_result
FROM Student
WHERE section_id NOT IN (1010, 1020, 1110);


-- 5
SELECT last_name, section_id
FROM Student
WHERE last_name LIKE '%r';

-- 6
SELECT last_name, year_result
FROM Student
WHERE last_name LIKE '__n%'
AND year_result > 10;


-- 7
SELECT last_name, year_result
FROM Student
WHERE year_result <= 3
ORDER BY year_result DESC;


-- 8
SELECT first_name || ' ' || last_name AS "Nom Complet", year_result
FROM Student 
WHERE section_id = 1010
ORDER BY "Nom Complet" DESC;

-- 9
SELECT last_name, section_id, year_result
FROM Student
WHERE section_id IN (1010, 1020)
	AND year_result NOT BETWEEN 12 AND 18
ORDER BY section_id ASC, last_name DESC;


-- 10
SELECT last_name, section_id, (year_result * 5) AS "Résultat sur 100"
FROM Student
WHERE CAST(section_id AS TEXT ) LIKE '13%'
	AND (year_result * 5) <=60
ORDER BY "Résultat sur 100" DESC;


