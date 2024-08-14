INSERT INTO extension (extension_idx, extension, status, option)
VALUES
    (1, 'bat', false, 'default'),
    (2, 'cmd', false, 'default'),
    (3, 'com', false, 'default'),
    (4, 'cpl', false, 'default'),
    (5, 'exe', false, 'default'),
    (6, 'scr', false, 'default'),
    (7, 'js', false, 'default')
    ON DUPLICATE KEY UPDATE extension_idx = extension_idx;
