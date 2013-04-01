-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 01 apr 2013 om 18:28
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

--
-- Gegevens worden uitgevoerd voor tabel `activeevent`
--

INSERT INTO `activeevent` (`eventId`) VALUES
(5);

--
-- Gegevens worden uitgevoerd voor tabel `activeround`
--

INSERT INTO `activeround` (`roundId`) VALUES
(4);

--
-- Gegevens worden uitgevoerd voor tabel `boardmode`
--

INSERT INTO `boardmode` (`boardMode`) VALUES
(3);

--
-- Gegevens worden uitgevoerd voor tabel `climbers`
--

INSERT INTO `climbers` (`climberId`, `firstname`, `lastname`, `nationality`, `sex`) VALUES
(3, 'Martina', 'Mattersdorfer', 'NED', 'F'),
(4, 'Tabitha', 'Buma', 'NED', 'F'),
(5, 'Anna', 'Scott Miller', 'NED', 'F'),
(6, 'Marianne', 'Verhaege', 'NED', 'F'),
(7, 'Aukje', 'van Weert', 'NED', 'F'),
(8, 'Kim', 'van den Hout', 'NED', 'F'),
(9, 'Rianne', 'Liebregts', 'NED', 'F'),
(10, 'Rachel', 'Nilwik', 'NED', 'F'),
(12, 'Roelien', 'van de Vrie', 'NED', 'F'),
(13, 'Irene', 'Pieper', 'NED', 'F'),
(14, 'Vera', 'Zijlstra', 'NED', 'F'),
(28, 'Hans', 'Busker', 'NED', 'M'),
(29, 'Jaap', 'Derks', 'NED', 'M'),
(30, 'Remmelt', 'Dirksen', 'NED', 'M'),
(31, 'Teun', 'Keusters', 'NED', 'M'),
(32, 'Riemer', 'Koorn', 'NED', 'M'),
(33, 'Kees', 'Mak', 'NED', 'M'),
(34, 'Ruben', 'Meibergen', 'NED', 'M'),
(35, 'Pim', 'Momberg', 'NED', 'M'),
(36, 'Tim', 'Reuser', 'NED', 'M'),
(37, 'Johan', 'Rodenhuis', 'NED', 'M'),
(38, 'Jasper', 'van der Sande', 'NED', 'M'),
(39, 'Elko', 'Schellingerhout', 'NED', 'M'),
(40, 'Rens', 'Schop', 'NED', 'M'),
(41, 'Bob', 'Schubert', 'NED', 'M'),
(42, 'Bas', 'Sijtsma', 'NED', 'M'),
(43, 'Timo', 'Tak', 'NED', 'M'),
(44, 'Stefan', 'Warsink', 'NED', 'M'),
(45, 'Wolter', 'Westdijk', 'NED', 'M'),
(46, 'Jeroen', 'van Zeeland', 'NED', 'M'),
(47, 'Rianne', 'van den Berg', 'NED', 'F'),
(48, 'Nikki', 'van Bergen', 'NED', 'F'),
(49, 'Magali', 'Hayen', 'BEL', 'F'),
(50, 'Laura', 'Huijbregts', 'NED', 'F'),
(51, 'Bouchra', 'Hemla', 'NED', 'F'),
(52, 'Anna', 'Kruis-Sitnyakowsky', 'NED', 'F'),
(53, 'Mirthe', 'van Liere', 'NED', 'F'),
(54, 'Aniek', 'Lith', 'NED', 'F'),
(55, 'Irene', 'Pieper', 'NED', 'F'),
(56, 'Marta', 'Shiryeva', 'RUS', 'F'),
(57, 'Eva', 'Vink', 'NED', 'F'),
(58, 'Nora', 'van Wassenaer', 'NED', 'F'),
(59, 'Robin', 'van Dijk', 'NED', 'M'),
(60, 'Stijn', 'Janssen', 'NED', 'M'),
(61, 'Piotr', 'Kmin', 'POL', 'M'),
(62, 'Alexander', 'Ledovskikh', 'UKR', 'M'),
(63, 'Kees', 'Mak', 'NED', 'M'),
(64, 'Ruben', 'Meibergen', 'NED', 'M'),
(65, 'Enrique', 'Perdok', 'NED', 'M'),
(66, 'Janneman', 'de Roon', 'NED', 'M'),
(67, 'Ferdinand', 'Schulte', 'NED', 'M'),
(68, 'Dennis', 'Teijsse', 'NED', 'M'),
(69, 'Koen', 'Vromans', 'NED', 'M'),
(70, 'Giel', 'Wieggers', 'NED', 'M'),
(71, 'Jascha', 'van de Bunt', 'NED', 'F'),
(72, 'Patricia', 'ter Heegde', 'NED', 'F'),
(73, 'Elisabeth', 'Keijzer', 'NED', 'F'),
(74, 'Maartje', 'van der Weijden', 'NED', 'F');

--
-- Gegevens worden uitgevoerd voor tabel `eventenrollment`
--

INSERT INTO `eventenrollment` (`eventId`, `climberId`, `startNumber`) VALUES
(5, 47, 1),
(5, 48, 2),
(5, 71, 3),
(5, 49, 4),
(5, 72, 5),
(5, 8, 6),
(5, 51, 7),
(5, 73, 8),
(5, 52, 9),
(5, 53, 10),
(5, 54, 11),
(5, 56, 14),
(5, 58, 15),
(5, 74, 16),
(5, 14, 17),
(5, 28, 51),
(5, 59, 52),
(5, 30, 53),
(5, 60, 54),
(5, 31, 55),
(5, 61, 56),
(5, 32, 57),
(5, 62, 58),
(5, 63, 59),
(5, 64, 60),
(5, 35, 61),
(5, 65, 62),
(5, 36, 63),
(5, 66, 64),
(5, 38, 65),
(5, 39, 66),
(5, 40, 67),
(5, 41, 68),
(5, 67, 69),
(5, 43, 70),
(5, 68, 71),
(5, 69, 72),
(5, 44, 73),
(5, 45, 74),
(5, 70, 75),
(5, 46, 76);

--
-- Gegevens worden uitgevoerd voor tabel `events`
--

INSERT INTO `events` (`eventId`, `name`, `date`, `place`, `location`, `international`) VALUES
(5, 'Boulder 1', '30-3-2013', 'Delft', 'Delfts Bleau', '0');

--
-- Gegevens worden uitgevoerd voor tabel `globals`
--

INSERT INTO `globals` (`name`, `value`) VALUES
('activeevent', 5),
('activeround', 4);

--
-- Gegevens worden uitgevoerd voor tabel `phases`
--

INSERT INTO `phases` (`phaseId`, `name`) VALUES
(1, 'Halve Finale'),
(2, 'Finale');

--
-- Gegevens worden uitgevoerd voor tabel `roundenrollment`
--

INSERT INTO `roundenrollment` (`eventId`, `roundId`, `startNumber`, `polePosition`) VALUES
(5, 3, 1, 3),
(5, 3, 2, 5),
(5, 3, 4, 4),
(5, 3, 7, 2),
(5, 3, 15, 1),
(5, 3, 17, 6),
(5, 4, 53, 4),
(5, 4, 55, 2),
(5, 4, 63, 1),
(5, 4, 66, 5),
(5, 4, 69, 3),
(5, 4, 70, 6);

--
-- Gegevens worden uitgevoerd voor tabel `rounds`
--

INSERT INTO `rounds` (`roundId`, `name`) VALUES
(3, 'Finale Dames'),
(4, 'Heren Finale');

--
-- Gegevens worden uitgevoerd voor tabel `scores`
--

INSERT INTO `scores` (`eventId`, `roundId`, `boulderNumber`, `startNumber`, `finished`, `topped`, `topAttempts`, `bonussed`, `bonusAttempts`, `attempts`) VALUES
(5, 3, 1, 1, 1, 1, 1, 1, 1, 1),
(5, 3, 1, 2, 1, 1, 1, 1, 1, 1),
(5, 3, 1, 4, 1, 1, 1, 1, 1, 1),
(5, 3, 1, 7, 1, 0, 0, 0, 0, 4),
(5, 3, 1, 15, 1, 0, 0, 1, 3, 3),
(5, 3, 1, 17, 1, 1, 1, 1, 1, 1),
(5, 3, 2, 1, 1, 0, 0, 0, 0, 5),
(5, 3, 2, 2, 1, 1, 2, 1, 2, 2),
(5, 3, 2, 4, 1, 0, 0, 1, 3, 4),
(5, 3, 2, 7, 1, 0, 0, 1, 2, 4),
(5, 3, 2, 15, 1, 0, 0, 1, 1, 2),
(5, 3, 2, 17, 1, 1, 2, 1, 2, 2),
(5, 3, 3, 1, 1, 0, 0, 0, 0, 3),
(5, 3, 3, 2, 1, 0, 0, 1, 1, 4),
(5, 3, 3, 4, 1, 0, 0, 1, 1, 3),
(5, 3, 3, 7, 1, 0, 0, 0, 0, 8),
(5, 3, 3, 15, 1, 0, 0, 0, 0, 3),
(5, 3, 3, 17, 1, 1, 1, 1, 1, 1),
(5, 3, 4, 1, 1, 0, 0, 1, 2, 4),
(5, 3, 4, 2, 1, 1, 1, 1, 1, 1),
(5, 3, 4, 4, 1, 0, 0, 1, 2, 5),
(5, 3, 4, 7, 1, 0, 0, 0, 0, 6),
(5, 3, 4, 15, 1, 0, 0, 0, 0, 4),
(5, 3, 4, 17, 1, 1, 2, 1, 2, 2),
(5, 4, 1, 53, 1, 0, 0, 1, 2, 4),
(5, 4, 1, 55, 1, 1, 3, 1, 1, 3),
(5, 4, 1, 63, 0, 0, 0, 0, 0, 0),
(5, 4, 1, 66, 1, 0, 0, 1, 1, 2),
(5, 4, 1, 69, 1, 1, 1, 1, 1, 1),
(5, 4, 1, 70, 1, 0, 0, 1, 1, 5),
(5, 4, 2, 53, 1, 1, 1, 1, 1, 1),
(5, 4, 2, 55, 1, 0, 0, 1, 7, 9),
(5, 4, 2, 63, 1, 0, 0, 1, 2, 9),
(5, 4, 2, 66, 1, 0, 0, 1, 2, 4),
(5, 4, 2, 69, 1, 1, 2, 1, 2, 2),
(5, 4, 2, 70, 1, 0, 0, 1, 7, 8),
(5, 4, 3, 53, 1, 0, 0, 0, 0, 5),
(5, 4, 3, 55, 1, 0, 0, 0, 0, 7),
(5, 4, 3, 63, 1, 0, 0, 0, 0, 7),
(5, 4, 3, 66, 1, 0, 0, 0, 0, 4),
(5, 4, 3, 69, 1, 0, 0, 0, 0, 5),
(5, 4, 3, 70, 1, 0, 0, 0, 0, 7),
(5, 4, 4, 53, 1, 0, 0, 1, 1, 2),
(5, 4, 4, 55, 1, 0, 0, 1, 1, 4),
(5, 4, 4, 63, 1, 0, 0, 1, 1, 3),
(5, 4, 4, 66, 1, 0, 0, 1, 1, 4),
(5, 4, 4, 69, 1, 1, 1, 1, 1, 1),
(5, 4, 4, 70, 1, 0, 0, 1, 1, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
