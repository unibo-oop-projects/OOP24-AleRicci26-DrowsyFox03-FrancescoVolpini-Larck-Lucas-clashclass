package clashclass.battle.destruction;

//destructionObservable deve chiamare update di UpdateProvider
//update va implementato all interno di destructionObservable

public interface DestructionObservable {

    void addObserver(DestructionObserver observer);

    void removeObserver(DestructionObserver observer);

}


