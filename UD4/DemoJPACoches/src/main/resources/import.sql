--create table Coche (
--	id INT,
--	marca VARCHAR(50),
--	modelo VARCHAR(50),
--	matricula VARCHAR(50),
--	cilindrada DECIMAL(5,1),
--	potencia INT,
--	color VARCHAR(50)
--);

insert into Persona (id, nombre, apellido) values(1, "Antonio", "Lopez");
insert into Persona (id, nombre, apellido) values(2, "Pablo", "Ruiz");
insert into Persona (id, nombre, apellido) values(3, "Jose", "Rodriguez");

insert into Coche (id, marca, modelo, matricula, cilindrada, potencia, color, persona_id) values (1, 'Ford', 'Explorer Sport Trac', 'JN8AS1MU7BM929670', 2570.5, 138, 'Indigo', 1);
insert into Coche (id, marca, modelo, matricula, cilindrada, potencia, color, persona_id) values (2, 'Nissan', 'Maxima', '3D73Y4EL3BG062951', 4574.0, 440, 'Goldenrod', 2);
insert into Coche (id, marca, modelo, matricula, cilindrada, potencia, color, persona_id) values (3, 'BMW', 'M', '3GYFNBE30ES876460', 7242.6, 436, 'Goldenrod', 3);
insert into Coche (id, marca, modelo, matricula, cilindrada, potencia, color, persona_id) values (4, 'BMW', 'Z4', 'JH4KB16577C684662', 3219.0, 630, 'Maroon', 2);
insert into Coche (id, marca, modelo, matricula, cilindrada, potencia, color, persona_id) values (5, 'Buick', 'Park Avenue', 'WDDHF5GB2AA165779', 5473.0, 788, 'Turquoise', 1);
