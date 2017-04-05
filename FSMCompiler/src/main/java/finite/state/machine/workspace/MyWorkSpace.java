package finite.state.machine.workspace;


public interface MyWorkSpace {

    /**
     *
     * @param workSpace
     */
    void start(MyWorkSpaceImpl workSpace);

    /**
     *
     * @param e
     */
    void submitEvent(String e);
}
