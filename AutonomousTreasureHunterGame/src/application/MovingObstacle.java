package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;

public abstract class MovingObstacle extends Obstacle{	
	
	public MovingObstacle(int mapSize, int[][] mapMatris, String imagePath) {
		super(mapSize, mapMatris, imagePath);
	}
	
	public abstract void moveM(GridPane gridPane);
	public abstract void renklendir(GridPane mapGrid);
	
	
	public void move(int startX, int startY, int endX, int endY, int durationSeconds) {
      
        int durationMillis = durationSeconds * 1000;

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(javafx.util.Duration.ZERO, 
                new KeyValue(getImageView().translateXProperty(), startX),
                new KeyValue(getImageView().translateYProperty(), startY)),
            new KeyFrame(javafx.util.Duration.millis(durationMillis), 
                new KeyValue(getImageView().translateXProperty(), endX),
                new KeyValue(getImageView().translateYProperty(), endY))
        );
        
        timeline.setOnFinished(e -> {
          
            setX(endX);
            setY(endY);
        });
        
        timeline.play();
        
    }
	public abstract void standart_harekete_basla(int p);
	
	public void move_tppp(GridPane gridPane, int endX, int endY) {
		
		 gridPane.getChildren().remove(getImageView());
	        GridPane.setColumnIndex(getImageView(), endX);
	        GridPane.setRowIndex(getImageView(), endY);
	        gridPane.getChildren().add(getImageView());
		
		
	       
	}
	
	
	
}
