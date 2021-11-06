-- Users
INSERT INTO users (name, surname, email) VALUES ('Petra', 'Kos', 'petra.kos@gmail.com');
INSERT INTO users (name, surname, email) VALUES ('Andraž', 'Andolšek Peklaj', 'andolsek.peklaj.a@gmail.com');
INSERT INTO users (name, surname, email) VALUES ('Žan', 'Oberstar', 'tofi.je.zakon@gmail.com');

-- Cities
INSERT INTO cities (name) VALUES ('Ljubljana');
INSERT INTO cities (name) VALUES ('Maribor');
INSERT INTO cities (name) VALUES ('Celje');

-- Station Locations
INSERT INTO stationLocations (cityId, address, xCoordinate, yCoordinate) VALUES (1, 'Ulica 1', 12.345, 12.345);
INSERT INTO stationLocations (cityId, address, xCoordinate, yCoordinate) VALUES (2, 'Ulica 2', 23.456, 23.456);
INSERT INTO stationLocations (cityId, address, xCoordinate, yCoordinate) VALUES (3, 'Ulica 3', 34.567, 34.567);

-- Owners
INSERT INTO owners (name, surname, email) VALUES ('Janez', 'Petrovic', 'janez.petrovic@gmail.com');
INSERT INTO owners (name, surname, email) VALUES ('Elon', 'Musk', 'musk.elon@tesla.com');
INSERT INTO owners (name, surname, email) VALUES ('Steve', 'Jobs', 'steve.jobs@microsoft.com');

-- Stations
INSERT INTO stations (stationName, ownerId, openTime, closeTime, price, wattage, adapterType, locationId) VALUES ('Station 1', 1, '06:00:00', '22:00:00', 0.60, 60000, 'Type 2', 1);
INSERT INTO stations (stationName, ownerId, openTime, closeTime, price, wattage, adapterType, locationId) VALUES ('Station 2', 2, '07:00:00', '23:00:00', 0.38, 50000, 'Type 2', 2);
INSERT INTO stations (stationName, ownerId, openTime, closeTime, price, wattage, adapterType, locationId) VALUES ('Station 3', 3, '08:00:00', '24:00:00', 0.25, 22000, 'Type 1', 3);

-- Charges
INSERT INTO charges (userId, stationId, beginTime, endTime, price) VALUES (1, 1, '08:00:00', '09:00:00', 20.5);
INSERT INTO charges (userId, stationId, beginTime, endTime, price) VALUES (2, 2, '08:00:00', '09:00:00', 21.5);
INSERT INTO charges (userId, stationId, beginTime, endTime, price) VALUES (3, 3, '08:00:00', '09:00:00', 22.5);

-- Reservations
INSERT INTO reservations (userId, stationId, reservationTime) VALUES (1, 2, '12:00:00');
INSERT INTO reservations (userId, stationId, reservationTime) VALUES (2, 3, '14:00:00');
INSERT INTO reservations (userId, stationId, reservationTime) VALUES (3, 1, '08:00:00');


