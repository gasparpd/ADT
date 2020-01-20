delimiter //
CREATE PROCEDURE aplicar_descuento(marca INT, descuento INT) AS
BEGIN
    UPDATE `smartphone` SET `PRECIO`= PRECIO - descuento WHERE ID_MARCA = marca;
    COMMIT;
END;
//