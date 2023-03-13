DELETE FROM BOOKINGS;
DELETE FROM CARS;
DELETE FROM USERS;
--- Test Data for: USERS ---
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'cblakden0', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Carson', 'Blakden');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'mjurkowski1', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Margit', 'Jurkowski');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'nleeburne2', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Norry', 'Leeburne');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'dfolini3', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Danyelle', 'Folini');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'ealcorn4', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Eward', 'Alcorn');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'lskyme5', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Leif', 'Skyme');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'hfochs6', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Harold', 'Fochs');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'mritelli7', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Mallorie', 'Ritelli');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'gtolcher8', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Gav', 'Tolcher');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'htwidale9', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Hillary', 'Twidale');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'ggarnula', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Gram', 'Garnul');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'hblenkharnb', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Haily', 'Blenkharn');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'ahegelc', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Abdul', 'Hegel');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'dgobelld', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Damien', 'Gobell');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'glordene', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Gard', 'Lorden');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'mblownf', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Morganne', 'Blown');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'kcrosierg', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Kasey', 'Crosier');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'ccollabineh', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Cammie', 'Collabine');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'rpickburni', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Regine', 'Pickburn');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'kbeckhousej', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Katha', 'Beckhouse');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'tsacksk', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Tobin', 'Sacks');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'stiltmanl', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Shawn', 'Tiltman');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'mjallinm', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Malvin', 'Jallin');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'jmorrowen', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Jacquenetta', 'Morrowe');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'lhaydocko', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Livy', 'Haydock');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'weberstp', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Willamina', 'Eberst');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'hcharlewoodq', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Harland', 'Charlewood');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'gpashr', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Gregor', 'Pash');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'dsantellos', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Dorise', 'Santello');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'rwoodyeart', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Roberta', 'Woodyear');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'ncaplisu', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Norman', 'Caplis');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'lvanbrughv', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Lucita', 'VanBrugh');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'dlideardw', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Dulcea', 'Lideard');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'jrollasonx', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Janey', 'Rollason');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'bespinazoy', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Burnard', 'Espinazo');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'randrewsz', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Raf', 'Andrews');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'lleander10', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Leticia', 'Leander');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'nvondruska11', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Noam', 'Vondruska');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'amerrifield12', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Ashien', 'Merrifield');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'btripe13', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Bevvy', 'Tripe');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'sdudney14', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Sandye', 'Dudney');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'pjerrems15', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Pansie', 'Jerrems');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'hdarrington16', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Hillery', 'Darrington');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'usoutheran17', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Ulrich', 'Southeran');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'dgianneschi18', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Dimitry', 'Gianneschi');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'ncatt19', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Neddie', 'Catt');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'qhasney1a', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Quinlan', 'Hasney');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'sshevelin1b', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Sabine', 'Shevelin');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'tsivil1c', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Torry', 'Sivil');
insert into USERS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_USERNAME, HASH_PASSWORD, TXT_FIRST_NAME, TXT_LAST_NAME) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'bfiveash1d', '$2a$10$9n2wekM9H0cPb9I558IN1.nTTYDg3ABvTbW.TBwCYb8C1ka8dQ5tm', 'Brigida', 'Fiveash');

--- Test Data for: CARS ---

insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Mitsubishi', 'Galant', 2009, 2362469, 2, 'IDR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Volkswagen', 'New Beetle', 2001, 1095919, 2, 'DOP');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'BMW', '5 Series', 2012, 2620637, 2, 'MYR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'GMC', 'Savana 3500', 2002, 1810382, 2, 'PKR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Acura', 'MDX', 2010, 1303307, 2, 'CZK');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Jeep', 'Wrangler', 2009, 2773847, 2, 'CUP');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Chevrolet', 'Corvette', 2001, 1929978, 2, 'PEN');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Dodge', 'Colt', 1989, 1046457, 2, 'EUR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Honda', 'CR-V', 2012, 2415925, 2, 'CNY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Audi', 'A6', 2001, 1531141, 2, 'NIO');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Lexus', 'LX', 2009, 1634387, 2, 'PEN');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Lincoln', 'Navigator L', 2007, 2575476, 2, 'IDR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Mercedes-Benz', 'CL-Class', 2005, 1968184, 2, 'VEF');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Pontiac', 'Grand Prix', 1998, 2076297, 2, 'NGN');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Saab', '9-7X', 2009, 2748537, 2, 'RUB');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Saab', '9-7X', 2006, 1012983, 2, 'RUB');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Pontiac', 'Bonneville', 1996, 1954186, 2, 'EUR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Ford', 'Escort', 1991, 2129958, 2, 'JPY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Dodge', 'Neon', 2005, 1100414, 2, 'EGP');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Nissan', 'Xterra', 2010, 1439874, 2, 'EUR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Mercedes-Benz', 'S-Class', 1993, 1797659, 2, 'CNY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Jeep', 'Liberty', 2011, 1225349, 2, 'CNY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Nissan', 'Pathfinder', 2009, 1579567, 2, 'CNY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Chevrolet', 'Silverado 1500', 2010, 1574307, 2, 'IDR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'GMC', 'Savana 3500', 2010, 2116339, 2, 'IDR');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Mercedes-Benz', 'C-Class', 2004, 2892393, 2, 'MNT');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Honda', 'Accord', 2004, 1276538, 2, 'CNY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Ram', '2500', 2011, 1843619, 2, 'THB');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Suzuki', 'XL-7', 2001, 1080856, 2, 'JPY');
insert into CARS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TXT_BRAND, TXT_MODEL, NUM_CONSTRUCTION_YEAR, BI_PRICE, INT_PRECISION, TXT_CURRENCY) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', 'Plymouth', 'Grand Voyager', 1998, 2181545, 2, 'EUR');

--- Test Data for: BOOKINGS
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2024-01-25 07:59:02', 'BOOKED', 2622896, 2, 18, 31);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-11-03 14:04:01', 'BOOKED', 2718293, 2, 9, 34);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-10-22 14:18:56', 'BOOKED', 2183717, 2, 6, 29);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2024-04-01 10:09:22', 'BOOKED', 2395959, 2, 16, 17);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-07-06 20:07:19', 'BOOKED', 1820084, 2, 3, 49);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-10-27 06:28:12', 'BOOKED', 1230403, 2, 2, 7);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-10-31 05:06:58', 'BOOKED', 1962135, 2, 19, 40);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-11-11 07:11:02', 'BOOKED', 1902816, 2, 22, 8);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-10-22 02:42:43', 'BOOKED', 2240839, 2, 20, 46);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-07-27 18:50:18', 'BOOKED', 2379740, 2, 7, 35);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2024-01-05 07:02:22', 'BOOKED', 1945445, 2, 21, 4);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-06-22 12:35:12', 'BOOKED', 1725493, 2, 13, 44);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2024-01-13 20:01:46', 'BOOKED', 2195396, 2, 15, 10);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-10-11 06:26:14', 'BOOKED', 2357579, 2, 11, 50);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2024-04-26 04:51:48', 'BOOKED', 2048882, 2, 8, 24);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2024-01-12 17:46:49', 'BOOKED', 2669144, 2, 17, 45);
insert into BOOKINGS (VERSION, TST_CREATED_ON, TST_UPDATED_ON, TST_BOOKED_UNTIL, TXT_BOOKING_STATUS, BI_BOOKING_PRICE, INT_PRECISION, ID_CAR, ID_USER) values (1, '2023-03-11 00:00:00', '2023-03-11 00:00:00', '2023-05-11 17:24:09', 'BOOKED', 1994982, 2, 4, 9);

