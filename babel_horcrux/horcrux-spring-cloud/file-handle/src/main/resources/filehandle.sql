
-- ----------------------------
-- Table structure for `fileinfo_tab`
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo_tab`;
CREATE TABLE `fileinfo_tab` (
  `file_id` varchar(50) NOT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `file_type` varchar(20) DEFAULT NULL,
  `file_path` varchar(100) DEFAULT NULL,
  `upload_time` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fileinfo_tab
-- ----------------------------
INSERT INTO `fileinfo_tab` VALUES ('01b8303884fb4fd6a098d1ed75482b7f', '圣火传递3.jpg', 'image', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\image', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('4cbd3826a65843a9a1334b03693994eb', 'server.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 16:57:39.000000');
INSERT INTO `fileinfo_tab` VALUES ('506428e6232d4c1186db89485776985b', '圣火传递3.jpg', 'image', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\image', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('56273ee9e13949a994c2fbc8ce8b6216', 'server.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 16:51:26.000000');
INSERT INTO `fileinfo_tab` VALUES ('66ca397fac9e44b288aff45b569921b9', '1.jpg', 'image', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\image', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('6c8f867824264bb2a9616c6a6a59cc9b', 'server.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 17:53:13.000000');
INSERT INTO `fileinfo_tab` VALUES ('6f37a42c084c405596298b03ef9e0db8', 'state-change.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 17:56:03.000000');
INSERT INTO `fileinfo_tab` VALUES ('766f13aba88e4dae9d2c84066d9d4200', 'controller.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('8cbeca47805a476dbdfeb669e10bb8da', 'SpringCloudEurekaHelloWorld.rar', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('8d00249d581c4df1a892b51c69271f80', 'chrome_100_percent.pak', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('8df66a4baa0447dfb955cb154044c54a', 'chrome.dll.sig', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('968b50653aaa4301a6fee55884e197a1', 'state-change.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('9778bb9bb2644709a598917ff4c2a6ff', 'server.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 17:10:05.000000');
INSERT INTO `fileinfo_tab` VALUES ('9cfbff28abc54f08b9832c6f1cd24b6f', 'server.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('9f190c4e830046458ae039e3ee655cd4', 'state-change.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('a6579ae708eb4c6889211af21696600f', 'controller.log.2017-11-17-16', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('bf71dd689e6243358df7d8a14c92cfbc', 'SpringCloudEurekaHelloWorld.rar', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 16:11:48.000000');
INSERT INTO `fileinfo_tab` VALUES ('c96674f5c5e3492c97fd45170dac24e5', 'server.log.2017-11-17-16', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 16:08:55.000000');
INSERT INTO `fileinfo_tab` VALUES ('ca45ffeca7904743b12d2e70813839f2', 'controller.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('d936e11cad77467cb2da067d0c3fe4dd', 'controller.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 15:54:11.907636');
INSERT INTO `fileinfo_tab` VALUES ('ea5210781de34d82b1cdbf559b53cf5e', 'server.log', 'document', 'F:\\horcrux-spring-cloud\\file-handle\\src\\main\\resources\\document', '2018-01-15 16:19:42.000000');

