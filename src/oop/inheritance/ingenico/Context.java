package oop.inheritance.ingenico;

import oop.inheritance.data.Transaction;
import oop.inheritance.data.TransactionResponse;

public class Context implements DispositivosStrategy {
    private DispositivosStrategy strategy;

    public Context(DispositivosStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public boolean open() {
        return strategy.open();
    }

    @Override
    public boolean send(Transaction transaction) {
       return strategy.send(transaction);
    }

    @Override
    public TransactionResponse receive() {
       return strategy.receive();
    }

    @Override
    public void close() {
        strategy.close();
    }
}
