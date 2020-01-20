delimiter //
CREATE PROCEDURE devolver_precio_mayor(p int, OUT model VARCHAR(20), OUT price int(5))
BEGIN
    SET model = 'INEXISTENTE';
    SET prize = 0;
    SELECT MODELO, PRECIO INTO model, price FROM smartphone WHERE PRECIO >= p
END //
delimiter ;