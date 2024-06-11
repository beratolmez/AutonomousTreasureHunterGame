
package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;


public class ProjectH extends Application {

	private Stage primaryStage;
    private int mapSize;
    private int cellSize;
    List<Element> elements = new ArrayList<>();
    public static int mapsize2;
    public static Character character2;
    public static void main(String[] args) {
        launch(args);
    	
    }
    
   
    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage; // primaryStage sınıf üyesine atanıyor
        primaryStage.setTitle("Project H");

        // Kullanıcıdan boyut girişi almak için TextField
        TextField mapSizeTextField = new TextField();
        mapSizeTextField.setPromptText("Boyutu girin");
        
        TextField cellSizeTextField = new TextField();
        cellSizeTextField.setPromptText("Boyutu girin");

        // Haritayı oluşturmak için Button
        Button createButton = new Button("Oluştur");
        createButton.setOnAction(e -> createMap(mapSizeTextField.getText(), cellSizeTextField.getText()));

        Button obstacleButton = new Button("Engel Boyut Ayarla");
        obstacleButton.setOnAction(e -> openObstacleSettings());
        
        // GridPane kullanarak düzeni oluştur
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
       
        
        
        grid.setHgap(1); // Hücreler arası yatay boşluk
        grid.setVgap(1); // Hücreler arası dikey boşluk

        // Component'ları GridPane'e ekle
        grid.add(new Label("Harita Boyutu:"), 0, 0);
        grid.add(mapSizeTextField, 1, 0);
        grid.add(new Label("Hücre Boyutu:"), 0, 10);
        grid.add(cellSizeTextField, 1, 10);
        grid.add(createButton, 1, 20);
        grid.add(obstacleButton, 1, 30);
        // Scene ve Stage oluştur
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        // Stage'i göster
        primaryStage.show();
    }
    private void openObstacleSettings() {
        Stage obstacleStage = new Stage();
        obstacleStage.setTitle("Engel Boyut Ayarla");

        // Engel boyutları girişi için TextField'lar
        TextField karakterb = new TextField();
        karakterb.setPromptText("Boyut Girin");
        //Character.objsize=Integer.parseInt(karakterb.getText());
        
       

        // "Onayla" butonu
        Button confirmButton = new Button("Onayla");
        confirmButton.setOnAction(e -> {
            // Bu kısımda engel boyutlarını kullanarak bir işlem yapabilirsiniz.
            // Örneğin, engel boyutlarına göre haritada engel oluşturabilirsiniz.
            // Bu örnekte sadece kapatma işlemi gerçekleştiriliyor.
        	Mountain.objsize=Integer.parseInt(karakterb.getText());
        	Summer_Mountain.objsize=Integer.parseInt(karakterb.getText());
            obstacleStage.close();
        });

        // GridPane kullanarak düzeni oluştur
        GridPane obstacleGrid = new GridPane();
        obstacleGrid.setAlignment(Pos.CENTER);
        obstacleGrid.setHgap(10);
        obstacleGrid.setVgap(10);
        obstacleGrid.setPadding(new Insets(25, 25, 25, 25));

        // Component'ları GridPane'e ekle
        obstacleGrid.add(new Label("Dag Boyutu"), 0, 0);
        obstacleGrid.add(karakterb, 1, 0);
        
       
        obstacleGrid.add(confirmButton, 1, 2);

        // Scene ve Stage oluştur
        Scene obstacleScene = new Scene(obstacleGrid, 300, 500);
        obstacleStage.setScene(obstacleScene);

        // Stage'i göster
        obstacleStage.show();
    }

    private void createMap(String mSize, String cSize) {
    	int p=0;
    	while (p!=60) {
            try {
              p=p+1;
    	
        try {
        	elements.clear();
            mapSize = Integer.parseInt(mSize);
            mapsize2=mapSize;
            cellSize = Integer.parseInt(cSize);
            
            if (mapSize < 0) {
                throw new NumberFormatException();
            }

            // Harita oluşturmak için GridPane'i temizle
            ((GridPane) primaryStage.getScene().getRoot()).getChildren().clear();
            
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);         
            GridPane mapGrid = new GridPane();
          
            mapGrid.setAlignment(Pos.CENTER);
            mapGrid.setPadding(new Insets(10));
            mapGrid.setHgap(1); 
            mapGrid.setVgap(1); 

       
            int[][] mapMatris = new int[mapSize][mapSize];
            
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {mapMatris[i][j]=0;}}
            
            for (int i = 0; i < mapMatris.length; i++) {
                for (int j = 0; j < mapMatris[i].length; j++) {
                    System.out.print(mapMatris[i][j] + "\t");
                }
                System.out.println(); 
            }
            
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    ImageView imageView;
                    if (j < mapSize / 2) { 
                        String winterImagePath = "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/Snow.jpg";
                        Image winterImage = new Image(winterImagePath);
                        imageView = new ImageView(winterImage);
                    } else { 
                        String grassImagePath = "file:///C:/Users/Sait%20Omer/eclipse-workspace/prolab2projes1/src/Images/Grass-Background.jpg";
                        Image grassImage = new Image(grassImagePath);
                        imageView = new ImageView(grassImage);
                    }
                    double targetWidth = cellSize;
                    double targetHeight = cellSize;
                    imageView.setFitWidth(targetWidth);
                    imageView.setFitHeight(targetHeight);
                    mapGrid.add(imageView, j, i);
                    
                    mapMatris [j][i] = 0;
                }
            }
         
            
          
            for (int i = 0; i < 1; i++) {
                Mountain newMountain = new Mountain(mapSize, mapMatris);
                elements.add(newMountain);
                newMountain.setImageView((Mountain.objsize)*cellSize);
                System.out.println("Mountain" + (i + 1) + " Koordinatları: (" + newMountain.getX() + ", " + newMountain.getY() + ")");
            }
            
            for (int i = 0; i < 1; i++) {
                Summer_Mountain newSummer_Mountain = new Summer_Mountain(mapSize, mapMatris);
                elements.add(newSummer_Mountain);
                newSummer_Mountain.setImageView((Summer_Mountain.objsize)*cellSize);
                System.out.println("Summer_Mountain" + (i + 1) + " Koordinatları: (" + newSummer_Mountain.getX() + ", " + newSummer_Mountain.getY() + ")");
            }
            
            for (int i = 0; i < 7; i++) {
                Wall newWall = new Wall(mapSize, mapMatris);
                elements.add(newWall);
                newWall.setImageView(10*cellSize, 1*cellSize);
                System.out.println("Wall" + (i + 1) + " Koordinatları: (" + newWall.getX() + ", " + newWall.getY() + ")");
            }
            
            for (int i = 0; i < 5; i++) {
                PineTree newPineTree = new PineTree(mapSize, mapMatris);
                elements.add(newPineTree);
                newPineTree.setImageView(3*cellSize);
                System.out.println("PineTree" + (i + 1) + " Koordinatları: (" + newPineTree.getX() + ", " + newPineTree.getY() + ")");
            }
            for (int i = 0; i < 5; i++) {
                Tree newTree = new Tree(mapSize, mapMatris);
                elements.add(newTree);
                newTree.setImageView(3*cellSize);
                System.out.println("Tree" + (i + 1) + " Koordinatları: (" + newTree.getX() + ", " + newTree.getY() + ")");
            }
            
            for (int i = 0; i < 2; i++) {
                Bee newBee = new Bee(mapSize, mapMatris);
                elements.add(newBee);
                newBee.setImageView(2*cellSize);
                System.out.println("Bee" + (i + 1) + " Koordinatları: (" + newBee.getX() + ", " + newBee.getY() + ")");
            }
            
            for (int i = 0; i < 10; i++) {
                Rock newRock = new Rock(mapSize, mapMatris);
                elements.add(newRock);
                newRock.setImageView(cellSize*(Rock.objsize));
                System.out.println("Rock" + (i + 1) + " Koordinatları: (" + newRock.getX() + ", " + newRock.getY() + ")");
            }
            
            for (int i = 0; i < 5; i++) {
                BronzeChest newBronzeChest = new BronzeChest(mapSize, mapMatris);
                elements.add(newBronzeChest);
                newBronzeChest.setImageView(cellSize);
                System.out.println("BronzeChest " + (i + 1) + " Koordinatları: (" + newBronzeChest.getX() + ", " + newBronzeChest.getY() + ")");
            }
            
            for (int i = 0; i < 5; i++) {
                EmeraldChest newEmeraldChest = new EmeraldChest(mapSize, mapMatris);
                elements.add(newEmeraldChest);
                newEmeraldChest.setImageView(cellSize);
                System.out.println("EmeraldChest " + (i + 1) + " Koordinatları: (" + newEmeraldChest.getX() + ", " + newEmeraldChest.getY() + ")");
            }
            
            for (int i = 0; i < 5; i++) {
                SilverChest newSilverChest = new SilverChest(mapSize, mapMatris);
                elements.add(newSilverChest);
                newSilverChest.setImageView(cellSize);
                System.out.println("SilverChest " + (i + 1) + " Koordinatları: (" + newSilverChest.getX() + ", " + newSilverChest.getY() + ")");
            }
            
            for (int i = 0; i < 5; i++) {
                GoldChest newGoldChest = new GoldChest(mapSize, mapMatris);
                elements.add(newGoldChest);
                newGoldChest.setImageView(cellSize);
                System.out.println("GoldChest " + (i + 1) + " Koordinatları: (" + newGoldChest.getX() + ", " + newGoldChest.getY() + ")");
            }
            for (int i = 0; i < 2; i++) {
                Bird newBird = new Bird(mapSize, mapMatris);
                elements.add(newBird);
                newBird.setImageView(2*cellSize);
                System.out.println("Bird" + (i + 1) + " Koordinatları: (" + newBird.getX() + ", " + newBird.getY() + ")");
            }
            
            Character character = new Character(mapSize, mapMatris);
           
            elements.add(character);
            System.out.println("Karakter Koordinatları: " + character.getX() + ", " + character.getY() + ")");
            character.setImageView(cellSize);
            
           
            
            for (int i = 0; i < mapMatris.length; i++) {
                for (int j = 0; j < mapMatris[i].length; j++) {
                    System.out.print(mapMatris[j][i] + "\t");
                }
                System.out.println(); 
            }
            
            
           for (int i = 0; i < elements.size(); i++) {
        	            
        	   mapGrid.add(elements.get(i).getImageView(), elements.get(i).getX(), elements.get(i).getY());
               System.out.println("Eleman eklendi " + i + ": ");
               
               if (elements.get(i) instanceof PineTree) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), 3);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), 3);
               }
               else if (elements.get(i) instanceof Tree) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), 3);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), 3);
               }
               
               else if (elements.get(i) instanceof Mountain) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), Mountain.objsize);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), Mountain.objsize);
               }
               
               else if (elements.get(i) instanceof Summer_Mountain) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), Summer_Mountain.objsize);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), Summer_Mountain.objsize);
               }
               
               else if (elements.get(i) instanceof Bee) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), Bee.objsize);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), Bee.objsize);
                   ((Bee) elements.get(i)).renklendir(mapGrid);
                   //((Bee) elements.get(i)).moveM(mapGrid);
                   ((Bee) elements.get(i)).standart_harekete_basla(cellSize);
                   //((Bee) elements.get(i)).standart_harekete_basla2(mapGrid);
               }
                   
               else if (elements.get(i) instanceof Bird) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), Bird.objsize);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), Bird.objsize);
                   ((Bird) elements.get(i)).renklendir(mapGrid);
                  ((Bird) elements.get(i)).standart_harekete_basla(cellSize);
               }
               
               else if (elements.get(i) instanceof Wall) {
                   GridPane.setRowSpan(elements.get(i).getImageView(), 1);
                   GridPane.setColumnSpan(elements.get(i).getImageView(), 10);
                   
               }

           }
           
           Button startButton = new Button("Basla En Kisa Yol");
           Button startButton2 = new Button("7x7 Gorus Alani Hareket");

           
           HBox buttonBox = new HBox();
           buttonBox.getChildren().addAll(startButton, startButton2);

           
           buttonBox.setSpacing(10);
           Sis.addSisToGrid(mapGrid,cellSize,character);
           BorderPane borderPane = new BorderPane();
           borderPane.setTop(buttonBox);
           borderPane.setLeft(mapGrid);
           
           scrollPane.setContent(borderPane);
           primaryStage.setScene(new Scene(scrollPane));
           primaryStage.show();
           
          
            
            int [][]trmap=Element.transpoze(mapMatris);
            character2=character;
            startButton.setOnAction(e -> {
         	   character.sandiklara_git(trmap, elements, mapGrid, borderPane);
         	   //character.sandiklara_git_7x7(trmap, elements, mapGrid);
            });
            startButton2.setOnAction(e -> {
          	  
          	   //character.sandiklara_git_7x7(trmap, elements, mapGrid, borderPane);
            	character.sandiklara_git_son7x7(trmap, elements, mapGrid, borderPane);
             });
           //System.out.println("ddd");
       	for(Sis sd:Sis.sislist) {
       		//System.out.println("111  "+sd.getX());     
       	    }
       		
           
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz boyut formatı. Lütfen bir sayı girin.");
        }
        break; // Program normal şekilde sonlandığında döngüyü kır
            } catch (Exception e) {
                System.out.println("Cakisma gerceklesti: " + e.getMessage());
                System.out.println("Program yeniden başlatılıyor...");
            }
        }
    }

}