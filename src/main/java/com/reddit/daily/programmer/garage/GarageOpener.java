package com.reddit.daily.programmer.garage;

import java.util.Arrays;
import java.util.List;

/**
 * Challenge : https://www.reddit.com/r/dailyprogrammer/comments/4cb7eh/20160328_challenge_260_easy_garage_door_opener/
 *
 * @author masseta
 */
public class GarageOpener {
    public static final String COMPLETE = "cycle_complete";
    public static final String CLICK = "button_clicked";
    public static final String EMERGENCY = "block_detected";
    public static final String CLEAR = "block_cleared";
    public static State state = State.CLOSED;

    enum State {OPENING, CLOSING, CLOSED, OPEN, STOPPED_WHILE_OPENING, STOPPED_WHILE_CLOSING, BLOCKED, EMERGENCY_OPENING}

    public static void main(String[] args) {
//        List<String> entries = Arrays.asList(CLICK, COMPLETE, CLICK, CLICK, CLICK, CLICK, COMPLETE);
        List<String> entries = Arrays.asList(CLICK, COMPLETE, CLICK, EMERGENCY, CLICK, COMPLETE, CLICK, CLEAR, CLICK, COMPLETE);
        System.out.println("Door: " + state.name());
        for (String entry : entries) {
            System.out.println("> " + entry);
            switch (state) {
                case CLOSED:                {handle(entry, State.OPENING, State.EMERGENCY_OPENING, State.CLOSED);break;}
                case OPEN:                  {handle(entry, State.CLOSING, State.BLOCKED, State.OPEN);break;}
                case CLOSING:               {handle(entry, State.STOPPED_WHILE_CLOSING, State.EMERGENCY_OPENING, State.CLOSED);break;}
                case OPENING:               {handle(entry, State.STOPPED_WHILE_OPENING, State.EMERGENCY_OPENING, State.OPEN);break;}
                case STOPPED_WHILE_CLOSING: {handle(entry, State.OPENING, State.EMERGENCY_OPENING, State.STOPPED_WHILE_CLOSING);break;}
                case STOPPED_WHILE_OPENING: {handle(entry, State.CLOSING, State.EMERGENCY_OPENING, State.STOPPED_WHILE_OPENING);break;}
                case EMERGENCY_OPENING:     {handle(entry, State.EMERGENCY_OPENING, State.EMERGENCY_OPENING, State.BLOCKED);break;}
                case BLOCKED:               {handle(entry, State.BLOCKED, State.BLOCKED, State.BLOCKED);break;}
                default:                    System.out.println("explode");
            }
            System.out.println("Door: " + state.name());
        }
    }

    public static void handle(String event, State ifClick, State ifEmergency, State ifComplete) {
        if(state == State.BLOCKED && CLEAR.equals(event)){
            state = State.OPEN;
        }
        switch (event) {
            case CLICK:         state = ifClick; break;
            case COMPLETE:      state = ifComplete; break;
            case EMERGENCY :    state =  ifEmergency; break;
            default : break;
        }
    }
}
