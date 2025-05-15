import requests

def obtener_topicos():
    try:
        response = requests.get("http://localhost:8080/topic")
        if response.status_code == 200:
            return response.json()
    except Exception as e:
        print("Error al obtener tópicos:", e)
    return []
