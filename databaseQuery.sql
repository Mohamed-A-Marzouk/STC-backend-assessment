SELECT 
    td.user_id,
    u.username,
    td.training_id,
    td.training_date,
    COUNT(*) AS count
FROM 
    Training_details td
JOIN 
    User u ON td.user_id = u.user_id
GROUP BY 
    td.user_id, td.training_id, td.training_date
HAVING 
    COUNT(*) > 1
ORDER BY 
    td.training_date DESC, td.user_id, td.training_id;