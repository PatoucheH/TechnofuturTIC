-- 3.1
INSERT INTO student
VALUES (100, 'Hugo', 'Pater', '04-04-1999', 'hpater', 1010, 20, 10);

-- 3.2
INSERT INTO student 
VALUES (101, 'Paul', Null, '04-04-2001',null, 1010,null, 10);

-- 3.3
CREATE TABLE section_archives AS TABLE section;

-- 3.4
INSERT INTO student
VALUES (102
		,'Bob'
		, 'Dylan'
		, '01-01-2000'
		, Null
		, (	SELECT section_id 
			FROM student
			WHERE first_name = 'Keanu'
			AND last_name = 'Reeves')
		, Null
		, 'EG' ||  RIGHT(	(	SELECT course_id 
								FROM course
								WHERE professor_id = (	
									SELECT professor_id
									FROM professor
									WHERE professor_name = 'zidda'	)
							)
							, 4
						)
);

-- 3.5
INSERT INTO section
VALUES (
		 1530
		,'Administration des SI'
		, 	(SELECT delegate_id
			FROM section
			WHERE section_id = 1010)
);

-- 3.6
UPDATE student
SET course_id = 'EG2210'
WHERE first_name = 'Hugo';

-- 3.7
UPDATE student 
SET last_name = 'Pierre'
WHERE student_id = 101;

UPDATE student 
SET year_result = 18, login = LOWER(LEFT(first_name, 1) || last_name)
WHERE student_id = 101;

--3.8
UPDATE student
SET year_result = 15
WHERE section_id = 1010;

-- 3.9
UPDATE section
SET delegate_id = (
					SELECT student_id
					FROM student
					WHERE first_name = 'Keanu'
)
WHERE section_id = 1530;

--3.10
UPDATE section
SET section_name = (
					SELECT section_name
					FROM section
					WHERE section_id = 1320
), delegate_id = (
					SELECT delegate_id
					FROM section
					WHERE section_id = 1320
)
WHERE section_id = 1530;

-- 3.11
UPDATE section
SET delegate_id = (
					SELECT student_id
					FROM student
					WHERE first_name = 'Alyssa'
					AND last_name = 'Milano'
)
WHERE section_id =  (
		SELECT section_id
		FROM student
		WHERE first_name = 'Alyssa'
		AND last_name = 'Milano'
);

--3.12
DELETE FROM student
WHERE student_id = 101;

--3.13
DELETE FROM student
WHERE student_id = 100;
DELETE FROM student 
WHERE last_name = 'bassinger';

--3.14
DELETE FROM student
WHERE year_result < 8;

--3.15
DELETE FROM course
WHERE professor_id is null;

--3.16








