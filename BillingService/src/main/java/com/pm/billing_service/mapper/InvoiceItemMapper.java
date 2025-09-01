//package com.pm.billing_service.mapper;
//
//import com.pm.billing_service.entity.InvoiceItem;
//import billing.InvoiceItem as GrpcInvoiceItem;
//
//public class InvoiceItemMapper {
//
//    // Convert JPA entity -> gRPC object
//    public static GrpcInvoiceItem toGrpc(InvoiceItem entity) {
//        return GrpcInvoiceItem.newBuilder()
//                .setId(entity.getId())
//                .setDescription(entity.getDescription())
//                .setQuantity(entity.getQuantity())
//                .setPrice(entity.getPrice())
//                .build();
//    }
//
//    // Convert gRPC object -> JPA entity (if needed)
//    public static InvoiceItem toEntity(GrpcInvoiceItem grpc) {
//        InvoiceItem entity = new InvoiceItem();
//        entity.setId(grpc.getId());
//        entity.setDescription(grpc.getDescription());
//        entity.setQuantity(grpc.getQuantity());
//        entity.setPrice(grpc.getPrice());
//        return entity;
//    }
//}
