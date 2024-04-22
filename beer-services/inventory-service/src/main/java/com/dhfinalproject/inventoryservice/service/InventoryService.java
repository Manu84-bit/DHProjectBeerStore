package com.dhfinalproject.inventoryservice.service;

import com.dhfinalproject.inventoryservice.dto.InventoryDTO;
import com.dhfinalproject.inventoryservice.model.Inventory;
import com.dhfinalproject.inventoryservice.repository.IInventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryService {
    private final IInventoryRepository inventoryRepository;

    public InventoryService(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void createInventory(InventoryDTO inventoryDTO){
        Inventory inventory = new Inventory();
        inventory.setStockCode(inventoryDTO.getStockCode());
        inventory.setStockQuantity(inventoryDTO.getStockQuantity());
        inventoryRepository.save(inventory);
    }

    public List<InventoryDTO> getInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();

        return inventoryList.stream().map(this::mapToInventoryDTO).toList();
    }

    public InventoryDTO getInventoryBySKU(String SkuCode){
        Inventory inventory = inventoryRepository.getInventoryByStockCode(SkuCode);
        return mapToInventoryDTO(inventory);
    }

    @Transactional(readOnly = true)
    public Integer isInStock(String stockCode){
        List<Inventory> inventoryList = inventoryRepository.findAll();
        Optional<Inventory> searchedInventory = inventoryList.stream().
                filter(inventory -> inventory.getStockCode().equals(stockCode)).findAny();

        return searchedInventory.get().getStockQuantity();
    }
    private InventoryDTO mapToInventoryDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setStockCode(inventory.getStockCode());
        inventoryDTO.setStockQuantity(inventory.getStockQuantity());
        return inventoryDTO;
    }
}
