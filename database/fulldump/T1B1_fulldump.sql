-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 04 mrt 2013 om 12:00
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

--
-- Gegevens worden uitgevoerd voor tabel `activeevent`
--

INSERT INTO `activeevent` (`eventId`) VALUES
(3);

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

--
-- Gegevens worden uitgevoerd voor tabel `activeround`
--

INSERT INTO `activeround` (`roundId`) VALUES
(4);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=28 ;

--
-- Gegevens worden uitgevoerd voor tabel `climbers`
--

INSERT INTO `climbers` (`climberId`, `firstname`, `lastname`, `nationality`, `sex`) VALUES
(3, 'Martina', 'Mattersdorfer', 'NED', 'F'),
(4, 'Tabitha', 'Buma', 'RUS', 'F'),
(5, 'Anna', 'Scott Miller', 'NED', 'F'),
(6, 'Marianne', 'Verhaege', 'NED', 'F'),
(7, 'Aukje', 'van Weert', 'NED', 'F'),
(8, 'Kim', 'van den Hout', 'OAS', 'F'),
(9, 'Rianne', 'Liebregts', 'NED', 'F'),
(10, 'Rachel', 'Nilwik', 'RUS', 'F'),
(12, 'Roelien', 'van de Vrie', 'NED', 'F'),
(13, 'Irene', 'Pieper', 'NED', 'F'),
(14, 'Vera', 'Zijlstra', 'NED', 'F'),
(15, 'Ben', 'de Sigaar', 'NED', 'M'),
(16, 'Joop', 'Sloop', 'NED', 'M'),
(17, 'Toon', 'Initiatief', 'NED', 'M'),
(18, 'Rustam', 'GELMANOV', 'RUS', 'M'),
(19, 'Kilian', 'FISCHHUBER', 'AUT', 'M'),
(20, 'Dmitrii', 'SHARAFUTDINOV', 'RUS', 'M'),
(21, 'Sean', 'MCCOLL', 'CAN', 'M'),
(22, 'Guillaume', 'GLAIRON MONDET', 'FRA', 'M'),
(23, 'Jakob', 'SCHUBERT', 'AUT', 'M'),
(24, 'Rei', 'SUGIMOTO', 'JPN', 'M'),
(25, 'Thomas', 'CALEYRON', 'FRA', 'M'),
(26, 'Jonas', 'BAUMANN', 'GER', 'M'),
(27, 'Jernej', 'KRUDER', 'SVN', 'M');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=38 ;

--
-- Gegevens worden uitgevoerd voor tabel `eventenrollment`
--

INSERT INTO `eventenrollment` (`eventId`, `climberId`, `startNumber`) VALUES
(5, 4, 10),
(5, 3, 11),
(5, 5, 12),
(5, 6, 13),
(5, 7, 14),
(5, 8, 15),
(5, 9, 16),
(5, 10, 17),
(5, 15, 20),
(5, 16, 21),
(5, 17, 22),
(3, 19, 30),
(3, 20, 31),
(3, 18, 32),
(3, 21, 33),
(3, 22, 34),
(3, 23, 35),
(3, 25, 36),
(3, 27, 37);

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

--
-- Gegevens worden uitgevoerd voor tabel `events`
--

INSERT INTO `events` (`eventId`, `name`, `date`, `place`, `location`, `international`) VALUES
(0, 'No event active', '', '', '', 'N'),
(1, 'NK Boulder', '2012', 'Rotterdam', 'City Center', '1'),
(3, 'WK boulder', '01-01-2000', 'Ehv', 'Monk', '1'),
(4, 'Acht Open', '26-1 -2013', 'Eindhoven', 'Kerkplein', '0'),
(5, 'Boulder 1', '30-3-2013', 'Delft', 'Delfts Bleau', '0');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `globals`
--

CREATE TABLE `globals` (
  `name` varchar(25) COLLATE utf8_bin NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Gegevens worden uitgevoerd voor tabel `globals`
--

INSERT INTO `globals` (`name`, `value`) VALUES
('activeevent', 1),
('activeround', 1);

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

--
-- Gegevens worden uitgevoerd voor tabel `phases`
--

INSERT INTO `phases` (`phaseId`, `name`) VALUES
(1, 'Halve Finale'),
(2, 'Finale');

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

--
-- Gegevens worden uitgevoerd voor tabel `roundenrollment`
--

INSERT INTO `roundenrollment` (`eventId`, `roundId`, `startNumber`, `polePosition`) VALUES
(3, 4, 30, 1),
(3, 4, 31, 2),
(3, 4, 32, 3),
(3, 4, 33, 4),
(3, 4, 34, 6),
(3, 4, 35, 5),
(3, 4, 36, 7),
(3, 4, 37, 8),
(5, 1, 10, 8),
(5, 1, 11, 7),
(5, 1, 12, 6),
(5, 1, 13, 5),
(5, 1, 14, 4),
(5, 1, 15, 3),
(5, 1, 16, 2),
(5, 1, 17, 1),
(5, 4, 20, 1),
(5, 4, 21, 3),
(5, 4, 22, 2);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rounds`
--

CREATE TABLE `rounds` (
  `roundId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`roundId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Gegevens worden uitgevoerd voor tabel `rounds`
--

INSERT INTO `rounds` (`roundId`, `name`) VALUES
(1, 'Halve Finale Dames'),
(2, 'Halve Finale Heren'),
(3, 'Finale Dames'),
(4, 'Heren Finale');

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

--
-- Gegevens worden uitgevoerd voor tabel `scores`
--

INSERT INTO `scores` (`eventId`, `roundId`, `boulderNumber`, `startNumber`, `finished`, `topped`, `topAttempts`, `bonussed`, `bonusAttempts`, `attempts`) VALUES
(1, 1, 1, 30, 1, 0, 0, 0, 0, 1),
(1, 1, 1, 31, 1, 0, 0, 1, 2, 2),
(1, 1, 1, 32, 1, 1, 1, 1, 1, 1),
(1, 1, 1, 33, 1, 1, 2, 1, 1, 2),
(1, 1, 1, 34, 1, 1, 1, 1, 1, 1),
(1, 1, 1, 35, 1, 1, 4, 1, 2, 4),
(1, 1, 1, 36, 1, 0, 0, 0, 0, 2),
(1, 1, 1, 37, 1, 0, 0, 1, 1, 1),
(1, 1, 2, 30, 1, 1, 6, 1, 6, 6),
(1, 1, 2, 31, 1, 1, 2, 1, 2, 2),
(1, 1, 2, 32, 1, 1, 1, 1, 1, 1),
(1, 1, 2, 33, 1, 1, 2, 1, 2, 2),
(1, 1, 2, 34, 1, 0, 0, 1, 1, 1),
(1, 1, 2, 35, 1, 0, 0, 1, 1, 1),
(1, 1, 2, 36, 1, 0, 0, 1, 3, 3),
(1, 1, 2, 37, 1, 0, 0, 1, 4, 4),
(1, 1, 3, 30, 1, 1, 1, 1, 1, 1),
(1, 1, 3, 31, 1, 1, 1, 1, 1, 1),
(1, 1, 3, 32, 1, 1, 2, 1, 2, 2),
(1, 1, 3, 33, 1, 0, 0, 0, 0, 0),
(1, 1, 3, 34, 1, 1, 2, 1, 1, 2),
(1, 1, 3, 35, 1, 1, 2, 1, 1, 2),
(1, 1, 3, 36, 1, 1, 1, 1, 1, 1),
(1, 1, 3, 37, 1, 1, 1, 1, 1, 1),
(1, 1, 4, 30, 1, 1, 1, 1, 1, 1),
(1, 1, 4, 31, 1, 1, 3, 1, 2, 3),
(1, 1, 4, 32, 1, 1, 2, 1, 1, 2),
(1, 1, 4, 33, 1, 1, 6, 1, 1, 6),
(1, 1, 4, 34, 1, 1, 2, 1, 2, 2),
(1, 1, 4, 35, 1, 1, 4, 1, 4, 4),
(1, 1, 4, 36, 1, 0, 0, 1, 1, 1),
(1, 1, 4, 37, 1, 0, 0, 1, 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
