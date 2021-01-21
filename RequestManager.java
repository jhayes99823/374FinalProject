
public class RequestManager {
	MachineSelectionBehavior selectionBehavior;
	NotificationManager notificationManager;

	public String handleRequest(String json) {
		Order order = JSONManager.parseOrderInput(json);
		Machine machine = performSelection(order);
		// Communicate with ControllerManager
		// to send request to machine and get
		// response back
		String controllerRespString = null; // = result of ControllerManager call
		ControllerResponse controllerResponse = JSONManager.parseControllerResponse(controllerRespString);
		String appResponse = JSONManager.createAppResponse(controllerResponse, machine);
		return appResponse;
	}

	public Machine performSelection(Order order) {
		return selectionBehavior.selectMachine(order);
	}

	public void setSelectionBehavior(MachineSelectionBehavior selectionBehavior) {
		this.selectionBehavior = selectionBehavior;
	}
}
