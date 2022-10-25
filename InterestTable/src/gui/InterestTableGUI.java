package gui;
import javafx.application.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import guiHandle.Interest;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
public class InterestTableGUI extends Application {
	private TextArea displayArea;
	private Slider slider;
	private TextField principalText, rateText;
	public void start(Stage primaryStage) {
		int sceneHeight = 700, sceneWidth = 500;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		//Display area
		displayArea = new TextArea();
		displayArea.setPrefSize(sceneWidth * 2,sceneHeight / 2);
		displayArea.setEditable(false);
		displayArea.setWrapText(true); 
		
		ScrollPane scrollpane = new ScrollPane(displayArea);
		BorderPane bpane = new BorderPane();
		bpane.setTop(scrollpane);
		GridPane central = new GridPane();
		//Properties
		central.setHgap(horSpaceBetweenNodes);
		central.setVgap(verSpaceBetweenNodes);
		central.setPadding(new Insets(paneBorderTop, paneBorderRight, 
							paneBorderBottom, paneBorderLeft));
		//Labels and text fields
		Label principal = new Label("Principal: ");
		principalText = new TextField();
		central.add(principal, 0, 0);
		central.add(principalText, 1, 0);
		Label rate = new Label("Rate(Percentage): ");
		rateText = new TextField();
		central.add(rate, 2, 0);
		central.add(rateText, 3, 0);
		central.add(new Label("Number of Years: "), 1, 1);
		
		//Slider
		slider = new Slider();
		slider.setMin(1);
		slider.setMax(25);
		slider.setValue(25);
		slider.setMajorTickUnit(0.5);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		central.add(slider, 2, 1);
		
		//Buttons
		Button button1 = new Button("SimpleInterest");
		central.add(button1, 0, 2);
		//Non-anon
		button1.setOnAction(new ButtonHandler());
		Button button2 = new Button("CompoundInterest");
		central.add(button2, 1, 2);
		//Anon
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				double principalValue = Double.parseDouble(principalText.getText());
				double ratePercentageValue = Double.parseDouble(rateText.getText());
				int years = (int) slider.getValue();
				displayArea.setText(Interest.compoundTable(
						principalValue, ratePercentageValue, years));
			}
		});
		
		Button button3 = new Button("BothInterests");
		//Lambda
		button3.setOnAction(e -> {
			double principalValue = Double.parseDouble(principalText.getText());
			double ratePercentageValue = Double.parseDouble(rateText.getText());
			int years = (int) slider.getValue();
			displayArea.setText(Interest.bothTable(
					principalValue, ratePercentageValue, years));
		});
		central.add(button3, 2, 2);
		bpane.setBottom(central);
		//Display Gui
		Scene scene = new Scene(bpane, sceneHeight, sceneWidth);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	private class ButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			double principalValue = Double.parseDouble(principalText.getText());
			double ratePercentageValue = Double.parseDouble(rateText.getText());
			int years = (int) slider.getValue();
			displayArea.setText(Interest.simpleTable(
					principalValue, ratePercentageValue, years));
		}
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
