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
WHERE year_result >= 16
ORDER BY year_result DESC;

-- 15

SELECT first_name, last_name, login, LOWER(LEFT(first_name, 2) || LEFT(last_name, 4)) AS "Nouveau login"
FROM Student 
WHERE year_result BETWEEN 6 AND 10;

-- 16
SELECT first_name, last_name, login, RIGHT(first_name, 3) ||  EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM birth_date) AS nouveau_login
FROM Student
WHERE year_result IN (10, 12, 14);

-- 17
SELECT last_name, login, year_result
FROM Student
WHERE last_name LIKE 'D%' OR last_name LIKE 'M%' OR last_name LIKE 'S%'
ORDER BY birth_date ASC;

-- 18
SELECT last_name, login, year_result
FROM Student
WHERE year_result > 10
	AND year_result % 2 <> 0;

-- 19
SELECT COUNT(*) AS "Nbre de noms de plus de 7 lettres"
FROM Student
WHERE LENGTH(last_name) >= 7;

-- 20
SELECT last_name, year_result,  
	CASE 
		WHEN year_result > 12 THEN 'OK'
		ELSE 'KO'
	END AS statut
FROM Student
WHERE birth_date < '1955-01-01';

-- 21
SELECT last_name, year_result, 
	CASE 
		WHEN year_result < 10 THEN 'inférieur'
		WHEN year_result = 10 THEN 'neutre'
		WHEN year_result > 10 THEN 'supérieur'
	END AS Categorie
FROM Student
WHERE birth_date BETWEEN '1955-01-01' AND '1965-01-01';

-- 22 
SELECT last_name, year_result, TO_CHAR(birth_date, 'DD TMMonth YYYY') AS Literal_date
FROM Student
WHERE birth_date BETWEEN '1975-01-01' AND '1985-01-01';


-- 23
SELECT 
    last_name,
    EXTRACT(MONTH FROM birth_date) AS mois_naissance,
    year_result,
    CASE
        WHEN year_result = 4 THEN NULL
        ELSE year_result
    END AS "Nouveau résultat"
FROM Student
WHERE EXTRACT(MONTH FROM birth_date) NOT IN (12, 1, 2, 3)
AND year_result < 7;




