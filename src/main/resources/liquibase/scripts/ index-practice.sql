-- liquibase formatted sql

-- changeset Nik

CREATE INDEX student_name_index ON student (name);

CREATE INDEX faculty_name_and_color_index ON faculty (name, color);