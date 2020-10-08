import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Main extends Application {  
    public static final int W_HEIGHT = 1600;
    public static final int W_WIDTH = 1000;

    @Override     
    public void start(Stage primaryStage) throws Exception { 
        Group root = new Group();
        
        Scene scene = new Scene(root, W_HEIGHT, W_WIDTH);

        primaryStage.setTitle("Candy Crush - Test");

        primaryStage.setScene(scene);

        Stop[] backgroundColors = new Stop[]{
            new Stop(0f, Color.web("#4287f5")),
            new Stop(0.25f, Color.web("#a742f5")),
            new Stop(0.5f, Color.web("#ef42f5")),
            new Stop(0.75f, Color.web("#b9f542")),
            new Stop(1f, Color.web("#42f5b9"))
        };
        Rectangle background = new Rectangle(W_WIDTH, W_HEIGHT, 
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.REPEAT,
            backgroundColors));
        background.widthProperty().bind(scene.widthProperty());
        background.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(background);

        primaryStage.show();
    }         
    public static void main(String[] args){           
       launch(args);      
    } 
 } 