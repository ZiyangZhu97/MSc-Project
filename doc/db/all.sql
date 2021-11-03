INSERT INTO `episode` (id, title, program_id, chapter_id, video, time, charge, sort, created_at, updated_at)
VALUES ('00000001', 'testEpisode01', '00000001', '00000000', '', 500, 'F', 1, now(), now());

INSERT INTO program (id, title, summary, length, price, image, type, charge, status, favorites, sort, created_at, updated_at)
VALUES ('00000001', 'testProgram01', 'a test program', 7200, 19.9, '', 'M', 'C', 'D', 100, 0, now(), now());

insert into `category` (id, parent, name, sort) values ('00000100', '00000000', 'Language', 100);
insert into `category` (id, parent, name, sort) values ('00000101', '00000100', 'Chinese', 101);
insert into `category` (id, parent, name, sort) values ('00000102', '00000100', 'Japanese', 102);
insert into `category` (id, parent, name, sort) values ('00000103', '00000100', 'Engilish', 103);
insert into `category` (id, parent, name, sort) values ('00000104', '00000100', 'Others', 104);

insert into `category` (id, parent, name, sort) values ('00000200', '00000000', 'Genre', 200);
insert into `category` (id, parent, name, sort) values ('00000201', '00000200', 'Action', 201);
insert into `category` (id, parent, name, sort) values ('00000202', '00000200', 'Comedy', 202);
insert into `category` (id, parent, name, sort) values ('00000203', '00000200', 'Romance', 203);



insert into `chapter` (id, program_id, name) values ('00000001', '00000000', 'test01');
insert into `chapter` (id, program_id, name) values ('00000002', '00000000', 'test02');
insert into `chapter` (id, program_id, name) values ('00000003', '00000000', 'test03');
insert into `chapter` (id, program_id, name) values ('00000004', '00000000', 'test04');
insert into `chapter` (id, program_id, name) values ('00000005', '00000000', 'test05');
insert into `chapter` (id, program_id, name) values ('00000006', '00000000', 'test06');
insert into `chapter` (id, program_id, name) values ('00000007', '00000000', 'test07');
insert into `chapter` (id, program_id, name) values ('00000008', '00000000', 'test08');
insert into `chapter` (id, program_id, name) values ('00000009', '00000000', 'test09');
insert into `chapter` (id, program_id, name) values ('00000010', '00000000', 'test10');
insert into `chapter` (id, program_id, name) values ('00000011', '00000000', 'test11');
insert into `chapter` (id, program_id, name) values ('00000012', '00000000', 'test12');
insert into `chapter` (id, program_id, name) values ('00000013', '00000000', 'test13');
insert into `chapter` (id, program_id, name) values ('00000014', '00000000', 'test14');
---------------------- test
insert into `test` (id, name) values (1, 'test');
insert into `test` (id, name) values (2, 'test2');
insert into `test` (id, name) values (4, 'test4');
insert into `test` (id, name) values (3, 'test3');
insert into `test` (id, name) values (5, 'test5');