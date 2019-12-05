-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2019 at 08:46 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `coursework_projects`
--

CREATE TABLE `coursework_projects` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `courseworkTitle` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `moduleTitle` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `intended_due_date` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `actual_completion_date` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_done` bit(1) NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `coursework_projects`
--

INSERT INTO `coursework_projects` (`id`, `courseworkTitle`, `moduleTitle`, `intended_due_date`, `actual_completion_date`, `is_done`, `user_id`) VALUES
(37, 'AADP Individual Work', 'Machine Learning', '11/19/2019', '11/19/2019', b'0', 1),
(38, 'AADP Individual Work', 'fg', '11/14/2019', '11/08/2019', b'0', 1),
(39, 'new lab', 'dev', '11/22/2019', '11/14/2019', b'0', 1),
(40, 'fr', 'rt', '11/13/2019', '11/19/2019', b'0', 1),
(41, 'Big Data coursework 9', 'Web dev 10', '11/18/2019', '11/12/2019', b'0', 1);

-- --------------------------------------------------------

--
-- Table structure for table `milestones`
--

CREATE TABLE `milestones` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `milestones` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `coursework_id` bigint(20) UNSIGNED NOT NULL,
  `is_completed` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'Kone Fanhatcha', 'fanhatcha@gmail.com', '000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coursework_projects`
--
ALTER TABLE `coursework_projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `coursework_projects_user_id_foreign` (`user_id`);

--
-- Indexes for table `milestones`
--
ALTER TABLE `milestones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `milestones_user_id_foreign` (`user_id`),
  ADD KEY `milestones_coursework_id_foreign` (`coursework_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coursework_projects`
--
ALTER TABLE `coursework_projects`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `milestones`
--
ALTER TABLE `milestones`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `coursework_projects`
--
ALTER TABLE `coursework_projects`
  ADD CONSTRAINT `coursework_projects_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `milestones`
--
ALTER TABLE `milestones`
  ADD CONSTRAINT `milestones_coursework_id_foreign` FOREIGN KEY (`coursework_id`) REFERENCES `coursework_projects` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `milestones_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
