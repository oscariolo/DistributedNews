# Proyecto: Distributed News - Cliente en Python
# Este cliente debe:
# 1. Conectarse a Kafka (localhost:9092)
# 2. Obtener tópicos disponibles desde un endpoint en el servidor Java (GET /topics)
# 3. Suscribirse a un tópico y escuchar mensajes en tiempo real
# 4. Mostrar noticias recibidas en consola
# 5. (Opcional) Permitir enviar noticias manualmente a un tópico
# El servidor en Java usa Spring Boot y crea tópicos por tipo de noticia.
# El cliente solo es de consola por ahora.
from consumidor import consumir_topico

if __name__ == "__main__":
    print("=== Distributed News - Cliente ===")
    topico = input("¿A qué tópico deseas suscribirte? (ej: deportes, politica, etc): ")
    consumir_topico(topico)