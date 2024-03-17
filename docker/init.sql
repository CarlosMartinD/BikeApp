CREATE TABLE IF NOT EXISTS public.bikes (
    id UUID NOT NULL,
    description VARCHAR(255) NULL,
    manufacturer VARCHAR(255) NULL,
    name VARCHAR(255) NOT NULL,
    price FLOAT8 NULL,
    CONSTRAINT bikes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.items (
	id uuid NOT NULL,
	description varchar(255) NULL,
	model varchar(255) NOT NULL,
	"type" varchar(255) NOT NULL,
	bike_id uuid NULL,
	CONSTRAINT items_pkey PRIMARY KEY (id),
	CONSTRAINT fk_bike_id FOREIGN KEY (bike_id) REFERENCES public.bikes(id)
);

CREATE TABLE IF NOT EXISTS public.users (
	username varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (username)
);

CREATE INDEX idx_item_type ON public.items("type");

INSERT INTO public.users (username,"password") VALUES
	 ('carlos','$2a$10$mFB7IaFEG03OhwOog9W1z.G8DRaF.DFG.XIh4HVvqWmhc55M/BUCS'),
	 ('otro','$2a$10$mFB7IaFEG03OhwOog9W1z.G8DRaF.DFG.XIh4HVvqWmhc55M/BUCS');
