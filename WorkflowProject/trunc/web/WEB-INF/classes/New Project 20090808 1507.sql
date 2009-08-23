-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema workflowdb
--

CREATE DATABASE IF NOT EXISTS workflowdb;
USE workflowdb;

--
-- Definition of table `files`
--

DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `Uid` int(11) NOT NULL auto_increment,
  `TaskUid` int(11) default NULL,
  `FileName` varchar(100) default NULL,
  `Blob` longblob,
  PRIMARY KEY  (`Uid`),
  UNIQUE KEY `Uid` (`Uid`),
  KEY `TaskUid` (`TaskUid`),
  CONSTRAINT `files_fk` FOREIGN KEY (`TaskUid`) REFERENCES `tasks` (`Uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `files`
--

/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` (`Uid`,`TaskUid`,`FileName`,`Blob`) VALUES 
 (6,28,'details.get.desc.xml',0x3C7765627363726970743E0D0A20203C73686F72746E616D653E44657461696C733C2F73686F72746E616D653E0D0A20203C6465736372697074696F6E3ED094D0B0D0BDD0BDD18BD0B520D0BFD0BE20D0BFD180D0BED0B5D0BAD182D1833C2F6465736372697074696F6E3E0D0A20203C75726C3E2F73676E68702F64657461696C733C2F75726C3E0D0A20203C61757468656E7469636174696F6E3E757365723C2F61757468656E7469636174696F6E3E0D0A3C2F7765627363726970743E0D0A);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;


--
-- Definition of table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `Uid` int(11) NOT NULL auto_increment,
  `InternalNumber` varchar(20) default NULL COMMENT 'Внутренний номер задачи',
  `ExternalNumber` varchar(20) default NULL COMMENT 'Внешний номер задачи',
  `Description` varchar(250) default NULL COMMENT 'Резолюция к задаче',
  `StartDate` date default NULL COMMENT 'Дата регистрации задачи',
  `DueDate` date default NULL COMMENT 'Дата окончания задачи',
  PRIMARY KEY  (`Uid`),
  UNIQUE KEY `Uid` (`Uid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tasks`
--

/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`Uid`,`InternalNumber`,`ExternalNumber`,`Description`,`StartDate`,`DueDate`) VALUES 
 (28,'T.2009-1','б/н','Тестовый пример','2009-08-21','2009-08-24');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `Uid` int(10) unsigned NOT NULL auto_increment,
  `Login` varchar(20) NOT NULL,
  `LastName` varchar(50) default NULL,
  `FirstName` varchar(50) default NULL,
  `MiddleName` varchar(50) default NULL,
  `Email` varchar(50) default NULL,
  `SessionUid` varchar(50) default NULL COMMENT 'Уникальный идентификатор сессии',
  PRIMARY KEY  (`Uid`),
  KEY `Login` (`Login`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`Uid`,`Login`,`LastName`,`FirstName`,`MiddleName`,`Email`,`SessionUid`) VALUES 
 (19,'48han','Худяков','Алексей','Николаевич','khudyakov@snos.ru',NULL),
 (21,'48ain','Анастасиади','Ирина','Николаевна',NULL,NULL),
 (22,'48als','Андреева','Лиля','Шамильевна',NULL,NULL),
 (23,'48bgs','Басманова','Гузель','Шакировна',NULL,NULL),
 (24,'48bma','Баталова','Марина','Александровна',NULL,NULL),
 (25,'48vai','Ваганов','Алексей','Иванович',NULL,NULL),
 (26,'48vaz','Валиева','Альфия','Зайнулловна',NULL,NULL),
 (27,'48vdb','Величко','Дмитрий','Борисович',NULL,NULL),
 (28,'48gov','Галимова','Оксана','Владимировна',NULL,NULL),
 (29,'48gar','Гареев','Айрат','Ралифович',NULL,NULL),
 (30,'48gla','Гашева','Любовь','Александровна',NULL,NULL),
 (31,'48gas','Глушенков','Андрей','Степанович',NULL,NULL),
 (32,'48gvp','Голубева','Валентина','Петровна',NULL,NULL),
 (33,'48gos','Гудкова','Ольга','Сергеевна',NULL,NULL),
 (34,'48dda','Давыдов','Денис','Александрович',NULL,NULL),
 (35,'48drn','Докучаева','Регина','Наильевна',NULL,NULL),
 (36,'48inn','Ибатуллина','Назифа','Нутфулловна',NULL,NULL),
 (37,'48kev','Калинина','Елена','Владимировна',NULL,NULL),
 (38,'48kav','Кириллов','Андрей','Витальевич',NULL,NULL),
 (39,'48kel','Кирюхина','Елена','Леонидовна',NULL,NULL),
 (40,'48kva','Котляров','Вячеслав','Анатольевич',NULL,NULL),
 (41,'48lni','Леманова','Наталья','Ивановна',NULL,NULL),
 (42,'48loa','Любимова','Ольга','Анатольевна',NULL,NULL),
 (43,'48mon','Михайлычева','Олеся','Никитовна',NULL,NULL),
 (44,'48nvv','Новикова','Виктория','Викторовна',NULL,NULL),
 (45,'48plm','Протасова','Любовь','Михайловна',NULL,NULL),
 (46,'48rua','Ремезова','Юлия','Александровна',NULL,NULL),
 (47,'48sat','Саитбатталова','Альфия','Талгатовна',NULL,NULL),
 (48,'48san','Самойлов','Андрей','Николаевич',NULL,NULL),
 (49,'48can','Семенова','Анастасия','Николаевна',NULL,NULL),
 (50,'48sna','Совина','Нина','Александровна',NULL,NULL),
 (51,'48svs','Старухин','Виктор','Сергеевич',NULL,NULL),
 (52,'48trn','Табульдин','Радик','Наильевич',NULL,NULL),
 (53,'48tir','Тагиров','Ильгиз','Ривильевич',NULL,NULL),
 (54,'48fai','Фахретдинова','Алсу','Ильсуровна',NULL,NULL),
 (55,'48faa','Фирсова','Анжела','Анатольевна',NULL,NULL),
 (56,'48flp','Фокина','Лариса','Петровна',NULL,NULL),
 (57,'48fop','Фролова','Олеся','Петровна',NULL,NULL),
 (58,'48hag','Халилов','Альберт','Газинурович',NULL,NULL),
 (59,'48hrd','Харисов','Руслан','Дильбарович',NULL,NULL),
 (61,'48cmv','Чернова','Мария','Викторовна',NULL,NULL),
 (62,'48sea','Шилов','Евгений','Анатольевич',NULL,NULL),
 (63,'48sgv','Ширшова','Галина','Владимировна',NULL,NULL),
 (64,'48jmi','Яковлева','Мария','Ивановна',NULL,NULL),
 (70,'48hzm','Харрасов','Зиннур','Минигалиевич','harrasov@snos.ru',NULL),
 (71,'48edv','Еремеев','Дмитрий','Васильевич','48edv@snos.ru',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `workflows`
--

DROP TABLE IF EXISTS `workflows`;
CREATE TABLE `workflows` (
  `Uid` int(11) NOT NULL auto_increment COMMENT 'Уникальный идентификатор',
  `TaskUid` int(11) NOT NULL COMMENT 'Идентификатор задачи',
  `ParentUserUid` int(11) NOT NULL default '0' COMMENT 'Идентификатор родительского пользователя',
  `UserUid` int(11) NOT NULL COMMENT 'Идентификатор пользователя, которому назначена задача',
  `Description` varchar(255) default NULL COMMENT 'Резолюция к задаче',
  `State` tinyint(2) NOT NULL default '0' COMMENT 'Идентификатор состояния задачи',
  `AssignDate` date default NULL COMMENT 'Дата назначения задания',
  `FinishDate` date default NULL COMMENT 'Дата завершения задачи',
  PRIMARY KEY  (`Uid`),
  UNIQUE KEY `Uid` (`Uid`),
  KEY `workflows_fk` (`TaskUid`),
  CONSTRAINT `workflows_fk` FOREIGN KEY (`TaskUid`) REFERENCES `tasks` (`Uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workflows`
--

/*!40000 ALTER TABLE `workflows` DISABLE KEYS */;
INSERT INTO `workflows` (`Uid`,`TaskUid`,`ParentUserUid`,`UserUid`,`Description`,`State`,`AssignDate`,`FinishDate`) VALUES 
 (12,28,19,71,'Тестовый пример',0,'2009-08-21',NULL);
/*!40000 ALTER TABLE `workflows` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
