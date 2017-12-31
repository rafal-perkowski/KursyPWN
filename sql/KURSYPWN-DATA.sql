/* USE DATABASE kursypwn */
USE kursypwn;

/* INSERT INTO grupy */
INSERT INTO grupy (nazwa, opis) VALUES 
("A","Kurs intensywny"), 
("B","Kurs weekendowy"), 
("C","Grupa w przygotowaniu");

/* INSERT INTO projekty */
INSERT INTO projekty (temat, opis, deadline, id_gr) VALUES 
("Aplikacja MySQL","Projekt bazy danych","2017-11-01",1),
("Aplikacja Python","Projekt aplikacji bazodanowej - indywidualny","2017-12-01",1),
("Aplikacja Java","Projekt aplikacji bazodanowej - grupowy","2018-01-01",1),
("Aplikacja MySQL","Projekt bazy danych","2017-11-02",2),
("Aplikacja Python","Projekt aplikacji bazodanowej - indywidualny","2017-12-02",2),
("Aplikacja Java","Projekt aplikacji bazodanowej - grupowy","2018-01-02",2),
("Aplikacja MySQL","Projekt bazy danych","2017-11-03",3),
("Aplikacja Python","Projekt aplikacji bazodanowej - indywidualny","2017-12-03",3),
("Aplikacja Java","Projekt aplikacji bazodanowej - grupowy","2018-01-03",3),
("Aplikacja MySQL","Projekt aplikacji bazodanowej - dowolny","2018-02-03",1),
("Aplikacja Python","Projekt aplikacji Python - dowolny","2018-02-03",2),
("Aplikacja Java","Projekt aplikacji Java - dowlny","2018-02-03",3);

/* INSERT INTO logowanie */
INSERT INTO logowanie (login, passwd, rola) VALUES 
("t1","t1","admin"), ("t2","t2","admin"), ("t3","t3","admin"), 
("t4","t4","admin"), ("t5","t5","admin"), ("t6","t6","admin"), 
("k1","k1","user"), ("k2","k2","user"), ("k3","k3","user"), 
("k4","k4","user"), ("k5","k5","user"), ("k6","k6","user"), 
("k7","k7","user"), ("k8","k8","user"), ("k9","k9","user"), 
("k10","k10","user"), ("k11","k11","user"), ("k12","k12","user"), 
("k13","k13","user"), ("k14","k14","user"), ("k15","k15","user"), 
("k16","k16","user"), ("k17","k17","user"), ("k18","k18","user");

/* INSERT INTO trenerzy */
INSERT INTO trenerzy (imie, nazwisko, telefon, email, github, id_lg) VALUES
("Michał","Kruczkowski","1234567","mk@email.com","mkgithub", 1),
("Jan","Kowalski","7654321","jk@email.com","jkgithub", 2),
("Piotr","Nowak","555666777","pn@email.com","pngithub", 3);

/* INSERT INTO kursanci */
INSERT INTO kursanci (imie, nazwisko, telefon, email, github, id_gr, id_lg) VALUES 
("Jan","Nowak","1111111","jn@email.com","jngithub", 1, 7),
("Robert","Matyja","2222222","rm@email.com","rmgithub", 1, 8),
("Anna","Nowak","3333333","an@email.com","angithub", 1, 9),
("Katarzyna","Kowalska","4444444","kk@email.com","kkgithub", 2, 10),
("Adam","Kowalski","5555555","ak@email.com","akgithub", 2, 11),
("Adam","Matyja","6666666","am@email.com","amgithub", 2, 12),
("Piotr","Kubica","7777777","jn@email.com","jngithub", 3, 13),
("Robert","Stoch","8888888","rs@email.com","rsgithub", 3, 14),
("Daniel","Kowal","9999999","dk@email.com","dkgithub", 3, 15);

/* INSERT INTO oceny */
INSERT INTO oceny (id_pr, id_kr, statusprj, ocena, id_tr, uwagi) VALUES
(1,1,2,0,null,null),(1,2,2,4,null,null),(1,3,2,8,null,null),
(2,1,2,2,null,null),(2,2,2,6,null,null),(2,3,2,10,null,null),
(3,1,1,null,null,null),(3,2,0,null,null,null),(3,3,0,null,null,null),
(4,4,2,0,null,null),(4,5,2,4,null,null),(4,6,2,8,null,null),
(5,4,2,2,null,null),(5,5,2,6,null,null),(5,6,2,10,null,null),
(6,4,1,null,null,null),(6,5,0,null,null,""),(6,6,0,null,null,null),
(7,7,2,0,null,null),(7,8,2,4,null,null),(7,9,2,8,null,null),
(8,7,2,2,null,null),(8,8,2,6,null,null),(8,9,2,10,null,null),
(9,7,1,null,null,null),(9,8,0,null,null,null),(9,9,0,null,null,null),
(10,1,0,null,null,null),(10,2,0,null,null,null),(10,3,0,null,null,null),
(11,4,0,null,null,null),(11,5,0,null,null,null),(11,6,0,null,null,null),
(12,7,0,null,null,null),(12,8,0,null,null,null),(12,9,0,null,null,null);

/* INSERT INTO kontakty */
INSERT INTO kontakty (id_nadawcy, flaga_ntk, id_odbiorcy, flaga_otk, temat, tresc, datetimetag) VALUES
(1,0,2,0,"Temat #1","Tresc #1","2017-01-01 00:00:00"),
(2,0,1,0,"Temat #2","Tresc #2","2017-01-01 00:00:00"),
(1,0,3,0,"Temat #3","Tresc #3","2017-01-01 00:00:00"),
(3,0,1,0,"Temat #4","Tresc #4","2017-01-01 00:00:00"),
(2,0,3,0,"Temat #5","Tresc #5","2017-01-01 00:00:00"),
(3,0,3,0,"Temat #6","Tresc #6","2017-01-01 00:00:00"),
(1,0,1,1,"Temat #7","Tresc #7","2017-01-01 00:00:00"),
(1,1,1,0,"Temat #8","Tresc #8","2017-01-01 00:00:00"),
(1,0,2,1,"Temat #9","Tresc #9","2017-01-01 00:00:00"),
(2,1,1,0,"Temat #10","Tresc #10","2017-01-01 00:00:00"),
(1,0,3,1,"Temat #11","Tresc #11","2017-01-01 00:00:00"),
(3,1,1,0,"Temat #12","Tresc #12","2017-01-01 00:00:00"),
(2,0,1,1,"Temat #13","Tresc #13","2017-01-01 00:00:00"),
(1,1,2,0,"Temat #14","Tresc #14","2017-01-01 00:00:00"),
(2,0,2,1,"Temat #15","Tresc #15","2017-01-01 00:00:00"),
(2,1,2,0,"Temat #16","Tresc #16","2017-01-01 00:00:00"),
(2,0,3,1,"Temat #17","Tresc #17","2017-01-01 00:00:00"),
(3,1,2,0,"Temat #18","Tresc #18","2017-01-01 00:00:00"),
(3,0,1,1,"Temat #19","Tresc #19","2017-01-01 00:00:00"),
(1,1,3,0,"Temat #20","Tresc #20","2017-01-01 00:00:00"),
(3,0,2,1,"Temat #21","Tresc #21","2017-01-01 00:00:00"),
(2,1,3,0,"Temat #22","Tresc #22","2017-01-01 00:00:00"),
(3,0,3,1,"Temat #23","Tresc #23","2017-01-01 00:00:00"),
(3,1,3,0,"Temat #24","Tresc #24","2017-01-01 00:00:00"),
(1,1,2,1,"Temat #25","Tresc #10","2017-01-01 00:00:00"),
(2,1,1,1,"Temat #26","Tresc #10","2017-01-01 00:00:00"),
(1,1,3,1,"Temat #27","Tresc #10","2017-01-01 00:00:00"),
(3,1,1,1,"Temat #28","Tresc #10","2017-01-01 00:00:00"),
(2,1,3,1,"Temat #29","Tresc #10","2017-01-01 00:00:00"),
(3,1,2,1,"Temat #30","Tresc #10","2017-01-01 00:00:00"),
(2,1,4,1,"Temat #31","Tresc #10","2017-01-01 00:00:00"),
(4,1,2,1,"Temat #32","Tresc #10","2017-01-01 00:00:00"),
(2,1,5,1,"Temat #33","Tresc #10","2017-01-01 00:00:00"),
(5,1,2,1,"Temat #34","Tresc #10","2017-01-01 00:00:00");

USE kursypwn;
