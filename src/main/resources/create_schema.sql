/*Old Tables*/
/*
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
*/

/*New Schema*/
CREATE DATABASE `crowd_requirements_development` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mturk_id` varchar(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `creativity_questions` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `creativity_questions_users` (
  `id` int(11) NOT NULL,
  `creativity_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_creativity_questions_user_user_idx` (`user_id`),
  KEY `FK_creativity_questions_user_creativity_question_idx` (`creativity_question_id`),
  CONSTRAINT `FK_creativity_questions_user_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_creativity_questions_user_creativity_question` FOREIGN KEY (`creativity_question_id`) REFERENCES `creativity_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `personality_questions` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `personality_questions_users` (
  `id` int(11) NOT NULL,
  `personality_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_personality_questions_user_user_idx` (`user_id`),
  KEY `FK_personality_questions_user_personality_question_idx` (`personality_question_id`),
  CONSTRAINT `FK_personality_questions_user_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personality_questions_user_personality_question` FOREIGN KEY (`personality_question_id`) REFERENCES `personality_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `postsurvey_questions` (
  `id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `postsurvey_questions_users` (
  `id` int(11) NOT NULL,
  `postsurvey_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_postsurvey_questions_idx` (`postsurvey_question_id`),
  KEY `FK_users_idx` (`user_id`),
  CONSTRAINT `FK_postsurvey_questions_user_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_postsurvey_questions_user_postsurvey_question` FOREIGN KEY (`postsurvey_question_id`) REFERENCES `postsurvey_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `presurvey_questions` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `presurvey_questions_users` (
  `id` int(11) NOT NULL,
  `presurvey_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_presurvey_questions_idx` (`presurvey_question_id`),
  KEY `FK_users_idx` (`user_id`),
  CONSTRAINT `FK_presurvey_questions_user_presurvey_questions` FOREIGN KEY (`presurvey_question_id`) REFERENCES `presurvey_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_presurvey_questions_user_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

