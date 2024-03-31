CREATE TABLE forums (
    id UUID PRIMARY KEY,
    teacher_id UUID NOT NULL,
    course_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    effective_date DATE NOT NULL,
    expiry_date DATE,
    is_active boolean default true,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES users(id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id)
);