insert into users(user_id, username, password, enabled) values ('1', 'apascualco','qwerty', true);
insert into users(user_id, username, password, enabled) values	('2', 'alberto','qwerty', true);

insert into roles(role_id, user_id, rol) values ('1', '1','ROLE_USER');
insert into roles(role_id, user_id, rol) values ('2', '1','ROLE_ADMIN');
insert into roles(role_id, user_id, rol) values ('3', '2','ROLE_USER');
