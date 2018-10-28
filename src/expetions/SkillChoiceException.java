package expetions;

import utils.ResourceUtil;

public class SkillChoiceException extends RuntimeException {
    private String message;
    public SkillChoiceException(String val){
       message =  ResourceUtil.get(val, "exception");
    }
    @Override
    public String getMessage(){
        return message;
    }
}
