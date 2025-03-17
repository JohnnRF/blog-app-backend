INSERT INTO blogdb.dbo.roles (name) VALUES
	 ('ADMIN'),
	 ('USER');

INSERT INTO blogdb.dbo.users (apellidos,nombres,password,username) VALUES
	 ('Andrade','Lucas','password123','lucas'),
	 ('Gomez','Daniel','password','daniel'),
	 ('Lopez','David','password123','david');

INSERT INTO blogdb.dbo.user_roles (user_id,role_id) VALUES
	 (1,1),
	 (1,2),
	 (2,2),
	 (3,2);

INSERT INTO blogdb.dbo.blogs (contenido,fecha_creacion,publico,titulo,user_id) VALUES
	 ('Blog de prueba','2025-03-15 20:59:41.404440',1,'blog 1',1),
	 ('Blog de prueba 2','2025-03-15 20:59:41.404000',1,'Blog 2',2),
	 ('Traveling around the world','2025-03-15 20:59:41.404000',1,'Traveling',3);