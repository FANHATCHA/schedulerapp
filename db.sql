CREATE TABLE `users` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `password` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `coursework_projects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseworkTitle` varchar(255) DEFAULT NULL,
  `moduleTitle` varchar(255) DEFAULT NULL,
  `intended_due_date` varchar(255) DEFAULT NULL,
  `actual_completion_date` varchar(255) DEFAULT NULL,
  `is_done` bit(1) NOT NULL,
  `user_id` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
