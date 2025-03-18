-- Create extension for UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the TaskStatus type
CREATE TYPE "TaskStatus" AS ENUM ('PENDING', 'COMPLETED');

-- Create the task_groups table
CREATE TABLE task_groups (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title VARCHAR(255) NOT NULL,
  description TEXT,  -- optional field
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create the tasks table
CREATE TABLE tasks (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  group_id UUID, -- optional field,
  title VARCHAR(255) NOT NULL,
  description TEXT,  -- optional field
  status "TaskStatus" NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_task_group
      FOREIGN KEY (group_id)
      REFERENCES task_groups (id)
      ON DELETE SET NULL
);