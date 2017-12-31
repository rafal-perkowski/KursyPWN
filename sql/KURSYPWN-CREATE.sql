/* CREATE and USE DATABASE kursypwn */
DROP DATABASE IF EXISTS kursypwn;
CREATE DATABASE IF NOT EXISTS kursypwn CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
#CREATE DATABASE IF NOT EXISTS kursypwn CHARACTER SET utf8 COLLATE utf8_general_ci;
USE kursypwn;

# CREATE TABLE with GROUPS
CREATE TABLE IF NOT EXISTS grupy(
id_gr INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nazwa VARCHAR(30) NOT NULL UNIQUE,
opis TEXT
);

# CREATE TABLE with PROJECTS
CREATE TABLE IF NOT EXISTS projekty(
id_pr INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
temat VARCHAR(50),
opis TEXT,
deadline date,
id_gr INT,
FOREIGN KEY (id_gr) REFERENCES grupy (id_gr) 
ON DELETE CASCADE ON UPDATE CASCADE
);

# CREATE TABLE with LOGIN DATA
CREATE TABLE IF NOT EXISTS logowanie(
id_lg INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
login VARCHAR(30) NOT NULL UNIQUE,
passwd VARCHAR(30),
rola VARCHAR(20)
);

# CREATE TABLE with TRAINERS
CREATE TABLE IF NOT EXISTS trenerzy(
id_tr INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
imie VARCHAR(30),
nazwisko VARCHAR(50),
telefon VARCHAR(20),
email VARCHAR(30),
github VARCHAR(50),
id_lg INT UNIQUE,
FOREIGN KEY (id_lg) REFERENCES logowanie (id_lg) 
ON DELETE CASCADE ON UPDATE CASCADE
);

# CREATE TABLE with PARTICIPANTS
CREATE TABLE IF NOT EXISTS kursanci(
id_kr INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
imie VARCHAR(30),
nazwisko VARCHAR(50),
telefon VARCHAR(20),
email VARCHAR(30),
github VARCHAR(50),
id_gr INT,
id_lg INT UNIQUE,
FOREIGN KEY (id_gr) REFERENCES grupy (id_gr) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_lg) REFERENCES logowanie (id_lg) 
ON DELETE CASCADE ON UPDATE CASCADE
);

# CREATE TABLE with ASSESSMENTS
CREATE TABLE IF NOT EXISTS oceny(
id_oc INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_pr INT,
id_kr INT,
statusprj INT,
ocena INT,
id_tr INT,
uwagi TEXT,
FOREIGN KEY (id_pr) REFERENCES projekty (id_pr) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_kr) REFERENCES kursanci (id_kr) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_tr) REFERENCES trenerzy (id_tr) 
ON DELETE CASCADE ON UPDATE CASCADE,
UNIQUE (id_pr, id_kr),
CHECK(ocena>=0 AND ocena<=10 AND ocena%2=0)
);

# CREATE TABLE with CONTACTS
CREATE TABLE IF NOT EXISTS kontakty(
id_ko INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_nadawcy INT,
flaga_ntk TINYINT,
id_odbiorcy INT,
flaga_otk TINYINT,
temat VARCHAR(50),
tresc TEXT,
datetimetag datetime
);

# CREATE TRIGGER for ASSESSMENTS
DELIMITER $$
CREATE TRIGGER oceny_before_insert BEFORE INSERT ON oceny
FOR EACH ROW
BEGIN
    IF (NEW.ocena<0 OR NEW.ocena>10 OR NEW.ocena%2 != 0) THEN
        SIGNAL SQLSTATE "ERROR"
            SET MESSAGE_TEXT = "nieprawidlowa wartosc kolumny oceny.ocena, spoza zakresu: 0,2,4,6,8,10";
    END IF;
END$$   
DELIMITER ;

/* CREATE VIEWS */
CREATE VIEW freelogins_view AS SELECT t1.id_lg FROM logowanie AS t1 LEFT JOIN kursanci AS t2 ON (t1.id_lg=t2.id_lg) WHERE t1.rola="user" AND t2.id_lg IS NULL;
CREATE VIEW projektyadmin_view AS SELECT t1.id_pr, t4.id_kr FROM projekty AS t1 LEFT JOIN oceny AS t2 ON (t1.id_pr=t2.id_pr) JOIN grupy AS t3 ON (t1.id_gr=t3.id_gr) JOIN kursanci AS t4 ON (t3.id_gr=t4.id_gr) WHERE t2.id_oc IS NULL;
CREATE VIEW projektyuser_view AS SELECT t1.id_pr, t1.temat, t1.opis, t1.deadline, t2.ocena, t2.uwagi, t3.id_kr FROM projekty AS t1 JOIN oceny AS t2 ON (t1.id_pr=t2.id_pr) JOIN kursanci AS t3 ON (t2.id_kr=t3.id_kr);
CREATE VIEW oceny_view AS SELECT t4.id_gr, t4.nazwa, t3.id_kr, t3.imie, t3.nazwisko, t2.id_pr, t2.temat, t2.deadline, t1.id_oc, t1.ocena, t1.uwagi  FROM oceny AS t1 JOIN projekty AS t2 ON (t1.id_pr=t2.id_pr) JOIN kursanci AS t3 ON (t1.id_kr=t3.id_kr) JOIN grupy AS t4 ON (t3.id_gr=t4.id_gr); 
CREATE VIEW statystyki_view_all AS SELECT ocena IS NOT NULL AS status, count(ocena IS NOT NULL) AS liczba FROM oceny_view GROUP BY ocena IS NOT NULL;
CREATE VIEW statystyki_view_grupy AS SELECT id_gr, nazwa, ocena IS NOT NULL AS status, count(ocena IS NOT NULL) AS liczba FROM oceny_view GROUP BY id_gr, ocena IS NOT NULL;
CREATE VIEW statystyki_view_kursanci AS SELECT id_gr, nazwa, id_kr, imie, nazwisko, ocena IS NOT NULL AS status, count(ocena IS NOT NULL) AS liczba FROM oceny_view GROUP BY id_kr, ocena IS NOT NULL;

CREATE VIEW uzytkownicy_view AS
SELECT 'trener' AS uzytkownik, 0 AS flaga_tk, id_tr AS id_utk, imie, nazwisko, telefon, email, github FROM trenerzy AS t1 JOIN logowanie AS t2 ON (t1.id_lg=t2.id_lg)
UNION
SELECT 'kursant' AS uzytkownik, 1 AS flaga_tk, id_kr AS id_utk, imie, nazwisko, telefon, email, github FROM kursanci AS t1 JOIN logowanie AS t2 ON (t1.id_lg=t2.id_lg);

CREATE VIEW korespondencja_view AS
(SELECT 'trener' AS nadawca, t1.imie AS imie_nadawcy, t1.nazwisko AS nazwisko_nadawcy, 'trener' AS odbiorca, t2.imie AS imie_odbiorcy, t2.nazwisko AS nazwisko_odbiorcy, t3.id_ko, t3.id_nadawcy, t3.flaga_ntk, t3.id_odbiorcy, t3.flaga_otk, t3.temat, t3.tresc, t3.datetimetag FROM trenerzy AS t1 JOIN trenerzy AS t2 JOIN kontakty AS t3 WHERE t3.id_nadawcy=t1.id_tr AND t3.flaga_ntk=0 AND t3.id_odbiorcy=t2.id_tr AND t3.flaga_otk=0)
UNION
(SELECT 'trener', t1.imie, t1.nazwisko, 'kursant', t2.imie, t2.nazwisko, t3.id_ko, t3.id_nadawcy, t3.flaga_ntk, t3.id_odbiorcy, t3.flaga_otk, t3.temat, t3.tresc, t3.datetimetag FROM trenerzy AS t1 JOIN kursanci AS t2 JOIN kontakty AS t3 WHERE t3.id_nadawcy=t1.id_tr AND t3.flaga_ntk=0 AND t3.id_odbiorcy=t2.id_kr AND t3.flaga_otk=1)
UNION
(SELECT 'kursant', t1.imie, t1.nazwisko, 'trener', t2.imie, t2.nazwisko, t3.id_ko, t3.id_nadawcy, t3.flaga_ntk, t3.id_odbiorcy, t3.flaga_otk, t3.temat, t3.tresc, t3.datetimetag FROM kursanci AS t1 JOIN trenerzy AS t2 JOIN kontakty AS t3 WHERE t3.id_nadawcy=t1.id_kr AND t3.flaga_ntk=1 AND t3.id_odbiorcy=t2.id_tr AND t3.flaga_otk=0)
UNION
(SELECT 'kursant', t1.imie, t1.nazwisko, 'kursant', t2.imie, t2.nazwisko, t3.id_ko, t3.id_nadawcy, t3.flaga_ntk, t3.id_odbiorcy, t3.flaga_otk, t3.temat, t3.tresc, t3.datetimetag FROM kursanci AS t1 JOIN kursanci AS t2 JOIN kontakty AS t3 WHERE t3.id_nadawcy=t1.id_kr AND t3.flaga_ntk=1 AND t3.id_odbiorcy=t2.id_kr AND t3.flaga_otk=1);

CREATE VIEW logowanie_view AS
SELECT t1.id_lg, t1.login, t1.passwd, t1.rola, 'trener' AS uzytkownik, 0 AS flaga_utk, t2.id_tr AS id_utk FROM logowanie AS t1 LEFT JOIN trenerzy AS t2 ON (t1.id_lg=t2.id_lg) WHERE t1.rola="admin"
UNION
SELECT t1.id_lg, t1.login, t1.passwd, t1.rola, 'kursant' AS uzytkownik, 1 AS flaga_utk, t2.id_kr FROM logowanie AS t1 LEFT JOIN kursanci AS t2 ON (t1.id_lg=t2.id_lg) WHERE t1.rola="user";

USE kursypwn;
