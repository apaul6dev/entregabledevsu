CREATE TABLE public.persona (
	id_persona serial4 NOT NULL,
	direccion varchar(255) NOT NULL,
	edad int4 NOT NULL,
	genero varchar(255) NOT NULL,
	identificacion varchar(255) NULL,
	nombre varchar(255) NOT NULL,
	telefono varchar(255) NULL,
	CONSTRAINT persona_pkey PRIMARY KEY (id_persona),
	CONSTRAINT uk_r5vsms84ih2viwd6tatk9o5pq UNIQUE (identificacion)
);

CREATE TABLE public.cliente (
	contrasena varchar(255) NOT NULL,
	estado varchar(255) NOT NULL,
	id_cliente int4 NOT NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente),
	CONSTRAINT fkb53lbx2pbv9hoqqn5fdawu602 FOREIGN KEY (id_cliente) REFERENCES public.persona(id_persona)
);

CREATE TABLE public.cuenta (
	id_cuenta serial4 NOT NULL,
	estado varchar(255) NOT NULL,
	numero_cuenta int4 NOT NULL,
	saldo_inicial float8 NOT NULL,
	tipo_cuenta varchar(255) NOT NULL,
	id_cliente int4 NOT NULL,
	CONSTRAINT cuenta_pkey PRIMARY KEY (id_cuenta),
	CONSTRAINT uk_pj7ncg765kt4klndu25bwbwe4 UNIQUE (numero_cuenta),
	CONSTRAINT fkmkmi3xf6wrp0y1mdn8nm4weim FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente)
);

CREATE TABLE public.movimiento (
	id_movimiento serial4 NOT NULL,
	fecha timestamp NULL,
	saldo float8 NOT NULL,
	tipo varchar(255) NOT NULL,
	valor float8 NOT NULL,
	id_cuenta int4 NOT NULL,
	CONSTRAINT movimiento_pkey PRIMARY KEY (id_movimiento),
	CONSTRAINT fk8veysyanipny5mpudj13t8873 FOREIGN KEY (id_cuenta) REFERENCES public.cuenta(id_cuenta)
);