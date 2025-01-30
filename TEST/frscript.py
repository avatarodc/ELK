import random
import json
import time
import os
from datetime import datetime

num_lines_per_batch = 500
log_folder = "logs"
log_file = os.path.join(log_folder, "fruit_stock_logs.json")
fruits = ['Pomme', 'Banane', 'Orange', 'Poire', 'Fraise']
actions = ['ajout', 'vente', 'mise à jour']
locations = ['Entrepôt A', 'Entrepôt B', 'Entrepôt C']
users = ['Alice', 'Bob', 'Charles', 'David']

stock_updates = {
    'Pomme': 120,
    'Banane': 80,
    'Orange': 100,
    'Poire': 150,
    'Fraise': 50
}

fruit_prices = {
    'Pomme': {'buy': random.uniform(0.5, 1.5), 'sell': random.uniform(1.0, 2.0)},
    'Banane': {'buy': random.uniform(0.2, 1.0), 'sell': random.uniform(0.5, 1.2)},
    'Orange': {'buy': random.uniform(0.3, 1.0), 'sell': random.uniform(0.8, 1.5)},
    'Poire': {'buy': random.uniform(0.6, 1.8), 'sell': random.uniform(1.0, 2.2)},
    'Fraise': {'buy': random.uniform(1.0, 3.0), 'sell': random.uniform(2.0, 4.0)}
}

event_types = ['INFO', 'AVERTISSEMENT', 'ERREUR']

def get_current_timestamp():
    return datetime.now().strftime('%Y-%m-%dT%H:%M:%S')

def generate_log_entry():
    fruit = random.choice(fruits)
    action = random.choice(actions)
    quantity = random.randint(1, 50)
    location = random.choice(locations)
    user = random.choice(users)

    if action == 'ajout':
        stock_updates[fruit] += quantity
        stock_updates[fruit] = min(stock_updates[fruit], 10000)
    elif action == 'vente' and stock_updates[fruit] >= quantity:
        stock_updates[fruit] -= quantity
        stock_updates[fruit] = max(stock_updates[fruit], 0)

    if action == 'ajout':
        event_type = 'INFO'
    elif action == 'vente' and stock_updates[fruit] < 10:
        event_type = 'AVERTISSEMENT'
    else:
        event_type = 'INFO'

    timestamp = get_current_timestamp()

    price = fruit_prices[fruit]['sell'] if action == 'vente' else fruit_prices[fruit]['buy']
    revenue = quantity * price if action == 'vente' else 0

    log_entry = {
        "timestamp": timestamp,
        "event_type": event_type,
        "message": f"{quantity} {fruit} {action} dans {location}. Stock actuel: {stock_updates[fruit]}",
        "fruit": fruit,
        "action": action,
        "quantity": quantity,
        "stock_level": stock_updates[fruit],
        "location": location,
        "user": user,
        "prix_par_unite": round(price, 2),
        "revenu": round(revenue, 2)
    }

    return log_entry

def write_logs_in_batches():
    if not os.path.exists(log_folder):
        os.makedirs(log_folder)

    with open(log_file, 'a', encoding='utf-8') as file:
        while True:
            for _ in range(num_lines_per_batch):
                log_entry = generate_log_entry()
                file.write(json.dumps(log_entry, ensure_ascii=False) + "\n")
            
            print(f"{num_lines_per_batch} journaux ont été écrits.")
            time.sleep(5)

if __name__ == "__main__":
    write_logs_in_batches()
