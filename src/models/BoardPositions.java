package models;

public class BoardPositions {
    public int[] getLayoutForPlayerInsideBox(int playerNum, int[] layout) {
        if (playerNum == 1) {
            return new int[] {layout[0] + 33, layout[1]};
        } else if (playerNum == 2) {
            return new int[] {layout[0], layout[1] + 33};
        } else if (playerNum == 3) {
            return new int[] {layout[0] + 33, layout[1] + 33};
        } else {
            return layout;
        }
    }

    public int[] getLayoutFromBoxNumber(int boxNum, int playerNum) {
        boolean moveInsideBox = true;
        int[] newLayout = new int[] {0, 0};

        if (boxNum < 10) {
            newLayout = new int[] {713 - 66 * boxNum, 741};
        } else if (boxNum == 10) {
            moveInsideBox = false;
            newLayout = new int[] {14, 710 + playerNum * 26};
        } else if (boxNum < 20) {
            newLayout = new int[] {33, 647 - 66 * (boxNum - 11)};
        } else if (boxNum < 31) {
            newLayout = new int[] {55 + 66 * (boxNum - 20), 27};
        } else if (boxNum < 40) {
            newLayout = new int[] {740, 121 + 66 * (boxNum - 31)};
        }

        if (moveInsideBox) {
            newLayout = getLayoutForPlayerInsideBox(playerNum, newLayout);
        }

        return newLayout;
    }
}
