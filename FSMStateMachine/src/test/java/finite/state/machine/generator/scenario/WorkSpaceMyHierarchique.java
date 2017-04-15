package finite.state.machine.generator.scenario;

import finite.state.machine.workspace.MyWorkSpaceImpl;

public class WorkSpaceMyHierarchique extends MyWorkSpaceImpl {

	public WorkSpaceMyHierarchique() {
		start(this, new MyHierarchique());
	}
}