package application;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bird extends MovingObstacle{
	public static int objsize=2;
	
	public Bird(int mapSize, int[][] mapMatris) {
		super(mapSize, mapMatris, "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/Bird_Cartoon.png");
	}

	
	int[] createRandomCoordinates(int mapSize, int[][] mapMatris) {
        Random localRandom = new Random();
        int x, y;

        do {
            x = localRandom.nextInt(mapSize - 5);
            y = localRandom.nextInt(mapSize - 5);
        } while (isCoordinateOccupied(x, y, mapMatris));
        
        for(int i = 0; i < 4; i++) {
        	for (int j = 0; j < 8; j++) {
        		mapMatris[x+i][y+j] = 1;
        	}
        }
        return new int[]{x, y};
    }
	
	@Override
    boolean isCoordinateOccupied(int x, int y, int[][] mapMatris) {
        for(int i = 0; i < 4; i++) {
        	for (int j = 0; j < 8; j++) {
                if (mapMatris[x+i][y+j] == 1 || mapMatris[x+i][y+j] == 2) {
                    return true;
                }
        	}
        }
        return false;
    }
	
	
	
	@Override
	public void standart_harekete_basla(int p) {
	    int startX = getX();
	    int startY = getY();
	    
	    KeyFrame firstMove = new KeyFrame(javafx.util.Duration.seconds(2), e -> {
	        move(startX, startY, startX , startY+ 4 * p, 1);
	    });
	    
	    KeyFrame secondMove = new KeyFrame(javafx.util.Duration.seconds(4), e -> {
	        move(startX , startY+ 4 * p, startX, startY, 1);
	    });
	    
	    Timeline animationTimeline = new Timeline(firstMove, secondMove);
	    animationTimeline.setCycleCount(Animation.INDEFINITE);
	    animationTimeline.play();
	}
	/*
	@Override
	public void moveM(GridPane gridPane) {
	    
	    Timeline timeline = new Timeline();
	    KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(0.5), e -> {
			int i = 1, j = 5;
			if (i < 7) {
				gridPane.getChildren().remove(getImageView());
				GridPane.setColumnIndex(getImageView(), getX() + i);
				GridPane.setRowIndex(getImageView(), getY());
				GridPane.setRowSpan(getImageView(), 2);
				GridPane.setColumnSpan(getImageView(), 2);
				gridPane.getChildren().add(getImageView());
				i++;
			}
			else {
				gridPane.getChildren().remove(getImageView());
			    GridPane.setColumnIndex(getImageView(), getX() + j);
			    GridPane.setRowIndex(getImageView(), getY());
	            GridPane.setRowSpan(getImageView(), 2);
	            GridPane.setColumnSpan(getImageView(), 2);
			    gridPane.getChildren().add(getImageView());
			    j--;
			    if(j == 0) {
			    	j = 5;
			    }
			}
	    });
	    timeline.getKeyFrames().add(keyFrame);
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();	
	}
	*/
	
	

    public void renklendir(GridPane mapGrid) {
    Region translucentRedRegion = new Region();
    translucentRedRegion.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
    mapGrid.add(translucentRedRegion, getX(), getY(), 2, 6);
    }


	@Override
	public void moveM(GridPane gridPane) {
		// TODO Auto-generated method stub
		
	}

}
