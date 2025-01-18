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

