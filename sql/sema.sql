use filmovi;

CREATE TABLE korisnik (
	korisnickoIme VARCHAR(50) NOT NULL,
    lozinka VARCHAR(50) NOT NULL,
    datumRegistracije DATETIME NOT NULL,
    uloga ENUM('KORISNIK', 'ADMIN') NOT NULL DEFAULT 'KORISNIK',
    obrisan BOOLEAN NOT NULL,
    PRIMARY KEY (korisnickoIme)
);

INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('milos', 'milos', '2019-12-18T10:15:10', 'korisnik', false);
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('lazar', 'lazar', '2019-12-18T11:15:10', 'korisnik', false);
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga, obrisan) VALUES ('nikola', 'nikola', '2019-12-18T12:15:10', 'korisnik', false);


create table film (
	id int primary key not null,
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

alter table film 
add constraint CK_godina_veca_od_nula check (godinaProizvodnje>0);

alter table film
add constraint CK_trajanje_vece_od_nula check (trajanje>0);



