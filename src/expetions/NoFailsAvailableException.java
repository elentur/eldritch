package expetions;

public class NoFailsAvailableException extends EncounterException {
    public NoFailsAvailableException(){
        super("${noFailsAvailable}");
    }
}
