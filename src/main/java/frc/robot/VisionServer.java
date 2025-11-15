package frc.robot;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import fi.iki.elonen.NanoHTTPD;

public class VisionServer extends NanoHTTPD {

    public volatile double steeringOffset = 999;

    public VisionServer() throws IOException {
        super(5805);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Vision server running on port 5805...");
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            if (session.getMethod() == Method.POST && session.getUri().equals("/vision")) {

                HashMap<String, String> body = new HashMap<>();
                session.parseBody(body);

                String json = body.get("postData");
                if (json != null) {
                    JSONObject data = new JSONObject(json);
                    steeringOffset = data.getDouble("steer");
                }

                return newFixedLengthResponse("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFixedLengthResponse("Invalid Request");
    }
}
