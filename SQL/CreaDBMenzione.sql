Drop database if exists Menzione;
create database Menzione;
use Menzione;


CREATE table Ingegnere(
	CF CHAR(16),
    Nome CHAR(20) NOT NULL,
    Cognome CHAR(20)NOT NULL,
    Email CHAR(40) NOT NULL ,
    Stipendio int NOT NULL,
    Studio char(2) NOT NULL,
    primary key(CF)
);

CREATE table Operaio(
	CF CHAR(16),
    Nome CHAR(20) NOT NULL,
    Cognome CHAR(20) NOT NULL,
    Email CHAR(40) NOT NULL,
    Stipendio int NOT NULL,
    Targa char(7) NOT NULL,
    primary key(CF)
);

CREATE table Amministratore(
	CF CHAR(16),
    Nome CHAR(20) NOT NULL,
    Cognome CHAR(20) NOT NULL,
    Email CHAR(30) NOT NULL,
    Stipendio int NOT NULL,
    primary key(CF)
);

CREATE Table Lavoro(
	ID CHAR(5) primary key,
    Localita char(50) not null,
    Descrizione char(30)
);

CREATE table Progetto(
	Codice char(2),
    Formato char(15),
    Scala CHAR(15),
    DataCreazione date not null,
    Ingegnere char(16),
    ID_Lavoro char(5),
    primary key(Codice),
    foreign key(ID_Lavoro) references Lavoro(ID),
    foreign key(Ingegnere) references Ingegnere(CF)
);


CREATE table Cliente(
	CF CHAR(16) primary key,
    Nome CHAR(8) NOT NULL,
    Cognome CHAR(8) NOT NULL,
    Email CHAR(15) NOT NULL,
    Amministratore char(16) not null,
    foreign key(Amministratore) references Amministratore(CF)
);


CREATE table Telefono(
	CF CHAR(16),
    Numero varchar(10),
    primary key(CF,Numero),
    foreign key(CF) REFERENCES Cliente(CF)
);

CREATE table Ordine(
	CodOrdine char(5),
    ID_Lavoro char(5),
    DataOrd date not null,
    Totale float not null,
    Amministratore char(16),
    primary key(CodOrdine,ID_Lavoro,DataOrd),
    foreign key (ID_Lavoro) references Lavoro(ID),
    foreign key (Amministratore) references Amministratore(CF)
);
CREATE table Materiale(
	Cod_Articolo char(3) primary key,
    Prezzo float not null,
    Decrizione CHAR(30)
);

CREATE table Fornitore(
	PIVA CHAR(11) primary key,
    Nome char(20),
    Telefono char(10),
    Fax char(10),
    Email CHAR(30)
);

CREATE table Mansione(
	Operaio char(16),
    ID_Lavoro char(5),
    DataEsecuzione date,
    Descrizione char(60),
    primary key(Operaio,ID_Lavoro,DataEsecuzione),
    foreign key(Operaio) references Operaio(CF),
    foreign key(ID_Lavoro) references Lavoro(ID)
);

CREATE table Comprendere(
	CodOrdine char(5),
    ID_Lavoro Char(5),
    Cod_Articolo char(3),
    Quantita int,
    primary key(CodOrdine,ID_Lavoro,Cod_Articolo),
    foreign key(CodOrdine) references Ordine(CodOrdine),
    foreign key(Cod_Articolo) references Materiale(Cod_Articolo)
);


CREATE table Fornitura(
	Cod_Articolo CHAR(3),
    PIVA CHAR(11),
    DataFornitura date,
    Quantita int not null,
    primary key(Cod_Articolo,PIVA,DataFornitura),
    foreign key(Cod_Articolo) references Materiale(Cod_Articolo),
    foreign key(PIVA) references Fornitore(PIVA)
);

INSERT Into Operaio Values
("CF1","Mario","Bianchi","email1@gmail.com",1500,"AC234DB"),
("CF2","Luigi","Cardo","email2@gmail.com",1200,"AK223LP"),
("CF3","Salvatore","Menzione","email3@gmail.com",1100,"PO982AS"),
("CF4","Pasquale","Grassi","email4@gmail.com",1500,"WE092OP"),
("CF5","Michele","Menzione","menzione.m@gmail.com",1,"DB937CD");

INSERT Into Ingegnere Values
("CF001","Mario","Bianchi","email1@gmail.com",1500,"2A"),
("CF002","Luigi","Cardo","email2@gmail.com",1200,"5B"),
("CF003","Salvatore","Menzione","email3@gmail.com",1100,"3K");


INSERT Into Amministratore Values
("CF02","Maria","Tizzi","email02@gmail.com",1690),
("CF03","Elia","DeFalco","email03@gmail.com",1600),
("CF04","Ferdinando","Menzione","email04@gmail.com",2000);

INSERT Into Lavoro Values
("IT902","Via Coda, San Giuseppe Vesuviano (NA)","Restrutturazione bagno"),
("IT903","Via Umberto I, San Giorgio (NA)","Restrutturazione Edificio"),
("IT904","Via Passanti, San Gennaro Vesuviano (NA)","Cambio pavimento");

INSERT INTO Progetto VALUES
("C2","Digitale","1 : 10","2022-01-10","CF001","IT902"),
("F3","Cartaceo","2 : 20","2022-01-16","CF003","IT903");


INSERT INTO Cliente VALUES
("CF11","Paolo","Grandi","emails@live.it","CF03"),
("CF12","Luigi","Biondi","emaila@live.it","CF02"),
("CF13","Alfredo","Bifulco","emaild@live.it","CF03");

INSERt INTO Telefono values
("CF11","3349827634"),
("CF12","3312567356"),
("CF13","3312189076");

INSERT INTO Materiale VALUES
("A19",2,"Sacca di cemento"),
("A20",2,"Spatola cementizia"),
("A21",3,"Mattoni"),
("A22",2.5,"Sacca di cemento Armato"),
("A23",10.5,"Spatola seghettata");

INSERT INTO Ordine VALUES
("OR100","IT902","2022-01-22",50,"CF02"),
("OR101","IT903","2022-01-22",20,"CF03"),
("OR102","IT904","2022-01-24",27.5,"CF03"),
("OR103","IT902","2022-01-24",31.5,"CF02");


INSERT Into Mansione Values
("CF1","IT902","2022-01-22","Preparazione lavoro"),
("CF2","IT903",'2022-01-22',"Preparazione lavoro"),
("CF4","IT904","2022-03-23","Preparazione Lavoro"),
("CF2","IT902","2022-01-23","Rimozione Pavimenti"),
("CF3","IT904",'2022-01-23',"Verniciatura pareti"),
("CF4","IT904",'2022-01-24',""),
("CF1","IT903",'2022-01-24',"Rimozione servizi igienici"),
("CF2","IT902",'2022-01-24',"Preparazione cementizia");

Insert INTO Comprendere VALUES
("OR100","IT902","A19",20),
("OR100","IT902","A20",5),
("OR101","IT903","A19",10),
("OR102","IT904","A22",11),
("OR103","IT902","A23",3);

INSERT INTO Fornitore VALUES
("PIVA1","Cementiz","TEL1","FAX1","EM1@gmail.com"),
("PIVA2","Vallone","TEL2","FAX2","EM2@gmail.com"),
("PIVA3","EdilCio","TEL3","FAX3","EM3@gmail.com"),
("PIVA4","Ediliamoci","TEL4","FAX4","EM4@gmail.com");

INSERT INTO Fornitura VALUES
("A19","PIVA1","2022-01-18",20),
("A19","PIVA1","2022-01-20",10),
("A21","PIVA3","2022-01-18",11);
