/* USE DATABASE kursypwn */
USE kursypwn;

/* SELECT * FROM STATEMENTS - for all created tables */
SELECT * FROM grupy;
SELECT * FROM projekty;
SELECT * FROM logowanie;
SELECT * FROM trenerzy;
SELECT * FROM kursanci;
SELECT * FROM oceny;
SELECT * FROM kontakty;

/* SELECT COUNT(*) STATEMENTS - for all created tables */
SELECT count(*) AS grupy FROM grupy;
SELECT count(*) AS projekty FROM projekty;
SELECT count(*) AS logowanie FROM logowanie;
SELECT count(*) AS trenerzy FROM trenerzy;
SELECT count(*) AS kursanci FROM kursanci;
SELECT count(*) AS oceny FROM oceny;
SELECT count(*) AS kontakty FROM kontakty;

/* SELECT * FROM STATEMENTS - for all created views */
SELECT * FROM freelogins_view;
SELECT * FROM projektyadmin_view;
SELECT * FROM projektyuser_view;
SELECT * FROM oceny_view;
SELECT * FROM statystyki_view_all;
SELECT * FROM statystyki_view_grupy;
SELECT * FROM statystyki_view_kursanci;
SELECT * FROM uzytkownicy_view;
SELECT * FROM korespondencja_view;
SELECT * FROM logowanie_view;

/* SELECT COUNT(*) STATEMENTS - for all created views */
SELECT count(*) AS freelogins_view FROM freelogins_view;
SELECT count(*) AS projektyadmin_view FROM projektyadmin_view;
SELECT count(*) AS projektyuser_view FROM projektyuser_view;
SELECT count(*) AS oceny_view FROM oceny_view;
SELECT count(*) AS statystyki_view_all FROM statystyki_view_all;
SELECT count(*) AS statystyki_view_grupy FROM statystyki_view_grupy;
SELECT count(*) AS statystyki_view_kursanci FROM statystyki_view_kursanci;
SELECT count(*) AS uzytkownicy_view FROM uzytkownicy_view;
SELECT count(*) AS korespondencja_view FROM korespondencja_view;
SELECT count(*) AS logowanie_view FROM logowanie_view;

USE kursypwn;
