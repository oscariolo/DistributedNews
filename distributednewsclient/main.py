from topicos import obtener_topicos
from consumidor import consumir_topico
import threading

if __name__ == "__main__":
    print("=== Cliente Distribuido de Noticias ===")

    topicos_disponibles = obtener_topicos()
    if not topicos_disponibles:
        print("No hay tópicos disponibles.")
        exit()

    print("\nTópicos disponibles:")
    for idx, t in enumerate(topicos_disponibles):
        print(f"{idx + 1}. {t}")

    seleccion = input("\nSelecciona tópicos por número separados por coma (ej: 1,3): ")
    try:
        indices = [int(i.strip()) - 1 for i in seleccion.split(",")]
        topicos = [topicos_disponibles[i] for i in indices if 0 <= i < len(topicos_disponibles)]
    except (ValueError, IndexError):
        print("Selección inválida.")
        exit()

    hilos = []
    eventos = []

    for topico in topicos:
        detener_evento = threading.Event()
        hilo = threading.Thread(target=consumir_topico, args=(topico, detener_evento), daemon=True)
        hilo.start()
        hilos.append(hilo)
        eventos.append(detener_evento)

    print("\nSuscrito a los tópicos seleccionados.")
    print("Escribe `exit` para detener el cliente.\n")

    try:
        while True:
            comando = input("> ").strip().lower()
            if comando == "exit":
                break
            else:
                print("Comando no reconocido. Usa `exit` para salir.")
    except KeyboardInterrupt:
        print("\nInterrupción del usuario recibida (CTRL+C). Cerrando...")

    print("Deteniendo consumidores...")
    for e in eventos:
        e.set()

    print("Esperando que los hilos terminen...")
    for hilo in hilos:
        hilo.join()

    print("Cliente finalizado.")
