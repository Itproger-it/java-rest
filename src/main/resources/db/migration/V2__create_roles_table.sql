CREATE TABLE "public"."roles" (
    id SERIAL PRIMARY KEY,
    authority VARCHAR(15) NOT NULL CHECK (authority IN ('ROLE_ADMIN', 'ROLE_USER'))
    );

--INSERT INTO "public"."roles" (authority) VALUES
--    ('ROLE_ADMIN'),
--    ('ROLE_USER');
