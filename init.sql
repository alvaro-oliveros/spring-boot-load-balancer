-- Crear tablas para microservicios

-- Tabla de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    ciudad VARCHAR(50)
);

-- Tabla de Productos
CREATE TABLE IF NOT EXISTS productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(50),
    stock INTEGER DEFAULT 0
);

-- Tabla de Proveedores
CREATE TABLE IF NOT EXISTS proveedores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    ciudad VARCHAR(50)
);

-- 20 registros de Clientes
INSERT INTO clientes (nombre, email, telefono, ciudad) VALUES
('Juan Pérez', 'juan.perez@email.com', '+51 987 654 321', 'Lima'),
('María García', 'maria.garcia@email.com', '+51 976 543 210', 'Arequipa'),
('Carlos Rodríguez', 'carlos.rodriguez@email.com', '+51 965 432 109', 'Cusco'),
('Ana Martínez', 'ana.martinez@email.com', '+51 954 321 098', 'Trujillo'),
('Luis Fernández', 'luis.fernandez@email.com', '+51 943 210 987', 'Chiclayo'),
('Rosa Sánchez', 'rosa.sanchez@email.com', '+51 932 109 876', 'Piura'),
('Pedro López', 'pedro.lopez@email.com', '+51 921 098 765', 'Ica'),
('Carmen Díaz', 'carmen.diaz@email.com', '+51 910 987 654', 'Tacna'),
('Jorge Ruiz', 'jorge.ruiz@email.com', '+51 909 876 543', 'Huancayo'),
('Lucía Torres', 'lucia.torres@email.com', '+51 898 765 432', 'Puno'),
('Miguel Vargas', 'miguel.vargas@email.com', '+51 887 654 321', 'Cajamarca'),
('Patricia Flores', 'patricia.flores@email.com', '+51 876 543 210', 'Ayacucho'),
('Ricardo Mendoza', 'ricardo.mendoza@email.com', '+51 865 432 109', 'Iquitos'),
('Elena Castro', 'elena.castro@email.com', '+51 854 321 098', 'Tumbes'),
('Fernando Rojas', 'fernando.rojas@email.com', '+51 843 210 987', 'Moquegua'),
('Sofía Herrera', 'sofia.herrera@email.com', '+51 832 109 876', 'Pucallpa'),
('Andrés Morales', 'andres.morales@email.com', '+51 821 098 765', 'Chimbote'),
('Gabriela Reyes', 'gabriela.reyes@email.com', '+51 810 987 654', 'Huaraz'),
('Daniel Ortiz', 'daniel.ortiz@email.com', '+51 809 876 543', 'Tarapoto'),
('Valeria Silva', 'valeria.silva@email.com', '+51 798 765 432', 'Juliaca');

-- 20 registros de Productos
INSERT INTO productos (nombre, precio, categoria, stock) VALUES
('Laptop HP Pavilion', 2500.00, 'Electrónicos', 15),
('Mouse Logitech MX', 89.99, 'Accesorios', 50),
('Teclado Mecánico RGB', 150.00, 'Accesorios', 30),
('Monitor Samsung 27"', 450.00, 'Electrónicos', 20),
('Webcam HD 1080p', 75.50, 'Accesorios', 40),
('Disco SSD 1TB', 120.00, 'Almacenamiento', 25),
('Memoria RAM 16GB', 85.00, 'Componentes', 60),
('Tarjeta Gráfica RTX 3060', 899.00, 'Componentes', 10),
('Audífonos Bluetooth Sony', 199.00, 'Audio', 35),
('Impresora Epson L3150', 320.00, 'Periféricos', 18),
('Router WiFi 6 TP-Link', 129.00, 'Redes', 45),
('Cable HDMI 2.1 3m', 25.00, 'Accesorios', 100),
('Cargador USB-C 65W', 45.00, 'Accesorios', 80),
('Tablet Samsung Galaxy', 650.00, 'Electrónicos', 22),
('Smartwatch Xiaomi', 89.00, 'Wearables', 55),
('Parlante JBL Flip 5', 180.00, 'Audio', 28),
('Hub USB 3.0 7 puertos', 35.00, 'Accesorios', 70),
('Mousepad Gaming XL', 29.99, 'Accesorios', 90),
('Cooler para Laptop', 42.00, 'Accesorios', 65),
('Cámara Web 4K Logitech', 250.00, 'Periféricos', 12);

-- 20 registros de Proveedores
INSERT INTO proveedores (nombre, email, telefono, ciudad) VALUES
('Tech Solutions SAC', 'ventas@techsolutions.com', '+51 987 111 222', 'Lima'),
('Importadora Digital', 'contacto@impdigital.com', '+51 976 222 333', 'Lima'),
('Distribuidora Norte', 'info@distnorte.com', '+51 965 333 444', 'Trujillo'),
('Electro Parts Peru', 'ventas@electroparts.pe', '+51 954 444 555', 'Arequipa'),
('CompuWorld SAC', 'compras@compuworld.pe', '+51 943 555 666', 'Lima'),
('MegaTech Distribuidores', 'ventas@megatech.com', '+51 932 666 777', 'Cusco'),
('InfoStore Peru', 'contacto@infostore.pe', '+51 921 777 888', 'Chiclayo'),
('Digital Express', 'ventas@digitalexpress.com', '+51 910 888 999', 'Piura'),
('TecnoMax SAC', 'info@tecnomax.pe', '+51 909 999 000', 'Lima'),
('Importaciones JR', 'ventas@importacionesjr.com', '+51 898 000 111', 'Ica'),
('PC Components Peru', 'contacto@pccomponents.pe', '+51 887 111 222', 'Lima'),
('Hardware Plus', 'ventas@hardwareplus.com', '+51 876 222 333', 'Arequipa'),
('Soluciones IT SAC', 'info@solucionesit.pe', '+51 865 333 444', 'Trujillo'),
('NetPeru Distribuidora', 'ventas@netperu.com', '+51 854 444 555', 'Lima'),
('Grupo Electrónico', 'contacto@grupoelectronico.pe', '+51 843 555 666', 'Huancayo'),
('TechImport SAC', 'ventas@techimport.pe', '+51 832 666 777', 'Lima'),
('Mayorista Digital', 'info@mayoristadigital.com', '+51 821 777 888', 'Cajamarca'),
('Proveedor Central', 'ventas@proveedorcentral.pe', '+51 810 888 999', 'Lima'),
('Electro Importadores', 'contacto@electroimp.com', '+51 809 999 000', 'Tacna'),
('Distribuciones Tech', 'ventas@disttech.pe', '+51 798 000 111', 'Puno');
