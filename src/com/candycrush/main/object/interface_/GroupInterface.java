package com.candycrush.main.object.interface_;

import com.candycrush.main.object.abstraction.GenericObject;

import java.util.ArrayList;

public interface GroupInterface<T> {

    void addObject(T object);

    void  removeObject(T object);

    ArrayList<T> getObjects();
}
