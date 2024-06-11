package application;

import java.util.Random;

public class Summer_Mountain extends SummerObstacle {

	public static int objsize=15;
	public Summer_Mountain(int mapSize, int[][] mapMatris) {
		super(mapSize, mapMatris, "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/Mountain3.png");
	}
	
	@Override
    int[] createRandomCoordinates(int mapSize, int[][] mapMatris) {
        Random localRandom = new Random();
        int x, y;

        do {
        	x = localRandom.nextInt((mapSize/2))+(mapSize/2)-objsize;
        	if(x<mapSize/2) {
        		x=(mapSize/2)+3;
        	}
            y = localRandom.nextInt(mapSize - objsize) + 3;
        } while (isCoordinateOccupied(x, y, mapMatris));
        
        for(int i = 0; i < objsize; i++) {
        	for (int j = 0; j < objsize; j++) {
        		mapMatris[x+i][y+j] = 1;
        		
        	}
        }
        return new int[]{x, y};
    }
	
	@Override
    boolean isCoordinateOccupied(int x, int y, int[][] mapMatris) {
        for(int i = 0; i < objsize; i++) {
        	for (int j = 0; j < objsize; j++) {
                if (mapMatris[x+i][y+j] == 1 || mapMatris[x+i][y+j] == 2) {
                    return true;
                }
        	}
        }
        return false;
    }
	
	
}
