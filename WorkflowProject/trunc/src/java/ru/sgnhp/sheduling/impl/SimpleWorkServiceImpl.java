package ru.sgnhp.sheduling.impl;

import ru.sgnhp.sheduling.SimpleWorkService;

public class SimpleWorkServiceImpl implements SimpleWorkService {

    public String doSomeJob() {
        System.out.println("Yo!");
        return null;
    }
}
