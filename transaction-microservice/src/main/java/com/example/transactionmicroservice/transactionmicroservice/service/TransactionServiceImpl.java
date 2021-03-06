package com.example.transactionmicroservice.transactionmicroservice.service;

import com.example.transactionmicroservice.transactionmicroservice.dao.TransactionRepository;
import com.example.transactionmicroservice.transactionmicroservice.dto.AmountMessageDTO;
import com.example.transactionmicroservice.transactionmicroservice.dto.TransactionDTO;
import com.example.transactionmicroservice.transactionmicroservice.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    private AmountMessageSender amountMessageSender;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO.getOperationType(), transactionDTO.getAmount(),
                transactionDTO.getDate(), transactionDTO.getAccountId(), transactionDTO.getCausal());
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> findAllByAccountId(int bankAccountId) {
        return transactionRepository.findAllByAccountIdOrderById(bankAccountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadePrelievoLastTen(int accountId) {
       List<Transaction> transactionList = transactionRepository.findAllByQueryMadePrelievo();
       List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(10,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadePrelievoLastTwenty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadePrelievo();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(20,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadePrelievoLastFifty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadePrelievo();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(50,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeDepositoLastTen(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeDeposito();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(10,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeDepositoLastTwenty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeDeposito();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(20,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeDepositoLastFifty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeDeposito();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(50,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeBonificoLastTen(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeBonifico();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(10,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeBonificoLastTwenty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeBonifico();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(20,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeBonificoLastFifty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeBonifico();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(50,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTen(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeRicaricaTelefonica();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(10,accountId, transactionList, transactionDTOList);

    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTwenty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeRicaricaTelefonica();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(20,accountId, transactionList, transactionDTOList);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastFifty(int accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByQueryMadeRicaricaTelefonica();
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        return getTransactionDTOListCount(50,accountId, transactionList, transactionDTOList);
    }

    private List<TransactionDTO> getTransactionDTOListCount(int count,int accountId, List<Transaction> transactionList, List<TransactionDTO> transactionDTOList) {
        int updatedLimit = 0;
        for (Transaction transaction : transactionList) {
            if(updatedLimit == count){
                break;
            }
            if(transaction.getAccountId() == accountId){
                TransactionDTO transactionDTO = new TransactionDTO(transaction.getOperationType(),
                        transaction.getAmount(),transaction.getDate(),transaction.getAccountId(),
                        transaction.getCausal());
                transactionDTO.setId(transaction.getId());
                transactionDTOList.add(transactionDTO);
                updatedLimit++;
            }
        }
        return transactionDTOList;
    }

    @Override
    public boolean sendAmountOp(AmountMessageDTO amountMessageDTO) {
        return amountMessageSender.sendAmountMessage(amountMessageDTO);
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadePrelievoLastTenLimit(int accountId) {
        return transactionRepository.findAllByQueryMadePrelievoLastTenLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadePrelievoLastTwentyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadePrelievoLastTwentyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadePrelievoLastFiftyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadePrelievoLastFiftyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeDepositoLastTenLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeDepositoLastTenLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeDepositoLastTwentyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeDepositoLastTwentyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeDepositoLastFiftyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeDepositoLastFiftyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeBonificoLastTenLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeBonificoLastTenLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeBonificoLastTwentyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeBonificoLastTwentyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeBonificoLastFiftyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeBonificoLastFiftyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTenLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeRicaricaTelefonicaLastTenLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastTwentyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeRicaricaTelefonicaLastTwentyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByQueryMadeRicaricaTelefonicaLastFiftyLimit(int accountId) {
        return transactionRepository.findAllByQueryMadeRicaricaTelefonicaLastFiftyLimit(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc(int accountId) {
        List<Transaction> transactionList = transactionRepository
                .findAllByAccountIdOrderByIdDesc(accountId);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transactionDTOList.size() == 10) {
                break;
            }
            TransactionDTO transactionDTO = new TransactionDTO(transaction.getOperationType(),
                    transaction.getAmount(), transaction.getDate(), transaction.getAccountId(),
                    transaction.getCausal());
            transactionDTO.setId(transaction.getId());
            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc20(int accountId) {
        List<Transaction> transactionList = transactionRepository
                .findAllByAccountIdOrderByIdDesc(accountId);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transactionDTOList.size() == 20) {
                break;
            }
            TransactionDTO transactionDTO = new TransactionDTO(transaction.getOperationType(),
                    transaction.getAmount(), transaction.getDate(), transaction.getAccountId(),
                    transaction.getCausal());
            transactionDTO.setId(transaction.getId());
            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderByIdDesc50(int accountId) {
        List<Transaction> transactionList = transactionRepository
                .findAllByAccountIdOrderByIdDesc(accountId);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transactionDTOList.size() == 50) {
                break;
            }
            TransactionDTO transactionDTO = new TransactionDTO(transaction.getOperationType(),
                    transaction.getAmount(), transaction.getDate(), transaction.getAccountId(),
                    transaction.getCausal());
            transactionDTO.setId(transaction.getId());
            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderLimitTen(int accountId) {
        return transactionRepository.findAllByAccountIdOrderLimitTen(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderLimitTwenty(int accountId) {
        return transactionRepository.findAllByAccountIdOrderLimitTwenty(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdOrderLimitFifty(int accountId) {
        return transactionRepository.findAllByAccountIdOrderLimitFifty(accountId)
                .stream().map(x -> new TransactionDTO(x.getOperationType(),
                        x.getAmount(), x.getDate(), x.getAccountId(), x.getCausal(),x.getId())).collect(Collectors.toList());
    }

}






