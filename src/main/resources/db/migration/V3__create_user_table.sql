CREATE TABLE "public"."users" (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    telegram_id VARCHAR(255) NOT NULL,
    about_info TEXT,
    date_of_birth DATE NOT NULL,
    city VARCHAR(50) NOT NULL,
    education_status VARCHAR(50) NOT NULL CHECK (education_status IN ('студент', 'закончил обучение', 'не имею профильного образования')),
    university VARCHAR(150),
    faculty VARCHAR(150),
    specialty VARCHAR(150),
    course INTEGER CHECK (course <= 6 AND course > 0)
);

--INSERT INTO "user" (
--    first_name, last_name, middle_name, email, phone_number,
--    username, telegram_id, about_info, date_of_birth, city,
--    education_status, university, faculty, specialty, course
--) VALUES
--(
--    'Иван', 'Иванов', 'Иванович', 'ivan@example.com', '+79991234567',
--    'ivanov_ivan', '@ivanov_ivan', 'Описание Ивана', '1990-01-01', 'Москва',
--    'студент', 'МГУ', 'Физический', 'Физика', 3
--),
--(
--    'Петр', 'Петров', 'Петрович', 'petr@example.com', '+79992345678',
--    'petrov_petr', '@petrov_petr', 'Описание Петра', '1995-02-02', 'Санкт-Петербург',
--    'закончил обучение', 'СПбГУ', 'Экономический', 'Экономика', 6
--);
