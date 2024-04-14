import cv2
import torch
import requests
import json
from datetime import datetime

# Carregar o modelo pré-treinado
model_path = "ml/python/models/yolov5m.pt"
model = torch.hub.load('ultralytics/yolov5', 'custom', path=model_path, force_reload=True)

cap = cv2.VideoCapture(0)

pessoas_entrada = {}
pessoas_saida = {}

while True:
    ret, frame = cap.read()
    if not ret:
        break

    results = model(frame)
    pessoas = 0

    for i, detection in enumerate(results.xyxy[0]):
        x1, y1, x2, y2, conf, cls = detection
        if cls == 0 and conf > 0.7:  # Verificar se é uma pessoa com alta confiança 70% (ajuste aqui para melhorar detecção)
            pessoas += 1
            cv2.rectangle(frame, (int(x1), int(y1)), (int(x2), int(y2)), (0, 255, 0), 2)

            if x1 < 10: 
                pessoas_entrada[i] = datetime.now()
            elif x2 > frame.shape[1] - 10 and pessoas_entrada.get(i): 
                data_entrada = {"dataEntrada": str(datetime.now().date()), "horaEntrada": datetime.now().strftime("%H:%M:%S"), "quantEntrada": 1, "obsEntrada": "Observacao, se houver"}
                response_entrada = requests.post('http://localhost:8080/entrada', json=data_entrada)
                print("Dados de entrada:")
                print(json.dumps(data_entrada, indent=4))
                del pessoas_entrada[i]
            if x2 > frame.shape[1] - 10:
                pessoas_saida[i] = datetime.now()
            elif x1 < 10 and pessoas_saida.get(i):
                data_saida = {"dataSaida": str(datetime.now().date()), "horaSaida": datetime.now().strftime("%H:%M:%S"), "quantSaida": 1, "obsSaida": "Observacao, se houver"}
                response_saida = requests.post('http://localhost:8080/saida', json=data_saida)
                print("Dados de saída:")
                print(json.dumps(data_saida, indent=4))
                del pessoas_saida[i]

    # Exibir o número de pessoas na imagem
    cv2.putText(frame, f"Pessoas: {pessoas}", (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2)

    cv2.imshow("Detecção de Pessoas", frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
