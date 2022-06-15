create database memory_game;

create table leaderboard (
	ranking int primary key,
    username varchar(18) ,
    score int
);