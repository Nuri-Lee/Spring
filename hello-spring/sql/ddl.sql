drop table if exists member CASCADE;
create table member
(
    id bigint generated  by default as identity,
    name varchar(255),
    primary key (id)
);

/* bigint == long 타입 */

insert into member(name) values('spring');

select * from member;