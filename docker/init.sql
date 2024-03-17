CREATE TABLE IF NOT EXISTS public.bikes (
    id UUID NOT NULL,
    description VARCHAR(500) NULL,
    manufacturer VARCHAR(255) NULL,
    name VARCHAR(255) NOT NULL,
    price FLOAT8 NULL,
    CONSTRAINT bikes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.items (
	id uuid NOT NULL,
	description varchar(500) NULL,
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
CREATE INDEX idx_bike_manufacturer ON public.bikes("manufacturer");
CREATE INDEX idx_bike_name ON public.bikes("name");


INSERT INTO public.users (username,"password") VALUES
	 ('user2','$2a$10$UnLWzcYjYaHFAfr4qtlPieOZUS32e/w4X0YD7dJOllY3bElDD2v3G'),
	 ('user1','$2a$10$jOkKWcQlfmbFWr0j6/0qduSXZ2XBvbPHW61NIxvasGrShOxgCf3H6'),
	 ('user','$2a$10$5vJoGGNHQW/XElUftxhH7.Xr.zOtHTETmIz1BPBANtWeGADx/mASO')
	 ;
