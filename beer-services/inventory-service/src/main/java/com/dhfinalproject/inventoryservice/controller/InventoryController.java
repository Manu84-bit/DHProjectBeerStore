package com.dhfinalproject.inventoryservice.controller;

import com.dhfinalproject.inventoryservice.dto.InventoryDTO;
import com.dhfinalproject.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryDTO inventoryDTO){
        inventoryService.createInventory(inventoryDTO);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> getInventory(){
        return inventoryService.getInventory();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Integer isInStock(@RequestParam String stockCode){
        return inventoryService.isInStock(stockCode);
    }

    @GetMapping("/{stockCode}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDTO getBySkuCode(@PathVariable String stockCode){
        return inventoryService.getInventoryBySKU(stockCode);
    }

}
