package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.Update;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/{id}")
    public PurchaseDto getPurchaseById(@PathVariable Long id) {
        log.info("Получен запрос на получение покупки по ID: " + id);
        return purchaseService.getPurchaseById(id);
    }

    @GetMapping
    public List<PurchaseDto> getAllPurchases() {
        log.info("Получен запрос на получение всех покупок.");
        return purchaseService.getAllPurchases();
    }

    @ResponseBody
    @PostMapping
    public PurchaseDto createPurchase(@RequestBody @Validated(Create.class) PurchaseDto PurchaseDto) {
        log.info("Добавлена покупка: " + PurchaseDto);
        return purchaseService.createPurchase(PurchaseDto);
    }

    @ResponseBody
    @PatchMapping("/{id}")
    public PurchaseDto updatePurchase(@RequestBody @Validated(Update.class) PurchaseDto user, @PathVariable Long id) {
        log.info("Получен запрос на изменение данных о покупке с ID: " + id);
        return purchaseService.updatePurchase(user, id);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseById(@PathVariable Long id) {
        log.info("Получен запрос на удаление покупки с ID: " + id);
        purchaseService.deletePurchaseById(id);
    }

    @GetMapping("/search")
    public List<PurchaseDto> searchPurchasesByName(@RequestParam String text,
                                                   @RequestParam(required = false, defaultValue = "0") int from,
                                                   @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("Получен запрос на поиск всех покупок");
        return purchaseService.searchAllPurchases(text,from,size);
    }

}
