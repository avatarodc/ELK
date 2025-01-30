import random
import json
import time
from datetime import datetime

num_lines = 2000
log_file = "app_logs.json"

event_types = ['INFO', 'WARNING', 'ERROR', 'DEBUG']
event_messages = {
    'INFO': [
        "L'application a démarré avec succès.",
        "L'utilisateur s'est connecté.",
        "Une tâche a été accomplie avec succès.",
        "La sauvegarde a été effectuée sans erreur.",
        "Le service a répondu correctement à la demande."
    ],
    'WARNING': [
        "La connexion au serveur a pris plus de temps que prévu.",
        "Le disque dur commence à manquer d'espace.",
        "L'authentification de l'utilisateur a échoué après plusieurs tentatives.",
        "La mémoire utilisée approche de la limite.",
        "La réponse du serveur est lente."
    ],
    'ERROR': [
        "Une erreur fatale s'est produite lors du démarrage de l'application.",
        "Impossible de se connecter à la base de données.",
        "Une exception non gérée a été levée dans le processus.",
        "L'application a rencontré une erreur inattendue et s'est arrêtée.",
        "Le serveur a renvoyé une erreur 500."
    ],
    'DEBUG': [
        "La méthode de calcul des données a été appelée.",
        "La configuration de l'application a été chargée.",
        "Un utilisateur a accédé à la page de profil.",
        "Le processus de synchronisation a été lancé.",
        "Le système a reçu une nouvelle requête API."
    ]
}

def generate_log_entry():
    event_type = random.choice(event_types)
    message = random.choice(event_messages[event_type])
    timestamp = datetime.now().strftime('%Y-%m-%dT%H:%M:%S')
    log_entry = {
        "timestamp": timestamp,
        "event_type": event_type,
        "message": message,
        "host": "localhost",
        "service": "my_app"
    }
    return log_entry

def write_logs_to_file(num_lines, file_name):
    with open(file_name, 'w', encoding='utf-8') as file:
        for _ in range(num_lines):
            log_entry = generate_log_entry()
            file.write(json.dumps(log_entry, ensure_ascii=False) + "\n")
            time.sleep(0.01)

if __name__ == "__main__":
    write_logs_to_file(num_lines, log_file)
    print(f"Logs have been generated and written to {log_file}.")
