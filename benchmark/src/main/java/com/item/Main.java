package com.item;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: ChangYajie
 * @date: 2019/8/14
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, InvocationTargetException {
       CaseLoader loader = new CaseLoader();
       loader.load().run();
    }
}
