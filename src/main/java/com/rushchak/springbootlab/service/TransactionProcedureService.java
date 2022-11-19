package com.rushchak.springbootlab.service;

public interface TransactionProcedureService {

    void addTransaction(String senderName, String receiverName, Double amount);

}
