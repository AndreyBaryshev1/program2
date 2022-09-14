package com.program2.studystruct.Adapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.Collections.swap;

public class Adapter implements Adaptee {
    ObservableList<IntegerProperty> list;
    CompletableFuture<Void> future;

    public Adapter() {
        list = FXCollections.observableArrayList();
    }

    public ObservableList<IntegerProperty> display() {
        return list;
    }

    @Override
    public void insert(int num) {
        list.add(new SimpleIntegerProperty(num));
    }

    @Override
    public void insert(int index, int num) {
        list.add(index, new SimpleIntegerProperty(num));
    }

    @Override
    public void delete(int index) {
        list.remove(index);
    }

    @Override
    public int find(int index) {
        return list.get(index).get();
    }
    @Override
    public void BubbleSort( boolean desc) throws ExecutionException, InterruptedException {
        if (future != null) future.get();
        future = CompletableFuture.runAsync(() ->
        {
            boolean change;
            IntegerProperty c, d;
            do {
                change = false;
                for (int i = 0; i < list.size() - 1; i++) {
                    c = list.get(i);
                    d = list.get(i + 1);
                    if (!desc) {
                        if (c.get() > d.get()) {
                            list.set(i, d);
                            list.set(i + 1, c);
                            try {
                                Thread.sleep(50);
                            } catch (Exception ignored) {
                            }
                            change = true;
                        }
                    } else {
                        if (c.get() < d.get()) {
                            list.set(i, d);
                            list.set(i + 1, c);
                            try {
                                Thread.sleep(50);
                            } catch (Exception ignored) {
                            }
                            change = true;
                        }
                    }
                }
            } while (change);
        });
    }

    public void sortShell( boolean desc) throws ExecutionException, InterruptedException {
        if (future != null) future.get();
        future = CompletableFuture.runAsync(() ->
        {
        int gap = list.size() / 2;
        while (gap >= 1) {
            for (int right = 0; right < list.size(); right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if(!desc) {
                        if (list.get(c).get() > list.get(c + gap).get()) {
                            swap(list, c, c + gap);
                            try {
                                Thread.sleep(50);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                    else {
                        if (list.get(c).get() < list.get(c + gap).get()) {
                            swap(list, c, c + gap);
                            try {
                                Thread.sleep(50);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
            gap = gap / 2;
        }
    });
    }
}
