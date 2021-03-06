package dad.javafx.imc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMCApp extends Application {

	private IMCModel model = new IMCModel();

	private TextField pesoText, alturaText;
	private Label imcLabel, diagnosisLabel;

	@Override
	public void start(Stage primaryStage) throws Exception {

		pesoText = new TextField();
		pesoText.setPromptText("peso");
		pesoText.setPrefColumnCount(5);

		alturaText = new TextField();
		alturaText.setPromptText("altura");
		alturaText.setPrefColumnCount(5);

		imcLabel = new Label();
		diagnosisLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");

		// bindeos

		pesoText.textProperty().bindBidirectional(model.pesoProperty(), new NumberStringConverter());
		alturaText.textProperty().bindBidirectional(model.alturaProperty(), new NumberStringConverter());
		imcLabel.textProperty()
				.bind(Bindings
						.when(model.alturaProperty().lessThanOrEqualTo(0).or(model.pesoProperty().lessThanOrEqualTo(0)))
						.then("IMC: (peso / altura^2)")
						.otherwise(Bindings.concat("IMC: ").concat(model.imcProperty().asString("%.2f")))

				);

		model.imcProperty().addListener((o, ov, nv) -> onIMCChanged(nv.doubleValue()));

		HBox pesoBox = new HBox();
		pesoBox.getChildren().addAll(new Label("Peso: "), pesoText, new Label("Kg"));
		pesoBox.setAlignment(Pos.CENTER);

		HBox alturaBox = new HBox();
		alturaBox.getChildren().addAll(new Label("altura"), alturaText, new Label("cm"));
		alturaBox.setAlignment(Pos.CENTER);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, imcLabel, diagnosisLabel);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("IMCApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void onIMCChanged(Double nv) {

		if (model.getAltura() != 0 && model.getPeso() != 0) {

			if (nv < 18.5)
				diagnosisLabel.setText("Bajo Peso");
			else if (nv < 25)
				diagnosisLabel.setText("Normal");
			else if (nv < 30)
				diagnosisLabel.setText("Sobrepeso");
			else
				diagnosisLabel.setText("Obeso");

		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
