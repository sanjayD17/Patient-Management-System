    package com.pm.billing_service.grpc;

    import billing.BillingRequest;
    import billing.BillingResponse;
    import billing.BillingServiceGrpc.BillingServiceImplBase;
    import io.grpc.stub.StreamObserver;
    import net.devh.boot.grpc.server.service.GrpcService;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    @GrpcService
    public class BillingGrpcService extends BillingServiceImplBase {

        private static final Logger log = LoggerFactory.getLogger(
                BillingGrpcService.class);

        @Override
        public void createBillingAccount(BillingRequest billingRequest,
                                         StreamObserver<BillingResponse> responseObserver) {

            log.info(" ✅ createBillingAccount request received {}", billingRequest.toString());

            // Business logic - e.g save to database, perform calculates etc

            BillingResponse response = BillingResponse.newBuilder()
                    .setAccountId("12345")
                    .setStatus("ACTIVE")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }
    }
//package com.pm.billing_service.grpc;
//
//import billing.BillingRequest;
//import billing.BillingResponse;
//import billing.BillingServiceGrpc.BillingServiceImplBase;
//import billing.InvoiceRequest;
//import billing.InvoiceResponse;
//import billing.InvoiceItem;
//
//import io.grpc.stub.StreamObserver;
//import net.devh.boot.grpc.server.service.GrpcService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//import java.util.stream.Collectors;
//@GrpcService
//public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
//
//    private final BillingService billingService;
//    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
//
//    public BillingGrpcService(BillingService billingService) {
//        this.billingService = billingService;
//    }
//
//    @Override
//    public void createBillingAccount(BillingRequest req,
//                                     StreamObserver<BillingResponse> resp) {
//
//        log.info("CreateBillingAccount: {}", req);
//
//        var account = billingService.createAccount(req.getPatientId(), req.getName(), req.getEmail());
//
//        BillingResponse response = BillingResponse.newBuilder()
//                .setAccountId(account.getId())
//                .setStatus(account.getStatus())
//                .build();
//
//        resp.onNext(response);
//        resp.onCompleted();
//    }
//
//    @Override
//    public void createInvoice(InvoiceRequest req,
//                              StreamObserver<InvoiceResponse> resp) {
//
//        log.info("CreateInvoice: {}", req);
//
//        // Convert gRPC InvoiceItem → Entity
//        List<InvoiceItem> items = req.getItemsList().stream()
//                .map(i -> new InvoiceItem(i.getDescription(), i.getQuantity(), i.getPrice()))
//                .toList();
//
//        var invoice = billingService.createInvoice(req.getPatientId(), items);
//        var account = invoice.getBillingAccountId();
//
//        // Convert Entity → gRPC InvoiceItem
//        List<billing.InvoiceItem> grpcItems = invoice.getItems().stream()
//                .map(i -> billing.InvoiceItem.newBuilder()
//                        .setDescription(i.getDescription())
//                        .setQuantity(i.getQuantity())
//                        .setPrice(i.getPrice())
//                        .build())
//                .toList();
//
//        InvoiceResponse response = InvoiceResponse.newBuilder()
//                .setInvoiceId(invoice.getId())
//                .setStatus(invoice.getStatus())
//                .setTotalAmount(invoice.getTotalAmount())
//                .setPatientName(account) // you can fetch name if needed
//                .setPatientEmail("")     // you can fetch email if needed
//                .setDate(invoice.getDate().toString())
//                .addAllItems(grpcItems)
//                .build();
//
//        resp.onNext(response);
//        resp.onCompleted();
//    }
//}
