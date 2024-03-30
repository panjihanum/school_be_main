CREATE TABLE forums (
    id UUID PRIMARY KEY,
    teacher_id UUID NOT NULL,
    course_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    effective_date DATE NOT NULL,
    expiry_date DATE,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES users(id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE comments (
    id UUID PRIMARY KEY,
    forum_id UUID NOT NULL,
    student_id UUID NOT NULL,
    comment_text TEXT NOT NULL,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_forum FOREIGN KEY (forum_id) REFERENCES forums(id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES users(id)
);