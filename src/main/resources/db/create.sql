CREATE DATABASE newsportal;
\c newsportal;
CREATE TABLE departments (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 about VARCHAR,
 website VARCHAR,
 email VARCHAR
);

CREATE TABLE users (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 position VARCHAR,
 roles VARCHAR
);

CREATE TABLE news (
 id SERIAL PRIMARY KEY,
 headline VARCHAR,
 content VARCHAR,
 author VARCHAR,
 departmentid INTEGER,
 createdat LONG
);

CREATE TABLE departments_users (
 id SERIAL PRIMARY KEY,
 Userid INTEGER,
 departmentid INTEGER
);

CREATE DATABSE newsportal_test WITH TEMPLATE newsportal;