package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;

import java.util.*;

/**
 * Represents an implementation of A*, a well-know pathfinding algorithm.
 * <a href="https://www.baeldung.com/java-a-star-pathfinding"/>
 */
public class AStarPathfindingImpl implements PathfindingAlgorithm {
    private final Queue<AStarPathNode> openSet;
    private final Set<AStarPathNode> closedSet;
    private final DistanceHeuristic distanceHeuristic;

    /**
     * Constructs the A* algorithm.
     *
     * @param distanceHeuristic the heuristic to use for distance estimation
     */
    public AStarPathfindingImpl(final DistanceHeuristic distanceHeuristic) {
        this.openSet = new PriorityQueue<>(Comparator.comparingDouble(AStarPathNode::getCostF));
        this.closedSet = new HashSet<>();
        this.distanceHeuristic = distanceHeuristic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PathNode> findPath(final PathNodeGrid pathNodeGrid, final PathNode startPathNode, final PathNode endPathNode) {
        final var nodes = pathNodeGrid.getNodes().stream()
                .map(AStarPathNode::new)
                .toList();

        final var start = new AStarPathNode(startPathNode);
        final var end = new AStarPathNode(endPathNode);

        this.openSet.clear();
        this.closedSet.clear();

        start.setCostG(0.0f);
        start.setCostH(this.distanceHeuristic.calculateDistance(start.getPosition(), end.getPosition()));
        start.setCostF(start.getCostG() + start.getCostH());

        this.openSet.add(start);

        while (!this.openSet.isEmpty()) {
            final var current = this.openSet.poll();

            if (current.equals(end)) {
                var path = new ArrayList<AStarPathNode>();
                var currentNode = current;
                while (currentNode != null) {
                    path.add(0, currentNode);
                    currentNode = currentNode.getParentNode();
                }
                return path.stream()
                        .map(AStarPathNode::getPathNode)
                        .toList();
            }

            this.closedSet.add(current);

            pathNodeGrid.getNeighborsPositionsOfNode(current.getPathNode())
                    .forEach(neighborPosition -> {
                        final var neighbor = nodes.stream()
                                .filter(node ->
                                        (int)node.getPosition().x() == neighborPosition.x() &&
                                        (int)node.getPosition().y() == neighborPosition.y())
                                .toList()
                                .getFirst();

                        if (this.closedSet.contains(neighbor)) {
                            return;
                        }

                        final var newDistance = this.distanceHeuristic.calculateDistance(current.getPosition(), neighbor.getPosition());
                        final var newCostG = current.getCostG() + neighbor.getCost() + newDistance;

                        if (!this.openSet.contains(neighbor) || newCostG < neighbor.getCostG()) {
                            neighbor.setParentNode(current);
                            neighbor.setCostG(newCostG);
                            neighbor.setCostH(this.distanceHeuristic.calculateDistance(neighbor.getPosition(), end.getPosition()));
                            neighbor.setCostF(neighbor.getCostG() + neighbor.getCostH());

                            if (!this.openSet.contains(neighbor)) {
                                this.openSet.add(neighbor);
                            }
                        }
                    });
        }

        return List.of();
    }

    private static final class AStarPathNode {
        private final PathNode pathNode;
        private AStarPathNode parentNode;
        private float costG;
        private float costH;
        private float costF;

        public AStarPathNode(final PathNode pathNode) {
            this.pathNode = pathNode;
            this.costG = Float.POSITIVE_INFINITY;
            this.costH = Float.POSITIVE_INFINITY;
            this.costF = Float.POSITIVE_INFINITY;
            this.parentNode = null;
        }

        public PathNode getPathNode() {
            return this.pathNode;
        }

        public Vector2D getPosition() {
            return new Vector2D(pathNode.getX(), pathNode.getY());
        }

        public float getCost() {
            return pathNode.getCost();
        }

        public float getCostG() {
            return costG;
        }

        public float getCostH() {
            return costH;
        }

        public float getCostF() {
            return costF;
        }

        public AStarPathNode getParentNode() {
            return this.parentNode;
        }

        public void setCostG(final float costG) {
            this.costG = costG;
        }

        public void setCostH(final float costH) {
            this.costH = costH;
        }

        public void setCostF(final float costF) {
            this.costF = costF;
        }

        public void setParentNode(final AStarPathNode parentNode) {
            this.parentNode = parentNode;
        }
    }
}
