CREATE TABLE `t_game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_name` varchar(20) character set utf8 default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `description` varchar(255) character set utf8 default NULL,
  `create_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;