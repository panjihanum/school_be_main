CREATE TABLE courses (
    id UUID PRIMARY KEY,
    teacher_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    effective_date DATE NOT NULL,
    expiry_date DATE,
    is_active boolean default true,
    CONSTRAINT fk_teacher_course FOREIGN KEY (teacher_id) REFERENCES users(id)
);

CREATE TABLE course_enrollments (
    course_id UUID NOT NULL,
    user_id UUID NOT NULL,
    is_active boolean NOT NULL default false,
    PRIMARY KEY (course_id, user_id),
    CONSTRAINT fk_course_enrollment_course FOREIGN KEY (course_id) REFERENCES courses(id),
    CONSTRAINT fk_course_enrollment_user FOREIGN KEY (user_id) REFERENCES users(id)
);