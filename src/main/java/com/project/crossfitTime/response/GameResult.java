package com.project.crossfitTime.response;

public class GameResult {

    private Boolean success;

    private String errMsg;

    private Object data;

    public GameResult(boolean success, String errMsg, Object o) {
        this.success = success;
        this.errMsg = errMsg;
        this.data = o;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
