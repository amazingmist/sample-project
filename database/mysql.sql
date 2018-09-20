USE Polypro;

DROP TABLE IF EXISTS `ChuyenDe`;
CREATE TABLE `ChuyenKhoaHocDe` (
  `MaCD` varchar(5) PRIMARY KEY,
  `TenCD` nvarchar(50) NOT NULL,
  `HocPhi` int(11) NOT NULL,
  `ThoiLuong` int(11) NOT NULL,
  `Hinh` nvarchar(50) NOT NULL,
  `MoTa` nvarchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `HocVien`;
CREATE TABLE `HocVien` (
  `MaHV` int(11) PRIMARY KEY auto_increment,
  `MaKH` int(11) NOT NULL,
  `MaNH` varchar(7) NOT NULL,
  `Diem` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `KhoaHoc`;
CREATE TABLE `KhoaHoc` (
  `MaKH` int(11)  PRIMARY KEY auto_increment,
  `MaCD` varchar(5) NOT NULL,
  `HocPhi` int(11) NOT NULL,
  `ThoiLuong` int(11) NOT NULL,
  `NgayKG` date NOT NULL,
  `GhiChu` nvarchar(50) NULL,
  `MaNV` varchar(50) NOT NULL,
  `NgayTao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `NguoiHoc`;
CREATE TABLE `NguoiHoc` (
  `MaNH` varchar(7) PRIMARY KEY,
  `HoTen` nvarchar(50) NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` bit NOT NULL,
  `DienThoai` varchar(15) NOT NULL,
  `Email` nvarchar(50) NOT NULL,
  `GhiChu` nvarchar(255) NULL,
  `MaNV` varchar(50) NOT NULL,
  `NgayDK` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `NhanVien`;
CREATE TABLE `NhanVien` (
  `MaNV` varchar(50) PRIMARY KEY,
  `MatKhau` varchar(50) NOT NULL,
  `HoTen` nvarchar(50) NOT NULL,
  `VaiTro` bit NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE ChuyenDe ALTER HocPhi SET DEFAULT 0;

ALTER TABLE ChuyenDe ALTER ThoiLuong SET DEFAULT 30;

ALTER TABLE ChuyenDe ALTER Hinh SET DEFAULT 'chuyen-de.png';

ALTER TABLE ChuyenDe ALTER MoTa SET DEFAULT '';

ALTER TABLE HocVien ALTER Diem SET DEFAULT 0;

ALTER TABLE KhoaHoc ALTER HocPhi SET DEFAULT 0;

ALTER TABLE KhoaHoc ALTER ThoiLuong SET DEFAULT 0;

-- ALTER TABLE KhoaHoc ALTER NgayTao SET DEFAULT NOW();

ALTER TABLE NguoiHoc ALTER GioiTinh SET DEFAULT 0;

-- ALTER TABLE NguoiHoc ALTER NgayDK SET DEFAULT NOW();

ALTER TABLE NhanVien ALTER VaiTro SET DEFAULT 0;



ALTER TABLE HocVien  ADD  CONSTRAINT FK_HocVien_KhoaHoc FOREIGN KEY(MaKH)
REFERENCES KhoaHoc(MaKH);

ALTER TABLE HocVien ADD  CONSTRAINT FK_HocVien_NguoiHoc FOREIGN KEY(MaNH)
REFERENCES NguoiHoc (MaNH);

ALTER TABLE KhoaHoc   ADD  CONSTRAINT FK_KhoaHoc_ChuyenDe FOREIGN KEY(MaCD)
REFERENCES ChuyenDe (MaCD);

ALTER TABLE KhoaHoc   ADD  CONSTRAINT FK_KhoaHoc_NhanVien FOREIGN KEY(MaNV)
REFERENCES NhanVien (MaNV);

ALTER TABLE NguoiHoc  ADD  CONSTRAINT FK_NguoiHoc_NhanVien FOREIGN KEY(MaNV)
REFERENCES NhanVien (MaNV);


INSERT INTO `ChuyenDe` VALUES ('JAV01','Lập trình Java cơ bản',300.0,90,'GAME.png','JAV01 - Lập trình Java cơ bản'),('JAV02','Lập trình Java nâng cao',300.0,90,'HTCS.jpg','JAV02 - Lập trình Java nâng cao'),('JAV03','Lập trình mạng với Java',200.0,70,'INMA.jpg','JAV03 - Lập trình mạng với Java'),('JAV04','Lập trình desktop với Swing',200.0,70,'LAYO.jpg','JAV04 - Lập trình desktop với Swing'),('PRO01','Dự án với công nghệ MS.NET MVC',300.0,90,'MOWE.png','PRO01 - Dự án với công nghệ MS.NET MVC'),('PRO02','Dự án với công nghệ Spring MVC',300.0,90,'Subject.png','PRO02 - Dự án với công nghệ Spring MVC'),('PRO03','Dự án với công nghệ Servlet/JSP',300.0,90,'GAME.png','PRO03 - Dự án với công nghệ Servlet/JSP'),('PRO04','Dự án với AngularJS & WebAPI',300.0,90,'HTCS.jpg','PRO04 - Dự án với AngularJS & WebAPI'),('PRO05','Dự án với Swing & JDBC',300.0,90,'INMA.jpg','PRO05 - Dự án với Swing & JDBC'),('PRO06','Dự án với WindowForm',300.0,90,'LAYO.jpg','PRO06 - Dự án với WindowForm'),('RDB01','Cơ sở dữ liệu SQL Server',100.0,50,'MOWE.png','RDB01 - Cơ sở dữ liệu SQL Server'),('RDB02','Lập trình JDBC',150.0,60,'Subject.png','RDB02 - Lập trình JDBC'),('RDB03','Lập trình cơ sở dữ liệu Hibernate',250.0,80,'GAME.png','RDB03 - Lập trình cơ sở dữ liệu Hibernate'),('SER01','Lập trình web với Servlet/JSP',350.0,100,'HTCS.jpg','SER01 - Lập trình web với Servlet/JSP'),('SER02','Lập trình Spring MVC',400.0,110,'INMA.jpg','SER02 - Lập trình Spring MVC'),('SER03','Lập trình MS.NET MVC',400.0,110,'LAYO.jpg','SER03 - Lập trình MS.NET MVC'),('SER04','Xây dựng Web API với Spring MVC & ASP.NET MVC',200.0,70,'MOWE.png','SER04 - Xây dựng Web API với Spring MVC & ASP.NET MVC'),('WEB01','Thiết kế web với HTML và CSS',200.0,70,'Subject.png','WEB01 - Thiết kế web với HTML và CSS'),('WEB02','Thiết kế web với Bootstrap',0.0,40,'GAME.png','WEB02 - Thiết kế web với Bootstrap'),('WEB03','Lập trình front-end với JavaScript và jQuery',150.0,60,'HTCS.jpg','WEB03 - Lập trình front-end với JavaScript và jQuery'),('WEB04','Lập trình AngularJS',250.0,80,'INMA.jpg','WEB04 - Lập trình AngularJS');

INSERT INTO `NhanVien` VALUES ('NoPT',123456,'Phạm Thị Nở',0),('PheoNC',123456,'Nguyễn Chí Phèo',0),('TeoNV',123456,'Nguyễn Văn Tèo',1);

INSERT INTO `KhoaHoc` VALUES (1,'PRO02',300.0,90,'2018-01-10','','TeoNV','2017-12-31'),(2,'PRO03',300.0,90,'2018-01-10','','TeoNV','2017-12-31'),(3,'RDB01',100.0,50,'2018-01-10','','TeoNV','2017-12-31'),(4,'JAV01',250.0,80,'2018-01-10','','TeoNV','2017-12-31');

INSERT INTO `NguoiHoc` VALUES ('PS01638','LỮ HUY CƯỜNG','1991-05-08',1,928768265,'PS01638@fpt.edu.vn','0928768265 - LỮ HUY CƯỜNG','PheoNC','1991-05-08'),('PS02037','ĐỖ VĂN MINH','1992-10-24',1,968095685,'PS02037@fpt.edu.vn','0968095685 - ĐỖ VĂN MINH','PheoNC','1992-10-24'),('PS02771','NGUYỄN TẤN HIẾU','1998-09-15',1,927594734,'PS02771@fpt.edu.vn','0927594734 - NGUYỄN TẤN HIẾU','PheoNC','1998-09-15'),('PS02867','NGUYỄN HỮU TRÍ','1997-10-27',1,946984711,'PS02867@fpt.edu.vn','0946984711 - NGUYỄN HỮU TRÍ','TeoNV','1997-10-27'),('PS02930','TRẦN VĂN NAM','2000-06-03',1,924774498,'PS02930@fpt.edu.vn','0924774498 - TRẦN VĂN NAM','TeoNV','2000-06-03'),('PS02979','ĐOÀN TRẦN NHẬT VŨ','1994-08-28',1,912374818,'PS02979@fpt.edu.vn','0912374818 - ĐOÀN TRẦN NHẬT VŨ','TeoNV','1994-08-28'),('PS02983','NGUYỄN HOÀNG THIÊN PHƯỚC','1993-04-04',1,912499836,'PS02983@fpt.edu.vn','0912499836 - NGUYỄN HOÀNG THIÊN PHƯỚC','PheoNC','1993-04-04'),('PS02988','HỒ HỮU HẬU','1993-02-08',1,924984876,'PS02988@fpt.edu.vn','0924984876 - HỒ HỮU HẬU','PheoNC','1993-02-08'),('PS03031','PHAN TẤN VIỆT','1990-04-05',1,924832716,'PS03031@fpt.edu.vn','0924832716 - PHAN TẤN VIỆT','PheoNC','1990-04-05'),('PS03046','NGUYỄN CAO PHƯỚC','1990-01-28',1,977117727,'PS03046@fpt.edu.vn','0977117727 - NGUYỄN CAO PHƯỚC','PheoNC','1990-01-28'),('PS03080','HUỲNH THANH HUY','1994-09-06',1,916436052,'PS03080@fpt.edu.vn','0916436052 - HUỲNH THANH HUY','PheoNC','1994-09-06'),('PS03088','NGUYỄN HOÀNG TRUNG','1991-09-02',1,938101529,'PS03088@fpt.edu.vn','0938101529 - NGUYỄN HOÀNG TRUNG','PheoNC','1991-09-02'),('PS03096','ĐOÀN HỮU KHANG','1994-02-21',1,945196719,'PS03096@fpt.edu.vn','0945196719 - ĐOÀN HỮU KHANG','PheoNC','1994-02-21'),('PS03104','LÊ THÀNH PHƯƠNG','1993-02-21',1,922948096,'PS03104@fpt.edu.vn','0922948096 - LÊ THÀNH PHƯƠNG','PheoNC','1993-02-21'),('PS03120','PHẠM NGỌC NHẬT TRƯỜNG','1999-06-24',1,994296169,'PS03120@fpt.edu.vn','0994296169 - PHẠM NGỌC NHẬT TRƯỜNG','PheoNC','1999-06-24'),('PS03130','ĐẶNG BẢO VIỆT','1990-02-14',1,917749344,'PS03130@fpt.edu.vn','0917749344 - ĐẶNG BẢO VIỆT','PheoNC','1990-02-14'),('PS03134','LÊ DUY BẢO','1996-08-08',1,926714368,'PS03134@fpt.edu.vn','0926714368 - LÊ DUY BẢO','PheoNC','1996-08-08'),('PS03172','NGUYỄN ANH TUẤN','1992-02-15',1,920020472,'PS03172@fpt.edu.vn','0920020472 - NGUYỄN ANH TUẤN','PheoNC','1992-02-15'),('PS03202','PHAN QUỐC QUI','1996-02-04',1,930649274,'PS03202@fpt.edu.vn','0930649274 - PHAN QUỐC QUI','PheoNC','1996-02-04'),('PS03203','ĐẶNG LÊ QUANG VINH','1990-01-02',1,920197355,'PS03203@fpt.edu.vn','0920197355 - ĐẶNG LÊ QUANG VINH','PheoNC','1990-01-02'),('PS03205','NGUYỄN MINH SANG','1995-05-02',1,967006218,'PS03205@fpt.edu.vn','0967006218 - NGUYỄN MINH SANG','PheoNC','1995-05-02'),('PS03222','TRẦM MINH MẪN','1997-02-09',1,911183649,'PS03222@fpt.edu.vn','0911183649 - TRẦM MINH MẪN','PheoNC','1997-02-09'),('PS03230','NGUYỄN PHẠM MINH HÂN','2000-10-14',1,983469892,'PS03230@fpt.edu.vn','0983469892 - NGUYỄN PHẠM MINH HÂN','PheoNC','2000-10-14'),('PS03233','LƯU KIM HOÀNG DUY','1996-07-04',1,938357735,'PS03233@fpt.edu.vn','0938357735 - LƯU KIM HOÀNG DUY','PheoNC','1996-07-04'),('PS03251','TRẦN QUANG VŨ','2000-03-10',1,914531913,'PS03251@fpt.edu.vn','0914531913 - TRẦN QUANG VŨ','PheoNC','2000-03-10'),('PS03304','BÙI NGỌC THUẬN','1995-01-26',1,999794115,'PS03304@fpt.edu.vn','0999794115 - BÙI NGỌC THUẬN','PheoNC','1995-01-26'),('PS03306','HỒ VĂN HÀNH','1992-04-15',1,912277868,'PS03306@fpt.edu.vn','0912277868 - HỒ VĂN HÀNH','PheoNC','1992-04-15'),('PS03308','TRẦN VIẾT HÙNG','1999-02-24',1,916050164,'PS03308@fpt.edu.vn','0916050164 - TRẦN VIẾT HÙNG','PheoNC','1999-02-24'),('PS03325','NGUYỄN HOÀNG MINH ĐỨC','1996-12-11',1,912234437,'PS03325@fpt.edu.vn','0912234437 - NGUYỄN HOÀNG MINH ĐỨC','PheoNC','1996-12-11'),('PS03346','PHAN THANH PHONG','1990-01-21',1,937891282,'PS03346@fpt.edu.vn','0937891282 - PHAN THANH PHONG','PheoNC','1990-01-21'),('PS03383','TRẦN VŨ LUÂN','1998-04-08',1,962030316,'PS03383@fpt.edu.vn','0962030316 - TRẦN VŨ LUÂN','PheoNC','1998-04-08'),('PS03389','VŨ ĐỨC TUẤN','1993-02-24',1,911637415,'PS03389@fpt.edu.vn','0911637415 - VŨ ĐỨC TUẤN','PheoNC','1993-02-24'),('PS03410','TRẦN  NHẠT','1992-06-08',1,946219377,'PS03410@fpt.edu.vn','0946219377 - TRẦN  NHẠT','PheoNC','1992-06-08'),('PS03411','TRƯƠNG THÀNH ĐẠT','1993-11-05',1,991509408,'PS03411@fpt.edu.vn','0991509408 - TRƯƠNG THÀNH ĐẠT','PheoNC','1993-11-05'),('PS03425','TÔ VĂN NĂNG','1992-07-28',1,915134551,'PS03425@fpt.edu.vn','0915134551 - TÔ VĂN NĂNG','PheoNC','1992-07-28'),('PS03454','NGUYỄN NHẬT VĨNH','1999-07-09',1,917886371,'PS03454@fpt.edu.vn','0917886371 - NGUYỄN NHẬT VĨNH','PheoNC','1999-07-09'),('PS03472','NGUYỄN VĂN HUY','1990-01-22',1,960620997,'PS03472@fpt.edu.vn','0960620997 - NGUYỄN VĂN HUY','PheoNC','1990-01-22'),('PS03488','NGUYỄN NHƯ NGỌC','1995-05-09',0,912880267,'PS03488@fpt.edu.vn','0912880267 - NGUYỄN NHƯ NGỌC','PheoNC','1995-05-09'),('PS03530','PHẠM THÀNH TÂM','2000-03-11',1,918161783,'PS03530@fpt.edu.vn','0918161783 - PHẠM THÀNH TÂM','PheoNC','2000-03-11'),('PS03553','ĐINH TẤN CÔNG','2000-08-15',1,918182551,'PS03553@fpt.edu.vn','0918182551 - ĐINH TẤN CÔNG','PheoNC','2000-08-15'),('PS03561','LÊ MINH ĐIỀN','1995-01-05',1,948199564,'PS03561@fpt.edu.vn','0948199564 - LÊ MINH ĐIỀN','PheoNC','1995-01-05'),('PS03596','NGUYỄN THANH HIỀN','1991-07-09',1,910545901,'PS03596@fpt.edu.vn','0910545901 - NGUYỄN THANH HIỀN','PheoNC','1991-07-09'),('PS03603','LÊ PHẠM KIM THANH','1994-08-05',0,924696779,'PS03603@fpt.edu.vn','0924696779 - LÊ PHẠM KIM THANH','PheoNC','1994-08-05'),('PS03610','TRẦN ĐÌNH TRƯỜNG','1995-01-16',1,941528106,'PS03610@fpt.edu.vn','0941528106 - TRẦN ĐÌNH TRƯỜNG','PheoNC','1995-01-16'),('PS03614','NGUYỄN VĂN SÁU','1990-04-27',1,940711328,'PS03614@fpt.edu.vn','0940711328 - NGUYỄN VĂN SÁU','PheoNC','1990-04-27'),('PS03618','PHÍ ĐÌNH VIỆT HÙNG','1996-12-09',1,939020097,'PS03618@fpt.edu.vn','0939020097 - PHÍ ĐÌNH VIỆT HÙNG','PheoNC','1996-12-09'),('PS03638','PHẠM NHẬT MINH','1997-07-18',1,927771672,'PS03638@fpt.edu.vn','0927771672 - PHẠM NHẬT MINH','PheoNC','1997-07-18'),('PS03640','LƯU THANH NGỌC','1993-12-01',0,918358164,'PS03640@fpt.edu.vn','0918358164 - LƯU THANH NGỌC','PheoNC','1993-12-01'),('PS03662','NGUYỄN CAO NGỌC LỢI','1990-04-24',1,930260679,'PS03662@fpt.edu.vn','0930260679 - NGUYỄN CAO NGỌC LỢI','PheoNC','1990-04-24'),('PS03674','TRẦN TUẤN ANH','1996-06-11',1,914082094,'PS03674@fpt.edu.vn','0914082094 - TRẦN TUẤN ANH','PheoNC','1996-06-11');

INSERT INTO `HocVien` VALUES (1,2,'PS01638',-1.0),(2,2,'PS02037',-1.0),(3,2,'PS02771',-1.0),(5,2,'PS02930',-1.0),(7,2,'PS02983',-1.0),(8,2,'PS02988',-1.0),(11,3,'PS01638',-1.0),(12,3,'PS02037',-1.0),(13,3,'PS02771',-1.0),(14,3,'PS02867',-1.0),(15,3,'PS02930',-1.0),(16,1,'PS01638',8.0),(17,1,'PS02037',9.0),(19,1,'PS02867',3.0),(20,1,'PS02930',7.0),(33,1,'PS02771',8.0),(34,1,'PS02979',4.0),(35,1,'PS02983',6.0),(36,1,'PS02988',-1.0),(37,1,'PS03031',-1.0),(38,1,'PS03046',-1.0),(39,1,'PS03080',-1.0),(40,1,'PS03088',-1.0),(41,1,'PS03096',-1.0),(42,1,'PS03104',-1.0),(43,1,'PS03120',-1.0),(44,1,'PS03130',-1.0);


DELIMITER //
DROP PROCEDURE IF EXISTS sp_BangDiem;
CREATE PROCEDURE `sp_BangDiem` 
(IN MaKH INT)
BEGIN
	SELECT
		nh.MaNH,
		nh.HoTen,
		hv.Diem
	FROM HocVien hv
		JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH
	WHERE hv.MaKH = MaKH
	ORDER BY hv.Diem DESC;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS sp_ThongKeDiem;
CREATE PROCEDURE `sp_ThongKeDiem` ()
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
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS sp_ThongKeDoanhThu;
CREATE PROCEDURE `sp_ThongKeDoanhThu` 
(IN Year INT)
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
	WHERE YEAR(NgayKG) = @Year
	GROUP BY TenCD;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS sp_ThongKeNguoiHoc;
CREATE PROCEDURE `sp_ThongKeNguoiHoc` ()
BEGIN
	SELECT
		YEAR(NgayDK) Nam,
		COUNT(*) SoLuong,
		MIN(NgayDK) DauTien,
		MAX(NgayDK) CuoiCung
	FROM NguoiHoc
	GROUP BY YEAR(NgayDK);
END //
DELIMITER ;