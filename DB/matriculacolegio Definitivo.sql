-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2023 a las 03:48:17
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `matriculacolegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apoderado`
--

CREATE TABLE `apoderado` (
  `id_apoderado` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `ind` char(1) NOT NULL DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `apoderado`
--

INSERT INTO `apoderado` (`id_apoderado`, `nombre`, `apellido`, `dni`, `fecha_nac`, `direccion`, `telefono`, `ind`) VALUES
(1, 'Anthony', 'Castro', '70151821', '2023-10-30', 'Los Olivos', '95158193', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `areas_docente`
--

CREATE TABLE `areas_docente` (
  `id_area` int(11) NOT NULL,
  `nombre_area` varchar(20) DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `areas_docente`
--

INSERT INTO `areas_docente` (`id_area`, `nombre_area`, `estado`) VALUES
(1, 'Matematicas', 'Activo'),
(2, 'Comunicacion', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase`
--

CREATE TABLE `clase` (
  `id_clase` int(11) NOT NULL,
  `idsalon` varchar(11) DEFAULT NULL,
  `idcurso` int(11) DEFAULT NULL,
  `ind` char(1) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clase`
--

INSERT INTO `clase` (`id_clase`, `idsalon`, `idcurso`, `ind`) VALUES
(1, '2B', 1, 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `id_curso` int(11) NOT NULL,
  `nombre_curso` varchar(20) DEFAULT NULL,
  `ind` char(1) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`id_curso`, `nombre_curso`, `ind`) VALUES
(1, 'Aritmetica', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `id_docente` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `correo` varchar(35) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `idarea` int(11) DEFAULT NULL,
  `ind` char(1) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `docente`
--

INSERT INTO `docente` (`id_docente`, `nombre`, `apellido`, `dni`, `fecha_nac`, `direccion`, `correo`, `telefono`, `idarea`, `ind`) VALUES
(4, 'Jose', 'Ramirez', '80126915', '2023-09-30', 'Ancon', 'jramirez@gmail.com', '992516824', 2, 'S'),
(5, 'Diego', 'Quispe', '28916126', '2023-10-10', 'Ate', 'dquispe@gmail.com', '92572162', 2, 'S'),
(7, 'Piero', 'Arias', '12414', '2023-10-12', 'asdasf', 'asdx', '235253', 1, 'N'),
(8, 'Piero', 'Arias', '3525', '2023-10-12', 'asdasf', 'asfzcb', '235253', 1, 'S'),
(9, 'Harold', 'Gutierrez', '71957101', '2023-11-01', 'Los Olivos', 'hgutierrez@gmail.com', '91561696', 1, 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `id_estudiante` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `ind` char(1) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`id_estudiante`, `nombre`, `apellido`, `dni`, `fecha_nac`, `direccion`, `ind`) VALUES
(1, 'Piero', 'Arias', '214', '2023-10-12', 'asdasf', 'N'),
(2, 'Max', 'Sanchez', '74196478', '2023-10-09', 'Los Olivos', 'S'),
(3, 'Giusseppee', 'Reyes', '74527415', '2023-10-12', 'Ancon', 'S'),
(4, 'Samir', 'Vergara', '79812364', '2023-10-02', 'Los Olivos', 'S'),
(5, 'Joaquin', 'Rojas', '72462486', '2023-10-05', 'Puente Piedra', 'S'),
(6, 'Raul', 'Reynaga', '79821513', '2023-10-06', 'San Isidro', 'S'),
(7, 'Francesco', 'Riva', '71528615', '2023-10-03', 'SMP', 'S'),
(8, 'Gustavo', 'Rimari', '72985272', '2023-10-11', 'Ate', 'S'),
(9, 'Juan', 'Perez', '74156821', '2023-10-12', 'Los Olivos', 'S'),
(12, 'Max', 'Sanchez', '34', '2023-10-05', 'Los Olivos', 'N'),
(13, 'Max', 'Sanchez', '5235', '2023-10-12', 'Los Olivos', 'N'),
(14, 'asdxc', 'vcv', '74196478', '2023-10-04', 'zxc', 'N'),
(15, 'Max', 'Sanchez', '342', '2023-10-03', 'Los Olivos', 'N'),
(16, 'Carlos', 'Gutierrez', '79162961', '2023-11-09', 'Ate', 'N'),
(17, 'Abraham', 'Sanchez', '71926712', '2023-11-08', 'Puente Piedra', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `id_horario` int(11) NOT NULL,
  `idclase` int(11) DEFAULT NULL,
  `dia_semana` varchar(10) DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_final` time DEFAULT NULL,
  `ind` char(1) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id_horario`, `idclase`, `dia_semana`, `hora_inicio`, `hora_final`, `ind`) VALUES
(1, 1, 'Lunes', '10:00:00', '11:30:00', 'S'),
(2, 1, 'Viernes', '15:00:00', '16:30:00', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario_doc`
--

CREATE TABLE `horario_doc` (
  `id_horario_doc` int(11) NOT NULL,
  `iddocente` int(11) DEFAULT NULL,
  `idhorario` int(11) DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `id_matricula` int(11) NOT NULL,
  `idestudiante` int(11) DEFAULT NULL,
  `idapoderado` int(11) DEFAULT NULL,
  `idsalon` varchar(11) DEFAULT NULL,
  `esc_procedencia` varchar(50) DEFAULT NULL,
  `esRepitente` varchar(2) DEFAULT NULL,
  `costo_matricula` varchar(10) DEFAULT NULL,
  `fecha_matricula` date DEFAULT NULL,
  `estado` char(1) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`id_matricula`, `idestudiante`, `idapoderado`, `idsalon`, `esc_procedencia`, `esRepitente`, `costo_matricula`, `fecha_matricula`, `estado`) VALUES
(1, 3, 1, '2A', 'San Carlos', 'Si', '1400', '2023-12-14', 'S'),
(2, 6, 1, '5A', 'Trilce', 'No', '1600', '2023-12-05', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salon`
--

CREATE TABLE `salon` (
  `id_salon` varchar(11) NOT NULL,
  `grado` varchar(20) DEFAULT NULL,
  `estado` varchar(10) DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salon`
--

INSERT INTO `salon` (`id_salon`, `grado`, `estado`) VALUES
('1A', '1er grado', 'S'),
('2A', '2do grado', 'S'),
('2B', '2do grado', 'S'),
('4A', '5to grado', 'N'),
('4S', '4to grado', 'N'),
('5A', '5to grado', 'S'),
('5B', '5to grado', 'S'),
('6A', '6to grado', 'N'),
('6B', '6to grado', 'N');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  `contrasena` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `dni`, `usuario`, `contrasena`) VALUES
(1, 'Piero', 'Rojas', '71655697', 'piero', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apoderado`
--
ALTER TABLE `apoderado`
  ADD PRIMARY KEY (`id_apoderado`);

--
-- Indices de la tabla `areas_docente`
--
ALTER TABLE `areas_docente`
  ADD PRIMARY KEY (`id_area`);

--
-- Indices de la tabla `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`id_clase`),
  ADD KEY `idsalon` (`idsalon`),
  ADD KEY `idcurso` (`idcurso`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id_curso`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`id_docente`),
  ADD KEY `idarea` (`idarea`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`id_estudiante`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id_horario`),
  ADD KEY `idclase` (`idclase`);

--
-- Indices de la tabla `horario_doc`
--
ALTER TABLE `horario_doc`
  ADD PRIMARY KEY (`id_horario_doc`),
  ADD KEY `iddocente` (`iddocente`),
  ADD KEY `idhorario` (`idhorario`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`id_matricula`),
  ADD KEY `idestudiante` (`idestudiante`),
  ADD KEY `idapoderado` (`idapoderado`),
  ADD KEY `idsalon` (`idsalon`);

--
-- Indices de la tabla `salon`
--
ALTER TABLE `salon`
  ADD PRIMARY KEY (`id_salon`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apoderado`
--
ALTER TABLE `apoderado`
  MODIFY `id_apoderado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `areas_docente`
--
ALTER TABLE `areas_docente`
  MODIFY `id_area` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `clase`
--
ALTER TABLE `clase`
  MODIFY `id_clase` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `docente`
--
ALTER TABLE `docente`
  MODIFY `id_docente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  MODIFY `id_estudiante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_horario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `horario_doc`
--
ALTER TABLE `horario_doc`
  MODIFY `id_horario_doc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `id_matricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clase`
--
ALTER TABLE `clase`
  ADD CONSTRAINT `clase_ibfk_1` FOREIGN KEY (`idsalon`) REFERENCES `salon` (`id_salon`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `clase_ibfk_2` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`id_curso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `docente`
--
ALTER TABLE `docente`
  ADD CONSTRAINT `area_ibfk_1` FOREIGN KEY (`idarea`) REFERENCES `areas_docente` (`id_area`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`idclase`) REFERENCES `clase` (`id_clase`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `horario_doc`
--
ALTER TABLE `horario_doc`
  ADD CONSTRAINT `horario_doc_ibfk_1` FOREIGN KEY (`iddocente`) REFERENCES `docente` (`id_docente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `horario_doc_ibfk_2` FOREIGN KEY (`idhorario`) REFERENCES `horario` (`id_horario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`idestudiante`) REFERENCES `estudiante` (`id_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`idapoderado`) REFERENCES `apoderado` (`id_apoderado`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matricula_ibfk_3` FOREIGN KEY (`idsalon`) REFERENCES `salon` (`id_salon`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
