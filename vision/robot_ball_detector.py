import cv2
import requests
from ultralytics import YOLO

# ---------------------------
#  CONFIG
# ---------------------------
ROBORIO_URL = "http://roborio-3459-frc.local:5805/vision"  # Custom endpoint on robot
TEAM = 3459

# Load YOLO model
model = YOLO("yolov8s-world.pt")
model.set_classes(["red ball", "blue ball"])  # only detect balls

# Open camera stream from roboRIO
cap = cv2.VideoCapture(f"http://roborio-{TEAM}-frc.local:1181/stream.mjpg")


def send_to_robot(steer_value):
    """Send steering offset via HTTP POST."""
    try:
        requests.post(
            ROBORIO_URL,
            json={"steer": float(steer_value)},
            timeout=0.05
        )
    except:
        pass    # ignore network hiccups


while True:
    ret, frame = cap.read()
    if not ret:
        break

    frame = cv2.flip(frame, 1)
    results = model(frame)

    # default if no ball found
    steering_offset = 999  

    if results and len(results[0].boxes) > 0:
        # pick biggest detection
        largest = max(results[0].boxes, key=lambda box: (box.xyxy[0][2] - box.xyxy[0][0]))

        x1, y1, x2, y2 = largest.xyxy[0]
        ball_center = (x1 + x2) / 2
        frame_center = frame.shape[1] / 2

        # normalize (-1 ... +1)
        steering_offset = (ball_center - frame_center) / frame_center

    # send to robot
    send_to_robot(steering_offset)

    # optional preview
    annotated = results[0].plot()
    cv2.imshow("Ball Detector", annotated)

    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

cap.release()
cv2.destroyAllWindows()
