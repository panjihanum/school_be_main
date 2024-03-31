CREATE TABLE chat_messages (
    id UUID PRIMARY KEY,
    content TEXT,
    sender_id UUID,
    forum_id UUID,
    timestamp TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (forum_id) REFERENCES forums(id)
);