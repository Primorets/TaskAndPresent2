package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.exception.PurchaseNotFoundException;
import com.example.taskandpresent2.exception.UserNotFoundException;
import com.example.taskandpresent2.pageable.Pagination;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import com.example.taskandpresent2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public PurchaseDto getPurchaseById(Long id) {
        return PurchaseMapper.toPurchaseDto(purchaseRepository.findById(id).orElseThrow(()
                -> new PurchaseNotFoundException("Покупка не была создана.")));
    }

    @Override
    public List<PurchaseDto> getAllPurchases() {
        return purchaseRepository.findAll().stream().map(PurchaseMapper::toPurchaseDto).collect(toList());
    }

    @Override
    public List<PurchaseDto> getAllPurchasesByBuyerId(Long buyerId, int from, int size) {
        return purchaseRepository.getPurchasesByBuyer_Id(buyerId, Pagination.makePageRequest(from, size)).stream()
                .map(PurchaseMapper::toPurchaseDto)
                .sorted(Comparator.comparing(PurchaseDto::getId))
                .collect(toList());
    }

    @Override
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        validatePurchase(purchaseDto);
        return PurchaseMapper.toPurchaseDto(purchaseRepository.save(PurchaseMapper.toPurchase(purchaseDto)));


    }

    @Override
    public PurchaseDto updatePurchase(PurchaseDto purchaseDto, Long id) {
        purchaseDto.setId(id);
        Purchase purchase = purchaseRepository.findById(purchaseDto.getId()).orElseThrow(()
                -> new UserNotFoundException("Пользователь не был зарегестрирован."));
        if (purchaseDto.getName() == null) {
            purchaseDto.setName(purchase.getName());
        }
        if (purchaseDto.getStatusPurchases() == null) {
            purchaseDto.setStatusPurchases(purchase.getStatusPurchases());
        }
        if (purchaseDto.getQuantity() == 0) {
            purchaseDto.setQuantity(purchase.getQuantity());
        }
        if (purchaseDto.getBuyer() == null) {
            purchaseDto.setBuyer(purchase.getBuyer());
        }
        return PurchaseMapper.toPurchaseDto(purchaseRepository.save(PurchaseMapper.toPurchase(purchaseDto)));
    }

    @Override
    public void deletePurchaseById(Long id) {
        purchaseRepository.deleteById(id);
    }

    private void validatePurchase(PurchaseDto purchaseDto) {
        if (purchaseDto.getName().isEmpty() || purchaseDto.getName().contains(" ")) {
            throw new ValidationException("Введено пустое имя");
        }
    }
}
