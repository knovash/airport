use airport;

insert into aircarriers(name) values ('Belavia'), ('Aeroflot'), ('Wizzair'), ('Ryanair');

insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (1, 1101, 'Boeing', 100, '2022-05-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (2, 2201, 'Airbus', 200, '2022-06-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (3, 3301, 'Boeing', 300, '2022-07-01');
insert into aircrafts(aircarrier_id, number, model, seats, service_date) values (4, 4401, 'Airbus', 400, '2022-08-01');

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

insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, number, date) values (1, 1, 1, 1, 101, '2022-09-11');
insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, number, date) values (2, 2, 2, 2, 102, '2022-09-12');
insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, number, date) values (3, 3, 3, 3, 103, '2022-09-13');
insert into flights(aircarrier_id, aircraft_id, airstrip_id, direction_id, number, date) values (4, 4, 4, 4, 104, '2022-09-14');

insert into pilots_licenses(number, type) values (12301, 'first pilot');
insert into pilots_licenses(number, type) values (12302, 'second pilot');
insert into pilots_licenses(number, type) values (12303, 'first pilot');
insert into pilots_licenses(number, type) values (12304, 'second pilot');
insert into pilots_licenses(number, type) values (12305, 'first pilot');
insert into pilots_licenses(number, type) values (12306, 'second pilot');
insert into pilots_licenses(number, type) values (12307, 'first pilot');
insert into pilots_licenses(number, type) values (12308, 'second pilot');

insert into pilots(pilot_license_id, aircarrier_id, name) values (1, 1, 'Igor');
insert into pilots(pilot_license_id, aircarrier_id, name) values (2, 1, 'Ivan');
insert into pilots(pilot_license_id, aircarrier_id, name) values (3, 2, 'Max');
insert into pilots(pilot_license_id, aircarrier_id, name) values (4, 2, 'Petr');
insert into pilots(pilot_license_id, aircarrier_id, name) values (5, 3, 'Mihail');
insert into pilots(pilot_license_id, aircarrier_id, name) values (6, 3, 'Oleg');
insert into pilots(pilot_license_id, aircarrier_id, name) values (7, 4, 'Dima');
insert into pilots(pilot_license_id, aircarrier_id, name) values (8, 4, 'Alex');

insert into pilot_flights(pilot_id, flight_id) values (1, 1); 
insert into pilot_flights(pilot_id, flight_id) values (2, 1);
insert into pilot_flights(pilot_id, flight_id) values (3, 2); 
insert into pilot_flights(pilot_id, flight_id) values (4, 2); 
insert into pilot_flights(pilot_id, flight_id) values (5, 3);
insert into pilot_flights(pilot_id, flight_id) values (6, 3);
insert into pilot_flights(pilot_id, flight_id) values (7, 4);
insert into pilot_flights(pilot_id, flight_id) values (8, 4);

insert into passports(number) values (1234561);
insert into passports(number) values (1234562);
insert into passports(number) values (1234563);
insert into passports(number) values (1234564);
insert into passports(number) values (1234565);
insert into passports(number) values (1234566);
insert into passports(number) values (1234567);
insert into passports(number) values (1234568);

insert into passengers(passport_id, name) values (1, 'Ivan');
insert into passengers(passport_id, name) values (2, 'Oleg');
insert into passengers(passport_id, name) values (3, 'Olga');
insert into passengers(passport_id, name) values (4, 'Anna');
insert into passengers(passport_id, name) values (5, 'Petr');
insert into passengers(passport_id, name) values (6, 'Denis');
insert into passengers(passport_id, name) values (7, 'Zina');
insert into passengers(passport_id, name) values (8, 'Alex');

insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (1, 1, 1, 1100, 1001, 11);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (1, 2, 1, 1100, 1002, 12);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (2, 3, 2, 1200, 1003, 22);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (2, 4, 2, 1200, 1004, 23);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (3, 5, 3, 1300, 1005, 31);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (3, 6, 3, 1300, 1006, 32);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (4, 7, 4, 1400, 1007, 44);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (4, 8, 4, 1400, 1008, 45);


-- 10 запросов каждой операции (update, delete)

update directions set country = 'India' where country = 'Iran';         
-- заменить название страны Иран на Индия
update directions set country = 'Iran' where id = 13;                   
-- замнить название страны на Иран в ид=13
update directions set distance = 777 where country = 'Japan';           
-- заменить все если старана Япония расстояние на 777
update directions set distance = 777 where distance = 1000;             
-- заменить все расстояния равные 1000 на 77
update directions set distance = 33 where id > 2 and distance = 777;    
-- заменить расстояние на 33 все ид после 2 и если расстояние =777
update aircrafts set model = 'Embraer' where model = 'Airbus';
update aircrafts set seats = 550 where model = 'Embraer';
update aircrafts set service_date ='2022-08-22' where id = 3;
update aircrafts set service_date ='2022-08-12' where service_date ='2022-08-22';

delete from directions where distance > 2000;
delete from passengers where name = 'Petr';
delete from passengers where id = 2 or id > 6;

delete from airports where id = 1; 
-- удалился один аэропорт, две полосы, два полета, два гейта, у 4х пилотов полеты, 4 билета, аэропорт-перевозчик два.
-- неудалились: перевозчики, самолеты, направления, пассажиры, пилоты.

delete from flights where id = 1; 
-- удален один полет, у двух пилотов удалились полеты, удалились все два билета на этот полет

delete from tickets where id = 1; 
-- удален один билет

delete from aircarriers where id = 1; 
-- удален один перевозчик, удален его самолет, аэропорт-перевозчик удален один, полет перевозчика, два пилота перевозчика, полеты двух пилотов, удалены билеты на полеты перевозчика.
-- неудалены: лицензии пилотов

delete from aicrafts where id = 1; 
-- удален самолет, удален полет этого самолета, у 2х пилотов удалены полеты, удалены билеты на полеты этого самолета.

delete from direction where id = 1; 
-- удалено направление, удален полет в этом направлении, у двух пилотов удалены полеты, удалены 2 билета.

delete from gates where id=1; 
-- удален выход, удалились билеты через этот выход.

delete from passengers where id=1; 
-- удален пассажир, удален его билет.
-- пасспорт не удален.

delete from pilots where id=1; 
-- удален пилот, пилот-полет удален его полет.

delete from airstrips where id=1; 
-- удалена полоса. удален полет с этой полосы. пилот-полет удален. удалены билеты на полет с этой полосы.


-- по 5 запросов на обычный select, с where (хотя бы 1 запрос с составным условием and/or, с импользованием is null/is not null, in, order by)

select * from flights;
select id, number, date from flights;
select id, number, date as date_of_flight from flights;
select id as flight_id, number as flight_name, date as flight_date from flights;
select id as flight_id, number as flight_name, date as flight_date from flights where id > 2;

insert into aircarriers(name) values ('S7'), ('Amirates');
-- добавим два перевозчика
update aircarriers set name = 'Emirates' where name = 'Amirates';

select 
c.id as carrier_id, c.name as carrier, 
p.id as pilot_id, p.name as pilot
from aircarriers c inner join pilots p on p.aircarrier_id = c.id;
-- получим список перевозчиков (только с пилотами) и пилотов в них

select 
c.id as carrier_id, c.name as carrier, 
p.id as pilot_id, p.name as pilot
from aircarriers c left join pilots p on p.aircarrier_id = c.id;
-- получим список всех перевозчиков (и тех в которых нет пилотов) и пилотов в них

insert into pilots(name) values ('John new'), ('Victor new');
-- невозможно добавить двух пилотов. потому что у лицензии нет дефолтного значения. надо создать лицензии

select 
l.id as license_id, l.number as license_umber, l.type as license_type, 
p.id as pilot_id, p.name as pilot_name
from pilots_licenses l inner join pilots p on p.pilot_license_id = l.id;
-- показать все лицензии пилотов

insert into pilots_licenses(number, type) values (22301, 'first pilot'), (22302, 'second pilot'); 
-- добавил две лицензии

select * from pilots_licenses;
-- показывает 10 лицензий (две новые)

select 
l.id as license_id, l.number as license_umber, l.type as license_type, 
p.id as pilot_id, p.name as pilot_name
from pilots_licenses l inner join pilots p on p.pilot_license_id = l.id;
-- показывает 8 лицензий у которые есть у пилотов

select 
l.id as license_id, l.number as license_umber, l.type as license_type, 
p.id as pilot_id, p.name as pilot_name
from pilots_licenses l left join pilots p on p.pilot_license_id = l.id;
-- показывает 10 лицензий. две никому не принадлежат

insert into pilots(name, pilot_license_id, aircarrier_id) values ('John new', 9, 5), ('Victor new', 10, 6);
-- добавляем двух пилотов. используя две новые лицензии в две новые авиакомпании S7 и Emirates

select 
c.id as carrier_id, c.name as carrier, 
p.id as pilot_id, p.name as pilot
from aircarriers c left join pilots p on p.aircarrier_id = c.id;
-- показывает всех перевозчиков и их пилотов. теперь два новых перевозчика с новыми пилотами

select 
p.id as pilot_id, p.name as pilot,
f.id as flight_id
from pilot_flights f left join pilots p on p.id = f.id;
-- показать пилотов и их полеты. только пилоты у которых есть полеты

select 
p.id as pilot_id, p.name as pilot,
f.id as flight_id, f.flight_id as flight_number_id
from pilot_flights f right join pilots p on p.id = f.id;
-- всех пилотов и их полеты. id номеров полетов. пилоты без полетов полет=null

select 
p.id as pilot_id, p.name as pilot,
f.id as flight_id, f.flight_id as flight_number_id
from pilot_flights f right join pilots p on p.id = f.id
where f.flight_id = 2;
-- показать список пилотов и id номеров полетов (не номер полета)
-- два пилота для полета id 2

select 
p.id as pilot_id, p.name as pilot,
f.id as flight_id, f.flight_id as flight_number_id
from pilot_flights f right join pilots p on p.id = f.id
where p.name like '%i%';

select 
p.id as pilot_id, p.name as pilot,
f.id as flight_id, f.flight_id as flight_number_id
from pilot_flights f right join pilots p on p.id = f.id
where f.flight_id in (2,4);

select 
p.id as pilot_id, p.name as pilot,
f.id as flight_id, f.flight_id as flight_number_id
from pilot_flights f right join pilots p on p.id = f.id
where f.flight_id is null;

select * from directions order by directions.country asc;
select * from directions order by directions.distance asc;
select aircarrier_id, model, seats, service_date from aircrafts order by aircrafts.model asc;
select aircarrier_id, model, seats, service_date from aircrafts order by aircrafts.service_date desc;
select aircarrier_id, model, seats, service_date from aircrafts order by aircrafts.model asc, aircrafts.service_date desc;
-- сортировка order by

select count(model) from aircrafts where model = 'Airbus';
-- подсчет count. два эирбаса


-- с inner join, с left join (хотя бы 1 запрос с минимум 3-мя join-ми), group by – having. 

select pilots.id as pilot_id, pilots.name as pilot, pilot_flights.flight_id as flight_id
from pilot_flights left join pilots on pilots.id = pilot_flights.id;
-- список всех пилотов и ид их полетов. только пилоты у которых есть полеты

select pilots.id as pilot_id, pilots.name as pilot, pilot_flights.flight_id as flight_id
from pilot_flights right join pilots on pilots.id = pilot_flights.id;
-- список всех пилотов с id полетов. у пилотов без полетов id null

select 
passengers.name as passengers_name, 
tickets.seat as seat,
flights.number as flight_number,
flights.date as flight_date,
aircarriers.name as carrier,
airport_aircarriers.airport_id as airport_id,
airports.name as airport
from passengers 
join tickets on passengers.id = tickets.id
join flights on tickets.flight_id = flights.id
join aircarriers on flights.aircarrier_id = aircarriers.id
join airport_aircarriers on aircarriers.id = airport_aircarriers.id
join airports on airport_aircarriers.airport_id = airports.id;
-- список пассажиров, место, номер полета, дата, перевозчик, из аэропорта.

-- тоже самое сокращенно:

select 
p.name as passengers_name, 
t.seat as seat,
f.number as flight_number,
f.date as flight_date,
c.name as carrier,
pc.airport_id as airport_id,
a.name as airport
from passengers p
join tickets t on p.id = t.id
join flights f on t.flight_id = f.id
join aircarriers c on f.aircarrier_id = c.id
join airport_aircarriers pc on c.id = pc.id
join airports a on pc.airport_id = a.id;

-- добавлю еще пассажиров
insert into passports(number) values (2234561);
insert into passports(number) values (2234562);
insert into passports(number) values (2234563);
insert into passports(number) values (2234564);
insert into passports(number) values (2234565);
insert into passengers(passport_id, name) values (9, 'Ivan new');
insert into passengers(passport_id, name) values (10, 'Oleg new');
insert into passengers(passport_id, name) values (11, 'Olga new');
insert into passengers(passport_id, name) values (12, 'Anna new');
insert into passengers(passport_id, name) values (13, 'Petr new');
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (2, 9, 2, 1200, 1009, 28);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (3, 10, 3, 1300, 1010, 38);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (3, 11, 3, 1300, 1011, 37);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (4, 12, 4, 1400, 1012, 41);
insert into tickets(flight_id, passenger_id, gate_id, price, number, seat) values (4, 13, 4, 1400, 10013, 42);


select 
c.name as carrier,
count(p.id) as passengers_count
from passengers p
join tickets t on p.id = t.id
join flights f on t.flight_id = f.id
join aircarriers c on f.aircarrier_id = c.id
join airport_aircarriers pc on c.id = pc.id
join airports a on pc.airport_id = a.id group by c.id;
-- подсчет. список перевозчиков и количество пассажиров у каждого перевозчика 2,3,4,4

select 
c.name as carrier,
count(p.id) as passengers_count
from passengers p
join tickets t on p.id = t.id
join flights f on t.flight_id = f.id
join aircarriers c on f.aircarrier_id = c.id
join airport_aircarriers pc on c.id = pc.id
join airports a on pc.airport_id = a.id group by c.id having passengers_count >3;
-- список перевозчиков у которых пассажиров больше трех

