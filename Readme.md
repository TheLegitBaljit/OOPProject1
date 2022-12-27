Observer Design Pattern:




The observer design pattern is a behavioral design pattern that allows an object (known as the subject) to notify a set of objects (known as observers) when its state changes.
This allows the observers to be kept in sync with the subject and receive updates about its state.



Implementation:




To implement the observer design pattern, you will need to define a subject interface with methods for adding and removing observers,
as well as for notifying them of state changes. You will also need to define an observer interface with a method for receiving updates from the subject.

Next, you will need to create a concrete subject class that implements the subject interface.
This class should maintain a list of observers and provide methods for adding and removing them, as well as for notifying them of state changes.

Finally, you will need to create concrete observer classes that implement the observer interface and register themselves with the subject to receive updates.



Our project:

In our project, the subject is an object named GroupAdmin who holds an ArrayList of the members that are registered to him and an UndoableStringBuilder.
We can use all the methods we built in the previous project on the UndoableStringBuilder.
each time we use one of these functions, the Members who are registered, will be updated because when we register them to GroupAdmin, we use the notify function,
which shallow copies the GroupAdmins UndoableStringBuilder into the Member's UndoableStringbuilder who just registered.
when the members UndoableStringBuilder has a shallowed copy version of the GroupAdmin's UndoableStringBuilder, obviously when we change the GroupAdmin's
UndoableStringbuilder it will also make the same change for the Members that are registered.

We made sure that one Member cannot register twice to the same GroupAdmin, and that one member cannot register to two different GroupAdmins.
