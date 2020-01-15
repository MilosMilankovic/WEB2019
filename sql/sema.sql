
drop database if exists filmovi2;
create database filmovi2;
use filmovi2;

CREATE TABLE korisnik (
	id INT PRIMARY KEY NOT NULL auto_increment,
	korisnickoIme VARCHAR(50) NOT NULL,
    lozinka VARCHAR(50) NOT NULL,
    datumRegistracije DATETIME NOT NULL,
    uloga ENUM('KORISNIK', 'ADMIN') NOT NULL DEFAULT 'KORISNIK',
    obrisan BOOLEAN NOT NULL
);

INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('milos', 'milos', '2019-12-18T10:15:10', 'korisnik', false);
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('lazar', 'lazar', '2019-12-18T11:15:10', 'korisnik', false);
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('nikola', 'nikola', '2019-12-18T12:15:10', 'korisnik', false);
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('pera', 'pera', '2019-12-18T12:15:10', 'admin', false);

create table film (
	id int primary key not null auto_increment,
	naziv nvarchar(50) not null,
	reziser nvarchar(50),
	glumci nvarchar(50),
	zanrovi nvarchar(50),
	trajanje int not null,
	distributer nvarchar(50) not null,
	zemljaPorekla nvarchar(50) not null, 
	godinaProizvodnje int not null,
	opis nvarchar(50),
    obrisan boolean not null
);

INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES (1, "balkanska medja", "milos", "miloss, lazar", "akcija", 100, "rts", "srbija", 2019, "dobar", false);
INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES (2, " hari poter", "lazar", "mpera, peric", "avanturisticki", 150, "rts", "srbija", 2010, "solidan", false);
INSERT INTO film (id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES (3, "x- men", "djoka", "djoka, lazar", "akcija", 100, "rts", "srbija", 2019, "dobar", false);

alter table film 
add constraint CK_godina_veca_od_nula check (godinaProizvodnje>0);

alter table film
add constraint CK_trajanje_vece_od_nula check (trajanje>0);



create table tip_projekcije
(
	id INT PRIMARY KEY NOT NULL auto_increment,
	naziv NVARCHAR(50) NOT NULL
);

INSERT INTO tip_projekcije(id, naziv) VALUES (1,"2D");
INSERT INTO tip_projekcije(id, naziv) VALUES (2,"3D");
INSERT INTO tip_projekcije(id, naziv) VALUES (3,"4D");


create table sala
(
	id INT PRIMARY KEY NOT NULL auto_increment,
	naziv nvarchar(50) not null,
	tip_projekcije int not null,
	foreign key (tip_projekcije) references tip_projekcije(id)
);

INSERT INTO sala(id, naziv, tip_projekcije) VALUES (1, "sala1", 1);
INSERT INTO sala(id, naziv, tip_projekcije) VALUES (2, "sala2", 2);
INSERT INTO sala(id, naziv, tip_projekcije) VALUES (3, "sala3", 3);

create table sediste
(
	redni_broj int primary key not null auto_increment,
	sala int not null,
	foreign key (sala) references sala(id)
);

INSERT INTO sediste(redni_broj, sala) VALUES (1,1);
INSERT INTO sediste(redni_broj, sala) VALUES (2,2);
INSERT INTO sediste(redni_broj, sala) VALUES (3,3);

create table projekcija
(
	id int primary key not null auto_increment,
	film int not null,
	tipProjekcije int not null,
	sala int not null, 
	datumIvreme datetime not null,
	cenaKarte decimal(18,2) not null,
	administrator int not null,
    obrisan boolean not null,
	foreign key (film) references film(id),
	foreign key (tipProjekcije) references tip_projekcije(id),
	foreign key (sala) references sala(id),
    foreign key (administrator) references korisnik (id)
);
use filmovi2;
DROP table projekcija;

alter table projekcija
add constraint CK_cena_veca_od_nula check (cenaKarte>0.00);

INSERT INTO projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan) VALUES (1,1,1,1, '2020-2-16T12:15:10', 300, 4, false);
INSERT INTO projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan) VALUES (2,2,2,2, '2020-2-16T12:16:10', 250, 4, false);
INSERT INTO projekcija(id, film, tipProjekcije, sala, datumIvreme, cenaKarte, administrator, obrisan) VALUES (3,3,3,3, '2020-2-16T12:17:30', 350, 4, false);

create table karta
(
	id int primary key not null auto_increment,
	projekcijaId int not null,
	sediste int not null,
	datumIvreme datetime not null,
	obican_korisnik int not null,
    obrisan boolean not null,
	foreign key (projekcijaId) references projekcija (id),
	foreign key (sediste) references sediste (redni_broj),
    foreign key (obican_korisnik) references korisnik (id)
);


INSERT INTO karta(id, projekcijaId, sediste, datumIvreme, obican_korisnik, obrisan) VALUES (1,1,1, '2020-2-16T12:15:00', 1, false);
INSERT INTO karta(id, projekcijaId, sediste, datumIvreme, obican_korisnik, obrisan) VALUES (2,2,2, '2020-2-16T12:16:00', 2, false);
INSERT INTO karta(id, projekcijaId, sediste, datumIvreme, obican_korisnik, obrisan) VALUES (3,3,3, '2020-2-16T12:17:10', 3, false);
