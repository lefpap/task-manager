-- Create extension for UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the TaskStatus type
CREATE TYPE "TaskStatus" AS ENUM ('PENDING', 'DONE');

-- Create the task_groups table
CREATE TABLE task_groups (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title TEXT NOT NULL,
  description TEXT,  -- optional field
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP  -- optional field
);

-- Create the tasks table
CREATE TABLE tasks (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  task_group_id UUID NOT NULL,
  title TEXT NOT NULL,
  description TEXT,  -- optional field
  status "TaskStatus" NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,  -- optional field
  CONSTRAINT fk_task_group
      FOREIGN KEY (task_group_id)
      REFERENCES task_groups (id)
      ON DELETE CASCADE  -- adjust the delete behavior as needed
);