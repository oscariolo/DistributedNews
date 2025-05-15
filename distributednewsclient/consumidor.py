from kafka import KafkaConsumer

def consumir_topico(topico, detener_evento):
    consumer = KafkaConsumer(
        topico,
        bootstrap_servers='localhost:9092',
        auto_offset_reset='earliest',
        enable_auto_commit=True,
        group_id='distributed-news-client',
        value_deserializer=lambda x: x.decode('utf-8'),
        consumer_timeout_ms=1000  # <-- Esto hace que `poll` no se quede bloqueado
    )

    print(f"[{topico}] Escuchando mensajes...")

    try:
        while not detener_evento.is_set():
            mensajes = consumer.poll(timeout_ms=1000)
            for tp, records in mensajes.items():
                for record in records:
                    print(f"[{record.topic}] {record.value}")
    except Exception as e:
        print(f"[{topico}] Error: {e}")
    finally:
        consumer.close()
        print(f"[{topico}] Conexión cerrada.")
