-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 17-02-2020 a las 12:50:20
-- Versión del servidor: 5.7.11
-- Versión de PHP: 5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `smartphones`
--
CREATE DATABASE IF NOT EXISTS `smartphones` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `smartphones`;

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `aplicar_descuento` (`marca` INT, `descuento` INT)  BEGIN
    UPDATE smartphone SET PRECIO= PRECIO - descuento WHERE ID_MARCA = marca;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_comprob_marca` (`id_marca` INT, OUT `nom` VARCHAR(15), OUT `id_m` INT)  BEGIN
	SET id_m = 0;
	SET nom = 'INEXISTENTE';
	SELECT NOMBRE, ID INTO nom, id_m FROM fabricante WHERE ID = id_marca;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fabricante`
--

CREATE TABLE IF NOT EXISTS `fabricante` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(15) NOT NULL,
  `FUNDACION_YEAR` varchar(4) NOT NULL,
  `MATRIZ` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `fabricante`
--

TRUNCATE TABLE `fabricante`;
--
-- Volcado de datos para la tabla `fabricante`
--

INSERT INTO `fabricante` (`ID`, `NOMBRE`, `FUNDACION_YEAR`, `MATRIZ`) VALUES
(1, 'APPLE', '1976', 0),
(2, 'SAMSUNG', '1938', 0),
(3, 'OPPO', '2004', 0),
(4, 'HUAWEI', '1987', 0),
(5, 'REALME', '2018', 3),
(6, 'HONOR', '2013', 4),
(7, 'XIAOMI', '2010', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `smartphone`
--

CREATE TABLE IF NOT EXISTS `smartphone` (
  `ID_SMARTPHONE` int(2) PRIMARY KEY AUTO_INCREMENT,
  `ID_MARCA` int(2) NOT NULL,
  `MODELO` varchar(20) NOT NULL,
  `PULGADAS_PANTALLA` varchar(10) NOT NULL,
  `PRECIO` int(5) NOT NULL,
	FOREIGN KEY (ID_MARCA)
        REFERENCES fabricante (ID)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `smartphone`
--

TRUNCATE TABLE `smartphone`;
--
-- Volcado de datos para la tabla `smartphone`
--

INSERT INTO `smartphone` (`ID_SMARTPHONE`, `ID_MARCA`, `MODELO`, `PULGADAS_PANTALLA`, `PRECIO`) VALUES
(1, 1, 'iPhone X', '5.8', 1159),
(2, 2, 'GALAXY S10', '6.1', 909),
(3, 3, 'RENO 2', '6.5', 499),
(4, 4, 'MATE 30 PRO', '6.53', 1099),
(5, 5, 'X2 PRO', '6.5', 399),
(6, 6, '20 PRO', '6.26', 599),
(7, 7, 'MI NOTE 10', '6.47', 549),
(8, 7, 'MI 9', '6.39', 449);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `smartphone`
--
-- ALTER TABLE `smartphone`
--  ADD CONSTRAINT `smartphone_ibfk_1` FOREIGN KEY (`ID_MARCA`) REFERENCES `fabricante` (`ID`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
