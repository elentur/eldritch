package expetions;

import utils.ResourceUtil;

public class InvestigatorChoiceException extends RuntimeException {
    private String message;
    public InvestigatorChoiceException(String val){
       message =  ResourceUtil.get(val, "exception");
    }
    @Override
    public String getMessage(){
        return message;
    }
}
