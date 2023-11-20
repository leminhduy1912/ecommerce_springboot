package com.ecommerce.library.services.impl;

import com.ecommerce.library.converters.ProductConverter;
import com.ecommerce.library.dtos.ProductDTO;
import com.ecommerce.library.entities.CategoryEntity;
import com.ecommerce.library.entities.ProductEntity;
import com.ecommerce.library.repositories.CategoryRepository;
import com.ecommerce.library.repositories.ProductRepository;
import com.ecommerce.library.services.IProductService;
import com.ecommerce.library.services.IStorageImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private ProductConverter productConverter;
  @Autowired
  private IStorageImage imageUpload;


  @Override
  public List<ProductDTO> findProductsByPage(int pageNo,int limit) {
      Pageable pageable = PageRequest.of(pageNo,limit);
      List<ProductDTO> dtos = new ArrayList<>();
      Page<ProductEntity> entities = productRepository.findAll(pageable);
      for (ProductEntity entity : entities){
        dtos.add(productConverter.toDto(entity));
      }
    return dtos;
  }

    @Override
    public Page<ProductDTO> finddAllByNameOrDescription(String keyword,int page,int limit) {
      Pageable pageable = PageRequest.of(page-1,limit);
        Page<ProductEntity> entities = productRepository.findByNameOrDescription(keyword,pageable);
        System.out.println("search keyword: " + entities);

        List<ProductDTO> dtos = new ArrayList<>();
        for (ProductEntity entity : entities) {
            dtos.add(productConverter.toDto(entity));
        }

        return new PageImpl<>(dtos, entities.getPageable(), entities.getTotalElements());
    }



    @Override
    @Transactional
  public void deleteById(Long id) {
    ProductEntity productEntity = productRepository.getById(id);
    productEntity.setIsActivated(0);
    productEntity.setIsDeleted(1);
    productRepository.save(productEntity);
  }

  @Override
  public ProductDTO findById(Long id) {
    ProductEntity productEntity = productRepository.getById(id);
    return productConverter.toDto(productEntity);
  }

  @Override
  @Transactional
  public void enableProduct(Long id) {
    ProductEntity product = productRepository.getById(id);
    product.setIsActivated(1);
    product.setIsDeleted(0);
    productRepository.save(product);
  }

  @Override
  @Transactional
  public ProductDTO save(MultipartFile file, ProductDTO dto) throws IOException {
    ProductEntity entity = productConverter.toEntity(dto);

    if (file == null) {
      entity.setImage(null);
    } else {
      String stringFileUpload =  imageUpload.storeFile(file);
      entity.setImage(stringFileUpload);
    }
      entity.setIsActivated(1);
      entity.setIsDeleted(0);

    Optional<CategoryEntity> optionalCategory = categoryRepository.findById(Long.parseLong(dto.getCategoryId()));
    if (optionalCategory.isPresent()) {
      CategoryEntity category = optionalCategory.get();
      entity.setCategory(category);
    } else {
      entity.setCategory(null);
    }
    return productConverter.toDto(productRepository.save(entity));
  }

  @Override
  @Transactional
  public ProductDTO update(MultipartFile imageFileUpdate, Long id,ProductDTO updateProduct) throws IOException {
      Optional<ProductEntity> oldEntity = productRepository.findById(id);
      ProductEntity productEntity;
      if (oldEntity.isPresent()) {
          ProductEntity newEntity = productConverter.toEntity(updateProduct);
          productEntity = productConverter.toEntity(oldEntity.get(),newEntity);
          if (imageFileUpdate == null) {
              productEntity.setImage(null);
          } else {
              imageUpload.delete(productEntity.getImage());
              String stringFileUpload = imageUpload.storeFile(imageFileUpdate);
              productEntity.setImage(stringFileUpload);
          }
          productEntity.setIsActivated(1);
          productEntity.setIsDeleted(0);
          if(updateProduct.getCategoryId() != null){
              Optional<CategoryEntity> optionalCategory = categoryRepository.findById(Long.parseLong(updateProduct.getCategoryId()));
              if (optionalCategory.isPresent()) {
                  CategoryEntity category = optionalCategory.get();
                  productEntity.setCategory(category);
              } else {
                  productEntity.setCategory(null);
              }
          }
          else {productEntity.setCategory(null);}

      } else {
          return null;
      }
      System.out.println("update product" + productEntity);
      return productConverter.toDto(productRepository.save(productEntity));
  }

  @Override
  public List<ProductDTO> randomProduct() {
      List<ProductEntity>   entities=  productRepository.randomProduct();
      List<ProductDTO> dtos = new ArrayList<>();
      for(ProductEntity entity : entities){
         dtos.add(productConverter.toDto(entity));
      }
      return dtos;
  }

  @Override
  public List<ProductDTO> filterHighestCostPriceProducts() {
    List<ProductEntity> entities = productRepository.filterHighestCostPriceProducts();
    List<ProductDTO> dtos = new ArrayList<>();
    for(ProductEntity entity : entities){
      dtos.add(productConverter.toDto(entity));
    }
    return dtos;
  }

  @Override
  public List<ProductDTO> filterLowestCostPriceProducts() {
    List<ProductEntity> entities = productRepository.filterLowestCostPriceProducts();
    List<ProductDTO> dtos = new ArrayList<>();
    for(ProductEntity entity : entities){
      dtos.add(productConverter.toDto(entity));
    }
    return dtos;
  }

  @Override
  public int getTotalItems() {
    return (int)productRepository.count();
  }

}
