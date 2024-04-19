package com.example.db_project.service;

import com.example.db_project.core.AccountRole;
import com.example.db_project.core.AccountStatus;
import com.example.db_project.core.AccountType;
import com.example.db_project.core.CardStatus;
import com.example.db_project.core.curr.CurrencyCode;
import com.example.db_project.core.curr.CurrencyName;
import com.example.db_project.core.curr.ExchangeRate;
import com.example.db_project.repo.IAccountRepository;
import com.example.db_project.repo.ICardRepository;
import com.example.db_project.repo.IClientAccountRepository;
import com.example.db_project.repo.ICurrencyRepository;
import com.example.db_project.repo.entity.*;
import com.example.db_project.service.api.IEntityFillService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EntityFillService implements IEntityFillService {
    private final ICardRepository cardRepository;
    private final IClientAccountRepository iClientAccountRepository;
    private final ICurrencyRepository currencyRepository;
    private final IAccountRepository accountRepository;
    private static final Random random = new Random();

    public EntityFillService(ICardRepository cardRepository, IClientAccountRepository iClientAccountRepository, ICurrencyRepository currencyRepository, IAccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.iClientAccountRepository = iClientAccountRepository;
        this.currencyRepository = currencyRepository;
        this.accountRepository = accountRepository;
    }

    //    @Scheduled(fixedRate = 1000)
    public void update() {
        for (int i = 0; i < 30; i++) {


            Random random = new Random();

            Transaction transaction = new Transaction(new Card(), new Card(),
                    BigDecimal.valueOf(random.nextDouble() * 10000), LocalDateTime.now());
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            Card card = new Card(generateCardNumber(), LocalDateTime.now(),
                    getRandomCardStatus(), transactions);

            List<Card> cards = new ArrayList<>();
            cards.add(card);
            CurrencyCode currencyCode = getRandomCurrencyCode();
            CurrencyName currencyName = getRandomCurrencyName();
            BigDecimal exchangeRate = getRandomExchangeRate();

            Currency currency = new Currency(currencyCode, currencyName, exchangeRate);
            Account account = new Account(getRandomAccountType(), BigDecimal.valueOf(random.nextDouble() * 10000),
                    LocalDateTime.now(), getRandomAccountStatus(), currency, cards);

            List<Account> accounts = new ArrayList<>();
            accounts.add(account);

            Branch branch = new Branch(generateRandomBranchName(), generateRandomBranchAddress(), generateRandomBranchWorkingHours());
            Client client = new Client(generateRandomName(), generateRandomSurname(), generateRandomAddress()
                    , generateRandomPhoneNumber(), generateRandomMailAddress(), branch);
            ClientAccount clientAccount = new ClientAccount(client, account, AccountRole.OWNER);

            iClientAccountRepository.save(clientAccount);
        }
    }

    //@Scheduled(fixedRate = 1000)
    public void updateCards() {
        List<Card> cards = cardRepository.findAllUninitializedCards();

        for (Card c : cards) {
            c.setCardNumber(generateCardNumber());
            c.setExpirationDate(LocalDateTime.now());
            c.setCardStatus(getRandomCardStatus());
            cardRepository.save(c);
        }
    }
//    @Scheduled(fixedRate = 1000)
    public void fillCurrency() {
        CurrencyCode[] codes = CurrencyCode.values();
        CurrencyName[] names = CurrencyName.values();
        ExchangeRate[] rates = ExchangeRate.values();
        for (int i = 0; i < 4; i++) {
            Currency currency = new Currency(codes[i], names[i], BigDecimal.valueOf(Double.parseDouble(rates[i].getRate())));
            currencyRepository.save(currency);
        }
    }


    private static CurrencyCode getRandomCurrencyCode() {
        CurrencyCode[] values = CurrencyCode.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    private static CardStatus getRandomCardStatus() {
        CardStatus[] values = CardStatus.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    private static CurrencyName getRandomCurrencyName() {
        CurrencyName[] values = CurrencyName.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    private static BigDecimal getRandomExchangeRate() {
        ExchangeRate[] values = ExchangeRate.values();
        ExchangeRate exchangeRate = values[ThreadLocalRandom.current().nextInt(values.length)];
        return BigDecimal.valueOf(Double.parseDouble(exchangeRate.getRate()));
    }

    private static AccountStatus getRandomAccountStatus() {
        AccountStatus[] values = AccountStatus.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    private static AccountType getRandomAccountType() {
        AccountType[] values = AccountType.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        // Префикс карты (обычно 4 символа)
        cardNumber.append("4"); // Пример: Visa

        // 15 случайных цифр
        for (int i = 0; i < 15; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }

        // Вычисление контрольной суммы (Luhn algorithm)
        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = Character.getNumericValue(cardNumber.charAt(i));
        }
        for (int i = digits.length - 2; i >= 0; i -= 2) {
            int digit = digits[i];
            digit *= 2;
            if (digit > 9) {
                digit -= 9;
            }
            digits[i] = digit;
        }
        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }
        int checksum = (sum * 9) % 10;
        cardNumber.append(checksum);

        return cardNumber.toString();
    }

    public static String generateRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah", "Ivan", "Julia"};
        return names[random.nextInt(names.length)];
    }

    // Метод для генерации случайной фамилии
    public static String generateRandomSurname() {
        String[] surnames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        return surnames[random.nextInt(surnames.length)];
    }

    // Метод для генерации случайного адреса
    public static String generateRandomAddress() {
        String[] addresses = {"123 Main St", "456 Elm St", "789 Oak St", "101 Maple Ave", "202 Pine St", "303 Cedar Ave", "404 Birch St", "505 Spruce Ave", "606 Walnut St", "707 Cherry Ave"};
        return addresses[random.nextInt(addresses.length)];
    }

    // Метод для генерации случайного номера телефона
    public static String generateRandomPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("+");
        for (int i = 0; i < 10; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }

    // Метод для генерации случайного адреса электронной почты
    public static String generateRandomMailAddress() {
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com"};
        return generateRandomName().toLowerCase() + "." + generateRandomSurname().toLowerCase() + "@" + domains[random.nextInt(domains.length)];
    }

    public static String generateRandomBranchName() {
        String[] NAMES = {"Branch A", "Branch B", "Branch C", "Branch D", "Branch E"};
        Random random = new Random();
        return NAMES[random.nextInt(NAMES.length)];
    }

    // Генерация случайного адреса филиала
    public static String generateRandomBranchAddress() {
        String[] ADDRESSES = {"Address 1", "Address 2", "Address 3", "Address 4", "Address 5"};
        Random random = new Random();
        return ADDRESSES[random.nextInt(ADDRESSES.length)];
    }

    // Генерация случайного рабочего времени филиала
    public static String generateRandomBranchWorkingHours() {
        String[] WORKING_HOURS = {"9:00 - 17:00", "10:00 - 18:00", "8:00 - 16:00", "9:30 - 17:30", "10:30 - 18:30"};
        Random random = new Random();
        return WORKING_HOURS[random.nextInt(WORKING_HOURS.length)];
    }
}
