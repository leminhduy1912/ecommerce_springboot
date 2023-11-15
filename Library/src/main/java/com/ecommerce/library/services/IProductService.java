package com.ecommerce.library.services;

import com.ecommerce.library.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    List<ProductDTO> findProductsByPage(int pageNo,int limit);
    Page<ProductDTO> finddAllByNameOrDescription(String keyword,int page,int limit);
    void deleteById(Long id);
    ProductDTO findById(Long id);
    void enableProduct(Long id);
    ProductDTO save(MultipartFile imageFile, ProductDTO dto) throws IOException;
    ProductDTO update(MultipartFile imageFile,Long idProduct,ProductDTO updateProduct) throws IOException;
    public List<ProductDTO> randomProduct();
    List<ProductDTO> filterHighestCostPriceProducts();
    List<ProductDTO> filterLowestCostPriceProducts();
    int getTotalItems();
}
