import java.util.Arrays;
import java.util.Stack;

class Point implements Comparable<Point> {
    Double x;
    Double y;

    Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point o) {
        if (this.y < o.y) return -1;
        if (this.y > o.y) return 1;
        if (this.x < o.x) return -1;
        if (this.x > o.x) return 1;
        return 0;
    }

}

public class Test {

    public static void main(String[] args) {
        Point[] points = new Point[]{new Point(1d, 3d), new Point(2d, 3d), new Point(2d, 2d), new Point(3d, 3d), new Point(4d, 7d), new Point(0d, 7d)};
        Double axis = getVerticalAxisByCenter(points);
        assert axis == 2d;
        axis = getVerticalAxisByMedian(points);
        assert axis == 2d;
    }

    private static Double getVerticalAxisByCenter(Point[] points) {
        Arrays.sort(points);
        Double axis = Arrays.stream(points).reduce(0d, (aDouble, point) -> aDouble + point.x, (aDouble, aDouble2) -> aDouble + aDouble2) / points.length,
                previousY = null;
        Stack<Point> pointStack = new Stack<>();
        for (Point p : points) {
            Double pointRelativeX = p.x - axis;
            if (pointRelativeX != 0d) {
                if (previousY == null) {
                    previousY = p.y;
                    pointStack.push(p);
                } else if (!previousY.equals(p.y)) {
                    if (!pointStack.isEmpty()) {
                        throw new IllegalArgumentException("There's no vertical axis");
                    }
                    pointStack.clear();
                    previousY = p.y;
                    pointStack.push(p);
                } else {
                    if (Math.abs(pointStack.peek().x - axis) == Math.abs(pointRelativeX)) {
                        pointStack.pop();
                    } else {
                        pointStack.push(p);
                    }
                }
            }
        }
        if (!pointStack.isEmpty()) {
            throw new IllegalArgumentException("There's no vertical axis");
        } else {
            return axis;
        }
    }

    private static Double getVerticalAxisByMedian(Point[] points) {
        Arrays.sort(points);
        Double axis = null,
                previousAxis = null,
                previousY = null;
        int cutStart = 0, cutEnd = 0;
        for (int i = 0, pointsLength = points.length; i < pointsLength; i++) {
            Point p = points[i];
            if (previousY == null) {
                previousY = p.y;
                cutStart = i;
                cutEnd = i;
            } else if (!previousY.equals(p.y)) {
                final int cutLength = cutEnd - cutStart;
                axis = cutLength % 2 == 0 ? points[cutStart + cutLength / 2].x : (points[cutStart + cutLength / 2].x + points[cutStart + cutLength / 2 + 1].x) / 2d;
                if (previousAxis != null && !axis.equals(previousAxis)) {
                    throw new IllegalArgumentException("There's no vertical axis");
                } else {
                    previousAxis = axis;
                    previousY = p.y;
                    cutStart = i;
                    cutEnd = i;
                }

            } else {
                cutEnd = i;
            }
        }
        final int cutLength = cutEnd - cutStart;
        axis = cutLength % 2 == 0 ? points[cutStart + cutLength / 2].x : (points[cutStart + cutLength / 2].x + points[cutStart + cutLength / 2 + 1].x) / 2d;
        if (previousAxis != null && !axis.equals(previousAxis)) {
            throw new IllegalArgumentException("There's no vertical axis");
        }
        return axis;
    }
}
