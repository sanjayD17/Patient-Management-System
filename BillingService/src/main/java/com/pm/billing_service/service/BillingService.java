//package com.pm.billing_service.service;
//
//import com.pm.billing_service.entity.BillingAccount;
//import com.pm.billing_service.entity.Invoice;
//import com.pm.billing_service.entity.InvoiceItem;
//import com.pm.billing_service.repository.BillingAccountRepository;
//import com.pm.billing_service.repository.InvoiceRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.UUID;
//@Service
//public class BillingService {
//
//    private final BillingAccountRepository accountRepo;
//    private final InvoiceRepository invoiceRepo;
//
//    public BillingService(BillingAccountRepository accountRepo, InvoiceRepository invoiceRepo) {
//        this.accountRepo = accountRepo;
//        this.invoiceRepo = invoiceRepo;
//    }
//
//    public BillingAccount createAccount(String patientId, String name, String email) {
//        BillingAccount account = new BillingAccount();
//        account.setId(UUID.randomUUID().toString());
//        account.setPatientId(patientId);
//        account.setName(name);
//        account.setEmail(email);
//        account.setStatus("ACTIVE");
//        return accountRepo.save(account);
//    }
//
//    public Invoice createInvoice(String patientId, List<InvoiceItem> items) {
//        BillingAccount account = accountRepo.findByPatientId(patientId);
//        if (account == null) throw new RuntimeException("Billing account not found");
//
//        Invoice invoice = new Invoice();
//        invoice.setId(UUID.randomUUID().toString());
//        invoice.setBillingAccountId(account.getId());
//        invoice.setDate(LocalDate.now());
//        invoice.setItems(items);
//        invoice.setTotalAmount(items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
//        invoice.setStatus("GENERATED");
//
//        return invoiceRepo.save(invoice);
//    }
//}
