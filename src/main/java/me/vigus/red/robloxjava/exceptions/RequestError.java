package me.vigus.red.robloxjava.exceptions;

import me.vigus.red.robloxjava.connection.json.ErrorJson;

public class RequestError extends Exception{

    private final ErrorJson errorJson;

    public RequestError(ErrorJson errorJson){
        super(errorJson.getUserFacingMessage());
        this.errorJson = errorJson;
    }

    public RequestError(String error) {
        super(error);
        this.errorJson = new ErrorJson();
        this.errorJson.setMessage(error);
        this.errorJson.setUserFacingMessage(error);
    }

    public ErrorJson getErrorJson() {
        return this.errorJson;
    }
}
