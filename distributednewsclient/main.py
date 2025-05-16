from topicos import obtener_topicos
from consumidor import consumir_topico
import threading

def mostrar_menu():
    print("\n=== Menú Cliente Distribuido de Noticias ===")
    print("1. Suscribirse a tópicos")
    print("2. Ver suscripciones activas")
    print("3. Desuscribirse de un tópico")
    print("4. Agregar información (pendiente)")
    print("0. Salir")

if __name__ == "__main__":
    suscripciones = set()  # Solo guarda los nombres de los tópicos suscritos

    while True:
        mostrar_menu()
        opcion = input("Selecciona una opción: ").strip()

        if opcion == "1":
            topicos_disponibles = obtener_topicos()
            if not topicos_disponibles:
                print("No hay tópicos disponibles.")
                continue

            print("\nTópicos disponibles:")
            for idx, t in enumerate(topicos_disponibles):
                print(f"{idx + 1}. {t}")

            seleccion = input("\nSelecciona tópicos por número separados por coma (ej: 1,3): ")
            try:
                indices = [int(i.strip()) - 1 for i in seleccion.split(",")]
                topicos = [topicos_disponibles[i] for i in indices if 0 <= i < len(topicos_disponibles)]
            except (ValueError, IndexError):
                print("Selección inválida.")
                continue

            for topico in topicos:
                if topico in suscripciones:
                    print(f"Ya estás suscrito a '{topico}'.")
                    continue
                suscripciones.add(topico)
                print(f"Suscripción a '{topico}' guardada.")

        elif opcion == "2":
            if not suscripciones:
                print("No tienes suscripciones activas.")
            else:
                print("Mostrando mensajes de tus suscripciones activas (presiona Ctrl+C para volver al menú):")
                hilos = []
                eventos = []
                try:
                    for topico in suscripciones:
                        detener_evento = threading.Event()
                        hilo = threading.Thread(target=consumir_topico, args=(topico, detener_evento), daemon=True)
                        hilo.start()
                        hilos.append(hilo)
                        eventos.append(detener_evento)
                    while True:
                        pass  # Mantiene el hilo principal esperando hasta Ctrl+C
                except KeyboardInterrupt:
                    print("\nRegresando al menú...")
                    for evento in eventos:
                        evento.set()
                    for hilo in hilos:
                        hilo.join()

        elif opcion == "3":
            if not suscripciones:
                print("No tienes suscripciones para desuscribirte.")
                continue
            print("Tópicos suscritos:")
            topicos_lista = list(suscripciones)
            for idx, t in enumerate(topicos_lista):
                print(f"{idx + 1}. {t}")
            seleccion = input("Selecciona el número del tópico para desuscribirte (o varios separados por coma): ")
            try:
                indices = [int(i.strip()) - 1 for i in seleccion.split(",")]
                topicos_a_eliminar = [topicos_lista[i] for i in indices if 0 <= i < len(topicos_lista)]
            except (ValueError, IndexError):
                print("Selección inválida.")
                continue
            for topico in topicos_a_eliminar:
                suscripciones.remove(topico)
                print(f"Desuscrito de '{topico}'.")

        elif opcion == "4":
            print("Funcionalidad de agregar información aún no implementada.")

        elif opcion == "0":
            print("Saliendo del cliente.")
            break

        else:
            print("Opción inválida. Intenta de nuevo.")