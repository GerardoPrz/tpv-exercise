package oop.inheritance.ingenico;

import oop.inheritance.data.Transaction;
import oop.inheritance.data.TransactionResponse;

public interface DispositivosStrategy {
    boolean open();
    boolean send(Transaction transaction);
    TransactionResponse receive();
    void close();
}

