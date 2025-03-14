package clashclass.ecs;

public interface Component {
    public void setGameObjectReference(GameObject gameObject);

    public void initialize();

    public void update(float deltaTime);
}
