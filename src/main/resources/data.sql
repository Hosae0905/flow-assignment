INSERT INTO extension (extension_idx, extension, status)
VALUES
    (1, 'bat', false),
    (2, 'cmd', false),
    (3, 'com', false),
    (4, 'cpl', false),
    (5, 'exe', false),
    (6, 'scr', false),
    (7, 'js', false)
    ON DUPLICATE KEY UPDATE extension_idx = extension_idx;
