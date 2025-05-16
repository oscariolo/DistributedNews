from faker import Faker
import random

fake = Faker('es_ES')  # Localización en español

# Equipos y estadios simulados
teams_list = ["Tigres", "Monterrey", "Barcelona", "Real Madrid", "River", "Boca", "Liverpool", "Manchester City"]
stadiums = ["Estadio Azteca", "La Bombonera", "Camp Nou", "Santiago Bernabéu", "Old Trafford", "Estadio Monumental"]

# Eventos y decisiones simuladas
political_events = [
    "Resultados electorales anunciados",
    "Nueva ley ambiental propuesta",
    "Debate político en cadena nacional",
    "Firma de tratado bilateral",
    "Protestas frente al Congreso",
    "Sesión extraordinaria del Senado"
]

decisions_list = [
    "Reforma educativa aprobada",
    "Se inicia consulta ciudadana",
    "Acuerdo de cooperación firmado",
    "Medidas de austeridad anunciadas",
    "Proyecto de ley archivado",
    "Investigación parlamentaria abierta"
]

# Generador de mensajes de deportes
def generate_sports_messages(n=5):
    sports_messages = []
    for _ in range(n):
        team1, team2 = random.sample(teams_list, 2)
        sports_messages.append({
            "topicName": "sports",
            "scores": f"{random.randint(0, 4)}-{random.randint(0, 4)}",
            "teams": f"{team1} vs {team2}",
            "date": fake.date_between(start_date='-1y', end_date='today').strftime('%Y-%m-%d'),
            "location": random.choice(stadiums),
            "reds": str(random.randint(0, 2)),
            "yellows": str(random.randint(1, 6)),
            "highlights": random.choice([
                f"Golazo de {team1} en el tiempo de descuento",
                f"Parada clave del portero de {team2}",
                f"Partido muy físico con varias faltas",
                f"{team1} remontó en los últimos minutos"
            ])
        })
    return sports_messages

# Generador de mensajes políticos
def generate_politics_messages(n=5):
    politics_messages = []
    for _ in range(n):
        politics_messages.append({
            "topicName": "politics",
            "event": random.choice(political_events),
            "location": fake.city(),
            "date": fake.date_between(start_date='-1y', end_date='today').strftime('%Y-%m-%d'),
            "participants": f"{fake.company()}, {fake.company()}",
            "decisions": random.choice(decisions_list)
        })
    return politics_messages

# Ejemplo de uso:
sports = generate_sports_messages(5)
politics = generate_politics_messages(5)

for msg in sports:
    print(msg)

print("-----")

for msg in politics:
    print(msg)
