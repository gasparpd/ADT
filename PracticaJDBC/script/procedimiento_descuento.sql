#Escrito por mí
delimiter //
CREATE PROCEDURE aplicar_descuento(marca INT, descuento INT)
BEGIN
    UPDATE smartphone SET PRECIO= PRECIO - descuento WHERE ID_MARCA = marca;
END //
delimiter ;

#Exportado de PHPMyAdmin
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aplicar_descuento`(marca INT, descuento INT)
BEGIN
    UPDATE smartphone SET PRECIO= PRECIO - descuento WHERE ID_MARCA = marca;
END$$
DELIMITER ;