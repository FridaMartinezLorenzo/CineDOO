-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 25-05-2024 a las 21:36:15
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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`IdCategoria`, `Nombre`) VALUES
(1, 'Acción'),
(2, 'Comedia'),
(3, 'Drama'),
(4, 'Terror'),
(5, 'Ciencia Ficción'),
(6, 'Animación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funciones`
--

DROP TABLE IF EXISTS `funciones`;
CREATE TABLE IF NOT EXISTS `funciones` (
  `idFuncion` int NOT NULL AUTO_INCREMENT,
  `IdPelicula` int NOT NULL,
  `IdHorario` int NOT NULL,
  `IdSala` int NOT NULL,
  PRIMARY KEY (`idFuncion`),
  KEY `IdPelicula` (`IdPelicula`),
  KEY `IdSala` (`IdSala`),
  KEY `IdHorario` (`IdHorario`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `funciones`
--

INSERT INTO `funciones` (`idFuncion`, `IdPelicula`, `IdHorario`, `IdSala`) VALUES
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
  `idHorario` int NOT NULL AUTO_INCREMENT,
  `HoraInicio` text NOT NULL,
  PRIMARY KEY (`idHorario`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`idHorario`, `HoraInicio`) VALUES
(1, '10:00:00'),
(2, '12:30:00'),
(3, '15:00:00'),
(4, '17:30:00'),
(5, '20:00:00'),
(6, '22:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
CREATE TABLE IF NOT EXISTS `peliculas` (
  `idPelicula` int NOT NULL AUTO_INCREMENT,
  `Titulo` text NOT NULL,
  `Sinopsis` text NOT NULL,
  `FechaEstreno` text NOT NULL,
  `Duracion` int NOT NULL,
  PRIMARY KEY (`idPelicula`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`idPelicula`, `Titulo`, `Sinopsis`, `FechaEstreno`, `Duracion`) VALUES
(1, 'La Gran Aventura', 'Un grupo de amigos emprende una épica travesía para salvar su hogar.', '2024-06-01', 120),
(2, 'Risas y Lagrimas', 'Una comedia dramática sobre las complejidades de la vida familiar.', '2024-05-15', 90),
(3, 'Misterios del Pasado', 'Un detective investiga un antiguo misterio en una pequeña ciudad.', '2024-05-22', 110),
(4, 'Noche de Miedo', 'Un grupo de jóvenes lucha por sobrevivir en una noche llena de terror.', '2024-05-29', 100),
(5, 'El Futuro es Hoy', 'Una visión futurista donde la tecnología domina cada aspecto de la vida.', '2024-06-05', 130),
(6, 'Aventuras Animadas', 'Un divertido viaje animado lleno de risas y enseñanzas.', '2024-06-12', 85);

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `idSala` int NOT NULL AUTO_INCREMENT,
  `NumAsiento` int NOT NULL,
  PRIMARY KEY (`idSala`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`idSala`, `NumAsiento`) VALUES
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
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `Nombre` text NOT NULL,
  `Correo` text NOT NULL,
  `Contrasena` text NOT NULL,
  `idRol` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `Nombre`, `Correo`, `Contrasena`, `idRol`) VALUES
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
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(9, 5, 9, 450, 3),
(10, 3, 10, 600, 4);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
