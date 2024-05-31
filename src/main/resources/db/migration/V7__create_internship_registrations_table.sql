CREATE TABLE "public"."internship_registrations" (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    internship_id INTEGER NOT NULL,
    registration_date DATE DEFAULT CURRENT_DATE,
    CONSTRAINT intern_id FOREIGN KEY (internship_id) REFERENCES "public"."internships" (id) ON DELETE CASCADE,
    CONSTRAINT users_id FOREIGN KEY (user_id) REFERENCES "public"."users" (id) ON DELETE CASCADE
);
