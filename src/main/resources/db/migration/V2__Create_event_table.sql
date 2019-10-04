CREATE TABLE `t_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(20) character set utf8 default NULL,
  `event_content` varchar(255) character set utf8 default NULL,
  `game_id` int(11) NOT NULL,
  `deleted` tinyint(1) default 0,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;