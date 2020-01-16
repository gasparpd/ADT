-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 16-01-2020 a las 12:43:12
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



--
-- Truncar tablas antes de insertar `smartphone`
--
TRUNCATE TABLE `smartphone`;
--
-- Truncar tablas antes de insertar `fabricante`
--
TRUNCATE TABLE `fabricante`;
--
-- Volcado de datos para la tabla `fabricante`
--

INSERT DELAYED INTO `fabricante` (`ID`, `NOMBRE`, `FUNDACION_YEAR`, `MATRIZ`) VALUES
(1, 'APPLE', '1976', NULL),
(2, 'SAMSUNG', '1938', NULL),
(3, 'OPPO', '2004', NULL),
(4, 'HUAWEI', '1987', NULL),
(5, 'REALME', '2018', 3),
(6, 'HONOR', '2013', 4),
(7, 'XIAOMI', '2010', NULL);

--
-- Volcado de datos para la tabla `smartphone`
--

INSERT DELAYED INTO `smartphone` (`ID_SMARTPHONE`, `ID_MARCA`, `MODELO`, `PULGADAS_PANTALLA`, `PRECIO`) VALUES
(1, 1, 'iPhone X', '5.8', '1159€'),
(2, 2, 'GALAXY S10', '6.1', '909€'),
(3, 3, 'RENO 2', '6.5', '499€'),
(4, 4, 'MATE 30 PRO', '6.53', '1099€'),
(5, 5, 'X2 PRO', '6.5', '399€'),
(6, 6, '20 PRO', '6.26', '599€'),
(7, 7, 'MI NOTE 10', '6.47', '549€'),
(8, 7, 'MI 9', '6.39', '449€');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
