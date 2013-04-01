-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 01 apr 2013 om 18:26
-- Serverversie: 5.5.25
-- PHP-versie: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `T1B1`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `activeevent`
--

CREATE TABLE `activeevent` (
  `eventId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `activephase`
--

CREATE TABLE `activephase` (
  `phaseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `activeround`
--

CREATE TABLE `activeround` (
  `roundId` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `boardmode`
--

CREATE TABLE `boardmode` (
  `boardMode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `climbers`
--

CREATE TABLE `climbers` (
  `climberId` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(20) COLLATE utf8_bin NOT NULL,
  `nationality` varchar(3) COLLATE utf8_bin NOT NULL,
  `sex` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0',
  PRIMARY KEY (`climberId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=75 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `eventenrollment`
--

CREATE TABLE `eventenrollment` (
  `eventId` int(11) NOT NULL,
  `climberId` int(11) NOT NULL,
  `startNumber` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`climberId`,`eventId`),
  UNIQUE KEY `startNumber` (`startNumber`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=77 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `events`
--

CREATE TABLE `events` (
  `eventId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `date` varchar(20) COLLATE utf8_bin NOT NULL,
  `place` varchar(20) COLLATE utf8_bin NOT NULL,
  `location` varchar(20) COLLATE utf8_bin NOT NULL,
  `international` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N',
  PRIMARY KEY (`eventId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `globals`
--

CREATE TABLE `globals` (
  `name` varchar(25) COLLATE utf8_bin NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `phasedefinition`
--

CREATE TABLE `phasedefinition` (
  `eventId` int(11) NOT NULL,
  `phaseId` int(11) NOT NULL,
  `roundId` int(11) NOT NULL,
  `sequence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `phases`
--

CREATE TABLE `phases` (
  `phaseId` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rounddefinition`
--

CREATE TABLE `rounddefinition` (
  `eventId` int(11) NOT NULL,
  `roundId` int(11) NOT NULL,
  `nrOfBoulders` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `roundenrollment`
--

CREATE TABLE `roundenrollment` (
  `eventId` int(11) NOT NULL,
  `roundId` int(11) NOT NULL,
  `startNumber` int(11) NOT NULL,
  `polePosition` int(11) NOT NULL,
  PRIMARY KEY (`eventId`,`roundId`,`startNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rounds`
--

CREATE TABLE `rounds` (
  `roundId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`roundId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `scores`
--

CREATE TABLE `scores` (
  `eventId` int(11) NOT NULL DEFAULT '0',
  `roundId` int(11) NOT NULL DEFAULT '0',
  `boulderNumber` int(11) NOT NULL DEFAULT '0',
  `startNumber` int(11) NOT NULL DEFAULT '0',
  `finished` int(11) NOT NULL DEFAULT '1',
  `topped` int(11) DEFAULT NULL,
  `topAttempts` int(11) DEFAULT NULL,
  `bonussed` int(11) DEFAULT NULL,
  `bonusAttempts` int(11) DEFAULT NULL,
  `attempts` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`eventId`,`roundId`,`boulderNumber`,`startNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
