from kafka import KafkaConsumer

def consumir_topico(topico):
    consumer = KafkaConsumer(
        topico,
        bootstrap_servers='localhost:9092',
        auto_offset_reset='earliest',
        enable_auto_commit=True,
        group_id='distributed-news-client',
        value_deserializer=lambda x: x.decode('utf-8'),
    )
    print(f"Escuchando mensajes en el tópico '{topico}'... (CTRL+C para salir)")
    try:
        for mensaje in consumer:
            print(f"[{mensaje.topic}] {mensaje.value}")
    except KeyboardInterrupt:
        print("\nConsumo detenido por el usuario.")
    finally:
        consumer.close()
        print("Conexión cerrada.")
