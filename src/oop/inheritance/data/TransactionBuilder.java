package oop.inheritance.data;

import java.time.LocalDateTime;


public class TransactionBuilder implements Builder{

    private int amountInCents;
    private Card card;
    private LocalDateTime localDateTime;

    TransactionBuilder(){}

    public TransactionBuilder setAmountInCents(int amountInCents) {
        this.amountInCents = amountInCents;
       return this;
    }

    public TransactionBuilder setCard(Card card) {
        this.card = card;
        return this;
    }

    public TransactionBuilder setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }
    public Transaction build() {
        return new Transaction(amountInCents, card, localDateTime);
    }
}
