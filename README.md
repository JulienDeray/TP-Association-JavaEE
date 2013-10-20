Ecole des Mines - Fil-a2 - JavaEE
=================================

Par Mathieu HERBERT, Gwenael BEC et Julien DERAY

Lancement de la base de données
-------------------------------

* Aller dans le repertoire "bin" du dossier "derby".
* Lancer la base avec `./startNetwoServer` (Linux/Mac) ou `startNetwoServer.bat` (Windows)

-> La base tourne sur le port 1527 \o/


Utilisation
-----------

User : test
Passwd : Test0



Dossiers utiles :
----------------

other : base derby + fichier sql d'initialisation

src/main/java/com/association/controller/servlets : les servlets

src/main/java/com/association/controller/filter : le filtre de verification d'authentification de l'utilisateur appliqué sur toutes les URLs sauf sur la page Login
src/main/java/com/association/controller/service : les deux services article et login
src/main/java/com/core : Tools.java -> api pour crypter le mot de passe
 
