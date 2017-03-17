CREATE Database dikt;
USE Database dikt;
-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 25, 2016 at 07:39 PM
-- Server version: 5.6.28-0ubuntu0.15.10.1
-- PHP Version: 5.6.11-1ubuntu3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dikt`
--

-- --------------------------------------------------------

--
-- Table structure for table `Credentials`
--

CREATE TABLE IF NOT EXISTS `Credentials` (
  `Username` varchar(25) NOT NULL,
  `Password` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Credentials`
--

INSERT INTO `Credentials` (`Username`, `Password`) VALUES
('cs11101', '1234'),
('cs11104', '1234'),
('cs131011', '1234'),
('cs131027', '1234'),
('cs131071', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `JNCT_Roles_Services`
--

CREATE TABLE IF NOT EXISTS `JNCT_Roles_Services` (
  `RoleServices_id` int(7) NOT NULL,
  `Services_FK` int(7) NOT NULL,
  `Roles_FK` int(7) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `JNCT_Roles_Services`
--

INSERT INTO `JNCT_Roles_Services` (`RoleServices_id`, `Services_FK`, `Roles_FK`) VALUES
(6, 0, 1),
(8, 0, 2),
(3, 1, 1),
(1, 1, 2),
(2, 2, 1),
(9, 2, 2),
(4, 3, 1),
(7, 3, 2),
(5, 4, 1),
(12, 5, 3),
(13, 6, 3),
(14, 7, 1),
(15, 7, 2),
(16, 7, 3);

-- --------------------------------------------------------

--
-- Table structure for table `JNCT_Students_Lessons`
--

CREATE TABLE IF NOT EXISTS `JNCT_Students_Lessons` (
  `Stud_FK` varchar(8) NOT NULL,
  `Les_FK` int(15) NOT NULL,
  `Grade` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `JNCT_Students_Lessons`
--

INSERT INTO `JNCT_Students_Lessons` (`Stud_FK`, `Les_FK`, `Grade`) VALUES
('11101', 3, 5),
('131011', 1, 2),
('131011', 2, 3),
('131011', 3, 9),
('131011', 4, 2),
('131011', 5, 5),
('131027', 1, 7),
('131027', 2, 10),
('131027', 3, 10),
('131027', 4, 2),
('131027', 5, 5),
('131071', 2, 5),
('131071', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Lessons`
--

CREATE TABLE IF NOT EXISTS `Lessons` (
  `LessonID` int(15) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Semester` varchar(15) NOT NULL,
  `Description` varchar(150) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Lessons`
--

INSERT INTO `Lessons` (`LessonID`, `Name`, `Semester`, `Description`) VALUES
(1, 'Eisagwgh sthn Plhroforikh', '1', 'c'),
(2, 'Methodologies upologiston', '4', 'java'),
(3, 'diktuakos programmatismos', '5', 'java ii'),
(4, 'diktya i', '3', 'osi'),
(5, 'diktya ii', '4', 'ip/tcp');

-- --------------------------------------------------------

--
-- Table structure for table `LoginKeys`
--

CREATE TABLE IF NOT EXISTS `LoginKeys` (
  `User_FK` varchar(25) NOT NULL,
  `Login_Key` varchar(50) NOT NULL,
  `TimeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `logedfrom` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Roles`
--

CREATE TABLE IF NOT EXISTS `Roles` (
  `R_id` int(11) NOT NULL,
  `R_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Roles`
--

INSERT INTO `Roles` (`R_id`, `R_name`) VALUES
(1, 'Admin'),
(2, 'Secretary'),
(3, 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `Services`
--

CREATE TABLE IF NOT EXISTS `Services` (
  `S_id` int(11) NOT NULL,
  `S_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Services`
--

INSERT INTO `Services` (`S_id`, `S_name`) VALUES
(0, ' Add Student'),
(1, 'Add/Modify Grade'),
(2, 'Modify Grades'),
(3, ' Show Data'),
(4, 'Add User'),
(5, 'Select cources'),
(6, 'show my data'),
(7, 'show cources');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `name` varchar(25) NOT NULL,
  `RegistrationNo` varchar(8) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `roles_FK` int(15) NOT NULL,
  `Cred_FK` varchar(25) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`name`, `RegistrationNo`, `last_name`, `roles_FK`, `Cred_FK`, `email`, `phone`) VALUES
('Giorgos', '11101', 'Giorgou', 1, 'cs11101', 'cs11101@teiath.gr', '2105463842'),
('Petros', '11104', 'Petrou', 2, 'cs11104', 'cs11104@teiath.gr', '2101234567'),
('Theofilos', '131011', 'Axiotis', 3, 'cs131011', 'cs131011@teiath.gr', '6971234567'),
('ioulios', '131027', 'tsiko', 3, 'cs131027', 'cs131027@teiath.gr', ''),
('Giannhs', '131071', 'Kyritsis', 3, 'cs131071', 'cs131071@teiath.gr', '2101234567');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Credentials`
--
ALTER TABLE `Credentials`
  ADD PRIMARY KEY (`Username`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `JNCT_Roles_Services`
--
ALTER TABLE `JNCT_Roles_Services`
  ADD PRIMARY KEY (`RoleServices_id`),
  ADD KEY `Services_FK` (`Services_FK`,`Roles_FK`),
  ADD KEY `Roles_FK` (`Roles_FK`);

--
-- Indexes for table `JNCT_Students_Lessons`
--
ALTER TABLE `JNCT_Students_Lessons`
  ADD PRIMARY KEY (`Stud_FK`,`Les_FK`),
  ADD KEY `Stud_FK` (`Stud_FK`,`Les_FK`),
  ADD KEY `Les_FK` (`Les_FK`);

--
-- Indexes for table `Lessons`
--
ALTER TABLE `Lessons`
  ADD PRIMARY KEY (`LessonID`);

--
-- Indexes for table `LoginKeys`
--
ALTER TABLE `LoginKeys`
  ADD PRIMARY KEY (`User_FK`,`Login_Key`),
  ADD KEY `User_FK` (`User_FK`,`Login_Key`),
  ADD KEY `User_FK_2` (`User_FK`);

--
-- Indexes for table `Roles`
--
ALTER TABLE `Roles`
  ADD PRIMARY KEY (`R_id`),
  ADD UNIQUE KEY `R_name` (`R_name`);

--
-- Indexes for table `Services`
--
ALTER TABLE `Services`
  ADD PRIMARY KEY (`S_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`RegistrationNo`),
  ADD KEY `roles_FK` (`roles_FK`),
  ADD KEY `User_FK` (`Cred_FK`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `JNCT_Roles_Services`
--
ALTER TABLE `JNCT_Roles_Services`
  MODIFY `RoleServices_id` int(7) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `Lessons`
--
ALTER TABLE `Lessons`
  MODIFY `LessonID` int(15) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `JNCT_Roles_Services`
--
ALTER TABLE `JNCT_Roles_Services`
  ADD CONSTRAINT `JNCT_Roles_Services_ibfk_1` FOREIGN KEY (`Services_FK`) REFERENCES `Services` (`S_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `JNCT_Roles_Services_ibfk_2` FOREIGN KEY (`Roles_FK`) REFERENCES `Roles` (`R_id`) ON UPDATE CASCADE;

--
-- Constraints for table `JNCT_Students_Lessons`
--
ALTER TABLE `JNCT_Students_Lessons`
  ADD CONSTRAINT `JNCT_Students_Lessons_ibfk_2` FOREIGN KEY (`Les_FK`) REFERENCES `Lessons` (`LessonID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `JNCT_Students_Lessons_ibfk_3` FOREIGN KEY (`Stud_FK`) REFERENCES `Users` (`RegistrationNo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `LoginKeys`
--
ALTER TABLE `LoginKeys`
  ADD CONSTRAINT `LoginKeys_ibfk_1` FOREIGN KEY (`User_FK`) REFERENCES `Credentials` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Users`
--
ALTER TABLE `Users`
  ADD CONSTRAINT `Users_ibfk_1` FOREIGN KEY (`roles_FK`) REFERENCES `Roles` (`R_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Users_ibfk_2` FOREIGN KEY (`Cred_FK`) REFERENCES `Credentials` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
