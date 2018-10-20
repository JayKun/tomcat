-- select database
USE CS144;

-- drop existing tables
DROP TABLE IF EXISTS Posts;
DROP TABLE IF EXISTS UserId;

-- create table Posts
CREATE TABLE Posts(
  username	VARCHAR(40),
  postid	INTEGER,
  title		VARCHAR(100),
  body		TEXT,
  modified	TIMESTAMP DEFAULT '2000-01-01 00:00:00',
  created	TIMESTAMP DEFAULT '2000-01-01 00:00:00',
  PRIMARY KEY(username, postid)
);

-- create table UserId
CREATE TABLE UserId(
  username	VARCHAR(40),
  postid	INTEGER
);

