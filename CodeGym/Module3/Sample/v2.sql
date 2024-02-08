use test;
create table type_product(
	idType int auto_increment primary key,
    typeName varchar(20)
);

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    codeName VARCHAR(20),
    idType int,
    price DOUBLE,
    dateSx DATE,
    about VARCHAR(255),
    FOREIGN KEY (idType) REFERENCES type_product (idType)
);
INSERT INTO `test`.`type_product` (`typeName`) VALUES ('Loai A');
INSERT INTO `test`.`type_product` (`typeName`) VALUES ('Loai B');
INSERT INTO `test`.`type_product` (`typeName`) VALUES ('Loai C');
INSERT INTO `test`.`type_product` (`typeName`) VALUES ('Loai D');

INSERT INTO `test`.`product` (`name`, `codeName`, `idType`, `price`, `dateSx`, `about`) VALUES ('Iphone', 'DT-3643', '3', '400000', '2023-03-08', 'Thương hiệu Mỹ');
INSERT INTO `test`.`product` (`name`, `codeName`, `idType`, `price`, `dateSx`, `about`) VALUES ('OPPO', 'DT-4643', '1', '42000', '2022-03-08', 'Thương hiệu Trung Quốc');
INSERT INTO `test`.`product` (`name`, `codeName`, `idType`, `price`, `dateSx`, `about`) VALUES ('Nokia', 'DT-1245', '4', '5000', '2020-03-08', 'Điện thoại cục gạch');
INSERT INTO `test`.`product` (`name`, `codeName`, `idType`, `price`, `dateSx`, `about`) VALUES ('Samsung', 'DT-9540', '2', '90000', '2021-03-08', 'Thương hiệu Hàn Quốc');