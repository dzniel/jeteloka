-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2022 at 05:24 AM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jeteloka`
--

-- --------------------------------------------------------

--
-- Table structure for table `apartment`
--

CREATE TABLE `apartment` (
  `buildingID` char(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `floorNumber` int(11) NOT NULL,
  `location` varchar(255) NOT NULL,
  `area` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `apartment`
--

INSERT INTO `apartment` (`buildingID`, `name`, `floorNumber`, `location`, `area`) VALUES
('AP001', 'District 8', 21, 'Senopati', 150),
('AP002', 'Kempinski Grand Indonesia', 32, 'Menteng', 600),
('AP003', 'Pasific Place', 25, 'Senayan', 270),
('AP004', 'Pondok Indah Residence', 19, 'Pondok Pinang', 500),
('AP005', 'Taman Anggrek Residence', 22, 'Tomang', 375);

-- --------------------------------------------------------

--
-- Table structure for table `guesthouse`
--

CREATE TABLE `guesthouse` (
  `buildingID` char(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `ownerName` varchar(255) NOT NULL,
  `garage` varchar(255) NOT NULL,
  `area` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guesthouse`
--

INSERT INTO `guesthouse` (`buildingID`, `name`, `location`, `ownerName`, `garage`, `area`) VALUES
('GH001', 'TinTin', 'Tanah Sereal', 'TinTin Winata', '1', 875),
('GH002', 'MyHome', 'Tomang', 'Vito Caris', '0', 510),
('GH003', 'Hermes', 'Kijang', 'Jason Jayadi', '0', 760),
('GH004', 'JeTXel', 'Kemanggisan', 'Justine Winata', '1', 1200),
('GH005', 'Crystall', 'Syahdan', 'Eiris Eigner', '0', 800);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionID` char(5) NOT NULL,
  `rentTime` int(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userEmail` varchar(255) NOT NULL,
  `userPhone` varchar(255) NOT NULL,
  `buildingID` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apartment`
--
ALTER TABLE `apartment`
  ADD PRIMARY KEY (`buildingID`);

--
-- Indexes for table `guesthouse`
--
ALTER TABLE `guesthouse`
  ADD PRIMARY KEY (`buildingID`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
