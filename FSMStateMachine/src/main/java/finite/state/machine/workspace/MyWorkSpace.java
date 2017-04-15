package finite.state.machine.workspace;


import finite.state.machine.generator.StateMachine;

public interface MyWorkSpace {

    /**
     *
     * @param workSpace
     */
    void start(MyWorkSpaceImpl workSpace, StateMachine stateMachine);

    /**
     *
     * @param e
     */
    void submitEvent(String e);
}
