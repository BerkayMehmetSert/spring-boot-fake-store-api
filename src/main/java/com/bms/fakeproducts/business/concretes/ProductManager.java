package com.bms.fakeproducts.business.concretes;

import com.bms.fakeproducts.business.abstracts.ProductService;
import com.bms.fakeproducts.dataAccess.ProductDao;
import com.bms.fakeproducts.entities.concretes.Category;
import com.bms.fakeproducts.entities.concretes.Product;
import com.bms.fakeproducts.entities.dtos.ProductDto;
import com.bms.fakeproducts.exceptions.AllReadyException;
import com.bms.fakeproducts.exceptions.FieldNotEmptyException;
import com.bms.fakeproducts.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    private final ProductDao productDao;
    private final ModelMapper modelMapper;

    public ProductManager(ProductDao productDao, ModelMapper modelMapper) {
        this.productDao = productDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = this.productDao.findAll();
        List<ProductDto> productDtoList = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        if (productDtoList.isEmpty()) throw new NotFoundException("No product found with the given id");
        return productDtoList;
    }

    @Override
    public ProductDto getById(int productId) {
        Optional<Product> product = this.productDao.findById(productId);
        if (product.isEmpty()) throw new NotFoundException("No product found with the given id");
        return modelMapper.map(product.get(), ProductDto.class);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Category category = modelMapper.map(productDto.getCategory(), Category.class);

        if (productDao.existsByProductName(product.getProductName())) {
            throw new AllReadyException("Product already exists");

        } else if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");

        } else if (product.getProductName().isEmpty() || product.getDescription().isEmpty()) {
            throw new FieldNotEmptyException("Product name or description cannot be empty");

        }
        product.setCategory(category);
        return modelMapper.map(productDao.save(product), ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Optional<Product> product = this.productDao.findById(productId);
        Category category = modelMapper.map(productDto.getCategory(), Category.class);

        if (product.isEmpty() || productDao.existsByProductName(productDto.getProductName())) {
            throw new AllReadyException("Product already exists");

        } else if (productDto.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");

        } else if (productDto.getProductName().isEmpty() || productDto.getDescription().isEmpty()) {
            throw new FieldNotEmptyException("Product name or description cannot be empty");

        }
        product.get().setProductName(productDto.getProductName());
        product.get().setPrice(productDto.getPrice());
        product.get().setCategory(category);
        return modelMapper.map(productDao.save(product.get()), ProductDto.class);
    }

    @Override
    public ProductDto deleteProduct(int productId) {
        Product product = this.productDao.findById(productId)
                .orElseThrow(() -> new NotFoundException("No product found with the given id"));
        this.productDao.delete(product);
        return modelMapper.map(product, ProductDto.class);
    }

}
