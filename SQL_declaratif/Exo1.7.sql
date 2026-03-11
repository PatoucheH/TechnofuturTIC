DROP TABLE IF EXISTS Section CASCADE;
DROP TABLE IF EXISTS Professor CASCADE;
DROP TABLE IF EXISTS Course CASCADE;
DROP TABLE IF EXISTS Student CASCADE;
DROP TABLE IF EXISTS Grade CASCADE;

CREATE TABLE Section (
  section_id int NOT NULL,
  section_name varchar(50),
  delegate_id int NOT NULL
);

CREATE TABLE Professor (
  professor_id int NOT NULL,
  professor_name varchar(30) NOT NULL,
  professor_surname varchar(30) NOT NULL,
  section_id int NOT NULL,
  professor_office int NOT NULL,
  professor_email varchar(30) NOT NULL,
  professor_hire_date timestamp NOT NULL,
  professor_wage int NOT NULL
);

CREATE TABLE Course (
  course_id varchar(8) NOT NULL,
  course_name varchar(200) NOT NULL,
  course_ects decimal(3,1) NOT NULL,
  professor_id INT NOT NULL  
);

CREATE TABLE Student (
  student_id int NOT NULL,
  first_name varchar(50),
  last_name varchar(50),
  birth_date timestamp,
  login varchar(50),
  section_id int,
  year_result int,
  course_id varchar(8) NOT NULL  
);

CREATE TABLE Grade (
  grade char(2) NOT NULL check (grade in ('E','TB','B','S','F','I','IG')),
  lower_bound int NOT NULL,
  upper_bound int NOT NULL
);

ALTER TABLE Section
ADD CONSTRAINT PK_section PRIMARY KEY (section_id);


ALTER TABLE Professor
ADD CONSTRAINT PK_professor PRIMARY KEY (professor_id);
ALTER TABLE Professor
ADD CONSTRAINT FK_professor_section foreign key (section_id) references Section (section_id);

ALTER TABLE Course
ADD CONSTRAINT PK_course PRIMARY KEY (course_id);
ALTER TABLE Course
ADD CONSTRAINT FK_course_professor FOREIGN KEY (professor_id) references Professor (professor_id);

ALTER TABLE Student 
ADD CONSTRAINT PK_student PRIMARY KEY (student_id);
ALTER TABLE Student
ADD CONSTRAINT FK_student_section foreign key (section_id) references section (section_id);
ALTER TABLE Student
ADD CONSTRAINT FK_student_course FOREIGN KEY (course_id) REFERENCES Course (course_id);

ALTER TABLE Section
ADD CONSTRAINT FK_section_delegate FOREIGN KEY (delegate_id) REFERENCES Student (student_id);

ALTER TABLE Grade
ADD CONSTRAINT PK_grade PRIMARY KEY (grade);