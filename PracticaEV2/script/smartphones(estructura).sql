-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 17-01-2020 a las 11:11:17
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
DROP TABLE IF EXISTS `smartphone`;
CREATE TABLE `smartphone` (
  `ID_SMARTPHONE` int(2) NOT NULL AUTO_INCREMENT,
  `ID_MARCA` int(2) NOT NULL,
  `MODELO` varchar(20) NOT NULL,
  `PULGADAS_PANTALLA` varchar(10) NOT NULL,
  `PRECIO` varchar(5) NOT NULL,
  PRIMARY KEY (`ID_SMARTPHONE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fabricante`
--
-- Desactivo el check de claves foráneas por problemas
-- con las mismas al hacer el DROP TABLE
-- SET foreign_key_checks = 0;
-- -------------------------------------------------------
DROP TABLE IF EXISTS `fabricante`;
CREATE TABLE `fabricante` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(15) NOT NULL,
  `FUNDACION_YEAR` varchar(4) NOT NULL,
  `MATRIZ` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `smartphone`
--



--
-- Índices para tablas volcadas
--
-- Reactivo el check de claves foráneas
-- SET foreign_key_checks = 1;
-- -------------------------------------------------------
--
-- Indices de la tabla `fabricante`
--
ALTER TABLE `fabricante`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `smartphone`
--
ALTER TABLE `smartphone`
  ADD PRIMARY KEY (`ID_SMARTPHONE`),
  ADD KEY `smartphone_ibfk_1` (`ID_MARCA`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `smartphone`
--
ALTER TABLE `smartphone`
  ADD CONSTRAINT `smartphone_ibfk_1` FOREIGN KEY (`ID_MARCA`) REFERENCES `fabricante` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
