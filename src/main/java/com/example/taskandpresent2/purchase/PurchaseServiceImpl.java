package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.event.EventService;
import com.example.taskandpresent2.exception.PurchaseNotFoundException;
import com.example.taskandpresent2.exception.UserNotFoundException;
import com.example.taskandpresent2.exception.ValidationException;
import com.example.taskandpresent2.pageable.Pagination;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    PurchaseMapper purchaseMapper = new PurchaseMapper();
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private EventService eventService;

    @Override
    public PurchaseDto getPurchaseById(Long id) {
        return PurchaseMapper.toPurchaseDto(purchaseRepository.findById(id).orElseThrow(()
                -> new PurchaseNotFoundException("Покупка не была создана.")));
    }

    @Override
    public List<PurchaseDto> getAllPurchases() {
        return purchaseRepository.findAll().stream()
                .map(PurchaseMapper::toPurchaseDto)
                .collect(toList());
    }

    @Override
    public List<PurchaseDto> getAllPurchasesByBuyerId(Long buyerId, int from, int size) {
        return purchaseRepository.getPurchasesByBuyer_Id(buyerId, Pagination.makePageRequest(from, size)).stream()
                .map(PurchaseMapper::toPurchaseDto)
                .sorted(Comparator.comparing(PurchaseDto::getId))
                .collect(toList());
    }

    @Transactional
    @Override
    public PurchaseDto createPurchase(Long userId, PurchaseDto purchaseDto) {
        validatePurchase(purchaseDto);
        eventService.checkUserInEvent(userId, purchaseDto.getEventDtoId());
        return PurchaseMapper.toPurchaseDto(purchaseRepository.save(purchaseMapper.toPurchase(purchaseDto)));
    }

    @Transactional
    @Override
    public PurchaseDto updatePurchase(Long userId, PurchaseDto purchaseDto, Long id) {
        eventService.checkUserInEvent(userId, purchaseDto.getEventDtoId());
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
        if (purchaseDto.getBuyerId() == null) {
            purchaseDto.setBuyerId(purchase.getBuyer().getId());
        }
        if (purchaseDto.getDimension() == null) {
            purchaseDto.setDimension(purchase.getDimension());
        }
        return PurchaseMapper.toPurchaseDto(purchaseRepository.save(purchaseMapper.toPurchase(purchaseDto)));
    }

    @Transactional
    @Override
    public void deletePurchaseById(Long userId,Long id) {
        eventService.checkUserInEvent(userId,getPurchaseById(id).getEventDtoId());
        purchaseRepository.deleteById(id);
    }

    @Override
    public List<PurchaseDto> searchAllPurchases(String text, int from, int size) {
        return purchaseRepository.searchPurchasesByName(text,
                        Pagination.makePageRequest(from, size)).stream()
                .map(PurchaseMapper::toPurchaseDto)
                .collect(toList());
    }

    private void validatePurchase(PurchaseDto purchaseDto) {
        if (purchaseDto.getName().isEmpty() || purchaseDto.getName() == null) {
            throw new ValidationException("Введено пустое имя");
        }
        if (purchaseDto.getStatusPurchases() == null) {
            purchaseDto.setStatusPurchases(StatusPurchases.NEED);
        } else if (!(purchaseDto.getStatusPurchases().equals(StatusPurchases.NEED)
                || purchaseDto.getStatusPurchases().equals(StatusPurchases.ALREADY_HAS)
                || purchaseDto.getStatusPurchases().equals(StatusPurchases.BOUGHT))) {
            throw new ValidationException("Указан не существующий статус");
        }
        if (purchaseDto.getEventDtoId() == null || purchaseDto.getEventDtoId() == 0) {
            throw new ValidationException("Не указано id мероприятия");
        }
    }
}
