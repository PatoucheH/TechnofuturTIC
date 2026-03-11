-- 2
SELECT last_name, birth_date, login, year_result 
FROM Student;


-- 3
SELECT first_name || ' ' || last_name AS Full_name, student_id, birth_date
FROM Student;

-- 4
SELECT first_name || '|' || last_name || '|' || birth_date || '|' ||
		login || '|' || section_id || '|' || year_result || '|' ||
		course_id AS "Info Student"
FROM Student;
