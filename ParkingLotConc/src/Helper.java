public class Helper {
    void print(String s) {
        System.out.print(s);
    }

    void println(String s) {
        print(s + "\n");
    }

    String getSpotId(int floor, int row, int column) {
        return floor + "-" + row + "-" + column;
    }

    Integer[] getSpotLocation(String spotId) {
        Integer[] location = {-1, -1, -1};
        String[] ss = spotId.split("-");
        for (int i = 0; i < 3 && i < ss.length; i++) {
            location[i] = Integer.parseInt(ss[i]);
        }
        return location;
    }
}