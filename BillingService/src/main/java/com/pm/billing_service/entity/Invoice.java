//package com.pm.billing_service.entity;
//
//import billing.InvoiceItem;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Data
//public class Invoice {
//    @Id
//    private String id;
//    private String billingAccountId;
//    private double totalAmount;
//    private LocalDate date;
//    private String status; // GENERATED / PAID
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "invoice_id")
//    private List<InvoiceItem> items;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getBillingAccountId() {
//        return billingAccountId;
//    }
//
//    public void setBillingAccountId(String billingAccountId) {
//        this.billingAccountId = billingAccountId;
//    }
//
//    public double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public List<InvoiceItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<InvoiceItem> items) {
//        this.items = items;
//    }
//}