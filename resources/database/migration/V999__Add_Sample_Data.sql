SET search_path = school;

-- student
COPY student (id, name, age) FROM stdin;
1	test 1	20
2	test 2	20
3	test 3	20
\.

SELECT pg_catalog.setval('student_id_seq', 3, true);

-- course
COPY course (id, name) FROM stdin;
1	course 1
2	course 2
3	course 3
\.

SELECT pg_catalog.setval('course_id_seq', 3, true);

-- registration
COPY registration (id, course_id, student_id, score) FROM stdin;
1	1	1	90
2	2	1	99
3	3	1	100
4	1	2	60
5	2	2	85
6	3	2	44
7	1	3	79
8	2	3	12
9	3	3	56
\.

SELECT pg_catalog.setval('registration_id_seq', 9, true);
