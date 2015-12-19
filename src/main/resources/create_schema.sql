CREATE TABLE presurvey_response (
  id INT NOT NULL auto_increment,
  mturk_id VARCHAR(20) NOT NULL,
  response_time DATETIME NOT NULL,
  gender VARCHAR(20) NOT NULL,
  age VARCHAR(20) NOT NULL,
  education VARCHAR(20) NOT NULL,
  socialmedia_frequency VARCHAR(20) NOT NULL,
  sharing_frequency VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE spotcheck_question (
  id INT NOT NULL auto_increment,
  name VARCHAR(200) NOT NULL,
  question VARCHAR(1024) NOT NULL,
  options VARCHAR(1024) NOT NULL,
  options_type VARCHAR(20) NOT NULL,
  answer VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);