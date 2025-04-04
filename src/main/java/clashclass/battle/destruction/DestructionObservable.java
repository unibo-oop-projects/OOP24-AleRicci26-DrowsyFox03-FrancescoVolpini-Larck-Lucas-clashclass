package clashclass.battle.destruction;

//destructionObservable deve chiamare update di UpdateProvider
//update va implementato all interno di destructionObservable

public interface DestructionObservable {
    /**
     * Add an observer to the set of Observers
     * @param observer to add
     */
    void addObserver(DestructionObserver observer);

    /**
     * Remove an observer to the set of Observers
     * @param observer to remove
     */
    void removeObserver(DestructionObserver observer);

}


