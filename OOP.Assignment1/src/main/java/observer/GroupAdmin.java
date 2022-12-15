package observer;

import java.util.*;

public class GroupAdmin implements Sender {
    public ArrayList<Member> memberlist; //An ArrayList that holds all the observers
    public UndoableStringBuilder usb; //An object of UndoableStringBuilder

    //A constructor of GroupAdmin
    public GroupAdmin() {
        this.memberlist = new ArrayList<>();
        this.usb = new UndoableStringBuilder();
    }

    /**
     * Register an observer
     * @param obj
     */
    @Override
    public void register(Member obj) {
        memberlist.add(obj);
    }

    /**
     * Unregister an observer
     * @param obj
     */
    @Override
    public void unregister(Member obj) {
        memberlist.remove(obj);
    }

    /**
     * Use the function insert and notify
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notify(this.usb);
    }

    /**
     * Use the function append and notify
     * @param obj
     */
    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notify(this.usb);
    }

    /**
     * Use the function delete and notify
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        notify(this.usb);
    }

    /**
     * Use the function undo and notify all the observers
     */
    @Override
    public void undo() {
        this.usb.undo();
        notify(this.usb);
    }

    /**
     * Notify all the observers
     * @param usb
     */
    public void notify(UndoableStringBuilder usb) {
        for (Member member : this.memberlist)
            member.update(usb);
    }
}