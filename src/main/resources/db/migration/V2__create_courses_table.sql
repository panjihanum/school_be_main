CREATE TABLE courses (
    id UUID PRIMARY KEY,
    admin_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    effective_date DATE NOT NULL,
    expiry_date DATE,
    CONSTRAINT fk_admin_course FOREIGN KEY (admin_id) REFERENCES users(id)
);