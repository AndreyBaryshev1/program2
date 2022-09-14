package com.program2.studystruct.Adapter;

import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface Adaptee {
    String toString();
    void insert(int num);
    void delete (int index);
    int find (int index);
    void insert(int index,int num);
    void BubbleSort(boolean desc) throws ExecutionException, InterruptedException;

}
