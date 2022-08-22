package com.msgpick;

import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.shops.dto.ShopRegisterRequest;
import com.msgpick.module.shops.dto.ShopSummaryResponse;
import com.msgpick.msgpick.code.Facility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.msgpick.msgpick.code.Status.REJECT;
import static com.msgpick.msgpick.code.Status.REVIEW;
import static java.util.stream.Collectors.toList;

public class FacilitiesConvertor {

    @Test
    void name() {
        String facilities = "WIFI,PARKING";

        /*List<Facility> collect = Arrays.stream(facilities.split(","))
                //.map(f -> Facility.valueOf(f))
                .collect(Collectors.toList());*/

        //collect.forEach(System.out::println);
    }

    @Test
    void name2() {
        List<Facility> t = new ArrayList<>();
        t.add(Facility.valueOf("WIFI"));
        t.add(Facility.valueOf("PARKING"));

        String test = t.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(","));

        System.out.println(test);

    }

    @Test
    void name3() {
        List<ShopRegisterRequest> list = new ArrayList<>();


        //var t = list.stream().map(a -> a.getFacilityData().split(",")).collect(Collectors.toList());
        // List<Facility> shopFacilityList = t.stream().map(Facility::valueOf).collect(Collectors.toList());


       /* IntStream.range(0, list.size())
                .forEach(i -> {
                    list.get(i).setFacilities(Arrays.stream(list.get(i).getFacilityData().split(","))
                            .map(Facility::valueOf)
                            .collect(Collectors.toList()));
                });*/



        /*for (int i = 0; i < list.size(); i++) {
            var shopFacility = list.get(i).getFacilityData();
            List<Facility> shopFacilityList = Arrays.stream(shopFacility.split(","))
                    .map(Facility::valueOf)
                    .collect(Collectors.toList());
            list.get(i).setFacilities(shopFacilityList);
        }*/

        System.out.println(list);

        // requestProgram.stream().forEach(Program -> Program.setShopId(initShop.getShopId()));


    }

    @Test
    void test() {
        List<ProgramRegisterRequest> list2 = new ArrayList<>();
        list2.add(ProgramRegisterRequest.builder().name("test1").build());
        list2.add(ProgramRegisterRequest.builder().name("test2").build());
        list2.add(ProgramRegisterRequest.builder().name("test3").build());


//        var test = list2.stream()
//                .map(s -> s.setShopId(1L))
//                .forEach();


    }

    @Test
    void test3() {

        List<ShopSummaryResponse> test = new ArrayList<>();
        test.add(new ShopSummaryResponse(1L, 1L, REVIEW, "성공"));
        test.add(new ShopSummaryResponse(2L, 1L, REJECT, "실패"));
        test.add(new ShopSummaryResponse(3L, 1L, REVIEW, "실패"));
        test.add(new ShopSummaryResponse(4L, 1L, REJECT, "성공"));
        test.add(new ShopSummaryResponse(5L, 1L, REVIEW, "실패"));


        System.out.println(test.get(0));

        test.stream()
                .collect(toList())
                .forEach(s -> s.setRejectMessage("========" + s.getRejectMessage()));

        System.out.println(test.get(0).getRejectMessage());
        System.out.println(test.get(0).getStatus());

        List<ShopSummaryResponse> t1 = new ArrayList<>();
        //t1.addAll(test);

        for (ShopSummaryResponse s : test) {
            t1.add(s);
        }
    }

}
