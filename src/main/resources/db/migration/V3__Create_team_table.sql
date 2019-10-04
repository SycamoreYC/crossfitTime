CREATE TABLE `t_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(20) character set utf8 default NULL,
  `game_id` int(11) NOT NULL,
  `deleted` tinyint(1) default 0,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;