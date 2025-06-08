-- Nouvelle Partie --
CREATE TABLE boss(
   id_boss INT AUTO_INCREMENT,
   nom VARCHAR(50),
   description VARCHAR(50),
   damage INT,
   isAlive BOOLEAN,
   PRIMARY KEY(id_boss)
);

CREATE TABLE world(
   id_place INT AUTO_INCREMENT,
   nom VARCHAR(50),
   description VARCHAR(50),
   progression INT,
   locked BOOLEAN,
   completed BOOLEAN,
   id_boss INT NOT NULL,
   PRIMARY KEY(id_place),
   UNIQUE(id_boss),
   FOREIGN KEY(id_boss) REFERENCES boss(id_boss)
);


CREATE TABLE item(
   id_item INT AUTO_INCREMENT,
   type VARCHAR(50),
   nom VARCHAR(50),
   description VARCHAR(50),
   amount INT,
   classe VARCHAR(50) DEFAULT " ",
   id_place INT NOT NULL,
   PRIMARY KEY(id_item),
   FOREIGN KEY(id_place) REFERENCES world(id_place)
);

CREATE TABLE enemy(
   id_enemy INT AUTO_INCREMENT,
   nom VARCHAR(50),
   description VARCHAR(50),
   damage INT,
   isAlive BOOLEAN,
   id_place INT NOT NULL,
   PRIMARY KEY(id_enemy),
   FOREIGN KEY(id_place) REFERENCES world(id_place)
);


-- Tables utilisées pour la sauvegarde des données (Sauvegarde de la partie) --
CREATE TABLE boss_save(
   id_boss INT,
   nom VARCHAR(50),
   description VARCHAR(50),
   damage INT,
   isAlive BOOLEAN,
   PRIMARY KEY(id_boss)
);

CREATE TABLE world_save(
   id_place INT,
   nom VARCHAR(50),
   description VARCHAR(50),
   progression INT,
   locked BOOLEAN,
   completed BOOLEAN,
   id_boss INT NOT NULL,
   PRIMARY KEY(id_place),
   id_boss INT NOT NULL
   );


CREATE TABLE item_save(
   id_item INT,
   type VARCHAR(50),
   nom VARCHAR(50),
   description VARCHAR(50),
   amount INT,
   classe VARCHAR(50) DEFAULT " ",
   id_place INT NOT NULL,
   PRIMARY KEY(id_item)
   );

CREATE TABLE enemy_save(
   id_enemy INT,
   nom VARCHAR(50),
   description VARCHAR(50),
   damage INT,
   isAlive BOOLEAN,
   id_place INT NOT NULL,
   PRIMARY KEY(id_enemy)
);



