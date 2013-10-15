create table PAYS (
	pa_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	pa_code varchar(255) NOT NULL,
	pa_nom varchar(255) NOT NULL,
	CONSTRAINT pays_pk PRIMARY KEY (pa_id)
	);

create table ADHERENT (
	ad_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
	ad_login varchar(255) NOT NULL, 
	ad_password varchar(255) NOT NULL, 
	ad_nom varchar(255) NOT NULL, 
	ad_prenom varchar(255) NOT NULL, 
	ad_adresse varchar(255), 
	ad_codepostal varchar(255),
	ad_ville varchar(255),
	ad_pa_id int,
	CONSTRAINT adherent_pk PRIMARY KEY (ad_id),
	CONSTRAINT pays_fk FOREIGN KEY (ad_pa_id) REFERENCES pays (pa_id),
	CONSTRAINT login_uc UNIQUE (ad_login)
	);

create table ARTICLE (
	ar_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	ar_code varchar(255) NOT NULL,
	ar_nom varchar(255) NOT NULL,
	ar_prix int NOT NULL,
	ar_stock int NOT NULL,
	CONSTRAINT article_pk PRIMARY KEY (ar_id)
	);

-- Toujours le pays avant l'adherent à cause de la cle etrangere !
insert into PAYS (pa_code, pa_nom)
values ('FR', 'France');

insert into ADHERENT (ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, ad_pa_id)
values ('test', '79b5daa411656f050457963a70e78bcc', 'DUPONT', 'Albert', 'Rue du bambou qui fuit', '17000', 'LA ROCHELLE', 1);

insert into ARTICLE (ar_code, ar_prix, ar_stock, ar_nom)
values ('1A', 360, 10, 'Peluche Power Ranger Rouge');

insert into ARTICLE (ar_code, ar_prix, ar_stock, ar_nom)
values ('2A', 300, 9, 'Peluche Power Ranger Vert');

insert into ARTICLE (ar_code, ar_prix, ar_stock, ar_nom)
values ('3A', 400, 6, 'Peluche Power Ranger Bleu');

insert into ARTICLE (ar_code, ar_prix, ar_stock, ar_nom)
values ('4A', 180, 3, 'Peluche Power Ranger Rose');


SELECT ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, pa_nom
FROM ADHERENT
LEFT JOIN PAYS
ON ADHERENT.ad_pa_id = PAYS.pa_id;