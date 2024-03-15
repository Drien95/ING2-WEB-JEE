CREATE DATABASE IF NOT EXISTS test;

USE test;

CREATE TABLE IF NOT EXISTS user (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  password varchar(45) DEFAULT NULL,
  adress varchar(45) DEFAULT NULL,
  city varchar(45) DEFAULT NULL,
  postal int DEFAULT NULL,
  role varchar(45) DEFAULT 'random',
  salt varchar(45) DEFAULT NULL,
  fidelity_point float DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS products (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  cat varchar(255) DEFAULT NULL,
  price int DEFAULT NULL,
  quantity int DEFAULT NULL,
  user_id int DEFAULT NULL,
  image varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY user_id_idx (user_id),
  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS orders (
  id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  order_date varchar(45) NOT NULL,
  price float DEFAULT NULL,
  method_payment varchar(45) DEFAULT NULL,
  state int NOT NULL,
  PRIMARY KEY (id),
  KEY user_id_idx (user_id),
  CONSTRAINT fk_user_id_order FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS cart (
  id int NOT NULL AUTO_INCREMENT,
  order_id int NOT NULL,
  product_id int NOT NULL,
  quantity int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_order_id_cart_idx (order_id),
  KEY fk_product_id_cart_idx (product_id),
  CONSTRAINT fk_order_id_cart FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT fk_product_id_cart FOREIGN KEY (product_id) REFERENCES products (id)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

insert into test.user (id,name,email,password,role) values (1,"admin","jee890703@gmail.com","a94a8fe5ccb19ba61c4c0873d391e987982fbbd3","admin");
insert into test.user (id,name,email,password,role) values (1,"seller","vendeur@vendeur.com","a94a8fe5ccb19ba61c4c0873d391e987982fbbd3","seller");

insert into test.products (name, cat, price, quantity, user_id) values ('Salvia fruticosa Mill.', 'Greek Oregano', 79.5, 84, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Anemia wrightii Baker', 'Wright''s Flowering Fern', 20.5, 63, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Arabis sparsiflora Nutt. var. californica Rollins', 'California Rockcress', 24.8, 83, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Falcaria vulgaris Bernh.', 'Sickleweed', 31.8, 91, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Atriplex argentea Nutt. ssp. argentea var. caput-medusae (Eastw.) Fosberg', 'Stalked Saltbush', 2.6, 71, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Triumfetta tomentosa Bojer', 'Tomentose Burbark', 80.4, 43, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Astragalus cimae M.E. Jones var. cimae', 'Cima Milkvetch', 79.9, 4, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Apacheria C.T. Mason', 'Apacheria', 5.1, 18, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Quercus ×ashei Trel.', 'Hybrid Oak', 95.1, 42, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Ratibida columnifera (Nutt.) Wooton & Standl.', 'Upright Prairie Coneflower', 52.7, 11, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Heuchera americana L. var. americana', 'American Alumroot', 68.6, 13, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Oxytheca watsonii Torr. & A. Gray', 'Watson''s Oxytheca', 54.6, 32, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Carex folliculata L.', 'Northern Long Sedge', 14.8, 16, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Limnobium laevigatum (Humb. & Bonpl. ex Willd.) Heine', 'West Indian Spongeplant', 34.1, 17, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Argemone glauca (Nutt. ex Prain) Pope', 'Smooth Pricklypoppy', 14.0, 61, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Rupertia physodes (Douglas ex Hook.) J. Grimes', 'Forest Scurfpea', 37.8, 39, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Koenigia islandica L.', 'Island Purslane', 82.7, 79, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Juglans ×bixbyi Rehder', 'Buart', 59.3, 45, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Eriogonum annuum Nutt.', 'Annual Buckwheat', 51.8, 35, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Lophosoria quadripinnata (J.F. Gmel.) C. Chr.', 'Diamondleaf Fern', 82.9, 78, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Chamaedaphne calyculata (L.) Moench', 'Leatherleaf', 45.7, 72, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Caloplaca Th. Fr.', 'Orange Lichen', 1.5, 52, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Rhytidiadelphus (Lindb. ex Limpr.) Warnst.', 'Goose Neck Moss', 91.3, 33, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Agrostis castellana Boiss. & Reuter', 'Bentgrass', 31.0, 43, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Rhynchospora Vahl', 'Beaksedge', 72.6, 79, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Quercus ×joorii Trel.', 'Joor Oak', 42.9, 81, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Lupinus guadalupensis Greene', 'Guadalupe Island Lupine', 50.0, 55, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Plumbago scandens L.', 'Doctorbush', 22.4, 6, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Hillia parasitica Jacq.', 'Tibey Trepador', 95.9, 81, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Browallia L.', 'Browallia', 30.2, 33, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Bidens eatonii Fernald', 'Eaton''s Beggarticks', 18.4, 61, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Caesalpinia L.', 'Nicker', 9.1, 54, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Sphinctrina tubiformis A. Massal.', 'Sphinctrina Lichen', 16.6, 56, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Anemone L.', 'Anemone', 6.5, 62, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Plagiobothrys strictus (Greene) I.M. Johnst.', 'Calistoga Popcornflower', 73.4, 9, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Bryum stirtonii Schimp.', 'Stirton''s Bryum Moss', 97.9, 27, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Cardamine ×incisa (Eames) K. Schum. (pro sp.)', 'Bittercress', 42.9, 76, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Polygala nitida Brandegee var. goliadensis T. Wendt', 'Shining Milkwort', 94.1, 36, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Crumia Schof.', 'Crumia Moss', 93.1, 77, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Phormium tenax J.R. Forst. & G. Forst.', 'New Zealand Flax', 36.6, 49, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Edrudia W.P. Jordan', 'Edrudia Lichen', 9.6, 33, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Baltimora L.', 'Baltimora', 52.4, 56, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Berberis wisleyensis Ahrendt', 'Wisley Barberry', 87.5, 16, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Trichilia triacantha Urb.', 'Bariaco', 19.6, 64, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Pangium edule Reinw. ex Blume', 'Football Fruit', 73.5, 11, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Coreopsis palmata Nutt.', 'Stiff Tickseed', 97.3, 85, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Fritillaria agrestis Greene', 'Stinkbells', 65.4, 84, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Rubus nigerrimus (Greene) Rydb.', 'Dark Raspberry', 91.3, 81, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Galium ambiguum W. Wight ssp. siskiyouense (Ferris) Dempster & Stebbins', 'Siskiyou Bedstraw', 6.7, 29, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Ulmus pumila L.', 'Siberian Elm', 4.4, 56, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Agoseris heterophylla (Nutt.) Greene var. crenulata Jeps.', 'Annual Agoseris', 68.1, 64, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Cassia afrofistula Brenan', 'Kenyan Shower', 62.7, 84, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Potamogeton marianensis Cham. & Schltdl.', 'Pondweed', 12.9, 29, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Cornus obliqua Raf.', 'Silky Dogwood', 62.3, 89, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Aristolochia elegans Mast.', 'Elegant Dutchman''s Pipe', 3.6, 90, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Astragalus nyensis Barneby', 'Nye Milkvetch', 14.9, 71, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Peperomia emarginella (Sw. ex Wikstr.) C. DC.', 'Guadeloupe Peperomia', 72.5, 100, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Dendropemon bicolor Krug & Urb.', 'Puerto Rico Leechbush', 36.8, 38, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Gaussia attenuata (O.F. Cook) Becc.', 'Llume', 46.8, 28, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Ochrosia elliptica Labill.', 'Elliptic Yellowwood', 67.8, 83, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Silene verecunda S. Watson ssp. platyota (S. Watson) C.L. Hitchc. & Maguire var. platyota (S. Watson) Jeps.', 'San Francisco Campion', 15.1, 54, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Acacia continua Benth.', 'Thorn Wattle', 29.7, 74, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Arenaria fendleri A. Gray var. porteri Rydb.', 'Porter''s Sandwort', 5.9, 74, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Pyxine albovirens (G. Mey.) Aptroot', 'Pyxine Lichen', 48.9, 82, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Geum urbanum L.', 'Herb Bennet', 61.0, 88, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Streptanthus Nutt.', 'Twistflower', 78.6, 94, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Lepraria lobificans Nyl.', 'Dust Lichen', 34.9, 83, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Salix lasiolepis Benth. var. bigelovii (Torr.) Bebb', 'Bigelow''s Willow', 57.5, 7, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Thuidium allenii Austin', 'Allen''s Thuidium Moss', 94.3, 63, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Nicotiana paniculata L.', 'Tobacco', 49.1, 43, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Castilleja sessiliflora Pursh', 'Downy Paintedcup', 19.8, 58, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Silene occidentalis S. Watson ssp. longistipitata C.L. Hitchc. & Maguire', 'Western Catchfly', 23.5, 96, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Hieracium kalmii L. var. subintegrum (Lepage) Lepage', 'Kalm''s Hawkweed', 96.9, 72, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Sericocarpus asteroides (L.) Britton, Sterns & Poggenb.', 'Toothed Whitetop Aster', 44.9, 38, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Lesquerella lescurii (A. Gray) S. Watson', 'Lescur''s Bladderpod', 36.4, 25, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Macromitrium richardii Schwägr.', 'Richard''s Macromitrium Moss', 99.0, 57, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Rhabdadenia biflora (Jacq.) Müll. Arg.', 'Mangrovevine', 40.6, 63, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Acarospora aeruginosa Hasse', 'Cracked Lichen', 77.0, 84, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Vernonia proctori Urbatsch', 'Proctor''s Ironweed', 3.7, 84, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Lupinus lyallii A. Gray ssp. lyallii var. roguensis Cox', 'Rogue River Lupine', 61.1, 30, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Rousselia humilis (Sw.) Urb.', 'Shineseed', 26.5, 87, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Wedelia acapulcensis Kunth var. hispida (Kunth) Strother', 'Hairy Wedelia', 24.3, 84, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Opuntia fragilis (Nutt.) Haw.', 'Brittle Pricklypear', 77.1, 5, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Stephanandra Siebold & Zucc.', 'Stephanandra', 50.7, 70, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Savia sessiliflora (Sw.) Willd.', 'Amansa Guapo', 70.0, 21, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Peperomia hernandiifolia (Vahl) A. Dietr.', 'West Indian Peperomia', 81.3, 99, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Carex scopulorum T. Holm var. bracteosa (L.H. Bailey) F.J. Herm.', 'Mountain Sedge', 2.9, 72, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Trifolium nanum Torr.', 'Dwarf Clover', 22.6, 82, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Lecidella stigmatea (Ach.) Hertel & Leuckert', 'Lecidella Lichen', 3.3, 23, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Carex ×subviridula Fernald', 'Sedge', 97.6, 29, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Woodsia plummerae Lemmon', 'Plummer''s Cliff Fern', 87.7, 78, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Carphephorus paniculatus (J.F. Gmel.) Herb.', 'Hairy Chaffhead', 4.6, 29, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Sphenoclea Gaertn.', 'Sphenoclea', 67.8, 42, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Coprosma ×molokaiensis H. St. John (pro sp.)', 'Mirrorplant', 99.0, 36, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Narthecium Huds.', 'Asphodel', 10.6, 58, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Salsola kali L. ssp. pontica (Pall.) Mosyakin', 'Russian Thistle', 78.3, 73, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Pterostegia Fisch. & C.A. Mey.', 'Pterostegia', 79.1, 31, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Cephalanthus occidentalis L.', 'Common Buttonbush', 35.4, 83, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Aucuba Thunb.', 'Acuba', 55.6, 55, 1);
insert into test.products (name, cat, price, quantity, user_id) values ('Saxifraga hirculus L. ssp. hirculus', 'Yellow Marsh Saxifrage', 77.1, 89, 1);
