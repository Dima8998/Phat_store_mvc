package com.example.phat_store_mvc.util;


import com.example.phat_store_mvc.model.security.ApplicationUser;
import com.example.phat_store_mvc.repositories.ApplicationUserRepo;
import lombok.RequiredArgsConstructor;
import com.example.phat_store_mvc.dao.services.BrandService;
import com.example.phat_store_mvc.dao.services.CategoryService;
import com.example.phat_store_mvc.model.entities.itemAttributes.Color;
import com.example.phat_store_mvc.model.entities.itemAttributes.Sex;
import com.example.phat_store_mvc.model.entities.itemAttributes.Size;
import com.example.phat_store_mvc.model.entities.stock.entities.Brand;
import com.example.phat_store_mvc.model.entities.stock.entities.Category;
import com.example.phat_store_mvc.model.entities.stock.entities.Item;
import com.example.phat_store_mvc.model.entities.stock.entities.StockPosition;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RestController
@RequestMapping(name = "/s")
@RequiredArgsConstructor
public class BaseInitController {
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ApplicationUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;
    private Category tempCategory;
    private Brand tempBrand;
    private Item tempItem;

    @GetMapping("/init")
    public void init() throws IOException {
        if (categoryService.findById(1).isEmpty()) {
            List.of("ГОЛОВНЫЕ УБОРЫ", "ОДЕЖДА", "ОБУВЬ", "СУМКИ", "АКСЕССУАРЫ", "ТВОРЧЕСТВО")
                    .forEach(category -> categoryService.save(Category.builder()
                            .name(category)
                            .catalogue(new HashSet<>())
                            .build())
                    );
            List.of("Kangol", "ProClub", "Novesta", "PHAT")
                    .forEach(brand -> brandService.save(Brand.builder()
                            .name(brand)
                            .catalogue(new HashSet<>())
                            .build()));
            itemsInit();
            usersInit();
        }
    }

    private void itemsInit() throws IOException {
        String itemsFile = "items.txt";
        try (Stream<String> stream = Files.lines(Path.of(itemsFile))) {
            List<String> lines = stream.toList();
            for (int i = 0; i < lines.size(); i++) {
                if (i < 15) {
                    if (i == 0) {
                        tempBrand = brandService.findById(1).get();
                        tempCategory = categoryService.findById(1).get();
                    }
                    saveModel(lines.get(i), 0);
                }
                if (14 < i && i < 30) {
                    if (i == 15) {
                        categoryService.save(tempCategory);
                        brandService.save(tempBrand);
                        tempBrand = brandService.findById(2).get();
                        tempCategory = categoryService.findById(2).get();
                    }
                    saveModel(lines.get(i), 1);
                }
                if (29 < i) {
                    if (i == 30) {
                        categoryService.save(tempCategory);
                        brandService.save(tempBrand);
                        tempBrand = brandService.findById(3).get();
                        tempCategory = categoryService.findById(3).get();
                    }
                    saveModel(lines.get(i), 2);
                }
            }
            categoryService.save(tempCategory);
            brandService.save(tempBrand);
        } catch (Exception e) {
            throw new RemoteException();
        }
    }

    private void saveModel(String modelDescription, int type) {
        String[] itemProps = modelDescription
                .substring(1, modelDescription.length() - 2)
                .split(", ");
        saveModelFromStringArray(itemProps, type);
    }

    private void saveModelFromStringArray(String[] itemProps, int type) {
        tempItem = Item.builder()
                .article(String.valueOf(new Random().nextInt(1000_000, 10_000_000)))
                .model(itemProps[2])
                .price(Double.parseDouble(itemProps[3]))
                .brand(tempBrand)
                .category(tempCategory)
                .sex(Sex.UNISEX)
                .positions(new HashSet<>())
                .build();
        tempCategory
                .getCatalogue()
                .add(tempItem);
        tempBrand
                .getCatalogue()
                .add(tempItem);
        saveModelStockPositions(type);
    }

    private void saveModelStockPositions(int type) {
        Arrays
                .stream(Color.values())
                .forEach(color -> saveStockPositionsByColor(color, type));
    }

    private void saveStockPositionsByColor(Color color, int type) {
        switch (type) {
            case 0 -> Size.HATS.getSizes()
                    .forEach(size ->
                            saveStockPositionByColorAndSize(color, size));
            case 1 -> Size.CLOTHES.getSizes()
                    .forEach(size ->
                            saveStockPositionByColorAndSize(color, size));
            case 2 -> Size.SHOES.getSizes()
                    .forEach(size ->
                            saveStockPositionByColorAndSize(color, size));
            default -> System.out.println();
        }
    }

    private void saveStockPositionByColorAndSize(Color color, String size) {
        StockPosition stockPosition = StockPosition.builder()
                .item(tempItem)
                .color(color)
                .size(size)
                .amount(new Random().nextInt(0, 20))
                .build();
        tempItem.getPositions().add(stockPosition);
    }

    private void usersInit() {
        ApplicationUser admin = new ApplicationUser("admin@ya.ru", passwordEncoder.encode("admin"));
        ApplicationUser user = new ApplicationUser("user@ya.ru", passwordEncoder.encode("user"));

        appUserRepo.save(admin);
        appUserRepo.save(user);
    }
}
