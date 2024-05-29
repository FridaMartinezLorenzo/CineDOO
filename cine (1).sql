-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 29-05-2024 a las 21:03:16
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `IdCategoria` int NOT NULL AUTO_INCREMENT,
  `Nombre` text NOT NULL,
  PRIMARY KEY (`IdCategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`IdCategoria`, `Nombre`) VALUES
(1, 'Acción'),
(2, 'Comedia'),
(3, 'Drama'),
(4, 'Terror'),
(5, 'Ciencia Ficción'),
(6, 'Animación'),
(12, 'Test Categoria'),
(13, 'Nueva Categoria'),
(14, 'Test Categoria'),
(15, 'Test Categoria'),
(17, 'Test Categoria'),
(18, 'Nueva Categoria'),
(19, 'Test Categoria'),
(20, 'Test Categoria'),
(22, 'Test Categoria'),
(23, 'Nueva Categoria'),
(24, 'Test Categoria'),
(25, 'Test Categoria'),
(27, 'Test Categoria'),
(28, 'Nueva Categoria'),
(29, 'Test Categoria'),
(30, 'Test Categoria'),
(32, 'Test Categoria'),
(33, 'Nueva Categoria'),
(34, 'Test Categoria'),
(35, 'Test Categoria'),
(37, 'Test Categoria'),
(38, 'Nueva Categoria'),
(39, 'Test Categoria'),
(40, 'Test Categoria'),
(42, 'Test Categoria'),
(43, 'Nueva Categoria'),
(44, 'Test Categoria'),
(45, 'Test Categoria'),
(47, 'Test Categoria'),
(48, 'Nueva Categoria'),
(49, 'Test Categoria'),
(50, 'Test Categoria'),
(52, 'Test Categoria'),
(53, 'Nueva Categoria'),
(54, 'Test Categoria'),
(55, 'Test Categoria'),
(57, 'Test Categoria'),
(58, 'Nueva Categoria'),
(59, 'Test Categoria'),
(60, 'Test Categoria'),
(62, 'Test Categoria'),
(63, 'Nueva Categoria'),
(64, 'Test Categoria'),
(65, 'Test Categoria'),
(67, 'Test Categoria'),
(68, 'Nueva Categoria'),
(69, 'Test Categoria'),
(70, 'Test Categoria'),
(72, 'Test Categoria'),
(73, 'Nueva Categoria'),
(74, 'Test Categoria'),
(75, 'Test Categoria'),
(77, 'Test Categoria'),
(78, 'Nueva Categoria'),
(79, 'Test Categoria'),
(80, 'Test Categoria'),
(82, 'Test Categoria'),
(83, 'Nueva Categoria'),
(84, 'Test Categoria'),
(85, 'Test Categoria'),
(87, 'Test Categoria'),
(88, 'Nueva Categoria'),
(89, 'Test Categoria'),
(90, 'Test Categoria'),
(92, 'Test Categoria'),
(93, 'Nueva Categoria'),
(94, 'Test Categoria'),
(95, 'Test Categoria'),
(97, 'Test Categoria'),
(98, 'Nueva Categoria'),
(99, 'Test Categoria'),
(100, 'Test Categoria'),
(102, 'Test Categoria'),
(103, 'Nueva Categoria'),
(104, 'Test Categoria'),
(105, 'Test Categoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funciones`
--

DROP TABLE IF EXISTS `funciones`;
CREATE TABLE IF NOT EXISTS `funciones` (
  `IdFuncion` int NOT NULL AUTO_INCREMENT,
  `IdPelicula` int NOT NULL,
  `IdHorario` int NOT NULL,
  `IdSala` int NOT NULL,
  PRIMARY KEY (`IdFuncion`),
  KEY `IdPelicula` (`IdPelicula`),
  KEY `IdSala` (`IdSala`),
  KEY `IdHorario` (`IdHorario`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `funciones`
--

INSERT INTO `funciones` (`IdFuncion`, `IdPelicula`, `IdHorario`, `IdSala`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 3, 3, 2),
(4, 4, 4, 3),
(5, 5, 5, 4),
(6, 6, 6, 2),
(7, 1, 2, 3),
(8, 2, 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

DROP TABLE IF EXISTS `horarios`;
CREATE TABLE IF NOT EXISTS `horarios` (
  `IdHorario` int NOT NULL AUTO_INCREMENT,
  `HoraInicio` text NOT NULL,
  PRIMARY KEY (`IdHorario`)
) ENGINE=MyISAM AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`IdHorario`, `HoraInicio`) VALUES
(1, '10:00:00'),
(2, '12:30:00'),
(3, '15:00:00'),
(4, '17:30:00'),
(5, '20:00:00'),
(6, '22:30:00'),
(10, '10:00:00'),
(12, '10:00:00'),
(13, '10:00:00'),
(14, '12:00:00'),
(15, '10:00:00'),
(17, '10:00:00'),
(18, '10:00:00'),
(19, '12:00:00'),
(20, '10:00:00'),
(22, '10:00:00'),
(23, '10:00:00'),
(24, '12:00:00'),
(25, '10:00:00'),
(27, '10:00:00'),
(28, '10:00:00'),
(29, '12:00:00'),
(30, '10:00:00'),
(32, '10:00:00'),
(33, '10:00:00'),
(34, '12:00:00'),
(35, '10:00:00'),
(37, '10:00:00'),
(38, '10:00:00'),
(39, '12:00:00'),
(40, '10:00:00'),
(42, '10:00:00'),
(43, '10:00:00'),
(44, '12:00:00'),
(45, '10:00:00'),
(47, '10:00:00'),
(48, '10:00:00'),
(49, '12:00:00'),
(50, '10:00:00'),
(52, '10:00:00'),
(53, '10:00:00'),
(54, '12:00:00'),
(55, '10:00:00'),
(57, '10:00:00'),
(58, '10:00:00'),
(59, '12:00:00'),
(60, '10:00:00'),
(62, '10:00:00'),
(63, '10:00:00'),
(64, '12:00:00'),
(65, '10:00:00'),
(67, '10:00:00'),
(68, '10:00:00'),
(69, '12:00:00'),
(70, '10:00:00'),
(72, '10:00:00'),
(73, '10:00:00'),
(74, '12:00:00'),
(75, '10:00:00'),
(77, '10:00:00'),
(78, '10:00:00'),
(79, '12:00:00'),
(80, '10:00:00'),
(82, '10:00:00'),
(83, '10:00:00'),
(84, '12:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
CREATE TABLE IF NOT EXISTS `peliculas` (
  `IdPelicula` int NOT NULL AUTO_INCREMENT,
  `Titulo` text NOT NULL,
  `Sinopsis` text NOT NULL,
  `FechaEstreno` text NOT NULL,
  `Duracion` int NOT NULL,
  `IdCategoria` int NOT NULL,
  PRIMARY KEY (`IdPelicula`)
) ENGINE=MyISAM AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`IdPelicula`, `Titulo`, `Sinopsis`, `FechaEstreno`, `Duracion`, `IdCategoria`) VALUES
(2, 'Risas y Lagrimas', 'A thriller that bends the mind', '2024-05-15', 90, 2),
(3, 'Misterios del Pasado', 'Un detective investiga un antiguo misterio en una pequeña ciudad.', '2024-05-22', 110, 3),
(4, 'Noche de Miedo', 'Un grupo de jóvenes lucha por sobrevivir en una noche llena de terror.', '2024-05-29', 100, 4),
(5, 'El Futuro es Hoy', 'Una visión futurista donde la tecnología domina cada aspecto de la vida.', '2024-06-05', 130, 5),
(6, 'Aventuras Animadas', 'Un divertido viaje animado lleno de risas y enseñanzas.', '2024-06-12', 85, 6),
(1, 'La Gran Aventura', 'Un grupo de amigos emprende una épica travesía para salvar su hogar.', '2024-06-01', 120, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

DROP TABLE IF EXISTS `promociones`;
CREATE TABLE IF NOT EXISTS `promociones` (
  `IdPromocion` int NOT NULL AUTO_INCREMENT,
  `FechaInicio` text NOT NULL,
  `FechaFin` text NOT NULL,
  `Descuento` int NOT NULL,
  PRIMARY KEY (`IdPromocion`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`IdPromocion`, `FechaInicio`, `FechaFin`, `Descuento`) VALUES
(1, '2024-05-20', '2024-05-27', 20),
(2, '2024-06-01', '2024-06-07', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `IdRol` int NOT NULL AUTO_INCREMENT,
  `Nombre` text NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`IdRol`, `Nombre`) VALUES
(1, 'Administrador'),
(2, 'Vendedor'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

DROP TABLE IF EXISTS `salas`;
CREATE TABLE IF NOT EXISTS `salas` (
  `IdSala` int NOT NULL AUTO_INCREMENT,
  `NumAsiento` int NOT NULL,
  PRIMARY KEY (`IdSala`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`IdSala`, `NumAsiento`) VALUES
(1, 50),
(2, 75),
(3, 100),
(4, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_boleto`
--

DROP TABLE IF EXISTS `tipo_boleto`;
CREATE TABLE IF NOT EXISTS `tipo_boleto` (
  `IdTipoBoleto` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Precio` float NOT NULL,
  PRIMARY KEY (`IdTipoBoleto`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_boleto`
--

INSERT INTO `tipo_boleto` (`IdTipoBoleto`, `Nombre`, `Precio`) VALUES
(1, 'General', 60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `Nombre` text NOT NULL,
  `Correo` text NOT NULL,
  `Contrasena` text NOT NULL,
  `IdRol` int NOT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `idRol` (`IdRol`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `Nombre`, `Correo`, `Contrasena`, `IdRol`) VALUES
(1, 'Admin', 'admin@cine.com', 'admin123', 1),
(2, 'Juan Perez', 'juan.perez@correo.com', 'juan123', 2),
(3, 'Maria Garcia', 'maria.garcia@correo.com', 'maria123', 3),
(4, 'Carlos Lopez', 'carlos.lopez@correo.com', 'carlos123', 3),
(5, 'Ana Torres', 'ana.torres@correo.com', 'ana123', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `IdVenta` int NOT NULL AUTO_INCREMENT,
  `IdUsuario` int NOT NULL,
  `IdFuncion` int NOT NULL,
  `Total` float NOT NULL,
  `CantBoletos` int NOT NULL,
  PRIMARY KEY (`IdVenta`),
  KEY `IdUsuario` (`IdUsuario`),
  KEY `IdFuncion` (`IdFuncion`)
) ENGINE=MyISAM AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IdVenta`, `IdUsuario`, `IdFuncion`, `Total`, `CantBoletos`) VALUES
(1, 3, 1, 100, 1),
(2, 4, 2, 200, 2),
(3, 5, 3, 300, 3),
(4, 3, 4, 400, 4),
(5, 4, 5, 500, 5),
(6, 5, 6, 600, 6),
(7, 3, 7, 150, 1),
(8, 4, 8, 300, 2),
(9, 5, 8, 450, 3),
(10, 3, 6, 600, 4),
(19, 1, 1, 200, 2),
(20, 1, 1, 150, 2),
(21, 1, 1, 150, 2),
(22, 1, 1, 150, 2),
(29, 1, 1, 200, 2),
(30, 1, 1, 150, 2),
(31, 1, 1, 150, 2),
(32, 1, 1, 150, 2),
(34, 1, 1, 200, 2),
(35, 1, 1, 150, 2),
(36, 1, 1, 150, 2),
(37, 1, 1, 150, 2),
(39, 1, 1, 200, 2),
(40, 1, 1, 150, 2),
(41, 1, 1, 150, 2),
(42, 1, 1, 150, 2),
(44, 1, 1, 200, 2),
(45, 1, 1, 150, 2),
(46, 1, 1, 150, 2),
(47, 1, 1, 150, 2),
(49, 1, 1, 200, 2),
(50, 1, 1, 150, 2),
(51, 1, 1, 150, 2),
(52, 1, 1, 150, 2),
(54, 1, 1, 200, 2),
(55, 1, 1, 150, 2),
(56, 1, 1, 150, 2),
(57, 1, 1, 150, 2),
(59, 1, 1, 200, 2),
(60, 1, 1, 150, 2),
(61, 1, 1, 150, 2),
(62, 1, 1, 150, 2),
(64, 1, 1, 200, 2),
(65, 1, 1, 150, 2),
(66, 1, 1, 150, 2),
(67, 1, 1, 150, 2),
(69, 1, 1, 200, 2),
(70, 1, 1, 150, 2),
(71, 1, 1, 150, 2),
(72, 1, 1, 150, 2),
(74, 1, 1, 200, 2),
(75, 1, 1, 150, 2),
(76, 1, 1, 150, 2),
(77, 1, 1, 150, 2),
(79, 1, 1, 200, 2),
(80, 1, 1, 150, 2),
(81, 1, 1, 150, 2),
(82, 1, 1, 150, 2),
(84, 1, 1, 200, 2),
(85, 1, 1, 150, 2),
(86, 1, 1, 150, 2),
(87, 1, 1, 150, 2),
(89, 1, 1, 200, 2),
(90, 1, 1, 150, 2),
(91, 1, 1, 150, 2),
(92, 1, 1, 150, 2),
(94, 1, 1, 200, 2),
(95, 1, 1, 150, 2),
(96, 1, 1, 150, 2),
(97, 1, 1, 150, 2),
(99, 1, 1, 200, 2),
(100, 1, 1, 150, 2),
(101, 1, 1, 150, 2),
(102, 1, 1, 150, 2),
(104, 1, 1, 200, 2),
(105, 1, 1, 150, 2),
(106, 1, 1, 150, 2),
(107, 1, 1, 150, 2),
(109, 1, 1, 200, 2),
(110, 1, 1, 150, 2),
(111, 1, 1, 150, 2),
(112, 1, 1, 150, 2),
(114, 1, 1, 200, 2),
(115, 1, 1, 150, 2),
(116, 1, 1, 150, 2),
(117, 1, 1, 150, 2),
(119, 1, 1, 200, 2),
(120, 1, 1, 150, 2),
(121, 1, 1, 150, 2),
(122, 1, 1, 150, 2),
(124, 1, 1, 200, 2),
(125, 1, 1, 150, 2),
(126, 1, 1, 150, 2),
(127, 1, 1, 150, 2),
(129, 1, 1, 200, 2),
(130, 1, 1, 150, 2),
(131, 1, 1, 150, 2),
(132, 1, 1, 150, 2),
(134, 1, 1, 200, 2),
(135, 1, 1, 150, 2),
(136, 1, 1, 150, 2),
(137, 1, 1, 150, 2),
(139, 1, 1, 200, 2),
(140, 1, 1, 150, 2),
(141, 1, 1, 150, 2),
(142, 1, 1, 150, 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
