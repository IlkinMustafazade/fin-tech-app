package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.request.AccountToAccountRequestDTO;
import com.mustafazada.techapp.dto.response.AccountResponseDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.entity.Account;
import com.mustafazada.techapp.exception.*;
import com.mustafazada.techapp.repositories.AccountRepository;
import com.mustafazada.techapp.util.AccountCheckUtil;
import com.mustafazada.techapp.util.CurrentUser;
import com.mustafazada.techapp.util.DTOCheckUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferMoneyService {
    AccountRepository accountRepository;
    AccountCheckUtil accountCheckUtil;
    CurrentUser currentUser;
    DTOCheckUtil dtoCheckUtil;

    @Transactional
    public CommonResponseDTO<?> account2account(AccountToAccountRequestDTO requestDTO) {
        currentUser.validateCurrentUserAndAccount(requestDTO.getDebitAccountNo());
        dtoCheckUtil.isValid(requestDTO);
        accountCheckUtil.checkAmount(requestDTO.getAmount());
        accountCheckUtil.checkEqualityOfAccounts(requestDTO.getDebitAccountNo(), requestDTO.getCreditAccountNo());
        Account debitAccount = getAccountByAccountNo(requestDTO.getDebitAccountNo(), "Debit");
        validateAccountStatus(debitAccount, "Debit");
        validateDebitAccountBalance(debitAccount.getBalance(), requestDTO.getAmount());
        Account creditAccount = getAccountByAccountNo(requestDTO.getCreditAccountNo(), "Credit");
        validateAccountStatus(creditAccount, "Credit");
        debitAccount.setBalance(debitAccount.getBalance().subtract(requestDTO.getAmount()));
        creditAccount.setBalance(creditAccount.getBalance().add(requestDTO.getAmount()));

        return CommonResponseDTO.builder().status(Status.builder()
                .statusCode(StatusCode.SUCCESS)
                .message("Transfer completed successfully")
                .build()).data(AccountResponseDTO.builder()
                .accountNo(debitAccount.getAccountNo())
                .currency(debitAccount.getCurrency())
                .isActive(debitAccount.getIsActive())
                .balance(debitAccount.getBalance())
                .build()).build();
    }

    private Account getAccountByAccountNo(Integer accountNo, String accountType) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNo(accountNo);
        if (optionalAccount.isEmpty()) {
            throw NotAccountExistException.builder().responseDTO(CommonResponseDTO.builder().status(Status.builder()
                    .statusCode(StatusCode.ACCOUNT_NOT_EXIST)
                    .message(accountType + " account: " + accountNo + " is not exist")
                    .build()).build()).build();
        }
        return optionalAccount.get();
    }

    private void validateAccountStatus(Account account, String accountType) {
        if (!account.getIsActive()) {
            throw NotActiveAccountException.builder().responseDTO(CommonResponseDTO.builder().status(Status.builder()
                    .statusCode(StatusCode.NOT_ACTIVE_ACCOUNT)
                    .message(accountType + " account: " + account.getAccountNo() + " is not active")
                    .build()).build()).build();
        }
    }

    private void validateDebitAccountBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) <= 0) {
            throw InsufficientBalanceException.builder().responseDTO(CommonResponseDTO.builder().status(Status.builder()
                    .statusCode(StatusCode.INSUFFICIENT_BALANCE)
                    .message("Balance: " + balance + " is less than " + amount)
                    .build()).build()).build();
        }
    }
}
