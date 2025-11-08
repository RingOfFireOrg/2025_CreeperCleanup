import cv2
from ultralytics import YOLO
from ntcore import NetworkTableInstance

# Load YOLO model
model = YOLO("yolov8s-world.pt")
model.set_classes(["red ball", "blue ball"])  # only detect balls

# NetworkTables setup
ntinst = NetworkTableInstance.getDefault()
ntinst.startClient4("vision")   # identifies this client name
ntinst.setServerTeam(3459)      # <-- replace with YOUR TEAM NUMBER
vision_table = ntinst.getTable("vision")

cap = cv2.VideoCapture("http://roborio-3459-frc.local:1181/stream.mjpg")

while True:
    ret, frame = cap.read()
    if not ret:
        break

    frame = cv2.flip(frame, 1)
    results = model(frame)

    # Default — no ball found
    steering_offset = 999

    if results and len(results[0].boxes) > 0:
        # pick biggest detection (closest ball)
        largest = max(results[0].boxes, key=lambda box: (box.xyxy[0][2] - box.xyxy[0][0]))

        x1, y1, x2, y2 = largest.xyxy[0]
        ball_center = (x1 + x2) / 2
        frame_center = frame.shape[1] / 2

        # offset (-1 = left, +1 = right)
        steering_offset = (ball_center - frame_center) / frame_center

    # Publish to NetworkTables
    vision_table.putNumber("steer", float(steering_offset))

    # Display annotated frame
    annotated = results[0].plot()
    cv2.imshow("Ball Detector", annotated)

    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

cap.release()
cv2.destroyAllWindows()
