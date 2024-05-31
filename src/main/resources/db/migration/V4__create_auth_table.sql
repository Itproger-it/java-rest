CREATE TABLE auth (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    role_id INT NOT NULL DEFAULT 2,
    user_id INT NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "users" (id) ON DELETE CASCADE
);

--INSERT INTO auth (login, password, role_id, user_id)
--VALUES
--    ('user1', 'password1', 1, 1),
--    ('user2', 'password2', 2, 2),