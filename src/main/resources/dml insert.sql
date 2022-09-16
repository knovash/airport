use airport;

insert into aircarriers(name) values ('Belavia'), ('Aeroflot'), ('Wizzair'), ('Ryanair');

insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (1, 1101, 'Boeing', 100, '2022-05-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (1, 1102, 'Airbus', 200, '2022-06-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (2, 2201, 'Boeing', 100, '2022-07-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (2, 2202, 'Airbus', 300, '2022-08-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (3, 3301, 'Boeing', 200, '2022-09-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (3, 3302, 'Boeing', 300, '2022-05-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (4, 4401, 'Boeing', 400, '2022-06-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (4, 4402, 'Airbus', 500, '2022-07-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (4, 4403, 'Airbus', 200, '2022-08-01');

insert into airports(name) values ('Minsk1'), ('Minsk2');

insert into airstrips(airport_id, number) values (1, 1);
insert into airstrips(airport_id, number) values (1, 2);
insert into airstrips(airport_id, number) values (2, 1);
insert into airstrips(airport_id, number) values (2, 2);
insert into airstrips(airport_id, number) values (2, 3);

insert into directions(country, distance) values ('Poland', 1000);
insert into directions(country, distance) values ('France', 2000);
insert into directions(country, distance) values ('Japan', 9000);
insert into directions(country, distance) values ('USA', 10000);

insert into passports(number) values (1234561);
insert into passports(number) values (1234562);
insert into passports(number) values (1234563);
insert into passports(number) values (1234564);
insert into passports(number) values (1234565);
insert into passports(number) values (1234566);

insert into passengers(passport_id, name) values (1, 'Ivan');
insert into passengers(passport_id, name) values (2, 'Oleg');
insert into passengers(passport_id, name) values (3, 'Olga');
insert into passengers(passport_id, name) values (4, 'Anna');
insert into passengers(passport_id, name) values (5, 'Petr');
insert into passengers(passport_id, name) values (6, 'Denis');

insert into gates(airport_id, number) values (1, 1);
insert into gates(airport_id, number) values (1, 2);
insert into gates(airport_id, number) values (1, 3);
insert into gates(airport_id, number) values (2, 1);
insert into gates(airport_id, number) values (2, 2);
insert into gates(airport_id, number) values (2, 3);

insert into airport_aircarriers(airport_id, aircarrier_id) values (1, 1);
insert into airport_aircarriers(airport_id, aircarrier_id) values (1, 2);
insert into airport_aircarriers(airport_id, aircarrier_id) values (2, 1);
insert into airport_aircarriers(airport_id, aircarrier_id) values (2, 2);
insert into airport_aircarriers(airport_id, aircarrier_id) values (2, 3);
insert into airport_aircarriers(airport_id, aircarrier_id) values (2, 4);

insert into flights(aircraft_id, airstrip_id, direction_id, number, date) values (1, 1, 1, 101, '2022-09-10');
insert into flights(aircraft_id, airstrip_id, direction_id, number, date) values (2, 1, 2, 102, '2022-09-10');
insert into flights(aircraft_id, airstrip_id, direction_id, number, date) values (3, 2, 3, 103, '2022-09-11');
insert into flights(aircraft_id, airstrip_id, direction_id, number, date) values (4, 2, 3, 104, '2022-09-11');
insert into flights(aircraft_id, airstrip_id, direction_id, number, date) values (5, 3, 4, 105, '2022-09-12');

insert into pilots_licenses(number, type) values (12301, 'first pilot');
insert into pilots_licenses(number, type) values (12302, 'second pilot');
insert into pilots_licenses(number, type) values (12303, 'first pilot');
insert into pilots_licenses(number, type) values (12304, 'second pilot');
insert into pilots_licenses(number, type) values (12305, 'first pilot');
insert into pilots_licenses(number, type) values (12306, 'second pilot');
insert into pilots_licenses(number, type) values (12307, 'first pilot');
insert into pilots_licenses(number, type) values (12308, 'second pilot');
insert into pilots_licenses(number, type) values (12309, 'first pilot');
insert into pilots_licenses(number, type) values (12300, 'second pilot');

insert into pilots(pilot_license_id, aircarrier_id, name) values (1, 1, 'Igor');
insert into pilots(pilot_license_id, aircarrier_id, name) values (2, 1, 'Ivan');
insert into pilots(pilot_license_id, aircarrier_id, name) values (3, 2, 'Max');
insert into pilots(pilot_license_id, aircarrier_id, name) values (4, 2, 'Petr');
insert into pilots(pilot_license_id, aircarrier_id, name) values (5, 3, 'Mihail');
insert into pilots(pilot_license_id, aircarrier_id, name) values (6, 3, 'Oleg');
insert into pilots(pilot_license_id, aircarrier_id, name) values (7, 4, 'Ivan');
insert into pilots(pilot_license_id, aircarrier_id, name) values (8, 4, 'Max');
insert into pilots(pilot_license_id, aircarrier_id, name) values (9, 4, 'Victor');
insert into pilots(pilot_license_id, aircarrier_id, name) values (10, 4, 'Igor');

insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (1, 1, 1, 100, 1001, 10);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (1, 2, 1, 100, 1002, 15);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (2, 3, 2, 200, 1003, 22);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (2, 4, 2, 200, 1004, 23);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (4, 5, 3, 300, 1005, 10);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (4, 6, 3, 300, 1006, 11);

insert into pilot_flights(pilot_id, flight_id) values (1, 1); 
insert into pilot_flights(pilot_id, flight_id) values (3, 4);
insert into pilot_flights(pilot_id, flight_id) values (5, 5); 
insert into pilot_flights(pilot_id, flight_id) values (7, 7); 
insert into pilot_flights(pilot_id, flight_id) values (8, 8);