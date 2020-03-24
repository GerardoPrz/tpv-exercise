package oop.inheritance;

import oop.inheritance.data.SupportedTerminal;
import oop.inheritance.ingenico.IngenicoDisplay;
import oop.inheritance.verifone.v240m.VerifoneV240mDisplay;

public class Verifone extends AbstractFactory implements Terminal {

        private SupportedTerminal supportedTerminal;

    public Verifone(SupportedTerminal supportedTerminal) {
            super(supportedTerminal);
        }


        @Override
        public void showMenu() {
            if (supportedTerminal == SupportedTerminal.VERIFONE) {
                VerifoneV240mDisplay verifoneV240mDisplay = new VerifoneV240mDisplay();

                verifoneV240mDisplay.showMessage(5, 5, "MENU");
                verifoneV240mDisplay.showMessage(5, 10, "1. VENTA");
                verifoneV240mDisplay.showMessage(5, 13, "2. DEVOLUCION");
                verifoneV240mDisplay.showMessage(5, 16, "3. REPORTE");
                verifoneV240mDisplay.showMessage(5, 23, "4. CONFIGURACION");
            }
        }

        @Override
        public void clearScreen() {
            if (supportedTerminal == SupportedTerminal.VERIFONE) {
                VerifoneV240mDisplay verifoneV240mDisplay = new VerifoneV240mDisplay();

                verifoneV240mDisplay.clear();
            }
        }
    }

