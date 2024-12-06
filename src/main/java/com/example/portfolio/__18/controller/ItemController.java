package com.example.portfolio.__18.controller;

import com.example.portfolio.__18.constant.ItemPosition;
import com.example.portfolio.__18.constant.ItemSellStatus;
import com.example.portfolio.__18.dto.ItemFormDto;
import com.example.portfolio.__18.dto.ItemSearchDto;
import com.example.portfolio.__18.dto.ShowItemDto;
import com.example.portfolio.__18.entity.Item;
import com.example.portfolio.__18.service.ItemService;
import com.example.portfolio.__18.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "ShopMall/ItemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList){

        if (bindingResult.hasErrors()){
            return "ShopMall/itemForm";
        }
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "천번째 상품 이미지는 필수 입력값입니다.");
            return "ShopMall/itemForm";
        }
        try{
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품등록중 에러가 발생하셧습니다.");
            return "ShopMall/itemForm";
        }
        return "redirect:/";
    }
    
    @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
        
        try{
            //조회한 상품 데이터를 모델에 담아서 뷰로 전달
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
            //상품 엔티티가 존재하지 않을 경우 에러메시지를 담아서 상품 등록 페이지로 이동합니다.
        } catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "ShopMall/itemForm";
        }
        return "ShopMall/itemForm";
    }

    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto,BindingResult bindingResult, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model){
        if (bindingResult.hasErrors()){
            return "ShopMall/itemForm";
        }
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력값입니다.");
            return "ShopMall/itemForm";
        }
        try{
            itemService.updateItem(itemFormDto, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하엿습니다.");
            return "ShopMall/itemForm";
        }
        return "redirect:/";
    }

    

//    value 에 상품 관리 화면 진입 시 URL에 페이지 번호가 없는 경우와 페이지 번호가 있는 경우 2가지를 매핑
    @GetMapping({"/admin/items", "/admin/items/{page}"})
    //페이징을 위해서 PageRequest.of메소드를 통해 Pageable객체를 생성
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Item> items = itemService.getAddminItemPage(itemSearchDto, pageable);
        //조회한 상품 데이터 및 페이징 정보를 뷰에 전달
        model.addAttribute("items", items);
        //페이지 전환 시 기존 검색 조건을 유지한 채 이동할수 있도록 뷰에 다시 전달
        model.addAttribute("itemSearchDto", itemSearchDto);
        //상품관리 메뉴 하단에 보여줄 페이지 번호의 최대 개수 5로 설정했으므로 최대 5개의 이동할 페이지 번호 만 보여줌
        model.addAttribute("maxPage",5);
        return "page_a-1";
    }

//전체브랜드
    @GetMapping("/admin/showitem")
    public String showall(ItemSearchDto itemSearchDto, @RequestParam(name="page",defaultValue = "0") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 8);
        Page<ShowItemDto> items = itemService.getShowItemPage(itemSearchDto,pageable);
        Long count = items.stream().filter(item -> item.getItemSellStatus() == ItemSellStatus.SELL).count();
        Long count2 = items.stream().filter(item -> item.getItemSellStatus() == ItemSellStatus.SOLD_OUT).count();
        model.addAttribute("sell", count);
        model.addAttribute("soldOut", count2);
        model.addAttribute("items", items);
//        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        return "page_all";
    }

//향수
    @GetMapping("/admin/perfume")
    public String perfume(ItemSearchDto itemSearchDto, @RequestParam(name="page",defaultValue = "0") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 8);
        Page<ShowItemDto> items = itemService.getShowItemPage(itemSearchDto,pageable);
        Long count = items.stream().filter(item -> item.getItemPosition() == ItemPosition.PERFUME && item.getItemSellStatus() == ItemSellStatus.SELL).count();
        Long count2 = items.stream().filter(item -> item.getItemPosition() == ItemPosition.PERFUME && item.getItemSellStatus() == ItemSellStatus.SOLD_OUT).count();
        model.addAttribute("sell", count);
        model.addAttribute("soldOut", count2);
        model.addAttribute("items", items);
        model.addAttribute("maxPage", 5);
        return "page_perfume";
    }
    //shop
    @GetMapping("/admin/shop")
    public String shop(ItemSearchDto itemSearchDto, @RequestParam(name="page",defaultValue = "0") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 8);
        Page<ShowItemDto> items = itemService.getShowItemPage(itemSearchDto,pageable);
        Long count = items.stream().filter(item -> item.getItemPosition() == ItemPosition.SHOP && item.getItemSellStatus() == ItemSellStatus.SELL).count();
        Long count2 = items.stream().filter(item -> item.getItemPosition() == ItemPosition.SHOP && item.getItemSellStatus() == ItemSellStatus.SOLD_OUT).count();
        model.addAttribute("sell", count);
        model.addAttribute("soldOut", count2);
        model.addAttribute("items", items);
        model.addAttribute("maxPage", 5);
        return "page_shop";
    }
    //outlet
    @GetMapping("/admin/outlet")
    public String outlet(ItemSearchDto itemSearchDto, @RequestParam(name="page",defaultValue = "0") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 8);
        Page<ShowItemDto> items = itemService.getShowItemPage(itemSearchDto,pageable);
        Long count = items.stream().filter(item -> item.getItemPosition() == ItemPosition.OUTLET && item.getItemSellStatus() == ItemSellStatus.SELL).count();
        Long count2 = items.stream().filter(item -> item.getItemPosition() == ItemPosition.OUTLET && item.getItemSellStatus() == ItemSellStatus.SOLD_OUT).count();
        model.addAttribute("sell", count);
        model.addAttribute("soldOut", count2);
        model.addAttribute("items", items);
        model.addAttribute("maxPage", 5);
        return "page_outlet";
    }

    //etc
    @GetMapping("/admin/etc")
    public String etc(ItemSearchDto itemSearchDto, @RequestParam(name="page",defaultValue = "0") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 8);
        Page<ShowItemDto> items = itemService.getShowItemPage(itemSearchDto,pageable);
        Long count = items.stream().filter(item -> item.getItemPosition() == ItemPosition.ETC && item.getItemSellStatus() == ItemSellStatus.SELL).count();
        Long count2 = items.stream().filter(item -> item.getItemPosition() == ItemPosition.ETC && item.getItemSellStatus() == ItemSellStatus.SOLD_OUT).count();
        model.addAttribute("sell", count);
        model.addAttribute("soldOut", count2);
        model.addAttribute("items", items);
        model.addAttribute("maxPage", 5);
        return "page_etc";
    }

    @GetMapping("/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "itemDtl";
    }
}
