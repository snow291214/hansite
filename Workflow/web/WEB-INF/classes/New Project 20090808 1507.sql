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
  `Blob` blob,
  PRIMARY KEY  (`Uid`),
  UNIQUE KEY `Uid` (`Uid`),
  KEY `TaskUid` (`TaskUid`),
  CONSTRAINT `files_fk` FOREIGN KEY (`TaskUid`) REFERENCES `tasks` (`Uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `files`
--

/*!40000 ALTER TABLE `files` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tasks`
--

/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`Uid`,`InternalNumber`,`ExternalNumber`,`Description`,`StartDate`,`DueDate`) VALUES 
 (14,'T.2009-1','None','Yo!','2009-08-08','2009-08-11'),
 (15,'T.2009-2','б/н','Yo!','2009-08-08','2009-08-11');
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
  PRIMARY KEY  (`Uid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`Uid`,`Login`,`LastName`,`FirstName`,`MiddleName`,`Email`) VALUES 
 (19,'ASU\\48han','Худяков','Алексей','Николаевич','khudyakov@snos.ru'),
 (21,'DOSNOS\\48ain','Анастасиади','Ирина','Николаевна',NULL),
 (22,'DOSNOS\\48als','Андреева','Лиля','Шамильевна',NULL),
 (23,'DOSNOS\\48bgs','Басманова','Гузель','Шакировна',NULL),
 (24,'DOSNOS\\48bma','Баталова','Марина','Александровна',NULL),
 (25,'DOSNOS\\48vai','Ваганов','Алексей','Иванович',NULL),
 (26,'DOSNOS\\48vaz','Валиева','Альфия','Зайнулловна',NULL),
 (27,'DOSNOS\\48vdb','Величко','Дмитрий','Борисович',NULL),
 (28,'DOSNOS\\48gov','Галимова','Оксана','Владимировна',NULL),
 (29,'DOSNOS\\48gar','Гареев','Айрат','Ралифович',NULL),
 (30,'DOSNOS\\48gla','Гашева','Любовь','Александровна',NULL),
 (31,'DOSNOS\\48gas','Глушенков','Андрей','Степанович',NULL),
 (32,'DOSNOS\\48gvp','Голубева','Валентина','Петровна',NULL),
 (33,'DOSNOS\\48gos','Гудкова','Ольга','Сергеевна',NULL),
 (34,'DOSNOS\\48dda','Давыдов','Денис','Александрович',NULL),
 (35,'DOSNOS\\48drn','Докучаева','Регина','Наильевна',NULL),
 (36,'DOSNOS\\48inn','Ибатуллина','Назифа','Нутфулловна',NULL),
 (37,'DOSNOS\\48kev','Калинина','Елена','Владимировна',NULL),
 (38,'DOSNOS\\48kav','Кириллов','Андрей','Витальевич',NULL),
 (39,'DOSNOS\\48kel','Кирюхина','Елена','Леонидовна',NULL),
 (40,'DOSNOS\\48kva','Котляров','Вячеслав','Анатольевич',NULL),
 (41,'DOSNOS\\48lni','Леманова','Наталья','Ивановна',NULL),
 (42,'DOSNOS\\48loa','Любимова','Ольга','Анатольевна',NULL),
 (43,'DOSNOS\\48mon','Михайлычева','Олеся','Никитовна',NULL),
 (44,'DOSNOS\\48nvv','Новикова','Виктория','Викторовна',NULL),
 (45,'DOSNOS\\48plm','Протасова','Любовь','Михайловна',NULL),
 (46,'DOSNOS\\48rua','Ремезова','Юлия','Александровна',NULL),
 (47,'DOSNOS\\48sat','Саитбатталова','Альфия','Талгатовна',NULL),
 (48,'DOSNOS\\48san','Самойлов','Андрей','Николаевич',NULL),
 (49,'DOSNOS\\48can','Семенова','Анастасия','Николаевна',NULL),
 (50,'DOSNOS\\48sna','Совина','Нина','Александровна',NULL),
 (51,'DOSNOS\\48svs','Старухин','Виктор','Сергеевич',NULL),
 (52,'DOSNOS\\48trn','Табульдин','Радик','Наильевич',NULL),
 (53,'DOSNOS\\48tir','Тагиров','Ильгиз','Ривильевич',NULL),
 (54,'DOSNOS\\48fai','Фахретдинова','Алсу','Ильсуровна',NULL),
 (55,'DOSNOS\\48faa','Фирсова','Анжела','Анатольевна',NULL),
 (56,'DOSNOS\\48flp','Фокина','Лариса','Петровна',NULL),
 (57,'DOSNOS\\48fop','Фролова','Олеся','Петровна',NULL),
 (58,'DOSNOS\\48hag','Халилов','Альберт','Газинурович',NULL),
 (59,'DOSNOS\\48hrd','Харисов','Руслан','Дильбарович',NULL),
 (60,'DOSNOS\\48han','Худяков','Алексей','Николаевич',NULL),
 (61,'DOSNOS\\48cmv','Чернова','Мария','Викторовна',NULL),
 (62,'DOSNOS\\48sea','Шилов','Евгений','Анатольевич',NULL),
 (63,'DOSNOS\\48sgv','Ширшова','Галина','Владимировна',NULL),
 (64,'DOSNOS\\48jmi','Яковлева','Мария','Ивановна',NULL);
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
  UNIQUE KEY `Uid` (`Uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workflows`
--

/*!40000 ALTER TABLE `workflows` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflows` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
