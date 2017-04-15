# SI4-S8-FSM

## Generator and Compilateur d'un machine à état

### Comment exécuter le générateur?
Le fichier generate.sh fournis à la racine du dossier, est le script pour lancer le générateur. Quelques options est fournis pour exécuter le programme. Les options fournis sont les suivantes :
• -f [file path] : cette option est obligatoire. Utilisez cette options pour indiquer le chemin de votre fichier scxml.
• -n [file name] : cette option n’est pas obligatoire. Utilisez cette option pour donner le nom au fichier généré.
• -w [t|f] : cette option n’est pas obligatoire. Utilisez cette option pour générer aussi le workspace.
 
Avant d’exécuter le script generate.sh, assurez-vous que le dossier statemachine est present à la racine du dossier (au meme endroit que le dossier d’entrée).
Voici un exemple d’exécution du script generate.sh.

### Comment exécuter le compilateur?
Le fichier run.sh fourni à la racine du dossier, est le script pour lancer le compilateur de state machine. Lorsque le fichier généré et le fichier de “work space” sont bien present dans le dossier statemachine, exécutez le script run.sh en précisant la classe main du programme.

Pour avoir plus d'explication, veuillez consultez le ReadMe.pdf