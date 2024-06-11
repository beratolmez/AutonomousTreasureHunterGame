package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Element {
	
	private int x;
	private int y;
	String imagePath;
	private Image image;
	private ImageView imageView;
	public static String[][] takip = new String[ProjectH.mapsize2][ProjectH.mapsize2];
	
	public Element(int mapSize, int[][] mapMatris, String imagePath) {
		//transpoze(mapMatris);
		int[] coordinates = createRandomCoordinates(mapSize, mapMatris);
        this.x = coordinates[0];
        this.y = coordinates[1];
		this.imagePath = imagePath;
        this.image = new Image(imagePath);
        this.imageView = new ImageView(image);
        
	}
	/*
	public int getobjsize() {
		return objsize;
	}
	public void setobjsize(int objsize) {
    	this.objsize = objsize;
    }*/
	
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
    
    public void setImageView(int width, int height) {
        double targetWidth = width;
        double targetHeight = height;
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
    }
	
    abstract int[] createRandomCoordinates(int mapSize, int[][] mapMatris);

    abstract boolean isCoordinateOccupied(int x, int y, int[][] mapMatris);
    
    
    public static int[][] transpoze(int [][]mapMatris) {
    	
    	
        int[][] transpose = new int[mapMatris[0].length][mapMatris.length];

       
        for (int i = 0; i < mapMatris.length; i++) {
            for (int j = 0; j < mapMatris[0].length; j++) {
                transpose[j][i] = mapMatris[i][j];
            }
        }
        return transpose;
        
    	
    	
    }
    public List<Element> nearestNeighborSort(List<Element> elements) {
        
        int startingIndex = 0;

        List<Element> sortedList = new ArrayList<>();
        List<Integer> usedIndices = new ArrayList<>();
        sortedList.add(elements.get(startingIndex));
        usedIndices.add(startingIndex);
        int n = elements.size();

        while (sortedList.size() < n) {
            double minDistance = Double.POSITIVE_INFINITY;
            int closestIndex = -1;

            for (int i = 0; i < n; i++) {
                if (!usedIndices.contains(i)) {
                    double currentDistance = distance(sortedList.get(sortedList.size() - 1), elements.get(i));
                    if (currentDistance < minDistance) {
                        minDistance = currentDistance;
                        closestIndex = i;
                    }
                }
            }

            sortedList.add(elements.get(closestIndex));
            usedIndices.add(closestIndex);
        }

        return sortedList;
    }
    public double distance(Element e1, Element e2) {
        int x1 = e1.getX();
        int y1 = e1.getY();
        int x2 = e2.getX();
        int y2 = e2.getY();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
