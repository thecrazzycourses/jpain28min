INSERT INTO course (id, name, created_date, last_updated_date) VALUES (1001, 'Java', sysdate(), sysdate());
INSERT INTO course (id, name, created_date, last_updated_date) VALUES (1002, 'JavaScript', sysdate(), sysdate());
INSERT INTO course (id, name, created_date, last_updated_date) VALUES (1003, 'Spring', sysdate(), sysdate());
INSERT INTO course (id, name, created_date, last_updated_date) VALUES (1004, 'NextJs', sysdate(), sysdate());
INSERT INTO course (id, name, created_date, last_updated_date) VALUES (1005, 'Java', sysdate(), sysdate());

-- First Passport then Student as Passport is refer in Student
INSERT INTO passport (id, number) VALUES (3001, 'A123545');
INSERT INTO passport (id, number) VALUES (3002, 'B45235');
INSERT INTO passport (id, number) VALUES (3003, 'C12345');
INSERT INTO passport (id, number) VALUES (3004, 'D65435');


INSERT INTO student (id, name, passport_id) VALUES (2001, 'Rahul', 3001);
INSERT INTO student (id, name, passport_id) VALUES (2002, 'Shalu', 3002);
INSERT INTO student (id, name, passport_id) VALUES (2003, 'Ravi', 3003);
INSERT INTO student (id, name, passport_id) VALUES (2004, 'Shubham', 3004);


INSERT INTO review (id, description, rating, course_id) VALUES (4001, 'Good', '5', 1001);
INSERT INTO review (id, description, rating, course_id) VALUES (4002, 'Voice not clear', '3', 1001);
INSERT INTO review (id, description, rating, course_id) VALUES (4003, 'Video not clear', '2', 1002);
INSERT INTO review (id, description, rating, course_id) VALUES (4004, 'Can be better', '4', 1003);

INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (2001, 1001);
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (2002, 1001);
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (2003, 1002);
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (2003, 1002);
