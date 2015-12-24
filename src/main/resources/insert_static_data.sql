/*insert into spotcheck_question(name, question, options, options_type, answer) values
  ('which_not_sns', 'Which of the following is not a social network site?', 'Facebook,Google Plus,Windows,Twitter', 'radio', 'Windows'),
  ('which_not_browser', 'Which of the following is not a Web browser?', 'PowerPoint,Chrome,Internet Explorer,Safari', 'radio', 'PowerPoint'),
  ('which_not_mobileOs', 'Which of the following is not a mobile operating system', 'Android,Oracle,Blackberry,iOS', 'radio', 'Oracle');
*/

--
-- Dumping data for table `presurvey_questions`
--

LOCK TABLES `presurvey_questions` WRITE;
/*!40000 ALTER TABLE `presurvey_questions` DISABLE KEYS */;
INSERT INTO `presurvey_questions` VALUES (1, 'What is your gender?', 'multiple_choice', 'Male|Female|Other'),
  (2, 'Which of the following categories includes your age?', 'multiple_choice', '18 to 24|25 to 34|35 to 45|45 to 54|55 or older'),
  (3, 'What is the highest level of school you have completed or the highest degree you have received?', 'multiple_choice', 'Less than high school degree|High school degree or equivalent (e.g., GED)|Some college but no degree|Bachelor degree|Graduate degree'),
  (4, 'If you went to college, what was your major?', 'text', 'For example, computer science, mechanical engineering, psychology, music, law, etc.'),
  (5, 'How familiar are you with concepts related to Computer Science or Information Technology (IT)?', 'multiple_choice', 'Very low familiarity|low familiarity|Medium familiarity|High familiarity|Very high familiarity'),
  (6, 'If you worked for an IT company, how long did you do so?', 'multiple_choice', 'Did not work for IT |For less than a year|For one to five years|For more than five years'),
  (7, 'How familiar are you with concepts such as Internet of Things, Smart Homes, and Smart Cities?', 'multiple_choice', 'Never heard the terms|Terms seem familiar|Read about the concepts a few times|Read about the concepts several times'),
  (8, 'Please use the text box below if you need to clarify any of your answers above', 'text', 'Optional clarifications');
/*!40000 ALTER TABLE `presurvey_questions` ENABLE KEYS */;
UNLOCK TABLES;
  
  
--
-- Dumping data for table `creativity_questions`
--

LOCK TABLES `creativity_questions` WRITE;
/*!40000 ALTER TABLE `creativity_questions` DISABLE KEYS */;
INSERT INTO `creativity_questions` VALUES 
  (1,'Capable'),
  (2,'Artificial'),
  (3,'Clever'),
  (4,'Cautious'),
  (5,'Confident'),
  (6,'Egotistical'),
  (7,'Commonplace'),
  (8,'Humourous'),
  (9,'Conservative'),
  (10,'Individualistic'),
  (11,'Conventional'),
  (12,'Informal'),
  (13,'Dissatisfied'),
  (14,'Insightful'),
  (15,'Suspicious'),
  (16,'Honest'),
  (17,'Intelligent'),
  (18,'Well mannered'),
  (19,'Wide interests'),
  (20,'Inventive'),
  (21,'Original'),
  (22,'Norrow interests'),
  (23,'Reflective'),
  (24,'Sincere'),
  (25,'Resourceful'),
  (26,'Self-confident'),
  (27,'Sexy'),
  (28,'Submissive'),
  (29,'Snobbish'),
  (30,'Unconventional');
/*!40000 ALTER TABLE `creativity_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personality_questions`
--

LOCK TABLES `personality_questions` WRITE;
/*!40000 ALTER TABLE `personality_questions` DISABLE KEYS */;
INSERT INTO `personality_questions` VALUES 
  (1,'Am the life of the party'),
  (2,'Sympathize with others\' feelings'),
  (3,'Get chores done right away'),
  (4,'Have frequent mood swings'),
  (5,'Have a vivid imagination'),
  (6,'Don\'t talk a lot'),
  (7,'Am not interested in other people\'s problems'),
  (8,'Often forget to put things back in their proper place'),
  (9,'Am relaxed most of the time'),
  (10,'Am not interested in abstract ideas'),
  (11,'Talk to a lot of different people at parties'),
  (12,'Feel others\' emotions'),
  (13,'Like order'),
  (14,'Get upset easily'),
  (15,'Have difficulty understanding abstract ideas'),
  (16,'Keep in the background'),
  (17,'Am not really interested in others'),
  (18,'Make a mess of things'),
  (19,'Seldom feel blue'),
  (20,'Do not have a good imagination');
/*!40000 ALTER TABLE `personality_questions` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `postsurvey_questions` WRITE;
/*!40000 ALTER TABLE `presurvey_questions` DISABLE KEYS */;
INSERT INTO `postsurvey_questions` VALUES (1, 'How long did the main tasks (excluding pre and post surveys) take?', 'text', 'duration in hours:minutes, e.g., 00:30 for thirty minutes'),
  (2, 'How do you rate the difficulty of main tasks?', 'multiple_choice', 'Very easy|Easy|Medium|High|Very high'),
  (3, 'Please provide any additional comments you have below', 'text', 'Optional comments');
/*!40000 ALTER TABLE `presurvey_questions` ENABLE KEYS */;
UNLOCK TABLES;

