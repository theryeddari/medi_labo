const username = process.env.MONGO_INITDB_ROOT_USERNAME;
const password = process.env.MONGO_INITDB_ROOT_PASSWORD;
const database = process.env.MONGO_INITDB_DATABASE;

const connectionString = `mongodb://${username}:${password}@localhost:27017`;

const db = connect(connectionString);

const medilaboDB = db.getSiblingDB(database);

// Création de la collection FollowNote dans la base de données medilabo_notes
medilaboDB.createCollection('FollowNote');

// Insertion des documents dans la collection FollowNote de medilabo_notes
medilaboDB.FollowNote.insertMany([
    {
        patientId: "1",
        date: new Date('2024-01-01T00:00:00Z'),
        note: 'Tout va bien',
        patient: 'TestNone'
    },
    {
        patientId: "2",
        date: new Date('2024-01-02T00:00:00Z'),
        note: 'Il se plaint également que son audition est anormale dernièrement',
        patient: 'TestBorderline'
    },
    {
        patientId: "2",
        date: new Date('2024-01-03T00:00:00Z'),
        note: 'Il remarque également que son audition continue d\'être anormale, quelques vertiges',
        patient: 'TestBorderline'
    },
    {
        patientId: "3",
        date: new Date('2024-01-04T00:00:00Z'),
        note: 'Le patient déclare qu\'il est fumeur depuis peu',
        patient: 'TestInDanger'
    },
    {
        patientId: "3",
        date: new Date('2024-01-05T00:00:00Z'),
        note: 'Il se plaint également de crises d’apnée respiratoire anormale, faible anticorps',
        patient: 'TestInDanger'
    },
    {
        patientId: "4",
        date: new Date('2024-01-06T00:00:00Z'),
        note: 'Le patient déclare qu\'il lui est devenu difficile de monter les escaliers',
        patient: 'TestEarlyOnset'
    },
    {
        patientId: "4",
        date: new Date('2024-01-07T00:00:00Z'),
        note: 'Il se plaint également d’être essoufflé',
        patient: 'TestEarlyOnset'
    },
    {
        patientId: "4",
        date: new Date('2024-01-08T00:00:00Z'),
        note: 'Le patient déclare avoir commencé à etre fumeur depuis peu, rechute dans l alcool ',
        patient: 'TestEarlyOnset'
    },
    {
        patientId: "4",
        date: new Date('2024-01-09T00:00:00Z'),
        note: 'Taille , Poids , Cholestérol , Vertiges et Réaction',
        patient: 'TestEarlyOnset'
    }
]);

// Optionnel : Vérifier les documents insérés (utile pour le débogage)
db.FollowNote.find().pretty();