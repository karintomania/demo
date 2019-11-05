CREATE TABLE public.customer (
	id int8 NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

INSERT INTO public.customer (id,first_name,last_name) VALUES 
(1,'田中','一郎')
,(2,'鈴木','次郎')
,(3,'佐藤','マイケル')
;