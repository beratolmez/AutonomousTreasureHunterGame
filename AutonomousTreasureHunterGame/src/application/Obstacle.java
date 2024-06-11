
package application;

import java.util.Random;

public class Obstacle extends Element{	
	
	
	
	public Obstacle(int mapSize, int[][] mapMatris, String imagePath) {
		super(mapSize, mapMatris, imagePath);
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