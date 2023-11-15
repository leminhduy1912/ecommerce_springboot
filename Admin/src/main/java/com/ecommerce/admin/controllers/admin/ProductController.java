package com.ecommerce.admin.controllers.admin;

import com.ecommerce.library.dtos.CategoryDTO;
import com.ecommerce.library.dtos.ProductDTO;
import com.ecommerce.library.services.ICategoryService;
import com.ecommerce.library.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
//    @GetMapping("/admin/products")
//    public String getAllProducts(Model model){
//        List<ProductDTO> products = iProductService.findAllProduct();
//        model.addAttribute("products",products);
//        model.addAttribute("size",products.size());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login";
//        }
//        return "products";
//    }
    @GetMapping("admin/products/{pageNo}")
    public String allProducts(@PathVariable("pageNo") int pageNo,
                              Model model,
                              Principal principal) {
        if (principal == null) {
            return "admin/login";
        }
        System.out.println(pageNo);
       int totalItems = iProductService.getTotalItems();

        List<ProductDTO> products = iProductService.findProductsByPage(pageNo-1,9);
       model.addAttribute("title", "Manage Products");
        model.addAttribute("size", products.size());
        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalItems / 9));
        System.out.println(products);
        System.out.println("items"+totalItems);
        System.out.println("pages" + (int) Math.ceil((double) totalItems / 9));

        return "admin/products";
    }
    @GetMapping("admin/products/search-products")
    public String searchProduct(@RequestParam(value="page") int pageNo,
                                @RequestParam(value = "keyword") String keyword,
                                Model model
    ) {
        System.out.println("page"+pageNo);
        System.out.println("keyword" + keyword);
                Pageable pageable =  PageRequest.of(pageNo,9);
        int totalItems = iProductService.getTotalItems();
        Page<ProductDTO> products = iProductService.finddAllByNameOrDescription(keyword,pageNo,9);
        model.addAttribute("title", "Result Search Products");
        model.addAttribute("size", products.getSize());
        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",products.getTotalPages());
        return "result-products";
    }
    @GetMapping("/admin/products/add-product")
    public String addProductPage(Model model) {
        model.addAttribute("title", "Add Product");
        List<CategoryDTO> categories = iCategoryService.findAllExist();
        model.addAttribute("categories", categories);
        model.addAttribute("productDto", new ProductDTO());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        System.out.print(categories);
        return "admin/add-product";
    }
    @PostMapping("/admin/products/save-product")
    public String addProduct(@Valid
                             @RequestParam("imageOfProduct") MultipartFile imageOfProduct,
                             @ModelAttribute ("productDTO") ProductDTO productDTO,
                             RedirectAttributes redirectAttributes){

        try {
            iProductService.save(imageOfProduct, productDTO);
            redirectAttributes.addFlashAttribute("success", "Add new product successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new product!");
        }
        return "redirect:/admin/products/1";

    }

    @GetMapping("/admin/products/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        List<CategoryDTO> categories = iCategoryService.findAllExist();
        ProductDTO productDto = iProductService.findById(id);
        model.addAttribute("title", "Add Product");
        model.addAttribute("categories", categories);
        model.addAttribute("productDto", productDto);
        return "admin/update-product";
    }
    @PostMapping("admin/products/update-product/{id}")
    public String updateProduct(@ModelAttribute("productDto") ProductDTO productDTO,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                @PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {

        try {

           ProductDTO dto = iProductService.update(imageProduct,id, productDTO);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/admin/products/1";

    }
    @RequestMapping(value = "admin/products/delete-product/{id}",method = {RequestMethod.PUT,RequestMethod.GET})
    public String deleteProducts(@PathVariable("id") Long id,
                                 RedirectAttributes redirectAttributes){
        try {
            System.out.print("deleted" + id);
            iProductService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/admin/products/1";
    }
    @RequestMapping(value = "/admin/products/enable-product/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledProduct(@PathVariable("id")  Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            iProductService.enableProduct(id);
            redirectAttributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Enabled failed!");
        }
        return "redirect:/admin/products/1";
    }


}
