use test;

create table benh_nhan(
idBn int PRIMARY KEY auto_increment,
codeBn varchar(20) Unique,
nameBn varchar(45)
);
create table benh_an(
idBa int PRIMARY KEY auto_increment,
codeBa varchar(20),
idBn int,
dateIn date,
dateOut date,
reason varchar(50),
FOREIGN KEY (idBn) REFERENCES benh_nhan (idBn)
);

INSERT INTO `test`.`benh_nhan` (`codeBn`, `nameBn`) VALUES ('BN-2003', 'Nguyễn Văn A');
INSERT INTO `test`.`benh_an` (`codeBa`, `idBn`, `dateIn`, `dateOut`, `reason`) VALUES ('BA-1001', '1', '2020-09-09', '2023-09-09', 'Bệnh phổi');
