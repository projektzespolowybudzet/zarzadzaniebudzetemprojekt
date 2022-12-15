package am.jsl.dolarek.dto.transaction;

import am.jsl.dolarek.domain.transaction.Transaction;
import am.jsl.dolarek.domain.transaction.TransactionSource;
import am.jsl.dolarek.domain.transaction.TransactionType;
import am.jsl.dolarek.domain.transaction.Transfer;
import am.jsl.dolarek.util.DateUtils;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *Transakcja służy do tworzenia i aktualizowania transakcji z sieci.
 */
public class TransactionDTO implements Serializable {

  /**
   * Wewnętrzny identyfikator
   */
  private long id;

  /**
   * Opis
   */
  private String description;

  /**
   * Identyfikator konta tej transakcji
   */
  private long accountId;

  /**
   * Identyfikator kategorii
   */
  private long categoryId;

  /**
   * Kwota transakcji
   */
  private double amount;

  /**
   * Status transakcji
   */
  private byte status;

  /**
   * Typ transakcji
   */
  private byte transactionType;

  /**
   * Data transakcji
   */
  private String transactionDate;

  /**
   * Źródło transakcji
   */
  private byte transactionSource = TransactionSource.MANUAL.getValue();

  /**
   * Kurs wymiany
   */
  private double exchangeRate;

  /**
   * Identyfikator konta docelowego
   */
  private long targetAccountId;

  /**
   * Konwertowana kwota
   */
  private double convertedAmount;

  /**
   * Identyfikator użytkownika
   */
  private long userId;

  /**
   * Domyślny konstruktor
   */
  public TransactionDTO() {
    super();
  }

  /**
   * Constructs new TransactionDTO with the given fields.
   *
   * @param accountId       the account id
   * @param transactionType the transaction type
   * @param transactionDate the transaction date
   * @param userId          the user id
   */
  public TransactionDTO(
    long accountId,
    byte transactionType,
    LocalDateTime transactionDate,
    long userId
  ) {
    super();
    this.accountId = accountId;
    this.transactionType = transactionType;
    this.transactionDate = DateUtils.format(transactionDate);
    this.userId = userId;
  }

  /**
   * Returns true if this transaction is transfer type
   *
   * @return true if transaction is transafer
   */
  public boolean isTransfer() {
    return transactionType == TransactionType.TRANSFER.getValue();
  }

  /**
   * Getter for property 'id'.
   *
   * @return Value for property 'id'.
   */
  public long getId() {
    return id;
  }

  /**
   * Setter for property 'id'.
   *
   * @param id Value to set for property 'id'.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Getter for property 'description'.
   *
   * @return Value for property 'description'.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for property 'description'.
   *
   * @param description Value to set for property 'description'.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter for property 'accountId'.
   *
   * @return Value for property 'accountId'.
   */
  public long getAccountId() {
    return accountId;
  }

  /**
   * Setter for property 'accountId'.
   *
   * @param accountId Value to set for property 'accountId'.
   */
  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  /**
   * Getter for property 'categoryId'.
   *
   * @return Value for property 'categoryId'.
   */
  public long getCategoryId() {
    return categoryId;
  }

  /**
   * Setter for property 'categoryId'.
   *
   * @param categoryId Value to set for property 'categoryId'.
   */
  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * Getter for property 'amount'.
   *
   * @return Value for property 'amount'.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Setter for property 'amount'.
   *
   * @param amount Value to set for property 'amount'.
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Getter for property 'status'.
   *
   * @return Value for property 'status'.
   */
  public byte getStatus() {
    return status;
  }

  /**
   * Setter for property 'status'.
   *
   * @param status Value to set for property 'status'.
   */
  public void setStatus(byte status) {
    this.status = status;
  }

  /**
   * Getter for property 'transactionType'.
   *
   * @return Value for property 'transactionType'.
   */
  public byte getTransactionType() {
    return transactionType;
  }

  /**
   * Setter for property 'transactionType'.
   *
   * @param transactionType Value to set for property 'transactionType'.
   */
  public void setTransactionType(byte transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * Getter for property 'transactionDate'.
   *
   * @return Value for property 'transactionDate'.
   */
  public String getTransactionDate() {
    return transactionDate;
  }

  /**
   * Setter for property 'transactionDate'.
   *
   * @param transactionDate Value to set for property 'transactionDate'.
   */
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  /**
   * Gets transaction local date time.
   *
   * @return the transaction local date time
   */
  public LocalDateTime getTransactionLocalDateTime() {
    return DateUtils.parse(transactionDate);
  }

  /**
   * Getter for property 'targetAccountId'.
   *
   * @return Value for property 'targetAccountId'.
   */
  public long getTargetAccountId() {
    return targetAccountId;
  }

  /**
   * Setter for property 'targetAccountId'.
   *
   * @param targetAccountId Value to set for property 'targetAccountId'.
   */
  public void setTargetAccountId(long targetAccountId) {
    this.targetAccountId = targetAccountId;
  }

  /**
   * Getter for property 'convertedAmount'.
   *
   * @return Value for property 'convertedAmount'.
   */
  public double getConvertedAmount() {
    return convertedAmount;
  }

  /**
   * Setter for property 'convertedAmount'.
   *
   * @param convertedAmount Value to set for property 'convertedAmount'.
   */
  public void setConvertedAmount(double convertedAmount) {
    this.convertedAmount = convertedAmount;
  }

  /**
   * Getter for property 'userId'.
   *
   * @return Value for property 'userId'.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Getter for property 'exchangeRate'.
   *
   * @return Value for property 'exchangeRate'.
   */
  public double getExchangeRate() {
    return exchangeRate;
  }

  /**
   * Setter for property 'exchangeRate'.
   *
   * @param exchangeRate Value to set for property 'exchangeRate'.
   */
  public void setExchangeRate(double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  /**
   * Gets transaction source.
   *
   * @return the transaction source
   */
  public byte getTransactionSource() {
    return transactionSource;
  }

  /**
   * Sets transaction source.
   *
   * @param transactionSource the transaction source
   */
  public void setTransactionSource(byte transactionSource) {
    this.transactionSource = transactionSource;
  }

  /**
   * Setter for property 'userId'.
   *
   * @param userId Value to set for property 'userId'.
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Converts this transaction dto to transaction domain object.
   *
   * @return the transaction
   */
  public Transaction toTransaction() {
    Transaction transaction = new Transaction();
    transaction.setId(getId());
    transaction.setAccountId(getAccountId());
    transaction.setCategoryId(getCategoryId());
    transaction.setAmount(getAmount());
    transaction.setStatus(getStatus());
    transaction.setTransactionType(getTransactionType());
    transaction.setTransactionDate(getTransactionLocalDateTime());
    transaction.setDescription(getDescription());

    // transfer
    if (isTransfer()) {
      Transfer transfer = new Transfer();
      transfer.setTargetAccountId(getTargetAccountId());
      transfer.setConvertedAmount(getConvertedAmount());
      transfer.setRate(getExchangeRate());
      transaction.setTransfer(transfer);
    }

    return transaction;
  }

  /**
   * Creates Transaction DTO object from Transaction domain object.
   *
   * @param transaction the transaction
   * @return the transaction dto
   */
  public static TransactionDTO from(Transaction transaction) {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setId(transaction.getId());
    transactionDTO.setAccountId(transaction.getAccountId());
    transactionDTO.setCategoryId(transaction.getCategoryId());
    transactionDTO.setAmount(transaction.getAmount());
    transactionDTO.setStatus(transaction.getStatus());
    transactionDTO.setTransactionType(transaction.getTransactionType());
    transactionDTO.setTransactionDate(
      DateUtils.format(transaction.getTransactionDate())
    );
    transactionDTO.setUserId(transaction.getUserId());
    transactionDTO.setDescription(transaction.getDescription());

    // transfer
    if (transactionDTO.isTransfer()) {
      Transfer transfer = transaction.getTransfer();
      transactionDTO.setTargetAccountId(transfer.getTargetAccountId());
      transactionDTO.setConvertedAmount(transfer.getConvertedAmount());
      transactionDTO.setExchangeRate(transfer.getRate());
    }

    return transactionDTO;
  }
}
