package oop.inheritance.data;

import java.time.LocalDateTime;

public interface Builder {
     TransactionBuilder setAmountInCents(int amountInCents);

     TransactionBuilder setCard(Card card);

     TransactionBuilder setLocalDateTime(LocalDateTime localDateTime);
}
