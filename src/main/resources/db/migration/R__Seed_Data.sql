-- Last updated: 2025-03-19

-- Reset existing data
DELETE FROM tasks;
DELETE FROM task_groups;

-- Seed task groups with hardcoded UUIDs
INSERT INTO task_groups (id, title, description) VALUES
  ('8d1e48c7-974a-4bcd-bf91-1e2df2a71234', 'Urgent Tasks', null),
  ('dce9ad75-2d8a-4b0d-9288-913762da6f56', 'Backlog', null)
ON CONFLICT (id) DO NOTHING;

-- Seed tasks referencing the task groups
INSERT INTO tasks (id, group_id, title, description) VALUES
  ('a5e1f66e-3d4f-4a7c-8a7f-2b4ef5f8e86d', '8d1e48c7-974a-4bcd-bf91-1e2df2a71234', 'Fix critical bug', null),
  ('b76ed5c3-5d5f-4e27-9b0d-5c7f8e69c6f1', '8d1e48c7-974a-4bcd-bf91-1e2df2a71234', 'Prepare presentation', null),
  ('c7a6f6b5-9d8f-4a21-b5ed-8f3e9b4d4bde', 'dce9ad75-2d8a-4b0d-9288-913762da6f56', 'Update documentation', null),
  -- Tasks with no group
  ('b0512c08-1b2e-4453-90ed-bc59a51ffd37', null, 'Go to the gym', null)
ON CONFLICT (id) DO NOTHING;
