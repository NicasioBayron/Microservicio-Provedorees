--liquibase formatted sql


--changeset naveen:19 create-proveedores
CREATE TABLE proveedores (
    id_proveedor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL,
    contacto_nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    categoria_insumo VARCHAR(100) NOT NULL
);


--changeset naveen:20 insert-proveedores
INSERT INTO proveedores (nombre_empresa, contacto_nombre, telefono, categoria_insumo) VALUES
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
