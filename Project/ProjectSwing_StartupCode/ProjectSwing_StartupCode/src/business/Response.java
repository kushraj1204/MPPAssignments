package business;

import java.util.HashMap;

/**
 * @author kush
 */
public class Response {
    private boolean status;
    private HashMap<String, String> formFieldMessages;
    private String message;
    private Object rsp;

    public Response() {
        status=true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public HashMap<String, String> getFormFieldMessages() {
        return formFieldMessages;
    }

    public void setFormFieldMessages(HashMap<String, String> formFieldMessages) {
        this.formFieldMessages = formFieldMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRsp() {
        return rsp;
    }

    public void setRsp(Object rsp) {
        this.rsp = rsp;
    }

    public static Response getRsp(String msg, boolean status) {
        Response rsp = new Response();
        rsp.setMessage(msg);
        rsp.setStatus(status);
        return rsp;
    }

    public static Response getRsp(String msg, boolean status,Object obj) {
        Response rsp = new Response();
        rsp.setMessage(msg);
        rsp.setStatus(status);
        rsp.setRsp(obj);
        return rsp;
    }
}