package g2.fsm.machine;

import org.apache.commons.scxml.env.SimpleErrorHandler;
import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.model.SCXML;
import org.apache.commons.scxml.model.State;
import org.apache.commons.scxml.model.Transition;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by danial on 04/03/17.
 */
public class StateMachine {

    private SCXML scxml;
    private State currentState;

    public StateMachine(String path){

        InputSource source = null;
        try {
            source = new InputSource(new BufferedReader(new FileReader(path)));

            scxml = SCXMLParser.parse(source, new SimpleErrorHandler());

            currentState = (State) scxml.getChildren().get(scxml.getInitial());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void execute_onentry(){

        if (currentState.getOnEntry() != null){
            // TODO execute onentry event
            System.out.println("onentry event");
        }
    }

    private void execute_onexit(){
        if (currentState.getOnExit() != null){
            // TODO execute onexit event
            System.out.println("onexit event");
        }
    }

    private void exit_procedure(){

        execute_onexit();

        while(currentState.getParent() != null){
            currentState = (State) currentState.getParent();
            exit_procedure();
        }

    }

    private void entry_procedure(){

        execute_onentry();

        while(currentState.getFirst()!=null){

            currentState = (State) currentState.getChildren().get(currentState.getFirst());

            entry_procedure();
        }
    }


    private State transition_procedure(String event){

        return transition_procedure_rec(currentState, event);
    }

    private State transition_procedure_rec(State state, String event){
        if (state.getTransitionsList(event) != null) {
            List list = state.getTransitionsList(event);

            Transition transition = (Transition) list.get(0);
            // TODO execute event
            System.out.println(transition.getEvent());

            return (State) transition.getTargets().get(0);
        }
        if (state.getParent()!=null){
            return transition_procedure_rec((State) state.getParent(), event);
        }
        return currentState;
    }

    public void execute(String event){

        entry_procedure();

        System.out.println("current state : " + currentState.getId());


        currentState = transition_procedure(event);

        System.out.println("new state : " + currentState.getId());

    }


}
