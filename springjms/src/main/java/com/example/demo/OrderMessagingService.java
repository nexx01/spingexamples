package com.example.demo;

public interface OrderMessagingService {
    void sendOrder(SomeOrser order
    );

    void ConvertAndSendOrder(SomeOrser order
    );

    void convertAndSendOrderNotSerialisable(OrderNotSerializable orderNotSerializable);

    void sendWithAdditionalMessage(SomeOrser order);


    void convertandsendWithAdditionalMessage(OrderNotSerializable orderNotSerializable);
}
