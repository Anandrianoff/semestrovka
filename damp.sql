-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных db
CREATE DATABASE IF NOT EXISTS `db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db`;


-- Дамп структуры для таблица db.comments
CREATE TABLE IF NOT EXISTS `comments` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(50) NOT NULL,
  `postId` int(11) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы db.comments: ~6 rows (приблизительно)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`commentId`, `author`, `postId`, `text`) VALUES
	(5, 'login1', 4, 'Good post!'),
	(6, 'login1', 3, 'Good too'),
	(7, 'andrey', 4, 'Thanks!'),
	(8, 'login1', 3, 'Very good!'),
	(9, 'qwertyu', 4, 'Great'),
	/*!40000 ALTER TABLE `comments` ENABLE KEYS */;


-- Дамп структуры для таблица db.dialogs
CREATE TABLE IF NOT EXISTS `dialogs` (
  `id_dialog` int(11) NOT NULL AUTO_INCREMENT,
  `user1` varchar(50) DEFAULT '0',
  `user2` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_dialog`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы db.dialogs: ~9 rows (приблизительно)
DELETE FROM `dialogs`;
/*!40000 ALTER TABLE `dialogs` DISABLE KEYS */;
INSERT INTO `dialogs` (`id_dialog`, `user1`, `user2`) VALUES
	(9, 'qwe', 'login1'),
	(10, 'login', 'login1'),
	(11, 'Anya', 'login1'),
	(12, 'andrey', 'papa'),
	(13, 'login1', 'papa'),
	(15, 'andrey', 'rustam'),
	(16, 'andrey', 'Ilvir'),
	(18, 'andrey', 'login1'),
	(19, 'andrey', 'qwertyu');
/*!40000 ALTER TABLE `dialogs` ENABLE KEYS */;


-- Дамп структуры для таблица db.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `id_dialog` int(11) NOT NULL DEFAULT '0',
  `message` mediumtext NOT NULL,
  `author` varchar(25) NOT NULL,
  `message_date` datetime NOT NULL,
  PRIMARY KEY (`id_message`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы db.messages: ~24 rows (приблизительно)
DELETE FROM `messages`;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`id_message`, `id_dialog`, `message`, `author`, `message_date`) VALUES
	(46, 10, 'adsdasdas', 'login1', '2016-11-09 23:12:13'),
	(47, 9, '78979', 'login1', '2016-11-09 23:15:03'),
	(48, 9, 'qweqwe', 'login1', '2016-11-09 23:19:50'),
	(49, 9, 'hello\n', 'login1', '2016-11-09 23:21:23'),
	(50, 9, 'qweads', 'login1', '2016-11-09 23:30:33'),
	(51, 9, 'asdd', 'login1', '2016-11-09 23:30:38'),
	(74, 18, 'Privet', 'andrey', '2016-11-19 22:34:20'),
	(75, 18, 'privet ot menya', 'login1', '2016-11-19 22:34:45'),
	(76, 19, 'Hi\n', 'qwertyu', '2016-11-19 22:37:17'),
	(77, 19, 'Hi too', 'andrey', '2016-11-19 22:37:41'),
	(78, 19, 'Hi from me', 'andrey', '2016-11-19 22:38:38'),
	(79, 19, 'i m here', 'qwertyu', '2016-11-19 22:38:46'),
	(80, 19, 'hello', 'qwertyu', '2016-11-19 22:39:28'),
	(81, 19, 'Helllloooo', 'andrey', '2016-11-19 22:39:40'),
	(82, 19, 'Yggg', 'andrey', '2016-11-19 22:39:45'),
	(83, 19, 'Gghjjjj', 'andrey', '2016-11-19 22:40:01'),
	(84, 19, 'hii\n', 'qwertyu', '2016-11-19 22:49:23'),
	(85, 19, 'adsdsa', 'andrey', '2016-11-19 22:52:14'),
	(86, 19, 'hello\n', 'qwertyu', '2016-11-19 23:15:49'),
	(87, 19, 'I\'m here\n', 'qwertyu', '2016-11-19 23:20:08'),
	(88, 19, 'oooh\n', 'andrey', '2016-11-19 23:20:21'),
	(89, 19, 'very good', 'andrey', '2016-11-19 23:20:30'),
	(90, 19, 'Anya here', 'andrey', '2016-11-20 15:00:45'),
	(91, 19, 'ooooh\n678', 'qwertyu', '2016-11-20 15:01:26');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;


-- Дамп структуры для таблица db.posts
CREATE TABLE IF NOT EXISTS `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `header` varchar(50) NOT NULL,
  `text` mediumtext NOT NULL,
  `author` varchar(30) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tags` varchar(50) NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы db.posts: ~3 rows (приблизительно)
DELETE FROM `posts`;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` (`post_id`, `header`, `text`, `author`, `date`, `tags`) VALUES
	(3, 'My first post', 'The Rolling Stones are the oldest performing rock band in music history. They started out in London in 1962 and have been commercially successful for over fifty years. Of the original line-up three members are still in the band: lead singer Mick Jagger, guitarist Keith Richards and drummer Charlie Watts. Ronnie Woods joined the band in 1975. Up to today, the Stones have sold more than 200 million albums.\r\nThe idea of founding a rock and roll band goes back to the schooldays of Mick Jagger and Keith Richards. In 1962 they performed at the Marquee Club in London for the first time. The music they played at that time was not rock and roll but rhythm and blues.\r\nThe Rolling Stones’ first US tour in June 1964 was not very successful, partly because they hadn’t had a hit record at that time.  The Beatles, formed at around the same time, were skyrocketing to success and landed one hit single after the other. While the Beatles had been gentleman-like, nice and always neatly dressed The Stones had been their counterparts, the bad boys, the Anti-Beatles, wearing longer hair and showing a more aggressive musical style.\r\nInternational breakthrough came in 1965 with their number one hit Satisfaction. The Stones had their most successful time in Britain and the US during 1965 and 1966. In 1967 members of the Rolling Stones were accused of taking drugs. Guitarist Brian Jones could not stand the pressure of success and left the band in 1969. He committed suicide  , shortly afterwards.\r\n\r\n ', 'andrey', '2016-11-10 20:10:55', 'first_post '),
	(4, 'My second post', 'Louis Armstrong was America’s greatest jazz musician. He was born in New Orleans , the birthplace of jazz. His father left the family shortly after Louis was born so the boy had to spend his early years living with his grandmother.\r\nLouis loved all kinds of music. He bought an old cornet and learned how to play. When he was in third grade he dropped out of school and started singing on the streets to get money for his family.\r\nLouis spent his teenage years selling newspapers and unloading bananas from boats. In his free time he listened to the famous bands of that time. Later on, he started performing on steamboats and during this period he learned how to read music and play classical pieces on the cornet.\r\nIn 1922 Louis started playing in a band in Chicago and later on moved to New York, where he played in one of the city’s most famous bands. No one else could play like he could. He hit the highest notes easily and played great solos.\r\nAfter a few years in New York he went back to Chicago where he made his first recordings. Here he started playing the trumpet. During one of the recordings Louis dropped his text on the floor. The producer told him to keep on singing so Louis improvised and sung syllables that had no meaning. This became known as “scat singing”. Soon many other jazz musicians tried to copy this style of singing.\r\nHis records made him famous and when he moved back to New York in 1929 bandleaders all over the city wanted to have Louis in their orchestras.\r\nHe not only recorded music, but also performed on Broadway and played in movies. Louis Armstrong was a singer, dancer and master of the trumpet. He travelled all over the world and made people happy through his music.', 'andrey', '2016-11-10 21:40:20', 'armstrong'),
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;


-- Дамп структуры для таблица db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` char(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` char(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role` char(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `firstname` char(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `lastname` char(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `confirmed` bit(1) DEFAULT b'0',
  `active` bit(1) DEFAULT b'0',
  `uuid` char(50) DEFAULT NULL,
  `deletedate` date DEFAULT NULL,
  UNIQUE KEY `Индекс 2` (`username`),
  KEY `Индекс 1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы db.users: ~14 rows (приблизительно)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `email`, `role`, `firstname`, `lastname`, `confirmed`, `active`, `uuid`, `deletedate`) VALUES
	(26, 'Anya', 'e10adc3949ba59abbe56e057f20f883e', 'korolenko97@list.ru', 'MUSICIAN', 'Anya', 'Kudryavtseva', b'0', b'0', '89eae28e-b63b-4945-8de9-9e06fac2689c', '2016-11-09'),
	(31, 'Ilvir', '6cbab356d49502660d99ed767604a327', 'deedpay@gmail.com', 'MUSICIAN', 'Ilvir', 'Dimuhametov', b'1', b'0', 'e50fc1c6-54d2-4c85-a09e-161a8f12e84c', '2016-11-13'),
	(28, 'andrey', 'e10adc3949ba59abbe56e057f20f883e', 'anandrianoff@ya.ru', 'MUSICIAN', 'Andrey', 'Andrianov', b'1', b'0', '1c19f1f1-2455-444c-b946-48194b3ce555', '2016-11-12'),
	(24, 'login', 'e10adc3949ba59abbe56e057f20f883e', 'anandrianoff@ya.ru', 'USER', 'name', 'lananme', b'0', b'0', '8446faa2-b3c6-492c-b305-9e5f674a1476', '2016-11-08'),
	(25, 'login1', 'fcea920f7412b5da7be0cf42b8c93759', 'anandrianoff@ya.ru', 'USER', 'name', 'lananme', b'1', b'0', '2a5cb931-e229-4f5b-a4d5-26a43343e853', '2016-11-08'),
	(27, 'papa', 'e10adc3949ba59abbe56e057f20f883e', 'avv72@udm.ru', 'USER', 'Vladimir', 'Andrianov', b'1', b'0', '97ad1bf5-7bda-43b9-a7c9-281751f44170', '2016-11-10'),
	(17, 'qwe', 'efe6398127928f1b2e9ef3207fb82663', 'q@q.q', 'MUSICIAN', 'qwe', 'qwe', b'1', b'0', '926a1849-180b-4513-bc16-13bec030a7fc', '2016-11-08'),
	(21, 'qweeqw', 'fd6a55d8a6912f83cd102ff508546dd6', 'qwe@qwe.qwe', 'MUSICIAN', 'qwqew', 'qwwqee', b'1', b'0', 'd16adcd6-1c14-42cd-8972-398b42a10ffe', '2016-11-08'),
	(18, 'qweqe', 'efe6398127928f1b2e9ef3207fb82663', 'q@q.q', 'USER', 'qweqwe', 'qweqwe', b'1', b'0', 'd1cec298-6a0b-47be-8510-767ada298bbe', '2016-11-08'),
	(19, 'qweqwe', '4081548905ec3894f50d9842da949359', 'a@yaqeeqwe.ru', 'MUSICIAN', 'qweqwe', 'qweqwe', b'0', b'0', 'b6519e1a-2e08-4c49-9c13-6cb8b0f35ff5', '2016-11-08'),
	(20, 'qweqweqeweqw', 'b6796e90e1864960571cc752f1f263ac', 'q@q.q', 'MUSICIAN', 'qwewqeewqeqw', 'qewqeweqweqw', b'0', b'0', '60169361-565d-4320-93d1-b59efe24ec68', '2016-11-08'),
	(32, 'qwertyu', 'e10adc3949ba59abbe56e057f20f883e', 'qweewqewq@qwweq.wer', 'MUSICIAN', 'name', 'lsdfl', b'1', b'0', '33aacb7d-00a9-4cf8-a812-d635974e25ff', '2016-11-24'),
	(29, 'rustam', 'e10adc3949ba59abbe56e057f20f883e', 'qbbiikk@gmail.com', 'MUSICIAN', 'Rust', 'Zub', b'1', b'0', '41714e82-dde3-4277-a9a8-d1528640fe1c', '2016-11-13'),
	(22, 'wqqwewew', '5e0f5cf012caf1c8d211c8fc86363219', 'qweewq@weqw.qwe', 'MUSICIAN', 'qewwqeew', 'ewqewqe', b'1', b'0', '22440796-8576-40ca-9ac6-2941655b8921', '2016-11-08');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
