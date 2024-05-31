CREATE TABLE IF NOT EXISTS public.tasks (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    taskUrl TEXT NOT NULL,
    internship_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    appraisal VARCHAR(30),
    CONSTRAINT fk_tasks_internship FOREIGN KEY (internship_id) REFERENCES public.internships (id),
    CONSTRAINT fk_tasks_user FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT ck_tasks_appraisal CHECK (appraisal IN ('зачет', 'незачет'))
);
