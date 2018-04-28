package expetions;

import utils.ResourceUtil;

public class EncounterException extends RuntimeException {
    private String message;
    public EncounterException(String val){
       message =  ResourceUtil.get(val,EncounterException.class);
    }
    @Override
    public String getMessage(){
        return message;
    }
}
