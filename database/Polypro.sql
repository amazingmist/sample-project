/*
 Navicat Premium Data Transfer

 Source Server         : MySQL Server
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : Polypro

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/10/2018 00:40:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chuyende
-- ----------------------------
DROP TABLE IF EXISTS `chuyende`;
CREATE TABLE `chuyende` (
  `MaCD` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenCD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `HocPhi` int(11) NOT NULL,
  `ThoiLuong` int(11) NOT NULL,
  `Hinh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MoTa` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaCD`),
  KEY `MaCD` (`MaCD`),
  KEY `MaCD_2` (`MaCD`),
  KEY `MaCD_3` (`MaCD`),
  KEY `MaCD_4` (`MaCD`),
  KEY `MaCD_5` (`MaCD`),
  KEY `MaCD_6` (`MaCD`),
  KEY `MaCD_7` (`MaCD`),
  KEY `MaCD_8` (`MaCD`),
  KEY `MaCD_9` (`MaCD`),
  KEY `MaCD_10` (`MaCD`),
  KEY `MaCD_11` (`MaCD`),
  KEY `MaCD_12` (`MaCD`),
  KEY `MaCD_13` (`MaCD`),
  KEY `MaCD_14` (`MaCD`),
  KEY `MaCD_15` (`MaCD`),
  KEY `MaCD_16` (`MaCD`),
  KEY `MaCD_17` (`MaCD`),
  KEY `MaCD_18` (`MaCD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chuyende
-- ----------------------------
BEGIN;
INSERT INTO `chuyende` VALUES ('JAV01', 'Lập trình Java cơ bản', 300, 90, 'GAME.png', 'JAV01 - Lập trình Java cơ bản');
INSERT INTO `chuyende` VALUES ('JAV02', 'Lập trình Java nâng cao', 300, 90, 'HTCS.jpg', 'JAV02 - Lập trình Java nâng cao');
INSERT INTO `chuyende` VALUES ('JAV03', 'Lập trình mạng với Java', 200, 70, 'INMA.jpg', 'JAV03 - Lập trình mạng với Java');
INSERT INTO `chuyende` VALUES ('JAV04', 'Lập trình desktop với Swing', 200, 70, 'LAYO.jpg', 'JAV04 - Lập trình desktop với Swing');
INSERT INTO `chuyende` VALUES ('OPENCV', 'Xử lý ảnh với OpenCV', 300, 100, 'OPENCV.jpg', '');
INSERT INTO `chuyende` VALUES ('PRO01', 'Dự án với công nghệ MS.NET MVC', 300, 90, 'MOWE.png', 'PRO01 - Dự án với công nghệ MS.NET MVC');
INSERT INTO `chuyende` VALUES ('PRO02', 'Dự án với công nghệ Spring MVC', 300, 90, 'Subject.png', 'PRO02 - Dự án với công nghệ Spring MVC');
INSERT INTO `chuyende` VALUES ('PRO03', 'Dự án với công nghệ Servlet/JSP', 300, 90, 'GAME.png', 'PRO03 - Dự án với công nghệ Servlet/JSP');
INSERT INTO `chuyende` VALUES ('PRO04', 'Dự án với AngularJS & WebAPI', 300, 90, 'HTCS.jpg', 'PRO04 - Dự án với AngularJS & WebAPI');
INSERT INTO `chuyende` VALUES ('PRO05', 'Dự án với Swing & JDBC', 300, 90, 'INMA.jpg', 'PRO05 - Dự án với Swing & JDBC');
INSERT INTO `chuyende` VALUES ('PRO06', 'Dự án với WindowForm', 300, 90, 'LAYO.jpg', 'PRO06 - Dự án với WindowForm');
INSERT INTO `chuyende` VALUES ('RDB01', 'Cơ sở dữ liệu SQL Server', 100, 50, 'MOWE.png', 'RDB01 - Cơ sở dữ liệu SQL Server');
INSERT INTO `chuyende` VALUES ('RDB02', 'Lập trình JDBC', 150, 60, 'Subject.png', 'RDB02 - Lập trình JDBC');
INSERT INTO `chuyende` VALUES ('RDB03', 'Lập trình cơ sở dữ liệu Hibernate', 250, 80, 'GAME.png', 'RDB03 - Lập trình cơ sở dữ liệu Hibernate');
INSERT INTO `chuyende` VALUES ('SER01', 'Lập trình web với Servlet/JSP', 350, 100, 'HTCS.jpg', 'SER01 - Lập trình web với Servlet/JSP');
INSERT INTO `chuyende` VALUES ('SER02', 'Lập trình Spring MVC', 400, 110, 'INMA.jpg', 'SER02 - Lập trình Spring MVC');
INSERT INTO `chuyende` VALUES ('SER03', 'Lập trình MS.NET MVC', 400, 110, 'LAYO.jpg', 'SER03 - Lập trình MS.NET MVC');
INSERT INTO `chuyende` VALUES ('SER04', 'Xây dựng Web API với Spring MVC & ASP.NET MVC', 200, 70, 'MOWE.png', 'SER04 - Xây dựng Web API với Spring MVC & ASP.NET MVC');
INSERT INTO `chuyende` VALUES ('WEB01', 'Thiết kế web với HTML và CSS', 200, 60, 'Subject.png', 'WEB01 - Thiết kế web với HTML và CSS');
INSERT INTO `chuyende` VALUES ('WEB02', 'Thiết kế web với Bootstrap', 0, 40, 'GAME.png', 'WEB02 - Thiết kế web với Bootstrap');
INSERT INTO `chuyende` VALUES ('WEB03', 'Lập trình front-end với JavaScript và jQuery', 150, 60, 'HTCS.jpg', 'WEB03 - Lập trình front-end với JavaScript và jQuery');
INSERT INTO `chuyende` VALUES ('WEB04', 'Lập trình AngularJS', 250, 80, 'INMA.jpg', 'WEB04 - Lập trình AngularJS');
COMMIT;

-- ----------------------------
-- Table structure for HocVien
-- ----------------------------
DROP TABLE IF EXISTS `HocVien`;
CREATE TABLE `HocVien` (
  `MaHV` int(11) NOT NULL AUTO_INCREMENT,
  `MaKH` int(11) NOT NULL,
  `MaNH` varchar(7) NOT NULL,
  `Diem` float NOT NULL,
  PRIMARY KEY (`MaHV`),
  KEY `FK_HocVien_KhoaHoc` (`MaKH`),
  KEY `FK_HocVien_NguoiHoc` (`MaNH`),
  CONSTRAINT `FK_HocVien_KhoaHoc` FOREIGN KEY (`MaKH`) REFERENCES `khoahoc` (`makh`),
  CONSTRAINT `FK_HocVien_NguoiHoc` FOREIGN KEY (`MaNH`) REFERENCES `nguoihoc` (`manh`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of HocVien
-- ----------------------------
BEGIN;
INSERT INTO `HocVien` VALUES (1, 2, 'PS01638', -1);
INSERT INTO `HocVien` VALUES (2, 2, 'PS02037', -1);
INSERT INTO `HocVien` VALUES (3, 2, 'PS02771', -1);
INSERT INTO `HocVien` VALUES (5, 2, 'PS02930', -1);
INSERT INTO `HocVien` VALUES (7, 2, 'PS02983', -1);
INSERT INTO `HocVien` VALUES (8, 2, 'PS02988', -1);
INSERT INTO `HocVien` VALUES (11, 3, 'PS01638', -1);
INSERT INTO `HocVien` VALUES (12, 3, 'PS02037', -1);
INSERT INTO `HocVien` VALUES (13, 3, 'PS02771', -1);
INSERT INTO `HocVien` VALUES (14, 3, 'PS02867', -1);
INSERT INTO `HocVien` VALUES (15, 3, 'PS02930', -1);
INSERT INTO `HocVien` VALUES (33, 1, 'PS02771', 8);
INSERT INTO `HocVien` VALUES (34, 1, 'PS02979', 4);
INSERT INTO `HocVien` VALUES (35, 1, 'PS02983', 6);
INSERT INTO `HocVien` VALUES (36, 1, 'PS02988', -1);
INSERT INTO `HocVien` VALUES (37, 1, 'PS03031', -1);
INSERT INTO `HocVien` VALUES (38, 1, 'PS03046', -1);
INSERT INTO `HocVien` VALUES (39, 1, 'PS03080', -1);
INSERT INTO `HocVien` VALUES (40, 1, 'PS03088', -1);
INSERT INTO `HocVien` VALUES (41, 1, 'PS03096', -1);
INSERT INTO `HocVien` VALUES (42, 1, 'PS03104', -1);
INSERT INTO `HocVien` VALUES (43, 1, 'PS03120', -1);
INSERT INTO `HocVien` VALUES (44, 1, 'PS03130', -1);
INSERT INTO `HocVien` VALUES (45, 2, 'PS02771', 10);
INSERT INTO `HocVien` VALUES (46, 1, 'PS02771', 10);
INSERT INTO `HocVien` VALUES (48, 1, 'PS03134', 6);
INSERT INTO `HocVien` VALUES (49, 1, 'PS03172', 9);
INSERT INTO `HocVien` VALUES (50, 1, 'PS03202', 3);
INSERT INTO `HocVien` VALUES (51, 1, 'PS03203', 3);
INSERT INTO `HocVien` VALUES (52, 1, 'PS03205', 3);
INSERT INTO `HocVien` VALUES (53, 1, 'PS03222', 5);
INSERT INTO `HocVien` VALUES (54, 1, 'PS03230', 3);
INSERT INTO `HocVien` VALUES (55, 1, 'PS03233', 4);
INSERT INTO `HocVien` VALUES (56, 1, 'PS06631', 10);
INSERT INTO `HocVien` VALUES (57, 2, 'PS06631', 8);
COMMIT;

-- ----------------------------
-- Table structure for KhoaHoc
-- ----------------------------
DROP TABLE IF EXISTS `KhoaHoc`;
CREATE TABLE `KhoaHoc` (
  `MaKH` int(11) NOT NULL AUTO_INCREMENT,
  `HocPhi` int(11) NOT NULL,
  `ThoiLuong` int(11) NOT NULL,
  `NgayKG` date NOT NULL,
  `GhiChu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NgayTao` date NOT NULL,
  `MaNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MaCD` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaKH`),
  KEY `FK_KhoaHoc_ChuyenDe` (`MaCD`),
  KEY `FK_KhoaHoc_NhanVien` (`MaNV`),
  CONSTRAINT `FK_KhoaHoc_ChuyenDe` FOREIGN KEY (`MaCD`) REFERENCES `chuyende` (`macd`),
  CONSTRAINT `FK_KhoaHoc_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of KhoaHoc
-- ----------------------------
BEGIN;
INSERT INTO `KhoaHoc` VALUES (1, 300, 90, '2018-01-10', '', '2017-12-31', 'TeoNV', 'PRO02');
INSERT INTO `KhoaHoc` VALUES (2, 300, 90, '2018-01-10', '', '2017-12-31', 'TeoNV', 'PRO03');
INSERT INTO `KhoaHoc` VALUES (3, 100, 50, '2018-01-10', '', '2017-12-31', 'TeoNV', 'RDB01');
INSERT INTO `KhoaHoc` VALUES (4, 250, 80, '2018-01-10', '', '2017-12-31', 'TeoNV', 'JAV01');
INSERT INTO `KhoaHoc` VALUES (6, 200, 70, '2017-07-17', '', '2018-10-08', 'TaiVT', 'JAV04');
INSERT INTO `KhoaHoc` VALUES (7, 300, 90, '2018-11-13', '', '2018-10-09', 'TaiVT', 'JAV01');
COMMIT;

-- ----------------------------
-- Table structure for MaXacNhan
-- ----------------------------
DROP TABLE IF EXISTS `MaXacNhan`;
CREATE TABLE `MaXacNhan` (
  `MaNV` varchar(50) NOT NULL,
  `MaXacNhan` varchar(10) NOT NULL,
  PRIMARY KEY (`MaNV`),
  CONSTRAINT `FK_MaXacNhan_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for NguoiHoc
-- ----------------------------
DROP TABLE IF EXISTS `NguoiHoc`;
CREATE TABLE `NguoiHoc` (
  `MaNH` varchar(7) NOT NULL,
  `HoTen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` bit(1) NOT NULL,
  `DienThoai` varchar(15) NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GhiChu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NgayDK` date NOT NULL,
  `Hinh` varchar(255) DEFAULT NULL,
  `MaNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaNH`),
  KEY `FK_NguoiHoc_NhanVien` (`MaNV`),
  CONSTRAINT `FK_NguoiHoc_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NguoiHoc
-- ----------------------------
BEGIN;
INSERT INTO `NguoiHoc` VALUES ('PS01638', 'LỮ HUY CƯỜNG', '1991-05-08', b'1', '928768265', 'PS01638@fpt.edu.vn', '0928768265 - LỮ HUY CƯỜNG', '1991-05-08', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS02037', 'ĐỖ VĂN MINH', '1992-10-24', b'1', '968095685', 'PS02037@fpt.edu.vn', '0968095685 - ĐỖ VĂN MINH', '1992-10-24', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS02771', 'NGUYỄN TẤN HIẾU', '1998-09-15', b'1', '927594734', 'PS02771@fpt.edu.vn', '0927594734 - NGUYỄN TẤN HIẾU', '1998-09-15', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS02867', 'NGUYỄN HỮU TRÍ', '1997-10-27', b'1', '946984711', 'PS02867@fpt.edu.vn', '0946984711 - NGUYỄN HỮU TRÍ', '1997-10-27', NULL, 'TeoNV');
INSERT INTO `NguoiHoc` VALUES ('PS02930', 'TRẦN VĂN NAM', '2000-06-03', b'1', '924774498', 'PS02930@fpt.edu.vn', '0924774498 - TRẦN VĂN NAM', '2000-06-03', NULL, 'TeoNV');
INSERT INTO `NguoiHoc` VALUES ('PS02979', 'ĐOÀN TRẦN NHẬT VŨ', '1994-08-28', b'1', '912374818', 'PS02979@fpt.edu.vn', '0912374818 - ĐOÀN TRẦN NHẬT VŨ', '1994-08-28', NULL, 'TeoNV');
INSERT INTO `NguoiHoc` VALUES ('PS02983', 'NGUYỄN HOÀNG THIÊN PHƯỚC', '1993-04-04', b'1', '912499836', 'PS02983@fpt.edu.vn', '0912499836 - NGUYỄN HOÀNG THIÊN PHƯỚC', '1993-04-04', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS02988', 'HỒ HỮU HẬU', '1993-02-08', b'1', '924984876', 'PS02988@fpt.edu.vn', '0924984876 - HỒ HỮU HẬU', '1993-02-08', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03031', 'PHAN TẤN VIỆT', '1990-04-05', b'1', '924832716', 'PS03031@fpt.edu.vn', '0924832716 - PHAN TẤN VIỆT', '1990-04-05', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03046', 'NGUYỄN CAO PHƯỚC', '1990-01-28', b'1', '977117727', 'PS03046@fpt.edu.vn', '0977117727 - NGUYỄN CAO PHƯỚC', '1990-01-28', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03080', 'HUỲNH THANH HUY', '1994-09-06', b'1', '916436052', 'PS03080@fpt.edu.vn', '0916436052 - HUỲNH THANH HUY', '1994-09-06', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03088', 'NGUYỄN HOÀNG TRUNG', '1991-09-02', b'1', '938101529', 'PS03088@fpt.edu.vn', '0938101529 - NGUYỄN HOÀNG TRUNG', '1991-09-02', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03096', 'ĐOÀN HỮU KHANG', '1994-02-21', b'1', '945196719', 'PS03096@fpt.edu.vn', '0945196719 - ĐOÀN HỮU KHANG', '1994-02-21', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03104', 'LÊ THÀNH PHƯƠNG', '1993-02-21', b'1', '922948096', 'PS03104@fpt.edu.vn', '0922948096 - LÊ THÀNH PHƯƠNG', '1993-02-21', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03120', 'PHẠM NGỌC NHẬT TRƯỜNG', '1999-06-24', b'1', '994296169', 'PS03120@fpt.edu.vn', '0994296169 - PHẠM NGỌC NHẬT TRƯỜNG', '1999-06-24', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03130', 'ĐẶNG BẢO VIỆT', '1990-02-14', b'1', '917749344', 'PS03130@fpt.edu.vn', '0917749344 - ĐẶNG BẢO VIỆT', '1990-02-14', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03134', 'LÊ DUY BẢO', '1996-08-08', b'1', '926714368', 'PS03134@fpt.edu.vn', '0926714368 - LÊ DUY BẢO', '1996-08-08', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03172', 'NGUYỄN ANH TUẤN', '1992-02-15', b'1', '920020472', 'PS03172@fpt.edu.vn', '0920020472 - NGUYỄN ANH TUẤN', '1992-02-15', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03202', 'PHAN QUỐC QUI', '1996-02-04', b'1', '930649274', 'PS03202@fpt.edu.vn', '0930649274 - PHAN QUỐC QUI', '1996-02-04', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03203', 'ĐẶNG LÊ QUANG VINH', '1990-01-02', b'1', '920197355', 'PS03203@fpt.edu.vn', '0920197355 - ĐẶNG LÊ QUANG VINH', '1990-01-02', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03205', 'NGUYỄN MINH SANG', '1995-05-02', b'1', '967006218', 'PS03205@fpt.edu.vn', '0967006218 - NGUYỄN MINH SANG', '1995-05-02', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03222', 'TRẦM MINH MẪN', '1997-02-09', b'1', '911183649', 'PS03222@fpt.edu.vn', '0911183649 - TRẦM MINH MẪN', '1997-02-09', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03230', 'NGUYỄN PHẠM MINH HÂN', '2000-10-14', b'1', '983469892', 'PS03230@fpt.edu.vn', '0983469892 - NGUYỄN PHẠM MINH HÂN', '2000-10-14', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03233', 'LƯU KIM HOÀNG DUY', '1996-07-04', b'1', '938357735', 'PS03233@fpt.edu.vn', '0938357735 - LƯU KIM HOÀNG DUY', '1996-07-04', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03251', 'TRẦN QUANG VŨ', '2000-03-10', b'1', '914531913', 'PS03251@fpt.edu.vn', '0914531913 - TRẦN QUANG VŨ', '2000-03-10', 'PS03251.jpg', 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03304', 'BÙI NGỌC THUẬN', '1995-01-26', b'1', '999794115', 'PS03304@fpt.edu.vn', '0999794115 - BÙI NGỌC THUẬN', '1995-01-26', 'PS03304.jpg', 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03306', 'HỒ VĂN HÀNH', '1992-04-15', b'1', '912277868', 'PS03306@fpt.edu.vn', '0912277868 - HỒ VĂN HÀNH', '1992-04-15', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03308', 'TRẦN VIẾT HÙNG', '1999-02-24', b'1', '916050164', 'PS03308@fpt.edu.vn', '0916050164 - TRẦN VIẾT HÙNG', '1999-02-24', 'avatar.jpg', 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03325', 'NGUYỄN HOÀNG MINH ĐỨC', '1996-12-11', b'1', '912234437', 'PS03325@fpt.edu.vn', '0912234437 - NGUYỄN HOÀNG MINH ĐỨC', '1996-12-11', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03346', 'PHAN THANH PHONG', '1990-01-21', b'1', '937891282', 'PS03346@fpt.edu.vn', '0937891282 - PHAN THANH PHONG', '1990-01-21', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03383', 'TRẦN VŨ LUÂN', '1998-04-08', b'1', '962030316', 'PS03383@fpt.edu.vn', '0962030316 - TRẦN VŨ LUÂN', '1998-04-08', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03389', 'VŨ ĐỨC TUẤN', '1993-02-24', b'1', '911637415', 'PS03389@fpt.edu.vn', '0911637415 - VŨ ĐỨC TUẤN', '1993-02-24', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03410', 'TRẦN  NHẠT', '1992-06-08', b'1', '946219377', 'PS03410@fpt.edu.vn', '0946219377 - TRẦN  NHẠT', '1992-06-08', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03411', 'TRƯƠNG THÀNH ĐẠT', '1993-11-05', b'1', '991509408', 'PS03411@fpt.edu.vn', '0991509408 - TRƯƠNG THÀNH ĐẠT', '1993-11-05', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03425', 'TÔ VĂN NĂNG', '1992-07-28', b'1', '915134551', 'PS03425@fpt.edu.vn', '0915134551 - TÔ VĂN NĂNG', '1992-07-28', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03454', 'NGUYỄN NHẬT VĨNH', '1999-07-09', b'1', '917886371', 'PS03454@fpt.edu.vn', '0917886371 - NGUYỄN NHẬT VĨNH', '1999-07-09', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03472', 'NGUYỄN VĂN HUY', '1990-01-22', b'1', '960620997', 'PS03472@fpt.edu.vn', '0960620997 - NGUYỄN VĂN HUY', '1990-01-22', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03488', 'NGUYỄN NHƯ NGỌC', '1995-05-09', b'0', '912880267', 'PS03488@fpt.edu.vn', '0912880267 - NGUYỄN NHƯ NGỌC', '1995-05-09', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03530', 'PHẠM THÀNH TÂM', '2000-03-11', b'1', '918161783', 'PS03530@fpt.edu.vn', '0918161783 - PHẠM THÀNH TÂM', '2000-03-11', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03553', 'ĐINH TẤN CÔNG', '2000-08-15', b'1', '918182551', 'PS03553@fpt.edu.vn', '0918182551 - ĐINH TẤN CÔNG', '2000-08-15', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03561', 'LÊ MINH ĐIỀN', '1995-01-05', b'1', '948199564', 'PS03561@fpt.edu.vn', '0948199564 - LÊ MINH ĐIỀN', '1995-01-05', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03596', 'NGUYỄN THANH HIỀN', '1991-07-09', b'1', '910545901', 'PS03596@fpt.edu.vn', '0910545901 - NGUYỄN THANH HIỀN', '1991-07-09', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03603', 'LÊ PHẠM KIM THANH', '1994-08-05', b'0', '924696779', 'PS03603@fpt.edu.vn', '0924696779 - LÊ PHẠM KIM THANH', '1994-08-05', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03610', 'TRẦN ĐÌNH TRƯỜNG', '1995-01-16', b'1', '941528106', 'PS03610@fpt.edu.vn', '0941528106 - TRẦN ĐÌNH TRƯỜNG', '1995-01-16', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03614', 'NGUYỄN VĂN SÁU', '1990-04-27', b'1', '940711328', 'PS03614@fpt.edu.vn', '0940711328 - NGUYỄN VĂN SÁU', '1990-04-27', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03618', 'PHÍ ĐÌNH VIỆT HÙNG', '1996-12-09', b'1', '939020097', 'PS03618@fpt.edu.vn', '0939020097 - PHÍ ĐÌNH VIỆT HÙNG', '1996-12-09', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03638', 'PHẠM NHẬT MINH', '1997-07-18', b'1', '927771672', 'PS03638@fpt.edu.vn', '0927771672 - PHẠM NHẬT MINH', '1997-07-18', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03640', 'LƯU THANH NGỌC', '1993-12-01', b'0', '918358164', 'PS03640@fpt.edu.vn', '0918358164 - LƯU THANH NGỌC', '1993-12-01', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03662', 'NGUYỄN CAO NGỌC LỢI', '1990-04-24', b'1', '930260679', 'PS03662@fpt.edu.vn', '0930260679 - NGUYỄN CAO NGỌC LỢI', '1990-04-24', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS03674', 'TRẦN TUẤN ANH', '1996-06-11', b'1', '914082094', 'PS03674@fpt.edu.vn', '0914082094 - TRẦN TUẤN ANH', '1996-06-11', NULL, 'PheoNC');
INSERT INTO `NguoiHoc` VALUES ('PS06630', 'TRẦN DUY QUANG', '1997-09-08', b'1', '091237412', 'PS06630@gmail.com', '091237412 - TRẦN DUY QUANG', '2018-10-08', NULL, 'TaiVT');
INSERT INTO `NguoiHoc` VALUES ('PS06631', 'VÕ THÀNH TÀI', '1997-07-17', b'1', '0932938178', 'PS06631@gmail.com', '0932938178 - VÕ THÀNH TÀI', '2018-10-08', 'PS06631.jpg', 'TaiVT');
COMMIT;

-- ----------------------------
-- Table structure for nhanvien
-- ----------------------------
DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(50) NOT NULL,
  `MatKhau` varchar(50) NOT NULL,
  `HoTen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` bit(1) NOT NULL,
  `DienThoai` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Email` varchar(50) NOT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hinh` varchar(255) DEFAULT NULL,
  `VaiTro` bit(1) NOT NULL,
  PRIMARY KEY (`MaNV`),
  KEY `MaNV` (`MaNV`),
  KEY `MaNV_2` (`MaNV`),
  KEY `MaNV_3` (`MaNV`),
  KEY `MaNV_4` (`MaNV`),
  KEY `MaNV_5` (`MaNV`),
  KEY `MaNV_6` (`MaNV`),
  KEY `MaNV_7` (`MaNV`),
  KEY `MaNV_8` (`MaNV`),
  KEY `MaNV_9` (`MaNV`),
  KEY `MaNV_10` (`MaNV`),
  KEY `MaNV_11` (`MaNV`),
  KEY `MaNV_12` (`MaNV`),
  KEY `MaNV_13` (`MaNV`),
  KEY `MaNV_14` (`MaNV`),
  KEY `MaNV_15` (`MaNV`),
  KEY `MaNV_16` (`MaNV`),
  KEY `MaNV_17` (`MaNV`),
  KEY `MaNV_18` (`MaNV`),
  KEY `MaNV_19` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nhanvien
-- ----------------------------
BEGIN;
INSERT INTO `nhanvien` VALUES ('MyTH', 'myth', 'Trần Hải My', '1997-07-17', b'0', '0912341124', 'taivtse@gmail.com', 'Tiền Giang', NULL, b'0');
INSERT INTO `nhanvien` VALUES ('PheoNC', '123456', 'Nguyễn Chí Phèo', '1999-07-15', b'0', '0932938178', 'chipheo@gmail.com', 'Tân Phú', 'PheoNC.jpg', b'0');
INSERT INTO `nhanvien` VALUES ('TaiVT', '12345', 'Võ Thành Tài', '1993-04-26', b'1', '0123871276', 'thanhtai17071997@gmail.com', 'Quận 1', 'avatar.jpg', b'1');
INSERT INTO `nhanvien` VALUES ('TeoNV', '123456', 'Nguyễn Văn Tèo', '1987-05-19', b'1', '0127263653', 'vanteo@gmail.com', 'Quận 3', 'TeoNV.jpg', b'1');
INSERT INTO `nhanvien` VALUES ('TuanNT', '12345', 'Nguyen Thanh Tuan', '1997-08-13', b'1', '0943948176', 'sol4.amazingmist@gmail.com', '417 cmt8', 'opencv.jpg', b'0');
COMMIT;

-- ----------------------------
-- Procedure structure for sp_BangDiem
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_BangDiem`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BangDiem`(IN MaKH INT)
BEGIN
	SELECT
		nh.MaNH,
		nh.HoTen,
		hv.Diem
	FROM HocVien hv
		JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH
	WHERE hv.MaKH = MaKH
	ORDER BY hv.Diem DESC;
END;
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_ThongKeDiem
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_ThongKeDiem`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ThongKeDiem`()
BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(MaHV) SoHV,
		MIN(Diem) ThapNhat,
		MAX(Diem) CaoNhat,
		AVG(Diem) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKH=hv.MaKH
		JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
	GROUP BY TenCD;
END;
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_ThongKeDoanhThu
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_ThongKeDoanhThu`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ThongKeDoanhThu`(IN nam INT)
BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(DISTINCT kh.MaKH) SoKH,
		COUNT(hv.MaHV) SoHV,
		SUM(kh.HocPhi) DoanhThu,
		MIN(kh.HocPhi) ThapNhat,
		MAX(kh.HocPhi) CaoNhat,
		AVG(kh.HocPhi) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKH=hv.MaKH
		JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
	WHERE YEAR(NgayKG) = nam
	GROUP BY TenCD;
END;
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_ThongKeNguoiHoc
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_ThongKeNguoiHoc`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ThongKeNguoiHoc`()
BEGIN
	SELECT
		YEAR(NgayDK) Nam,
		COUNT(*) SoLuong,
		MIN(NgayDK) DauTien,
		MAX(NgayDK) CuoiCung
	FROM NguoiHoc
	GROUP BY YEAR(NgayDK);
END;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
