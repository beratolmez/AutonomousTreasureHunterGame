package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Sis {

	private int x;
	private int y;
	String imagePath;
	private Image image;
	private ImageView imageView;
	static List<Sis> sislist = new ArrayList<>();
	

	static int[][] sisMap = new int[ProjectH.mapsize2][ProjectH.mapsize2];
	

	public Sis(int mapSize, String imagePath,int x,int y) {
		this.x =x;
		this.y=y;
		this.imagePath = imagePath;
        this.image = new Image(imagePath);
        this.imageView = new ImageView(image);
        
	}
	
	
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
	public ImageView getImageView() {
    	return imageView;
    }
	
    public void setImageView(int size) {
        double targetWidth = size;
        double targetHeight = size;
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
        
       
    }
	
	
	
	
	
	
	
	
	
	
	
	

	
static String path="file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/sis.png";
	
	
public static void addSisToGrid(GridPane gridPane, int cellSize, Character character) {
   

   
    for (int row = 0; row < gridPane.getRowCount(); row++) {
        for (int col = 0; col < gridPane.getColumnCount(); col++) {
            if (Math.abs(col - character.getX()) > 3 || Math.abs(row - character.getY()) > 3) {
                Sis sis = new Sis(ProjectH.mapsize2, path, col, row);
                sis.setImageView(cellSize); 
                gridPane.add(sis.getImageView(), col, row);
                sislist.add(sis);
                sisMap[row][col]=1;
            }
        }
    }
   
}
	

public static void sisguncelle(GridPane gridpane,List<Sis> sislist,Character character,int a,int b) {
	
	for(Sis sd:sislist) {
	for (int row = 0; row < ProjectH.mapsize2; row++) {
        for (int col = 0; col < ProjectH.mapsize2; col++) {
            if (Math.abs(sd.getX() - a) < 4 && Math.abs(sd.getY() - b) < 4) {
              sd.getImageView().setVisible(false);
            }
        }
        
	}
        
        
    }
	
	
	
	
	
}



public static void sisguncelle2(GridPane gridpane,int a,int b) {
	
	
	
	for(Sis sd:sislist) {
	for (int row = 0; row < ProjectH.mapsize2; row++) {
        for (int col = 0; col < ProjectH.mapsize2; col++) {
            if (Math.abs(sd.getX() - a) < 4 && Math.abs(sd.getY() - b) < 4) {
            	if (row == sd.getX() && col == sd.getY()) {
            		if(sd instanceof Sis) {
                    //sd.getImageView().setVisible(false);
                    
                    sisMap[col][row] = 0;
                    
            		}
                }
              
            }
        }
        
	}
        
        
    }
	
	
	
	
	
}


public static void sisguncelle22(GridPane gridpane,int a,int b) {
	
	
	
	for(Sis sd:sislist) {
	for (int row = 0; row < ProjectH.mapsize2; row++) {
        for (int col = 0; col < ProjectH.mapsize2; col++) {
            if (Math.abs(sd.getX() - a) < 4 && Math.abs(sd.getY() - b) < 4) {
            	if (row == sd.getX() && col == sd.getY()) {
            		if(sd instanceof Sis) {
                    sd.getImageView().setVisible(false);
                    
                    sisMap[col][row] = 0;
                    
            		}
                }
              
            }
        }
        
	}
        
        
    }
	
	
	
	
	
}
	


public static List<int[]> gorunurbolge_sandiklar(int [][]mapMatris) {
    List<int[]> sandikkordinat = new ArrayList<>();

    for (int row = 0; row < ProjectH.mapsize2; row++) {
        for (int col = 0; col < ProjectH.mapsize2; col++) {
            if (sisMap[row][col] == 0 && mapMatris[row][col] == 2) {
                int[] coordinate = {col, row};
                sandikkordinat.add(coordinate);
                mapMatris[row][col] =0;
            }
        }
    }

    return sandikkordinat;
}

public static boolean gorunurbolge_sandiklarvarmi(int [][]mapMatris) {
    List<int[]> sandikkordinat = new ArrayList<>();
    int control=0;
    for (int row = 0; row < ProjectH.mapsize2; row++) {
        for (int col = 0; col < ProjectH.mapsize2; col++) {
            if (sisMap[row][col] == 0 && mapMatris[row][col] == 2) {
            
                control=1;
               
            }
        }
    }
    if(control==1) {
    	return true;
    }
    else {
    return false;
    }
}



	
	
	
}
