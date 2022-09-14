package com.program2.studystruct;

import com.program2.studystruct.Adapter.Adapter;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HelloController implements Initializable {

    public TextField element;
    public TextField index;
    public RadioButton before;
    public RadioButton end;
    public RadioButton onIndex;
    public RadioButton after;
    public ToggleGroup insertGroup;
    public RadioButton desc;
    public TableView<IntegerProperty> list;
    Adapter adapter=new Adapter();
    public void Input()
    {
        if(end.isSelected())  adapter.insert(Integer.parseInt(element.getText()));
        else if(onIndex.isSelected()) adapter.insert(Integer.parseInt(index.getText()),
                Integer.parseInt(element.getText()));
        else if(before.isSelected() && Integer.parseInt(index.getText())-1>-1)
            adapter.insert(Integer.parseInt(index.getText())-1,
                Integer.parseInt(element.getText()));
        else if(adapter.display().size()>Integer.parseInt(index.getText())+1)
            adapter.insert(Integer.parseInt(index.getText())+1,
                    Integer.parseInt(element.getText()));
    }
    public void Delete()
    {
        adapter.delete(list.getSelectionModel().getSelectedIndex());
    }
    public void Search() {
        CompletableFuture.runAsync(() -> {
        for(int i=0;i<list.getItems().size() && i<Integer.parseInt(index.getText());i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.getSelectionModel().select(i);
        }
        });
        element.setText(String.valueOf(adapter.find(Integer.parseInt(index.getText()))));
    }
    public void randomFill()
    {
        Random random = new Random();
        for(int i=0;i<20;i++)
            adapter.insert(random.nextInt(50));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<IntegerProperty, Integer> Column = new TableColumn<>("Число");
        Column.setCellValueFactory(cellData -> cellData.getValue().asObject());
        list.getColumns().clear();
        list.getColumns().addAll(Column);
        list.setItems(adapter.display());
        element.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                element.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        index.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                index.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        randomFill();
    }

    public void sortShell() throws ExecutionException, InterruptedException {
        adapter.sortShell(desc.isSelected());

    }
    public void sortBubble() throws ExecutionException, InterruptedException {
        adapter.BubbleSort(desc.isSelected());
    }
}