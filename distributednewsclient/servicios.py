import requests

def obtener_topicos():
    response = requests.get("http://localhost:8080/topics")
    if response.status_code == 200:
        return response.json()
    else:
        print("No se pudo obtener tópicos.")
        return []
