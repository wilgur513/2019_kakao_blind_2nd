package api;

import api.action.ActionRequest;
import api.action.ActionResponse;
import api.oncall.OnCallResponse;
import api.start.StartResponse;
import rest.HttpMethod;
import rest.RestTemplate;

import static rest.HttpMethod.GET;
import static rest.HttpMethod.POST;

public class RestApi {
    private static final String SERVER_URL = "http://localhost:8000";

    public static ActionResponse actionApi(ActionRequest request, String token) {
        return RestTemplate.uri(uri("/action"))
                .header("X-Auth-Token", token)
                .header("Content-Type", "application/json")
                .method(HttpMethod.POST)
                .body(request)
                .getResponse(ActionResponse.class);
    }

    public static OnCallResponse onCallApi(String token) {
        return RestTemplate.uri(uri("/oncalls"))
                .method(GET)
                .header("X-Auth-Token", token)
                .getResponse(OnCallResponse.class);
    }

    public static StartResponse startApi(int problemId, int elevatorNum) {
        return RestTemplate.uri(uri("/start/token/" + problemId + "/" + elevatorNum))
                .method(POST)
                .getResponse(StartResponse.class);
    }

    private static String uri(String path) {
        return String.format("%s%s",SERVER_URL, path);
    }
}
