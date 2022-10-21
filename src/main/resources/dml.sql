use airport;

insert into aircarriers(name) values ('Belavia'), ('Aeroflot'), ('Wizzair'), ('Ryanair');

insert into aircrafts(aircarrier_id, number, model) values (1, 1101, 'Boeing');
insert into aircrafts(aircarrier_id, number, model) values (2, 2201, 'Airbus');
insert into aircrafts(aircarrier_id, number, model) values (3, 3301, 'Boeing');
insert into aircrafts(aircarrier_id, number, model) values (4, 4401, 'Airbus');
insert into aircrafts(aircarrier_id, number, model) values (1, 1102, 'Embraer');
insert into aircrafts(aircarrier_id, number, model) values (2, 2202, 'Embraer');
insert into aircrafts(aircarrier_id, number, model) values (3, 3302, 'Embraer');
insert into aircrafts(aircarrier_id, number, model) values (4, 4402, 'Embraer');
insert into aircrafts(aircarrier_id, number, model) values (1, 1103, 'TU');
insert into aircrafts(aircarrier_id, number, model) values (2, 2203, 'SU');
insert into aircrafts(aircarrier_id, number, model) values (3, 3303, 'TU');
insert into aircrafts(aircarrier_id, number, model) values (4, 4403, 'IL');

insert into airports(name) values ('Minsk1'), ('Minsk2');

insert into airstrips(airport_id, number) values (1, 1);
insert into airstrips(airport_id, number) values (1, 2);
insert into airstrips(airport_id, number) values (2, 1);
insert into airstrips(airport_id, number) values (2, 2);

insert into directions(country, distance) values ('Poland', 1000), ('France', 2000), ('Japan', 9000), ('USA', 10000);

insert into gates(airport_id, number) values (1, 1);
insert into gates(airport_id, number) values (1, 2);
insert into gates(airport_id, number) values (2, 1);
insert into gates(airport_id, number) values (2, 2);

insert into airport_aircarriers(airport_id, aircarrier_id) values (1, 1);
insert into airport_aircarriers(airport_id, aircarrier_id) values (1, 2);
insert into airport_aircarriers(airport_id, aircarrier_id) values (2, 3);
insert into airport_aircarriers(airport_id, aircarrier_id) values (2, 4);



insert into pilots(aircarrier_id, name) values (1, 'Igor');
insert into pilots(aircarrier_id, name) values (1, 'Ivan');
insert into pilots(aircarrier_id, name) values (2, 'Max');
insert into pilots(aircarrier_id, name) values (2, 'Petr');
insert into pilots(aircarrier_id, name) values (3, 'Mihail');
insert into pilots(aircarrier_id, name) values (3, 'Oleg');
insert into pilots(aircarrier_id, name) values (4, 'Dima');
insert into pilots(aircarrier_id, name) values (4, 'Alex');

insert into passports(number) values (1234561);
insert into passports(number) values (1234562);
insert into passports(number) values (1234563);
insert into passports(number) values (1234564);
insert into passports(number) values (1234565);
insert into passports(number) values (1234566);
insert into passports(number) values (1234567);
insert into passports(number) values (1234568);

insert into passengers(name, passport_id) values ('Ivan', 1);
insert into passengers(name, passport_id) values ('Oleg', 2);
insert into passengers(name, passport_id) values ('Olga', 3);
insert into passengers(name, passport_id) values ('Anna', 4);
insert into passengers(name, passport_id) values ('Petr', 5);
insert into passengers(name, passport_id) values ('Denis', 6);
insert into passengers(name, passport_id) values ('Zina', 7);
insert into passengers(name, passport_id) values ('Alex', 8);

insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, pilot_id, number, date) values (1, 1, 1, 1, 1, 101, '2022-09-11');
insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, pilot_id, number, date) values (2, 2, 2, 2, 2, 102, '2022-09-12');
insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, pilot_id, number, date) values (3, 3, 3, 3, 3, 103, '2022-09-13');
insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, pilot_id, number, date) values (4, 4, 4, 4, 4, 104, '2022-09-14');


insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (1, 1, 1, 1100, 11);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (1, 2, 1, 1100, 12);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (2, 3, 2, 1200, 22);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (2, 4, 2, 1200, 23);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (3, 5, 3, 1300, 31);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (3, 6, 3, 1300, 32);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (4, 7, 4, 1400, 44);
insert into tickets(flight_id, passenger_id, gate_id, price, seat) values (4, 8, 4, 1400, 45);
