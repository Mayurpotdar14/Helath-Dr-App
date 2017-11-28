-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 03, 2014 at 02:32 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mis`
--

-- --------------------------------------------------------

--
-- Table structure for table `disease_info`
--

CREATE TABLE IF NOT EXISTS `disease_info` (
  `DI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DI_NAME` varchar(256) NOT NULL,
  `DI_DESCRIPTION` varchar(256) NOT NULL,
  `DI_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`DI_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `disease_info`
--

INSERT INTO `disease_info` (`DI_ID`, `DI_NAME`, `DI_DESCRIPTION`, `DI_DATE`) VALUES
(12, 'fever', 'fever description fe', '2014-03-02 17:15:48'),
(13, 'cough and cold', 'cough and cold description', '2014-03-02 17:22:26'),
(14, 'jondis', 'jondis description', '2014-03-02 18:16:09');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `L_ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `L_USERNAME` varchar(128) DEFAULT NULL,
  `L_PASSWORD` varchar(128) DEFAULT NULL,
  `L_ROLL` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`L_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`L_ID`, `L_USERNAME`, `L_PASSWORD`, `L_ROLL`) VALUES
(5, 'nakul', 'nakul', 'Doctor'),
(6, 'dhruv', 'dhruv', 'Doctor'),
(7, 'dhairya', 'dhairya', 'Patient');

-- --------------------------------------------------------

--
-- Table structure for table `medicine_info`
--

CREATE TABLE IF NOT EXISTS `medicine_info` (
  `MI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MI_L_PATIENT_ID` int(11) NOT NULL,
  `MI_L_DOCTOR_ID` int(11) NOT NULL,
  `MI_MEDICINE_TYPE` varchar(30) NOT NULL,
  `MI_MEDICINE_NAME` varchar(256) NOT NULL,
  `MI_MEDICINE_QUANTITY` int(11) NOT NULL,
  `MI_MEDICINE_DAYS` int(11) NOT NULL,
  `MI_MEDICINE_DOSE` varchar(256) NOT NULL,
  `MI_STATUS` int(11) NOT NULL COMMENT '0-active 1-deactive',
  `MI_DI_ID` int(11) NOT NULL,
  `MI_DATE` date NOT NULL,
  PRIMARY KEY (`MI_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `medicine_info`
--

INSERT INTO `medicine_info` (`MI_ID`, `MI_L_PATIENT_ID`, `MI_L_DOCTOR_ID`, `MI_MEDICINE_TYPE`, `MI_MEDICINE_NAME`, `MI_MEDICINE_QUANTITY`, `MI_MEDICINE_DAYS`, `MI_MEDICINE_DOSE`, `MI_STATUS`, `MI_DI_ID`, `MI_DATE`) VALUES
(3, 7, 5, 'Tablet', 'Combiflame', 10, 5, 'Morning-Night', 0, 12, '0000-00-00'),
(4, 7, 5, 'Capsule', 'Pentop-DSR', 5, 5, 'Morning-', 0, 12, '0000-00-00'),
(5, 7, 6, 'Tablet', 'Tus-Q', 6, 6, 'Morning-', 0, 13, '0000-00-00'),
(6, 7, 6, 'Capsule', 'Pentop-DSR', 12, 12, 'Afternoon-Evening-', 0, 13, '0000-00-00'),
(7, 7, 6, 'Tablet', 'Nice', 6, 6, 'Night', 0, 13, '0000-00-00'),
(8, 7, 5, 'Tablet', 'Pentop-DSR', 30, 15, 'Morning-Night', 0, 14, '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `user_key`
--

CREATE TABLE IF NOT EXISTS `user_key` (
  `UK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UK_L_ID` int(20) unsigned NOT NULL,
  `UK_STATUS` int(10) unsigned DEFAULT NULL COMMENT '0-active 1-deactive',
  `UK_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UK_KEY` double DEFAULT NULL,
  PRIMARY KEY (`UK_ID`),
  KEY `UK_L_ID` (`UK_L_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user_key`
--

INSERT INTO `user_key` (`UK_ID`, `UK_L_ID`, `UK_STATUS`, `UK_TIMESTAMP`, `UK_KEY`) VALUES
(5, 7, 0, '2014-03-02 17:12:51', 607647);

-- --------------------------------------------------------

--
-- Table structure for table `user_reg_info`
--

CREATE TABLE IF NOT EXISTS `user_reg_info` (
  `URI_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `URI_L_ID` int(10) unsigned NOT NULL,
  `URI_NAME` varchar(128) DEFAULT NULL,
  `URI_ADDRESS` varchar(256) DEFAULT NULL,
  `URI_AGE` int(10) unsigned DEFAULT NULL,
  `URI_GENDER` varchar(10) DEFAULT NULL,
  `URI_CITY` varchar(128) DEFAULT NULL,
  `URI_REG_NO` varchar(256) DEFAULT NULL,
  `URI_MOB_NO` double unsigned DEFAULT NULL,
  `URI_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`URI_ID`),
  KEY `URI_L_ID` (`URI_L_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user_reg_info`
--

INSERT INTO `user_reg_info` (`URI_ID`, `URI_L_ID`, `URI_NAME`, `URI_ADDRESS`, `URI_AGE`, `URI_GENDER`, `URI_CITY`, `URI_REG_NO`, `URI_MOB_NO`, `URI_DATE`) VALUES
(4, 5, 'nakul maheshri', 'nasik ves', 3, 'Male', 'Nashik', '1234', 9850542309, '2014-03-02 17:04:07'),
(5, 6, 'dhruv maheshwri', 'sinnar', 4, 'Male', 'Nashik', 'asd123', 9999999, '2014-03-02 17:08:39'),
(6, 7, 'dhairya maheshwari', 'nasik', 3, 'Male', 'Pune', '', 555555, '2014-03-02 17:09:53');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_key`
--
ALTER TABLE `user_key`
  ADD CONSTRAINT `UK_L_ID` FOREIGN KEY (`UK_L_ID`) REFERENCES `login` (`L_ID`);

--
-- Constraints for table `user_reg_info`
--
ALTER TABLE `user_reg_info`
  ADD CONSTRAINT `URI_L_ID` FOREIGN KEY (`URI_L_ID`) REFERENCES `login` (`L_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
