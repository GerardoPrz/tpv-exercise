package oop.inheritance;

import oop.inheritance.data.SupportedTerminal;

import javax.swing.*;

public class AbstractFactory implements Terminal {

    private SupportedTerminal supportedTerminal;
   public AbstractFactory(SupportedTerminal supportedTerminal){
        this.supportedTerminal = supportedTerminal;
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void clearScreen() {

    }
}
