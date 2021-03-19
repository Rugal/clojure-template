--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: school; Type: SCHEMA; Schema: -; Owner: -
--

SET search_path = school;

CREATE TABLE student
(
    id  serial PRIMARY KEY,
    name character varying(50),
    age  integer
);

CREATE TABLE course
(
    id  serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE registration
(
    id   serial PRIMARY KEY,
    course_id   integer REFERENCES course (id),
    student_id   integer REFERENCES student (id),
    score integer
);
