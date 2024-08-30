USE medilabo_patient;
INSERT INTO patient (name, username, birthdate, gender, phone, adresse)
VALUES
    ('TestNone', 'Test', '1966-12-31 00:00:00', 'F', '100-222-3333', 'Brookside St'),
    ('TestBorderline', 'Test', '1945-06-24 00:00:00', 'M', '200-333-4444', 'High St'),
    ('TestInDanger', 'Test', '2004-06-18 00:00:00', 'M', '300-444-5555', 'Club Road'),
    ('TestEarlyOnset', 'Test', '2002-06-28 00:00:00', 'F', '400-555-6666', 'Valley Dr');