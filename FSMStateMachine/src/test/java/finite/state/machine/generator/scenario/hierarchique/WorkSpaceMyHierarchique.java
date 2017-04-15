package finite.state.machine.generator.scenario.hierarchique;

import finite.state.machine.workspace.MyWorkSpaceImpl;

public class WorkSpaceMyHierarchique extends MyWorkSpaceImpl {

	public WorkSpaceMyHierarchique() {
		start(this, new MyHierarchique());
	}
}