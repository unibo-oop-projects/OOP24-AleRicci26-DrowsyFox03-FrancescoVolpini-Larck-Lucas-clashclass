package clashclass.ai.behaviourtree;

/**
 * Represents a single Node in a BehaviourTree.
 */
public interface Node {
    /**
     * Initializes the node properties before processing it.
     */
    void onEnter();

    /**
     * Process the node every frame.
     *
     * @param deltaTime the time elapsed between the previous and the current frame
     *
     * @return the current state of the node after a single frame of processing
     */
    State onUpdate(float deltaTime);

    /**
     * Clears the node properties after processing it.
     */
    void onExit();

    /**
     * Represents the state of a node.
     */
    enum State {
        /**
         * Running State.
         */
        RUNNING,

        /**
         * Success State.
         */
        SUCCESS,

        /**
         * Failure State.
         */
        FAILURE
    }
}
