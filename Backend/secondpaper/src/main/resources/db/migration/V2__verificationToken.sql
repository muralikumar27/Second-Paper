CREATE TYPE token_type as ENUM (
    'REGISTRATION',
    'FORGOT_PASSWORD'
);

CREATE TABLE verification_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    token varchar(255),
    type token_type NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users_table(id) ON DELETE CASCADE
);