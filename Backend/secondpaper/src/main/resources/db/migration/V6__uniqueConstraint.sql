ALTER TABLE work_vaults
ADD CONSTRAINT uq_work_vaults_name_created_by UNIQUE(name, created_by);

ALTER TABLE team_vaults
ADD CONSTRAINT uq_team_vaults_name_work_vault_id UNIQUE(name, work_vault_id);