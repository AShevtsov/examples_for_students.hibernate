CREATE TABLE users (
	user_id SERIAL primary key,
	name varchar(50) not null,
	surname varchar(100) not null,
	age int
);

CREATE SEQUENCE group_id_seq;

CREATE TABLE groups (
	group_id integer primary key default nextval('group_id_seq'),
	name varchar(50) not null
);

CREATE TABLE users_groups (
	user_id integer REFERENCES users(user_id) ON DELETE CASCADE,
	group_id integer REFERENCES groups(group_id) ON DELETE RESTRICT
);

CREATE SEQUENCE message_id_seq;

CREATE TABLE messages (
    message_id integer primary key default nextval('message_id_seq'),
    user_id integer references users(user_id),
    text varchar(200) not null,
    time_of_message timestamp
);