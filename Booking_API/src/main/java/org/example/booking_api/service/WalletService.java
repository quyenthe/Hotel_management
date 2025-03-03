package org.example.booking_api.service;

import org.example.booking_api.model.Transactions;
import org.example.booking_api.model.Wallet;

import java.util.List;

public interface WalletService {

    public Wallet addMoney(int walletId, Long amount);


    public List<Transactions> getAllTransactions(int walletId);



    public Wallet createWallet(String email);


    public Wallet getWallet(int id);


    public Wallet getLoggedUserWallet(String email);


    public Float getBalance(String email);

    public Wallet payRideBill(int walletId, Float bill);
}
