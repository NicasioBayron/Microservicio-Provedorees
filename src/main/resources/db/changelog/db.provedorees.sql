--liquibase formatted sql


--changeset naveen:19
CREATE TABLE proveedores (
    idProveedor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreEmpresa VARCHAR(100) NOT NULL,
    contactoNombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    categoriaInsumo VARCHAR(100) NOT NULL
);


--changeset naveen:20
INSERT INTO proveedores (nombreEmpresa, contactoNombre, telefono, categoriaInsumo) VALUES
('Sony Music', 'Carlos Ramírez', '+56911112222', 'Música'),
('Universal Distribution', 'María González', '+56933334444', 'Vinilos'),
('Editorial Planeta', 'Ana Torres', '+56955556666', 'Libros'),
('Instrumentos Chile', 'Pedro Rojas', '+56977778888', 'Instrumentos'),
('Merchandising Music', 'Valentina Soto', '+56999990000', 'Poleras'),
('Gadgets y Más', 'Jorge Martínez', '+56922223333', 'Gadgets'),
('Arte y Cultura S.A.', 'Sofía López', '+56944445555', 'Arte'),
('Deportes y Música Ltda.', 'Diego Fernández', '+56966667777', 'Deportes'),
('Gaming World', 'Lucía García', '+56988889999', 'Gaming'),
('Moda Urbana', 'Martín Pérez', '+56900001111', 'Moda');
