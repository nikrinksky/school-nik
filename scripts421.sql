ALTER TABLE student ADD CONSTRAINT age_more_than_16 CHECK (age > 16);

ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE student ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty ADD CONSTRAINT name_color_unique UNIQUE (name, color);