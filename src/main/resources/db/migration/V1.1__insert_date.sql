INSERT INTO users(name, surname, age) VALUES
    ('Alex', 'Shevtsov', 25),
    ('Sergey', 'Kozlov', null);

INSERT INTO groups(name) VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO users_groups(group_id, user_id)
    SELECT group_id, 1 FROM groups WHERE name like 'ADMIN'
    UNION ALL
    SELECT group_id, 1 FROM groups WHERE name like 'USER'
    UNION ALL
    SELECT group_id, 2 FROM groups WHERE name like 'USER';

INSERT INTO messages(user_id, text, time_of_message) values
    (1, 'Hello, Sergey!', current_timestamp - (1 ||' minutes')::interval);

INSERT INTO messages(user_id, text, time_of_message) values
    (2, 'Hello, Alex!', current_timestamp);