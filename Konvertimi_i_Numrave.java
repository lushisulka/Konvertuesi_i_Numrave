package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Konvertimi_i_Numrave extends Application {
	
	// Deklarimi i komponenteve qe do te perdoren ne gjithe klasen
    private TextField kutia_e_Numrit;
    private ComboBox<String> baza_e_Hyrjes; //inputType
    private ComboBox<String> baza_e_Daljes; //outputType
    private Button butoni_Konverto;
    private Label etiketa_e_Rezultatit;
    private Button butoni_Pastrues;

    public static void main(String[] args) {
    // Thirrja e metodes main per te filluar ekzekutimin e aplikacionit
        launch(args);
    }


    public void start(Stage emriFaqes) {
    // Vendosja e titullit te dritares kryesore
        emriFaqes.setTitle("Konvertuesi i sistemit numerik");

        // Inicializimi i komponenteve
        kutia_e_Numrit = new TextField();
        baza_e_Hyrjes = new ComboBox<>();
        baza_e_Hyrjes.getItems().addAll("Binary", "Decimal", "Octal", "Hexadecimal");
        baza_e_Daljes = new ComboBox<>();
        baza_e_Daljes.getItems().addAll("Binary", "Decimal", "Octal", "Hexadecimal");
        butoni_Konverto = new Button("Konverto");
        etiketa_e_Rezultatit = new Label("Rezultati: ");

        // Krijimi i tabeles per vendosjen e komponenteve
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        // Shtimi i komponenteve ne tabele me komente per secilen pjese
        grid.add(new Label("Vendosni numrin:"), 0, 0);
        grid.add(kutia_e_Numrit, 1, 0);
        grid.add(new Label("Nga Baza:"), 0, 1);
        grid.add(baza_e_Hyrjes, 1, 1);
        grid.add(new Label("Te Baza:"), 0, 2);
        grid.add(baza_e_Daljes, 1, 2);
        grid.add(new Label(""), 0, 3);  // Hapesire bosh midis rreshtave
        grid.add(butoni_Konverto, 1, 3);
        grid.add(new Label("Rezultati:"), 0, 4);
        grid.add(etiketa_e_Rezultatit, 1, 4);

        // Krijimi i tabeles per vendosjen e komponenteve
        Button butoni_Pastrues = new Button("Pastro");
        butoni_Pastrues.setOnAction(e -> {
        	// Veprimi qe kryhet kur perdoruesi klikon butonin "Pastro"
        	kutia_e_Numrit.clear();
        	etiketa_e_Rezultatit.setText("Rezultati: "); 
                    }
                 );
        grid.add(butoni_Pastrues, 1, 5);

        // Tooltips per ndihme vizuale dhe informacion shtese per perdoruesin
        Tooltip kutia_e_NumritTooltip = new Tooltip("Ju lutem vendosni numrin tuaj ketu");
        Tooltip.install(kutia_e_Numrit, kutia_e_NumritTooltip);

        Tooltip baza_e_HyrjesTooltip = new Tooltip("Zgjidhni bazen e numrit tuaj qe vendoset");
        Tooltip.install(baza_e_Hyrjes, baza_e_HyrjesTooltip);

        Tooltip baza_e_DaljesTooltip = new Tooltip("Zgjidhni bazen per te konvertuar");
        Tooltip.install(baza_e_Daljes, baza_e_DaljesTooltip);
        
        Tooltip butoni_KonvertoTooltip = new Tooltip("Klikoni per te konvertuar numrin");
        Tooltip.install(butoni_Konverto, butoni_KonvertoTooltip);

        // Shtimi i nje veprimi per butonin e konvertimit
        butoni_Konverto.setOnAction(e -> konverto());
        // Krijimi i nje skene dhe shfaqja e dritares kryesore
        Scene skena = new Scene(grid, 400, 200);
        emriFaqes.setScene(skena);
        emriFaqes.show();
    }
  
    // Metoda per konvertimin e numrit
    private void konverto() {
    	// Marrja e vlerave nga komponentet
        String numri = kutia_e_Numrit.getText();
        String ngaTipi = baza_e_Hyrjes.getValue();
        String teTipi = baza_e_Daljes.getValue();

        // Kontrolli i formatit te numrit
        if (numri.matches("[0-9A-Fa-f]+")) {
            try {
            	// Thirrja e metodes per te kryer konvertimin
                String rezultati = konverto_numrin(numri, ngaTipi, teTipi);
                etiketa_e_Rezultatit.setText("Rezultati: " + rezultati);
            } catch (NumberFormatException e) {
            	etiketa_e_Rezultatit.setText("Format i pavlefshem i numrit");
            } catch (Exception e) {
            	etiketa_e_Rezultatit.setText("Gabim: " + e.getMessage());
            }
        } else {
        	etiketa_e_Rezultatit.setText("Ju lutem vendosni nje numer te vlefshem");
        }
    }
    
    // Metoda per kryerjen e konvertimit te numrit

    private String konverto_numrin (String vlera, String ngaTipi, String teTipi) {
        int ngaBaza = vendosBazen(ngaTipi);
        int teBaza = vendosBazen(teTipi);

        int VleraDecimale = Integer.parseInt(vlera, ngaBaza);
        return Integer.toString(VleraDecimale, teBaza);
    }

    // Metoda per kthimin e bazes nga emri i dhene
    private int vendosBazen(String type) {
        switch (type) {
            case "Binary":
                return 2;
            case "Decimal":
                return 10;
            case "Octal":
                return 8;
            case "Hexadecimal":
                return 16;
            default:
                return 10; // Kthimi i bazes 10 si nje baze e parazgjedhur
        }
    }
}
