CREATE TABLE IF NOT EXISTS applicant (
  email VARCHAR(255) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS token (
  id VARCHAR(255) NOT NULL PRIMARY KEY,
  email VARCHAR(255) NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS company (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  business_name VARCHAR(255) NOT NULL,
  sector VARCHAR(255) NOT NULL,
  description TEXT,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  cnpj VARCHAR(18) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS competence (
  id VARCHAR(255) NULL DEFAULT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS applicant_competence (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  competence_id VARCHAR(255) NOT NULL,
  applicant_email VARCHAR(255) NOT NULL,
  experience INT NOT NULL,
  FOREIGN KEY (competence_id) REFERENCES competence(id),
  FOREIGN KEY (applicant_email) REFERENCES applicant(email)
);

CREATE TABLE IF NOT EXISTS job (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  company_email VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  salary_range DOUBLE NOT NULL,
  description TEXT,
  state VARCHAR(255) NOT NULL,
  FOREIGN KEY (company_email) REFERENCES company(email)
);

CREATE TABLE IF NOT EXISTS job_competence (
  job_id INT NOT NULL,
  competence_id VARCHAR(255) NOT NULL,
  FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE,
  FOREIGN KEY (competence_id) REFERENCES competence(id),
  PRIMARY KEY (job_id, competence_id)
);

INSERT INTO competence (id) VALUES ('Python'),
  ('C#'),
  ('C++'),
  ('JS'),
  ('PHP'),
  ('Swift'),
  ('Java'),
  ('Go'),
  ('SQL'),
  ('Ruby'),
  ('HTML'),
  ('CSS'),
  ('NOSQL'),
  ('Flutter'),
  ('TypeScript'),
  ('Perl'),
  ('Cobol'),
  ('dotNet'),
  ('Kotlin'),
  ('Dart');