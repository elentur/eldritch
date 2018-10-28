package expetions;

import utils.ResourceUtil;

public class ReserveException extends RuntimeException {
    private String message;
    public ReserveException(String val){
       message =  ResourceUtil.get(val, "exception");
    }
    @Override
    public String getMessage(){
        return message;
    }
}
