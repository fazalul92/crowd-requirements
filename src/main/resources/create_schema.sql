CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mturk_id` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_phase` tinyint NOT NULL,
  `completion_code` VARCHAR(20),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `creativity_questions` (
  `id` int(11) NOT NULL  AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `creativity_questions_users` (
  `id` int(11) NOT NULL  AUTO_INCREMENT,
  `creativity_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_creativity_questions_user_user` (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY `FK_creativity_questions_user_creativity_question` (`creativity_question_id`) REFERENCES `creativity_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `personality_questions` (
  `id` int(11) NOT NULL  AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `personality_questions_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personality_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_personality_questions_user_user` (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY `FK_personality_questions_user_personality_question` (`personality_question_id`) REFERENCES `personality_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `postsurvey_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `question_type` varchar(50) DEFAULT NULL,
  `answer_choices` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `postsurvey_questions_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postsurvey_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_postsurvey_questions_user_user` (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY `FK_postsurvey_questions_user_postsurvey_question` (`postsurvey_question_id`) REFERENCES `postsurvey_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `presurvey_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `question_type` varchar(50) DEFAULT NULL,
  `answer_choices` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `presurvey_questions_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `presurvey_question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_presurvey_questions_user_presurvey_questions` (`presurvey_question_id`) REFERENCES `presurvey_questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY `FK_presurvey_questions_user_user` (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* PROJECT RELATED TABLES*/

/*For Question Categories: To be prepopulated by admin*/
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*For Projects: To be prepopulated by admin*/
CREATE TABLE `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*For Questions in Projects: To be prepopulated by admin*/
CREATE TABLE `project_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `description` varchar(4096) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*For assigning Projects to Users: To be prepopulated by admin*/
CREATE TABLE `projects_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*UserRequirements for ProjectQuestions*/
CREATE TABLE `requirements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role` varchar(256) NOT NULL,
  `feature` varchar(4096) NOT NULL,
  `benefit` varchar(4096) NOT NULL,
  `application_domain` varchar(256) NOT NULL,
  `application_domain_other` varchar(256) NOT NULL,
  `tags` varchar(1024) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `show_other` tinyint DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_user_requirements_user` (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `requirements_ratings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requirement_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `novelty` tinyint NOT NULL,
  `feasibility` tinyint NOT NULL,
  `created_at` datetime NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY `FK_ratings_requirements_requirement` (`requirement_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
   FOREIGN KEY `FK_requirements_ratings_user` (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `requirements_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(100) NOT NULL,
  `stemmed_tag` varchar(100) NOT NULL,
  `requirement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_requirements_tags_requirements` (`requirement_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stemmed_requirements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `req_id` int(11) NOT NULL,
  `stemmed_role` varchar(256),
  `stemmed_feature` varchar(4096),
  `stemmed_benefit` varchar(4096),
  `stemmed_tags` varchar(1024),
  PRIMARY KEY (`id`),
  FOREIGN KEY `FK_requirements_stemmed_reqid` (`req_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

