package expetions;

import utils.ResourceUtil;

public class ItemChoiceException extends RuntimeException {
    private String message;
    public ItemChoiceException(String val){
       message =  ResourceUtil.get(val, "exception");
    }
    @Override
    public String getMessage(){
        return message;
    }
}
