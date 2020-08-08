package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilsTest {

    @Test
    public void toHashSet() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void isNotEmpty() {
    }

    @Test
    public void testSortedSet() {
        class ProdSku {

            Long bossSkuId;
            Float price;

            public Float getPrice() {
                return price;
            }

        }

        ArrayList<ProdSku> sortedList = new ArrayList<>();

        ProdSku prodSku = new ProdSku();
        prodSku.bossSkuId = 1L;
        prodSku.price = 1.0F;
        sortedList.add(prodSku);

        prodSku = new ProdSku();
        prodSku.bossSkuId = 2L;
        prodSku.price = 1.2F;
        sortedList.add(prodSku);

        prodSku = new ProdSku();
        prodSku.bossSkuId = 3L;
        prodSku.price = 1.1F;
        sortedList.add(prodSku);

        prodSku = new ProdSku();
        prodSku.bossSkuId = 4L;
        prodSku.price = 1.2F;
        sortedList.add(prodSku);

        sortedList.sort((o1, o2) -> {
            if (o1.getPrice() < o2.getPrice()) {
                return -1;
            } else if (o1.getPrice() > o2.getPrice()) {
                return 1;
            }
            return 0;
        });

        for (ProdSku sku : sortedList) {
            System.out.println(sku.bossSkuId + " " + sku.price);
        }
    }


    @Test
    public void testToArrayList() {
        String[] strArrays = {"a", "b", "c"};
        List<?> objects = CollectionUtils.toLinkedList(strArrays);
        objects.forEach(System.out::println);


        Set<String> strSet = new HashSet<>();
        strSet.add("a");
        strSet.add("b");
        strSet.add("c");
        CollectionUtils.toLinkedList(strSet).forEach(System.out::println);
    }

    @Test
    public void test_toHashSet() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Set<Integer> set = CollectionUtils.toHashSet(list);

        for (Integer i : set) {
            System.out.println(i);
        }
    }

}
