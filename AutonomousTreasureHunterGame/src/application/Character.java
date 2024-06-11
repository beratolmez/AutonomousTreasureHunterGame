
package application;

import java.time.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Character extends Element{
	public static int objsize=1;

	public Character(int mapSize, int[][] mapMatris) {
		super(mapSize, mapMatris, "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/King.png");
		
	}
	
	VBox vbox = new VBox();
	
	@Override
    int[] createRandomCoordinates(int mapSize, int[][] mapMatris) {
        Random localRandom = new Random();
        int x, y;

        do {
            x = localRandom.nextInt(mapSize);
            y = localRandom.nextInt(mapSize);
        } while (isCoordinateOccupied(x, y, mapMatris));
        
        //mapMatris[x][y] = 1;
        return new int[]{x, y};
    }
	
	@Override
    boolean isCoordinateOccupied(int x, int y, int[][] mapMatris) {
            if (mapMatris[x][y] == 1 || mapMatris[x][y] == 2) {
                return true;
            }
        return false;
    }
   

	public void move(int startX, int startY, int endX, int endY, int cellSize, int durationSeconds, Runnable onFinished) {
	   
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
	        // Hareket tamamlandığında karakterin konumunu güncelle
	    	
	    	setX(((endX-startX)/cellSize)+startX);
	    	setY(((endY-startY)/cellSize)+startY);
	    	
	        onFinished.run();
	        // Hareket tamamlandığında başka işlemler yapılabilir
	        System.out.println("Hareket tamamlandı!");
	        System.out.println(getX() + " omm " + getY());
	    });
	    System.out.println(getX() + " omm " + getY());
	    timeline.play();
	}
	

	
	public void sandik_topla(int[][] mapMatris, int cellSize) {
	    int startX = getX();
	    int startY = getY();

	    // Find the nearest cell with value 1 horizontally
	    int endX = startX;
	    while (endX < mapMatris.length && mapMatris[endX][startY] != 1) {
	        endX++;
	    }

	    // Move the character horizontally to the found cell
	    move(startX, startY, startX+((endX-startX)*cellSize ), startY , cellSize, 3, () -> {
	        // Perform additional actions when movement is finished, if needed
	        System.out.println("Sandık toplandı!");
	    });
	}
	
    public void renklendir(GridPane mapGrid, int endX, int endY) {
    Region translucentRedRegion = new Region();
    translucentRedRegion.setStyle("-fx-background-color: rgba(0, 255, 0, 1);");
    mapGrid.add(translucentRedRegion, endX, endY, 1, 1);
    }
    
	public void move_tppp(GridPane gridPane, int endX, int endY) {
		
		renklendir(gridPane, endX, endY);
		gridPane.getChildren().remove(getImageView());
	    GridPane.setColumnIndex(getImageView(), endX);
	    GridPane.setRowIndex(getImageView(), endY);
	    gridPane.getChildren().add(getImageView());
	       
	}
	
	public void move_tp(GridPane gridPane, int endX, int endY) {
		
		Thread movementThread = new Thread(() -> {
		    try {
		        Thread.sleep(6000); // Delay in milliseconds (1000ms = 1 second)
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    Platform.runLater(() -> {
		        gridPane.getChildren().remove(getImageView());
		        GridPane.setColumnIndex(getImageView(), endX);
		        GridPane.setRowIndex(getImageView(), endY);
		        gridPane.getChildren().add(getImageView());
		    });
		});
	
		movementThread.start();
	
		
		
	
		
		
	
        
    }
	
	public void move_tp2(GridPane gridPane, int endX, int endY, int delayMillis) {
        PauseTransition pause = new PauseTransition(javafx.util.Duration.millis(delayMillis));
        pause.setOnFinished(event -> {
            Platform.runLater(() -> {
                gridPane.getChildren().remove(getImageView());
                GridPane.setColumnIndex(getImageView(), endX);
                GridPane.setRowIndex(getImageView(), endY);
                gridPane.getChildren().add(getImageView());
            });
        });
        pause.play();
    }

	public void move_tp3(GridPane gridPane, int endX, int endY, Runnable afterMove) {
	    Thread movementThread = new Thread(() -> {
	        try {
	            Thread.sleep(3000); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        Platform.runLater(() -> {
	            gridPane.getChildren().remove(getImageView());
	            GridPane.setColumnIndex(getImageView(), endX);
	            GridPane.setRowIndex(getImageView(), endY);
	            gridPane.getChildren().add(getImageView());
	            afterMove.run(); 
	            setX(endX);
	            setY(endY);
	            System.out.println(getX() + " omm " + getY());
	        });
	    });

	    movementThread.start();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 private static boolean dfs(int[][] grid, int y, int x, int targetY, int targetX, boolean[][] visited, List<int[]> path) {
	        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length || grid[y][x] == 1 || visited[y][x]) {
	            return false;
	        }

	        path.add(new int[]{x, y});
	        visited[y][x] = true;

	        if (x == targetX && y == targetY) {
	            return true;
	        }

	        if (dfs(grid, y + 1, x, targetY, targetX, visited, path) ||
	                dfs(grid, y - 1, x, targetY, targetX, visited, path) ||
	                dfs(grid, y, x + 1, targetY, targetX, visited, path) ||
	                dfs(grid, y, x - 1, targetY, targetX, visited, path)) {
	            return true;
	        }

	        
	        path.remove(path.size() - 1);
	        return false;
	    }
	
	
	
	public  List<int[]> findPath(int[][] grid, int startX, int startY, int targetX, int targetY) {
        List<int[]> path = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        if (dfs(grid, startY, startX, targetY, targetX, visited, path)) {
            return path;
        } else {
            return new ArrayList<>();
        }
    }
	
	
	
	
	 private int currentIndex = 0;
	
	
	
	public void sandiklarra_git(int [][]mapMatris,List<Element> elements, GridPane gridPane) {
		
		int i=0,j=0;
		List<int[]> path;
		
        
		int []yakinsandik=en_yakin_sandik_nerede( getX(), getY(), transpoze(mapMatris));
		System.out.println(yakinsandik[0]+"   "+yakinsandik[1]);
		//path = findPath(mapMatris, getX(), getY(), yakinsandik[0], yakinsandik[1]);
		
		path=ShortestPathFinder.findShortestPath(mapMatris, getX(), getY(), yakinsandik[0], yakinsandik[1]);
		if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found:");
            for (int[] coordinate : path) {
                System.out.println("(" + coordinate[0] + ", " + coordinate[1] + ")");
            }
        }	
		
		Timeline timeline = new Timeline();
		for(int []koordinat:path) {
			
			KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(currentIndex*0.5), event -> move_tppp(gridPane, koordinat[0], koordinat[1]));
            timeline.getKeyFrames().add(keyFrame);
            currentIndex++;
            setX(koordinat[0]);
	        setY(koordinat[1]);
	           
	        
	        }

	       
			
		int finalX = yakinsandik[0];
	    int finalY = yakinsandik[1];
	    KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds((currentIndex + 1) * 0.5), event -> {
	        for (Element ins : elements) {
	            if ((ins.getX() == finalX) && (ins.getY() == finalY) && (ins instanceof Chest)) {
	                ins.getImageView().setVisible(false);
	                mapMatris[yakinsandik[0]][yakinsandik[1]]=0;
	               
	                System.out.println("sandik toplandi "+getX() +" "+getY());
	                
	                
	            }
	        }
	       
	       
	        
	    });
	    timeline.getKeyFrames().add(keyFrame);
		 
		System.out.println("(lol" + getX() + ", " + getY() + ")");
	
		
		
		 timeline.play();
		 
		 
		 
		 
		 
        
        
		
	}
	
	
	
	public void sandiklara_gitonemli(int[][] mapMatris, List<Element> elements, GridPane gridPane) {
	    for (Element element : elements) {
	        if (element instanceof Chest) { 
	            Chest chest = (Chest) element;
	           
	            int chestX = chest.getX();
	            int chestY = chest.getY();
	            
	       
	            List<int[]> path = ShortestPathFinder.findShortestPath(mapMatris, getX(), getY(), chestX, chestY);
	            
	          
	            if (path.isEmpty()) {
	                System.out.println("No path found to chest at (" + chestX + ", " + chestY + ")");
	                continue;
	            }
	            
	     
	            Timeline timeline = new Timeline();
	            for (int[] coordinate : path) {
	                KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(currentIndex * 0.05), event -> move_tppp(gridPane, coordinate[0], coordinate[1]));
	                timeline.getKeyFrames().add(keyFrame);
	                currentIndex++;
	                setX(coordinate[0]);
	                setY(coordinate[1]);
	            }
	            
	      
	            KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds((currentIndex + 1) * 0.05), event -> {
	                chest.getImageView().setVisible(false); 
	                mapMatris[chestX][chestY] = 0; 
	                System.out.println("Chest at (" + chestX + ", " + chestY + ") collected");
	            });
	            timeline.getKeyFrames().add(keyFrame);
	            
	          
	            timeline.play();
	        }
	    }
	}
	public void printChestFind(int chestX, int chestY, BorderPane borderPane, VBox vbox) {
		
		Label messageLabel = new Label("Chest at (" + chestX + ", " + chestY + ") collected");
		vbox.getChildren().add(messageLabel);
		vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        messageLabel.setMaxWidth(300);
        messageLabel.setMaxHeight(100);
        borderPane.setRight(vbox);
	}
	public void sandiddklara_git(int[][] mapMatris, List<Element> elements, GridPane gridPane) {
		
		
	    for (Element element : elements) {
	        if ((element instanceof Chest)) { 
	            Chest chest = (Chest) element;
	        
	            int chestX = chest.getX();
	            int chestY = chest.getY();
	            
	          
	            List<int[]> path = ShortestPathFinder.findShortestPath(mapMatris, getX(), getY(), chestX, chestY);
	            
	            
	            if (path.isEmpty()) {
	                System.out.println("No path found to chest at (" + chestX + ", " + chestY + ")");
	                continue;
	            }
	            
	           
	            Timeline timeline = new Timeline();
	            for (int[] coordinate : path) {
	                KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(currentIndex * 0.05), event -> move_tppp(gridPane, coordinate[0], coordinate[1]));
	                timeline.getKeyFrames().add(keyFrame);
	                currentIndex++;
	                setX(coordinate[0]);
	                setY(coordinate[1]);
	            }
	            
	          
	            KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds((currentIndex + 1) * 0.05), event -> {
	                chest.getImageView().setVisible(false); 
	                mapMatris[chestX][chestY] = 0; 
	                System.out.println("Chest at (" + chestX + ", " + chestY + ") collected");
	            });
	            timeline.getKeyFrames().add(keyFrame);
	            
	         
	            timeline.play();
	        }
	    }
	    }
	
public void sandiklara_git(int[][] mapMatris, List<Element> elements, GridPane gridPane, BorderPane borderPane) {
		
	List<Element> elements2 = new ArrayList<>();
	List<Element> elements3 = new ArrayList<>();
	for (Element element : elements) {
		if ((element instanceof Character)) {
			
			elements2.add(element);
		}
		
	}
	for (Element element : elements) {
		if ((element instanceof Chest)) {
			elements2.add(element);
			}
		}
		
	elements3=nearestNeighborSort(elements2);
	
	
	    for (Element element : elements3) {
	        if ((element instanceof Chest)) {
	            Chest chest = (Chest) element;
	            
	            int chestX = chest.getX();
	            int chestY = chest.getY();
	            
	            
	            List<int[]> path = ShortestPathFinder.findShortestPath(mapMatris, getX(), getY(), chestX, chestY);
	            
	          
	            if (path.isEmpty()) {
	                System.out.println("No path found to chest at (" + chestX + ", " + chestY + ")");
	                continue;
	            }
	            
	           
	            Timeline timeline = new Timeline();
	            for (int[] coordinate : path) {
	            	KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(currentIndex * 0.1), event -> {
	                    move_tppp(gridPane, coordinate[0], coordinate[1]);
	                    Sis.sisguncelle(gridPane, Sis.sislist, ProjectH.character2,coordinate[0],coordinate[1]);
	                });
	                timeline.getKeyFrames().add(keyFrame);
	                currentIndex++;
	                setX(coordinate[0]);
	                setY(coordinate[1]);
	               
	                
	            }
	            
	            KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds((currentIndex + 1) * 0.1), event -> {
	                chest.getImageView().setVisible(false); 
	                mapMatris[chestX][chestY] = 0; 
	                System.out.println("Chest at (" + chestX + ", " + chestY + ") collected");
	                printChestFind(chestX, chestY, borderPane, vbox);
	            });
	            timeline.getKeyFrames().add(keyFrame);
	            
	            // Animasyonu başlat
	            timeline.play();
	        }
	    }
	    }



	
	public int [] en_yakin_sandik_nerede(int x,int y,int [][]mapMatris){
		
		
		
		int enYakinMesafe = 5000;
        int[] enYakinKonum = new int[2];
        int mesafe ;
        for (int p = 0; p < mapMatris.length; p++) {
            for (int q = 0; q < mapMatris[p].length; q++) {
                if (mapMatris[p][q] == 2) {
                     mesafe = Math.abs(p - getX()) + Math.abs(q - getY());
                    if (mesafe < enYakinMesafe) {
                        enYakinMesafe = mesafe;
                        enYakinKonum[0] = p;
                        enYakinKonum[1] = q;
                    }
                }
            }
        }
        
        System.out.println(enYakinKonum[0]+" momo "+enYakinKonum[1]);
        
        return enYakinKonum;
		
		
		
		
	}
	
	
	

	private boolean gidilebilirmi(int x, int y, int[][] mapMatris) {
	    
	    if (x >= 0 && x < mapMatris.length && y >= 0 && y < mapMatris[0].length && mapMatris[x][y] != 1) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	
	
	
	public static int xx=0;
	public static int yy=0;
	
	public void sandiklara_git_7x7(int[][] mapMatris, List<Element> elements, GridPane gridPane,BorderPane borderPane) {
		
		int tekrarsayisi=0;
		
		List<int[]> collectedChestss= new ArrayList<>();	
		List<int[]> toplandi= new ArrayList<>();
int chestsayisi=0;
		
		for(Element a:elements) {
			if(a instanceof Chest) {
				chestsayisi++;
			}
		}
		System.out.println(chestsayisi);
	xx=getX();
	yy=getY();
	List<int[]> yol = new ArrayList<>();
	int collected=0;
	
	while(collected<chestsayisi)
	{
	if(Sis.gorunurbolge_sandiklarvarmi(mapMatris)) {
	
		System.out.println("gorunurbolgedesandikvar");
		List<int[]> sandikkordinat = new ArrayList<>();
		
		sandikkordinat=Sis.gorunurbolge_sandiklar(mapMatris);
		
		
		
		
		for(int []s:sandikkordinat) {
			System.out.println("gorun=("+s[0]+","+s[1]+")");
		}
		
		
		for(int []sandik:sandikkordinat) {
			System.out.println("gorunurbolgesandikkordinatlari=("+sandik[0]+","+sandik[1]+")");
			List<int[]> path = new ArrayList<>();
			
			path=ShortestPathFinder.findShortestPath(mapMatris, xx, yy, sandik[0], sandik[1]);
			
			
			
			for(int []koordinat:path) {
				
                yol.add(koordinat);
               
                System.out.println(koordinat[0]+","+koordinat[1]);
                
                xx=koordinat[0];
    			yy=koordinat[1];
			
				
				if(xx==sandik[0]&&yy==sandik[1]) {
					collected++;
					toplandi.add(sandik);
					mapMatris[sandik[0]][sandik[1]]=0;
					System.out.println("lol");
				}
				Sis.sisguncelle2(gridPane,koordinat[0], koordinat[1]);
				
				
				
				
				
				
			}
			
			
			
		}
		
		
	}
	

	else if(!Sis.gorunurbolge_sandiklarvarmi(mapMatris)) {
		System.out.println("gorunurbolgedesandikyok");
		
		

		 Random rand = new Random();
		 int randomX = rand.nextInt(15)-7;
		 int randomY = rand.nextInt(15)-7;
		 int rx=0;
		 int ry=0;
		 
		 
		
	        for (int i = 0; i < Sis.sisMap.length; i++) {
	            for (int j = 0; j < Sis.sisMap.length; j++) {
	              if(Sis.sisMap[i][j]==1) {
	            	  
	            	  rx=i;
	            	  ry=j;
	            	 
	            	  break;
	            	  
	              }
	            }
	        }
		 
		 
		 
		 
		 
		 
		 if(gidilebilirmi((xx+randomX),(yy+randomY),mapMatris)){
			
			 
System.out.println((xx+randomX)+" random "+(yy+randomY));
			 List<int[]> path2 = new ArrayList<>();
			
			 
			path2=ShortestPathFinder.findShortestPath(mapMatris, xx, yy, rx, ry);
			 
			 for(int []kordinat:path2) {
				 
				 yol.add(kordinat);
				 
				 xx=kordinat[0];
	    			yy=kordinat[1];
					Sis.sisguncelle2(gridPane,kordinat[0], kordinat[1]);
					if(Sis.gorunurbolge_sandiklarvarmi(mapMatris)) {
						break;
					}
				 if(mapMatris[kordinat[0]][kordinat[1]]==2) {
		            	mapMatris[kordinat[0]][kordinat[1]]=0;
		            	collected++;
		            	toplandi.add(kordinat);
		            	
		            }
					
				 
				 
			 }
			 
			 
			 
			 
			 
			 
		 }
		 
		 
		 
		tekrarsayisi++;
		
		
		}
		if(tekrarsayisi>1000) {
			break;
		}
	
	}
	
	int z=0;
	List<Element> arrayl = new ArrayList<>();
	for(Element s:elements) {
		z=0;
		if(s instanceof Chest) {
			
			for(int k[]:yol) {
				
				if(s.getX()==k[0]&&s.getY()==k[1]) {
					
					z=1;
					break;
				}
				
				
			}
			if(z==0) {
				arrayl.add(s);
			}
			
		}
		
	}
	
	for(Element a:arrayl) {
		System.out.println(" "+a.getX()+" "+a.getY());
	}
	
	
	
	for(Element a:arrayl) {
		List<int []> pm = new ArrayList<>();
		pm=ShortestPathFinder.findShortestPath(mapMatris, xx, yy, a.getX(), a.getY());
		for(int []p:pm) {
			yol.add(p);
			xx=p[0];
			yy=p[1];
		}
		
	}
	
	Iterator<int[]> iterator = yol.iterator();
	int[] previous = null;
	while (iterator.hasNext()) {
	    int[] current = iterator.next();
	    if (Arrays.equals(current, previous)) {
	        iterator.remove(); 
	    } else {
	        previous = current;
	    }
	}
	
	
	for (int i = 0; i < Sis.sisMap.length; i++) {
	    for (int j = 0; j < Sis.sisMap[i].length; j++) {
	        System.out.print(Sis.sisMap[i][j] + " ");
	    }
	    System.out.println(); 
	}

	for(int []a:yol) {
		System.out.println(a[0]+" "+a[1]);
	}
	
	
	
	Timeline timeline = new Timeline();
	
	for (int []konum:yol){
		
		KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds( currentIndex*0.08), event -> {
            move_tppp(gridPane, konum[0], konum[1]);
            Sis.sisguncelle22(gridPane,konum[0],konum[1]);
            for (Element ins : elements) {
            	
                if ( (ins instanceof Chest)&&ins.getX()==konum[0]&&ins.getY()==konum[1]) {
                    ins.getImageView().setVisible(false);
                    System.out.println("Chest at (" + konum[0] + ", " + konum[1] + ") collected");
	                printChestFind(konum[0], konum[1], borderPane, vbox);
                    toplanansandik(ins,konum[0],konum[1]);
                   
                    
                }
               
            }
            
		 });
		
		
		timeline.getKeyFrames().add(keyFrame);
        currentIndex++;
        
		
		
		timeline.getKeyFrames().add(keyFrame);
        currentIndex++;
        setX(konum[0]);
        setY(konum[1]);
		
	}
	
	
	
	
	
	System.out.println("hareket "+yol.size()+" hamlede tamamlandi");
	timeline.play();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}	
			
	
	
	
	
	
	
	
	
	
	
	public static List<int[]> removeDuplicates(List<int[]> list) {
        Set<String> set = new HashSet<>();
        List<int[]> uniqueList = new ArrayList<>();

        for (int[] arr : list) {
            String key = arr[0] + "," + arr[1];
            if (set.add(key)) {
                uniqueList.add(arr);
            }
        }

        return uniqueList;
    }

	
	
	
	
public static void toplanansandik(Element chest,int x,int y) {
		if(chest instanceof BronzeChest) {
			
			System.out.println("Bronze Chest ("+x+","+y+") konumunda toplandi");
			return;
			
		}
if(chest instanceof EmeraldChest ) {
			
			System.out.println("Emerald Chest ("+x+","+y+") konumunda toplandi");
			return;
			
		}
		
if(chest instanceof GoldChest ) {
	
	System.out.println("Gold Chest ("+x+","+y+") konumunda toplandi");
	
	return;

		
		
		
		
	}
	

if(chest instanceof SilverChest ) {
	
	System.out.println("Silver Chest ("+x+","+y+") konumunda toplandi");
	
	return;

		
		
		
		
	}
	
	
	
	
	
}




public void sandiklara_git_son7x7(int[][] mapMatris, List<Element> elements, GridPane gridPane,BorderPane borderPane) {

	
	
	xx=getX();
	yy=getY();
	List<int[]> yol = new ArrayList<>();
	int kx=0;
	int ky=0;
	int collected=0;
	
	int gg=0;
	int loc=0;
	
	List<int[]> tut = new ArrayList<>();
	
	int chestsayisi=0;
	for(Element cc:elements) {
		if(cc instanceof Chest) {
			chestsayisi++;
		}
	}
	
	
	System.out.println(chestsayisi);
int om=0;
	while(collected<chestsayisi) {
		tut.clear();
		loc=1;
		for(int i=0;i<ProjectH.mapsize2;i++) {

			for(int j=0;j<ProjectH.mapsize2;j++) {
				if(Sis.sisMap[i][j]==1) {
					loc=0;
					break;
				}
			}
			
			if(loc==0) {
				break;
			}
		}
		
		
		
		
		
		
		
		
	while(true) {
		tut.clear();
		
		List<int[]> path = new ArrayList<>();	
		int k=0;
		int k2=0;
		
		 Random rand = new Random();
		  kx = rand.nextInt(ProjectH.mapsize2);
		  ky = rand.nextInt(ProjectH.mapsize2);
		
	om++;
	if(om>10000) {
		break;
	}
			
			
			if(mapMatris[kx][ky]!=1&&Sis.sisMap[kx][ky]==1&&mapMatris[kx][ky]!=2) {
				path=ShortestPathFinder.findShortestPath(mapMatris, xx, yy, kx, ky);
				//System.out.println(kx+" uu "+ky);
				Sis.sisMap[kx][ky]=0;
				
				for(int[]a:path) {
					
					
					for(int p=0;p<ProjectH.mapsize2;p++) {
						for(int r=0;r<ProjectH.mapsize2;r++) {
							if(mapMatris[p][r]==2&&Sis.sisMap[p][r]==0) {
								int arx=0;
								int ary=0;
								int []arr=new int[2];
								arr[0]=p;
								arr[1]=r;
								tut.add(arr);
								
							}
							
						}
						
					}
					if(!tut.isEmpty()) {
						break;
					}
					
					yol.add(a);
					xx=a[0];
					yy=a[1];
					Sis.sisguncelle2(gridPane, a[0], a[1]);
					if(mapMatris[a[0]][a[1]]==2) {
						mapMatris[a[0]][a[1]]=0;
						collected++;
					}
				}
				
				
				
				k=1;
				break;
				
			}
			
			
			if(k==1) {
				break;
			}
			if(k2==1) {
				break;
			}
			if(!tut.isEmpty()) {
				break;
			}
		
		
		gg++;
		if(gg>10000) {
			break;
		}
		//silme
		if(k2==1) {
			break;
		}
		
	}
	
	
	
	List<int[]> sandikkordinat = new ArrayList<>();
	if(!tut.isEmpty()) {
	for(int []ss:tut) {
		mapMatris[ss[0]][ss[1]]=2;
	}
	}
	
	sandikkordinat=Sis.gorunurbolge_sandiklar(mapMatris);
	
		for(int []ss:tut) {
			sandikkordinat.add(ss);
		}
		
	
	
	/////////////////////////
	
	for(int []sandik:sandikkordinat) {
		//System.out.println("gorunurbolgesandikkordinatlari=("+sandik[0]+","+sandik[1]+")");
		List<int[]> path = new ArrayList<>();
		
		path=ShortestPathFinder.findShortestPath(mapMatris, xx, yy, sandik[0], sandik[1]);
		
		
		
		for(int []koordinat:path) {
			
            yol.add(koordinat);
           
            System.out.println(koordinat[0]+","+koordinat[1]);
            
            xx=koordinat[0];
			yy=koordinat[1];
		
			
			if(xx==sandik[0]&&yy==sandik[1]) {
				collected++;
				
				
				mapMatris[sandik[0]][sandik[1]]=0;
			
			}
			Sis.sisguncelle2(gridPane,koordinat[0], koordinat[1]);
			
			
			
			
			//System.out.println(collected+"sst");
			
		}
		
		
		
	}
	collected=collected-tut.size();
	tut.clear();
	///////////////////////////
	
	}
	
	
	
	List<Element> elemanlarYoldaDegil = new ArrayList<>();

	
	for (Element x : elements) {
	   
	    if (x instanceof Chest) {
	        boolean yoldaMi = false;
	        
	        
	        for (int[] z : yol) {
	         
	            if (x.getX() == z[0] && x.getY() == z[1]) {
	                yoldaMi = true; 
	                break; 
	            }
	        }
	        
	        
	        if (!yoldaMi) {
	            elemanlarYoldaDegil.add(x); 
	        }
	    }
	}
	
	
	for(Element a:elemanlarYoldaDegil) {
		
		List<int[]> pathz = new ArrayList<>();
		pathz=ShortestPathFinder.findShortestPath(mapMatris, xx, yy, a.getX(),a.getY());
		
		for(int []pp:pathz) {
			yol.add(pp);
			xx=pp[0];
			yy=pp[1];
		}
		
		
	}
	
	
	System.out.println("atilan adim sayisi= "+yol.size());
	
	
	
	
	
	for (int i = 0; i < Sis.sisMap.length; i++) {
	    for (int j = 0; j < Sis.sisMap[i].length; j++) {
	        System.out.print(Sis.sisMap[i][j] + " ");
	    }
	    System.out.println(); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	Timeline timeline = new Timeline();
	


	for (int []konum:yol){
		
		KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds( currentIndex*0.08), event -> {
            move_tppp(gridPane, konum[0], konum[1]);
            Sis.sisguncelle22(gridPane,konum[0],konum[1]);
            for (Element ins : elements) {
            	
                if ( (ins instanceof Chest)&&ins.getX()==konum[0]&&ins.getY()==konum[1]) {
                    ins.getImageView().setVisible(false);
                    System.out.println("Chest at (" + konum[0] + ", " + konum[1] + ") collected");
	                printChestFind(konum[0], konum[1], borderPane, vbox);
                    toplanansandik(ins,konum[0],konum[1]);
                   
                    
                }
               
            }
            
		 });
		
		
		timeline.getKeyFrames().add(keyFrame);
        currentIndex++;
        
		
		
		timeline.getKeyFrames().add(keyFrame);
        currentIndex++;
        setX(konum[0]);
        setY(konum[1]);
		
	}
	
	timeline.play();


}








}
