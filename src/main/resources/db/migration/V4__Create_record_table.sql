CREATE TABLE `t_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(10) default NULL,
  `event_id` int(10) default NULL,
  `team_id` int(10) default NULL,
  `score` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;