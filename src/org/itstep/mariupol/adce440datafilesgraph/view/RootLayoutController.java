/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.mariupol.adce440datafilesgraph.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.itstep.mariupol.adce440datafilesgraph.MainApp;
import org.itstep.mariupol.adce440datafilesgraph.model.Channel;

public class RootLayoutController implements Initializable {

    @FXML
    private MenuItem mOpenMenuItem;
    
    @FXML
    private GridPane gridpanemenu;
    
    @FXML
    private ColorPicker mColorPicker;
    
    public GridPane getGridpanemenu(){
        return gridpanemenu;
    }
    
    private MainApp mMainApp;
    
    private ArrayList<Channel> mChannelsArrayList;
    
    private ArrayList<String> pathFilesArrayList;
    
    private GraphOverviewController mGraphOverviewController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pathFilesArrayList=new ArrayList<>();
    }

    @FXML
    private void handleOpenMenuItem() {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter =
            new FileChooser.ExtensionFilter("*.dat", "*.dat");
        fileChooser.getExtensionFilters().add(filter);
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Open");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        //ialogStage.initOwner(getPrimaryStage());
        File file = fileChooser.showOpenDialog(dialogStage);
        if (file != null) {
            openFile(file);
        }
    }
    
    @FXML
    private void handleChangeColor(){
        LineChart mLineChart=mGraphOverviewController.getLineChart();
        Color c = mColorPicker.getValue();
        String style = "-fx-background-color: #" + c.toString().substring(2)+";";
        //mLineChart.setStyle(style);
        
        Node lineChartContent = mLineChart.lookup(".chart-plot-background");
        lineChartContent.setStyle(style);
    }
    
        public void printAction(ActionEvent actionEvent){
        Node toPrint=(Node)mGraphOverviewController.getLineChart();
        if(!print(toPrint))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Произошла ошибка печати!");
            a.setTitle("Ошибка");
            a.setContentText("Произошла ошибка во время печати. Убедитесь, что принтер исправен и включен.");
            a.showAndWait();
        }
        
    }
    
    private boolean print(Node node){
    
        PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job != null) 
		{
                    boolean printed = job.printPage(node);
                    return printed;
                }
        return false;
    } 

    private void openFile(File file) {
//        mChannelsArrayList = MainApp.getChannelsData(file.getAbsolutePath().toLowerCase().replace(".dat", ""));
//        System.out.println(mChannelsArrayList.get(0).getDataItem(0));
        String filePathString = file.getAbsolutePath().toLowerCase().replace(".dat", "");
        
        if(!pathFilesArrayList.contains(filePathString)){
            pathFilesArrayList.add(filePathString);
        }
        else
            return;
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(RootLayoutController.class.getResource("GraphOverview.fxml"));
//        
//        GraphOverviewController graphOverviewController = loader.getController();
//        System.out.println(graphOverviewController);
//        graphOverviewController.showGraph(filePathString);
        //MainApp.startGraphShow(filePathString);
        mGraphOverviewController.setPathFilesArrayList(pathFilesArrayList);
        mGraphOverviewController.showGraph(filePathString);
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        mMainApp = mainApp;
        //System.out.println(mMainApp);
    }
    
    public void setGraphOverviewController(GraphOverviewController graphOverviewController) {
        mGraphOverviewController = graphOverviewController;
    }
}
