-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2023 at 11:34 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecovelos`
--

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `date_reclamation` date DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `id_util` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `nom`, `email`, `sujet`, `description`, `date_reclamation`, `etat`, `id_util`) VALUES
(1, 'John Doe', 'Problème avec un vélo', 'Le vélo numéro 123456 ne fonctionne pas.', 'john.doe@example.com', '2023-04-12', 'Nouvelle', 1),
(2, 'ehnb', 'kjhkj', 'hkjhkjh', 'khjb@gmail.com', '2023-04-08', 'kjhkj', 2),
(3, 'efafdf', 'rhd@gmail.com', 'thrhr', 'ekhdem', '2023-04-09', 'srhthrttrss', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `id_reclamation_id` int(11) NOT NULL,
  `date_reponse` date NOT NULL,
  `sujet_reponse` varchar(255) NOT NULL,
  `motif` varchar(255) NOT NULL,
  `avis` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `id_reclamation_id`, `date_reponse`, `sujet_reponse`, `motif`, `avis`) VALUES
(1, 0, '2023-04-10', 'trfgfgh', 'fhgdbgdgbgdb', 0),
(2, 0, '2023-04-10', 'rfgzgte', 'dfgdsgf', 0),
(3, 5, '2023-04-10', 'ytriijjhg', 'yujryrjjyr', 0),
(4, 6, '2023-04-10', 'aaaaaaaaaaa', 'kallamni', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nom_user` varchar(225) NOT NULL,
  `prenom_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nom_user`, `prenom_user`) VALUES
(1, 'benzarti', 'aziz'),
(2, 'khemakhem', 'youssef'),
(11, 'benzarti', 'aziz');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
