CREATE TYPE block_type AS ENUM (
    'PAGE',
    'TODO',
    'BULLETED_LIST',
    'NUMBERED_LIST',
    'HEADING_1',
    'HEADING_2',
    'HEADING_3',
    'HEADING_4'
);

CREATE TYPE vault_type AS ENUM (
    'DEFAULT',
    'PRIVATE',
    'NON_PRIVATE'
);

CREATE TYPE vault_role AS ENUM (
    'OWNER',
    'MEMBER',
    'VIEWER'
);

CREATE TABLE users_table (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(100) NOT NULL,
    password TEXT NOT NULL,
    email VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE work_vaults (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    created_by UUID NOT NULL,
    CONSTRAINT created_by_fk FOREIGN KEY (created_by) REFERENCES users_table(id) ON DELETE CASCADE
);

CREATE TABLE team_vaults (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    type vault_type NOT NULL,
    created_at DATE NOT NULL,
    created_by UUID NOT NULL,
    work_vault_id UUID NOT NULL,
    CONSTRAINT created_by_fk FOREIGN KEY (created_by) REFERENCES users_table(id),
    CONSTRAINT work_vault_fk FOREIGN KEY (work_vault_id) REFERENCES work_vaults(id)
);

CREATE TABLE data_blocks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    properties JSONB NOT NULL,
    created_at DATE NOT NULL,
    last_modified_at DATE NOT NULL,
    type block_type NOT NULL,
    created_by UUID NOT NULL,
    parent_id UUID,
    last_modified_by UUID NOT NULL,
    team_vault_id UUID NOT NULL,
    CONSTRAINT created_by_fk FOREIGN KEY (created_by) REFERENCES users_table(id),
    CONSTRAINT parent_fk FOREIGN KEY (parent_id) REFERENCES data_blocks(id),
    CONSTRAINT last_modified_by_fk FOREIGN KEY (last_modified_by) REFERENCES users_table(id),
    CONSTRAINT team_vault_fk FOREIGN KEY (team_vault_id) REFERENCES team_vaults ON DELETE CASCADE
);

CREATE TABLE files_detail (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    location VARCHAR(255) NOT NULL,
    data_block_id UUID,
    CONSTRAINT data_block_fk FOREIGN KEY (data_block_id) REFERENCES data_blocks(id) ON DELETE SET NULL
);

CREATE TABLE team_vault_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    team_vault_id UUID NOT NULL,
    role vault_role NOT NULL,
    joined_on DATE NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users_table(id) ON DELETE CASCADE,
    CONSTRAINT fk_team_vault FOREIGN KEY (team_vault_id) REFERENCES team_vaults(id) ON DELETE CASCADE,
    CONSTRAINT unique_user_team UNIQUE (user_id, team_vault_id)
);


