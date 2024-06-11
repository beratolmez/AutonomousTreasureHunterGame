package application;

import java.util.Random;

public class Wall extends Obstacle{

	public Wall(int mapSize, int[][] mapMatris) {
		super(mapSize, mapMatris, "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/wall.png");
	}
	
	int[] createRandomCoordinates(int mapSize, int[][] mapMatris) {
        Random localRandom = new Random();
        int x, y;

        do {
        	x = localRandom.nextInt((mapSize/2))+(mapSize/2)-2;
        	if(x<mapSize/2) {
        		x=(mapSize/2)+3;
        	}
            y = localRandom.nextInt(mapSize - 2) + 3;
        } while (isCoordinateOccupied(x, y, mapMatris));
        
        for(int i = 0; i < 10; i++) {
        	for (int j = 0; j < 1; j++) {
        		mapMatris[x+i][y+j] = 1;
        		
        	}
        }
        return new int[]{x, y};
    }
	
	@Override
    boolean isCoordinateOccupied(int x, int y, int[][] mapMatris) {
        for(int i = 0; i < 10; i++) {
        	for (int j = 0; j < 1; j++) {
                if (mapMatris[x+i][y+j] == 1 || mapMatris[x+i][y+j] == 2) {
                    return true;
                }
        	}
        }
        return false;
    }
	
	
	
}
