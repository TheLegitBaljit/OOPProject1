package observer;
import java.util.*;

public class GroupAdmin implements Sender{
    public ArrayList<Member> member_list;
    public UndoableStringBuilder usb;
    /**
     constructor for new GroupAdmin which is the observable in this project
     */
    public GroupAdmin()
    {
        this.member_list = new ArrayList<>();
        this.usb = new UndoableStringBuilder();
    }

    /**
     * register a new member(observer) to the GroupAdmin member list.
     * a member who registered will get all updates on changes made to the UndoableStringBuilder (shallow copy)
     * @param obj
     */
    @Override
    public void register(Member obj)
    {
        member_list.add(obj);
    }

    /**
     * remove a member from the GroupAdmin member list.
     * the member will stop receiving updates and his usb will be set to null.
     * @param obj
     */
    @Override
    public void unregister(Member obj)
    {
        obj.update(null);
        member_list.remove(obj);
    }

    /**
     * uses the insert function in UndoableStringBuilder and notifies the all the members that are registered
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj)
    {
        this.usb.insert(offset,obj);
        notify(this.usb);
    }

    /**
     * uses the append function in UndoableStringBuilder and notifies the all the members that are registered
     * @param obj
     */
    @Override
    public void append(String obj)
    {
        this.usb.append(obj);
        notify(this.usb);
    }

    /**
     * uses the delete function in UndoableStringBuilder and notifies the all the members that are registered
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end)
    {
        this.usb.delete(start,end);
        notify(this.usb);
    }

    /**
     * uses the undo function in UndoableStringBuilder and notifies the all the members that are registered
     */
    @Override
    public void undo() {
        this.usb.undo();
        notify(this.usb);
    }

    /**
     * notifies all the members that are registered to the GroupAdmin to update their UnDoableStringBuilder
     * @param usb
     * running time complexity O(n), n is the size of the arraylist that contains all the members that are registered to the GroupAdmin.
     */
    public void notify(UndoableStringBuilder usb)
    {
        for(Member member : this.member_list)
            member.update(usb);
    }
}