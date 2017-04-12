package finite.state.machine.object;

public class FSMEvents {


    private String id;
    private FSMEventType type;

    public FSMEvents(String id, String type){
        this.id = id;
        this.type = FSMEventType.valueOf(type.toUpperCase());
    }

    public String getId() {
        return id;
    }

    public FSMEventType getType() {
        return type;
    }
}
