import cv2
import torch

model_path = "src/main/python/models/yolov5m.pt"
model = torch.hub.load('ultralytics/yolov5', 'custom', path=model_path, force_reload=True)

cap = cv2.VideoCapture(0)

while True:
    ret, frame = cap.read()
    if not ret:
        break

    results = model(frame)

    pessoas = 0
    for i, detection in enumerate(results.xyxy[0]):
        x1, y1, x2, y2, conf, cls = detection
        if cls == 0:  # Verificar se a detecção é uma pessoa
            x1, y1, x2, y2 = map(int, [x1, y1, x2, y2])
            color = (0, 255, 0)  # Verde

            # Verificar se a confiança é alta e se o objeto está se movendo
            if conf > 0.7 and abs(x2 - x1) > 10 and abs(y2 - y1) > 10:
                cv2.rectangle(frame, (x1, y1), (x2, y2), color, 2)
                pessoas += 1

    cv2.putText(frame, f"Pessoas: {pessoas}", (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2)

    cv2.imshow("Detecção de Pessoas", frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
