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
INSERT INTO `presurvey_questions` VALUES (1,'Gender'),(2,'Age'),(3,'Highest level of education');
/*!40000 ALTER TABLE `presurvey_questions` ENABLE KEYS */;
UNLOCK TABLES;
  
  
--
-- Dumping data for table `creativity_questions`
--

LOCK TABLES `creativity_questions` WRITE;
/*!40000 ALTER TABLE `creativity_questions` DISABLE KEYS */;
INSERT INTO `creativity_questions` VALUES (1,'Capable'),(2,'Artificial'),(3,'Clever'),(4,'Cautious'),(5,'Confident'),(6,'Egotistical'),(7,'Commonplace'),(8,'Humourous'),(9,'Conservative'),(10,'Individualistic');
/*!40000 ALTER TABLE `creativity_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personality_questions`
--

LOCK TABLES `personality_questions` WRITE;
/*!40000 ALTER TABLE `personality_questions` DISABLE KEYS */;
INSERT INTO `personality_questions` VALUES (1,'Am the life of the party'),(2,'Sympathize with others\' feelings'),(3,'Get chores done right away'),(4,'Have frequent mood swings'),(5,'Have a vivid imagination'),(6,'Don\'t talk a lot'),(7,'Am not interested in other people\'s problems'),(8,'Often forget to put things back in their proper place'),(9,'Am relaxed most of the time'),(10,'Am not interested in abstract ideas');
/*!40000 ALTER TABLE `personality_questions` ENABLE KEYS */;
UNLOCK TABLES;
