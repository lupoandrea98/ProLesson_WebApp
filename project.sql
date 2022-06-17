-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 16, 2022 alle 11:32
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `corso`
--

CREATE TABLE `corso` (
  `id` int(11) NOT NULL,
  `titolo` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `corso`
--

INSERT INTO `corso` (`id`, `titolo`) VALUES
(1, 'algoritmi'),
(13, 'Diritto'),
(10, 'Formula'),
(3, 'informatica'),
(2, 'matematica'),
(6, 'psicologia sociale'),
(4, 'sicurezza');

-- --------------------------------------------------------

--
-- Struttura della tabella `docente`
--

CREATE TABLE `docente` (
  `id` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `docente`
--

INSERT INTO `docente` (`id`, `nome`, `cognome`) VALUES
(1, 'Fabio', 'Di Bitonto'),
(2, 'Lorenzo ', 'Cannavò'),
(3, 'Francesco', 'Bozzardini'),
(9, 'Gaia', 'Panichi'),
(10, 'Chiara', 'Trotta'),
(20, 'Valentina', 'Capolupo');

-- --------------------------------------------------------

--
-- Struttura della tabella `insegnamento`
--

CREATE TABLE `insegnamento` (
  `corso` int(11) NOT NULL,
  `docente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `insegnamento`
--

INSERT INTO `insegnamento` (`corso`, `docente`) VALUES
(1, 1),
(2, 2),
(3, 3),
(13, 20);

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazioni`
--

CREATE TABLE `prenotazioni` (
  `id` int(11) NOT NULL,
  `corso` varchar(40) NOT NULL,
  `docente` varchar(40) NOT NULL,
  `utente` int(11) NOT NULL,
  `stato` enum('attiva','effettuata','disdetta','') NOT NULL DEFAULT 'attiva',
  `giorno` enum('Lun','Mar','Mer','Gio','Ven') NOT NULL,
  `orario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `prenotazioni`
--

INSERT INTO `prenotazioni` (`id`, `corso`, `docente`, `utente`, `stato`, `giorno`, `orario`) VALUES
(30, 'matematica', 'Cannavò', 10, 'disdetta', 'Lun', 15),
(31, 'informatica', 'DiBitonto', 10, 'effettuata', 'Lun', 15),
(32, 'sicurezza', 'Bozzardini', 10, 'disdetta', 'Lun', 15),
(33, 'psicologia sociale', 'Trotta', 10, 'attiva', 'Lun', 18),
(34, 'sviluppo', 'Panichi', 10, 'effettuata', 'Lun', 18),
(35, 'matematica', 'Cannavò', 10, 'attiva', 'Gio', 18),
(36, 'sviluppo', 'Panichi', 10, 'disdetta', 'Mar', 15),
(37, 'sicurezza', 'Bozzardini', 10, 'attiva', 'Gio', 16);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id`, `username`, `password`, `isAdmin`) VALUES
(3, 'giacomo@agg.com', 'rog1', 1),
(10, 'andrea', 'asd', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `corso`
--
ALTER TABLE `corso`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `titolo_corso` (`titolo`);

--
-- Indici per le tabelle `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `insegnamento`
--
ALTER TABLE `insegnamento`
  ADD PRIMARY KEY (`corso`,`docente`),
  ADD KEY `docenti` (`docente`);

--
-- Indici per le tabelle `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD PRIMARY KEY (`id`),
  ADD KEY `prenotazioni-utente` (`utente`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Username` (`username`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `corso`
--
ALTER TABLE `corso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `docente`
--
ALTER TABLE `docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT per la tabella `prenotazioni`
--
ALTER TABLE `prenotazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `insegnamento`
--
ALTER TABLE `insegnamento`
  ADD CONSTRAINT `corsi` FOREIGN KEY (`corso`) REFERENCES `corso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `docenti` FOREIGN KEY (`docente`) REFERENCES `docente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD CONSTRAINT `prenotazioni-utente` FOREIGN KEY (`utente`) REFERENCES `utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
