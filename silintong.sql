-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 12, 2013 at 02:56 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `silintong`
--
CREATE DATABASE `silintong` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `silintong`;
-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `idanswer` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `idusername` int(11) NOT NULL,
  `idquestion` int(11) NOT NULL,
  `isapproved` tinyint(1) NOT NULL,
  `dateposted` date NOT NULL,
  `filename` varchar(20) NOT NULL,
  PRIMARY KEY (`idanswer`),
  KEY `idquestion` (`idquestion`),
  KEY `idusername` (`idusername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `idcategory` int(11) NOT NULL AUTO_INCREMENT,
  `namecategory` varchar(20) NOT NULL,
  PRIMARY KEY (`idcategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `idquestion` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `idusername` int(11) NOT NULL,
  `isanswered` tinyint(1) NOT NULL,
  `dateposted` date NOT NULL,
  `duedate` date NOT NULL,
  `pointgiven` int(11) NOT NULL,
  `idcategory` int(11) NOT NULL,
  `filename` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idquestion`),
  KEY `idusername` (`idusername`),
  KEY `idcategory` (`idcategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `idrating` int(11) NOT NULL AUTO_INCREMENT,
  `idvoter` int(11) NOT NULL,
  `idanswer` int(11) NOT NULL,
  `rate` int(11) NOT NULL,
  PRIMARY KEY (`idrating`),
  KEY `idanswer` (`idanswer`),
  KEY `idvoter` (`idvoter`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `firstname` varchar(10) NOT NULL,
  `lastname` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `poin` int(11) NOT NULL,
  `fotouser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`idquestion`) REFERENCES `question` (`idquestion`),
  ADD CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`idusername`) REFERENCES `user` (`iduser`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_2` FOREIGN KEY (`idcategory`) REFERENCES `category` (`idcategory`),
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`idusername`) REFERENCES `user` (`iduser`);

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`idanswer`) REFERENCES `answer` (`idanswer`),
  ADD CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`idvoter`) REFERENCES `user` (`iduser`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
