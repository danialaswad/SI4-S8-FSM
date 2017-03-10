package g2.fsm;

import java.util.*;

import java.lang.reflect.*;

public class StateMachine {

	private StateReflect stateReflect = new StateReflect();

	private Class reflectClass;

	private Map<String,Class[]> methodMap = new HashMap<String,Class[]>();

	public StateMachine(){

		reflectClass = stateReflect.getClass();
		Method[] methods = reflectClass.getDeclaredMethods(); 
		for (Method method : methods){ 
			 methodMap.put(method.getName(),method.getParameterTypes());
		}
	}

	private void switchState(String state, String transition){
		String methodName = state + "_" + transition;
		Method method = null;
		try{
			method = reflectClass.getMethod(methodName,methodMap.get(methodName));
			method.invoke(stateReflect,null);
		}catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	private String[] states= {  "S3",  "S4",  "S5",  "S6",  "S8",  "S1",  "S2", };

	String currentState = "S1";

	public void connectTo(){
	}

	public void switchState(String Transition){

	}
	public static void main(String[] args){
	}
}

