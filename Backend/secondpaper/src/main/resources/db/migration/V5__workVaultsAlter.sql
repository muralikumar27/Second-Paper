ALTER TABLE work_vaults
ADD COLUMN created_at TIMESTAMP;

ALTER TABLE team_vaults
DROP CONSTRAINT work_vault_fk;

ALTER TABLE team_vaults
ADD CONSTRAINT work_vault_fk
FOREIGN KEY (work_vault_id) REFERENCES work_vaults(id) ON DELETE CASCADE;