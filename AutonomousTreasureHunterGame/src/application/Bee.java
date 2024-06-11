package application;

import java.time.Duration;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bee extends MovingObstacle{
	public static int objsize=2;
	public Bee(int mapSize, int[][] mapMatris) {
		super(mapSize, mapMatris, "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/bee.png");
		
	}
	
	 int[] createRandomCoordinates(int mapSize, int[][] mapMatris) {
	        Random localRandom = new Random();
	        int x, y;

	        do {
	            x = localRandom.nextInt(mapSize - 5) + 3;
	            y = localRandom.nextInt(mapSize - 5);
	        } while (isCoordinateOccupied(x, y, mapMatris));
	        
	        for(int i = 0; i < 8; i++) {
	        	for (int j = 0; j < 4; j++) {
	        		mapMatris[x+i][y+j] = 1;
	        	}
	        }
	        return new int[]{x, y};
	    }
		
		@Override
	    boolean isCoordinateOccupied(int x, int y, int[][] mapMatris) {
	        for(int i = 0; i < 8; i++) {
	        	for (int j = 0; j < 3; j++) {
	                if (mapMatris[x+i][y+j] == 1 || mapMatris[x+i][y+j] == 2) {
	                    return true;
	                }
	        	}
	        }
	        return false;
	    }
	
	
	
	public void standart_harekete_basla(int p) {
	    int startX = getX();
	    int startY = getY();
	    
	    KeyFrame firstMove = new KeyFrame(javafx.util.Duration.seconds(1), e -> {
	        move(startX, startY, startX + 4 * p, startY, 1);
	    });
	    
	    KeyFrame secondMove = new KeyFrame(javafx.util.Duration.seconds(2), e -> {
	        move(startX + 4 * p, startY, startX, startY, 1);
	    });
	    
	    Timeline animationTimeline = new Timeline(firstMove, secondMove);
	    animationTimeline.setCycleCount(Animation.INDEFINITE);
	    animationTimeline.play();
	}

	@Override
	public void moveM(GridPane gridPane) {
		// TODO Auto-generated method stub
		
	}

	
	 public void standart_harekete_basla2(GridPane gridPane) {
		 int currentindex=0;
		 int startX = getX();
		    int startY = getY();
	        int[] currentIndexHolder = {0};
	        KeyFrame firstMove = new KeyFrame(javafx.util.Duration.seconds(0.5 * currentIndexHolder[0]), e -> {
	            for (int i = 0; i < 5; i++) {
	                move_tppp(gridPane, startX + 1, startY);
	            }
	            currentIndexHolder[0]++; // currentIndexHolder'ın elemanını artır
	        });

	        KeyFrame secondMove = new KeyFrame(javafx.util.Duration.seconds(0.5 * (currentIndexHolder[0] + 1)), e -> {
	            for (int i = 0; i < 5; i++) {
	                move_tppp(gridPane, startX - 1, startY);
	            }
	            //currentIndexHolder[0]++; // currentIndexHolder'ın elemanını artır
	        });

	        Timeline animationTimeline = new Timeline(firstMove, secondMove);
	        animationTimeline.setCycleCount(Animation.INDEFINITE);
	        animationTimeline.play();
	    }
	
	

	/*public void moveM(GridPane gridPane) {
	    
	    Timeline timeline = new Timeline();
	    KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(0.5), e -> {
	    	move(gridPane);
	    });
	    timeline.getKeyFrames().add(keyFrame);
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();	
	}
	*/
    public void renklendir(GridPane mapGrid) {
    Region translucentRedRegion = new Region();
    translucentRedRegion.setStyle("-fx-background-color: rgba(200, 0, 0, 0.5);");
    mapGrid.add(translucentRedRegion, getX(), getY(), 6, 2);
    }
	
}
