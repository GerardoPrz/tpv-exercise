package oop.inheritance;

import java.time.LocalDateTime;

import oop.inheritance.data.Card;
import oop.inheritance.data.CommunicationType;
import oop.inheritance.data.SupportedTerminal;
import oop.inheritance.data.Transaction;
import oop.inheritance.data.TransactionBuilder;
import oop.inheritance.data.TransactionResponse;
import oop.inheritance.ingenico.*;
import oop.inheritance.verifone.v240m.VerifoneV240mDisplay;
import oop.inheritance.ingenico.Context;
public class Application {

    private CommunicationType communicationType = CommunicationType.ETHERNET;
    private SupportedTerminal supportedTerminal;

    public Application(SupportedTerminal supportedTerminal) {
        this.supportedTerminal = supportedTerminal;
    }
    //abstractFactory
    public void showMenu() {
        if (supportedTerminal == SupportedTerminal.INGENICO) {
            IngenicoDisplay ingenicoDisplay = new IngenicoDisplay();

            ingenicoDisplay.showMessage(5, 5, "MENU");
            ingenicoDisplay.showMessage(5, 10, "1. VENTA");
            ingenicoDisplay.showMessage(5, 13, "2. DEVOLUCION");
            ingenicoDisplay.showMessage(5, 16, "3. REPORTE");
            ingenicoDisplay.showMessage(5, 23, "4. CONFIGURACION");
        } else {
            VerifoneV240mDisplay verifoneV240mDisplay = new VerifoneV240mDisplay();

            verifoneV240mDisplay.showMessage(5, 5, "MENU");
            verifoneV240mDisplay.showMessage(5, 10, "1. VENTA");
            verifoneV240mDisplay.showMessage(5, 13, "2. DEVOLUCION");
            verifoneV240mDisplay.showMessage(5, 16, "3. REPORTE");
            verifoneV240mDisplay.showMessage(5, 23, "4. CONFIGURACION");
        }

    }
    //abstractFactory
    public String readKey() {
        IngenicoKeyboard ingenicoKeyboard = new IngenicoKeyboard();

        return ingenicoKeyboard.get();
    }

    public void doSale() {
        IngenicoCardSwipper cardSwipper = new IngenicoCardSwipper();
        IngenicoChipReader chipReader = new IngenicoChipReader();
        IngenicoDisplay ingenicoDisplay = new IngenicoDisplay();
        IngenicoKeyboard ingenicoKeyboard = new IngenicoKeyboard();
        Card card;

        do {
            card = cardSwipper.readCard();
            if (card == null) {
                card = chipReader.readCard();
            }
        } while (card == null);

        ingenicoDisplay.clear();
        ingenicoDisplay.showMessage(5, 20, "Capture monto:");

        String amount = ingenicoKeyboard.get(); //Amount with decimal point as string

        Transaction transaction = new Transaction();

        TransactionBuilder.setLocalDateTime(LocalDateTime.now());
        TransactionBuilder.setCard(card);
        TransactionBuilder.setAmountInCents(Integer.parseInt(amount.replace(".", "")));

        TransactionResponse response = sendSale(transaction);

        if (response.isApproved()) {
            ingenicoDisplay.showMessage(5, 25, "APROBADA");
            printReceipt(transaction, response.getHostReference());
        } else {
            ingenicoDisplay.showMessage(5, 25, "DENEGADA");
        }
    }

    private void printReceipt(Transaction transaction, String hostReference) {
        IngenicoPrinter ingenicoPrinter = new IngenicoPrinter();
        Card card = transaction.getCard();

        ingenicoPrinter.print(5, "APROBADA");
        ingenicoPrinter.lineFeed();
        ingenicoPrinter.print(5, card.getAccount());
        ingenicoPrinter.lineFeed();
        ingenicoPrinter.print(5, "" + transaction.getAmountInCents());
        ingenicoPrinter.lineFeed();
        ingenicoPrinter.print(5, "________________");

    }

    private TransactionResponse sendSale(Transaction transaction) {

        Context Context;

        IngenicoEthernet ethernet = new IngenicoEthernet();
        IngenicoModem modem = new IngenicoModem();
        IngenicoGPS gps = new IngenicoGPS();
        TransactionResponse transactionResponse = null;

        switch (communicationType) {
            case ETHERNET:
                Context = new Context(ethernet);
                Context.open();
                Context.send(transaction);
                transactionResponse = Context.receive();
                Context.close();
                break;
            case GPS:
                Context = new Context(gps);
                Context.open();
                Context.send(transaction);
                transactionResponse = Context.receive();
                Context.close();
                break;
            case MODEM:
                Context = new Context(modem);
                Context.open();
                Context.send(transaction);
                transactionResponse = Context.receive();
                Context.close();
                break;
        }

        return transactionResponse;
    }

    public void doRefund() {
    }

    public void printReport() {
    }

    public void showConfiguration() {
    }
//abstractFactory
    public void clearScreen() {
        if (supportedTerminal == SupportedTerminal.INGENICO) {
            IngenicoDisplay ingenicoDisplay = new IngenicoDisplay();

            ingenicoDisplay.clear();
        } else {
            VerifoneV240mDisplay verifoneV240mDisplay = new VerifoneV240mDisplay();

            verifoneV240mDisplay.clear();
        }
    } //abstractFactory
}
