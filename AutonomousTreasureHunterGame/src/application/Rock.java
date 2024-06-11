package application;

import java.util.Random;

public class Rock extends SummerObstacle{
	public static int objsize=1;
	public Rock(int mapSize, int[][] mapMatris) {
		super(mapSize, mapMatris, "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/Kahverengi_Kaya.png");
	}

	
	
	@Override
    int[] createRandomCoordinates(int mapSize, int[][] mapMatris) {
        Random localRandom = new Random();
        int x, y;

        do {
        	x = localRandom.nextInt(mapSize);
            y = localRandom.nextInt(mapSize);
        } while (isCoordinateOccupied(x, y, mapMatris));
        
        mapMatris[x][y] = 1;
        return new int[]{x, y};
    }
	
	@Override
    boolean isCoordinateOccupied(int x, int y, int[][] mapMatris) {
            if (mapMatris[x][y] == 1 || mapMatris[x][y] == 2) {
                return true;
            }
        return false;
    }
}