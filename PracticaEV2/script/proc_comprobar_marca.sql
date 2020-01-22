delimiter //
CREATE PROCEDURE proc_comprob_marca (id_marca int, OUT nom VARCHAR(15), OUT id_m int)
BEGIN
    SET id_m = 0;
    SET nom = 'INECISTENTE';
    SELECT NOMBRE, ID INTO nom, id_m FROM fabricante WHERE ID = id_marca;
END //
delimiter ;