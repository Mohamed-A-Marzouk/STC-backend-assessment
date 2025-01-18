-- Drop existing tables if they exist
--DROP TABLE IF EXISTS permissions;
--DROP TABLE IF EXISTS files;
--DROP TABLE IF EXISTS items;
--DROP TABLE IF EXISTS permission_groups;

-- Create PermissionGroups Table
CREATE TABLE permission_groups (
    id SERIAL PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL
);

-- Create Items Table (Space, Folder, File)
CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    type ENUM('Space', 'Folder', 'File') NOT NULL,
    name VARCHAR(255) NOT NULL,
    permission_group_id INT,
    FOREIGN KEY (permission_group_id) REFERENCES permission_groups(id)
);

-- Create Files Table (Only for files)
CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    file_binary BYTEA NOT NULL,
    item_id INT,
    FOREIGN KEY (item_id) REFERENCES items(id)
);

-- Create Permissions Table
CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    permission_level ENUM('View', 'Edit') NOT NULL,
    group_id INT,
    FOREIGN KEY (group_id) REFERENCES permission_groups(id)
);

-- Insert permission groups
--INSERT INTO permission_groups (group_name) VALUES
--('Editor'),
--('Viewer');
--
---- Insert items (Spaces, Folders, Files)
---- Space
--INSERT INTO items (type, name, permission_group_id) VALUES
--('Space', 'Root Space', 1),  -- Admin group has access
--('Space', 'User Space', 2);  -- Editor group has access
--
---- Folder
--INSERT INTO items (type, name, permission_group_id) VALUES
--('Folder', 'Project Folder', 2),  -- Editor group
--('Folder', 'Documentation', 3);  -- Viewer group
--
---- File
--INSERT INTO items (type, name, permission_group_id) VALUES
--('File', 'Project Plan.pdf', 2),  -- Editor group
--('File', 'User Guide.docx', 3);  -- Viewer group
--
---- Insert file binary data
---- Assuming placeholder binary data for the sake of the example
--INSERT INTO files (binary, item_id) VALUES
--('\x1234567890ABCDEF', 4),  -- Binary data for 'Project Plan.pdf'
--('\xA1B2C3D4E5F67890', 5);  -- Binary data for 'User Guide.docx'
--
---- Insert user permissions
--INSERT INTO permissions (user_email, permission_level, group_id) VALUES
--('admin@example.com', 'Edit', 1),  -- Admin group
--('editor@example.com', 'Edit', 2),  -- Editor group
--('viewer@example.com', 'View', 3);  -- Viewer group
