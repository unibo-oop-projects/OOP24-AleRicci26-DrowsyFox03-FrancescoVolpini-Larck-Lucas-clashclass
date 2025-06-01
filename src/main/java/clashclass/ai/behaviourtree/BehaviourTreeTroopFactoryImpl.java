package clashclass.ai.behaviourtree;

import clashclass.ai.logic.ChooseTargetNearestLogicImpl;

import java.util.List;

public class BehaviourTreeTroopFactoryImpl implements BehaviourTreeFactory {
    @Override
    public BehaviourTree create() {
        return new BehaviourTreeImpl(
            new RootNode(
                new SequenceNode(List.of(
                    new ChooseNextTargetNode(new ChooseTargetNearestLogicImpl()),
                    new GoToTargetNode(0.1f),
                    new WaitNode(0.5f),
                    new SequenceNode(List.of(
                        new DamageTargetNode(),
                        new WaitNode(0.5f)
                    ))
                ))
            )
        );
    }
}
