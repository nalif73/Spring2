package com.geekbrains.ru.springproduct.service.impl;

import com.geekbrains.ru.springproduct.domain.ProductEntity;
import com.geekbrains.ru.springproduct.dto.ProductViewDto;
import com.geekbrains.ru.springproduct.repository.ProductRepository;
import com.geekbrains.ru.springproduct.service.ProductService;
import com.geekbrains.ru.springproduct.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    @Override
    public List<ProductViewDto> findAll() {
        List<ProductEntity> products = productRepository.findAll();

        return products.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductEntity findById(long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductViewDto save(ProductViewDto productViewDto) {
        ProductEntity entity = modelMapper.map(productViewDto, ProductEntity.class);
        ProductEntity savedEntity = productRepository.save(entity);

        return convertEntityToDto(savedEntity);
    }

    @Override
    public Page<ProductEntity> findAllByPage(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    public List<ProductEntity> findMinPrice(double price) {
        return productRepository.findAllByPriceLessThan(price);
    }

    public List<ProductEntity> findMaxPrice(double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    @Override
    @Transactional
    public ProductEntity saveWithImage(ProductEntity product, MultipartFile image) {
        ProductEntity savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImageLink(pathImage.toString());

            productRepository.save(savedProduct);
        }

        return savedProduct;
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
private ProductViewDto convertEntityToDto(ProductEntity savedEntity) {
    return modelMapper.map(savedEntity, ProductViewDto.class);
}


}