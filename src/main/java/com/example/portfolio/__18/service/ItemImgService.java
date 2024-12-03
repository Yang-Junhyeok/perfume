package com.example.portfolio.__18.service;

import com.example.portfolio.__18.entity.ItemImg;
import com.example.portfolio.__18.repository.ItemImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    //@Value 어노테이션을 통해 application.properties파일에 등록한 itemImgLocation값을 불러와서 itemImgLocation변수에 저장
    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile)throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";
        //폴더가 없을경우 폴더 생성
        File chkMkdir = new File("C:/shop/item");
        if (!chkMkdir.exists()){
            try{
                chkMkdir.mkdirs();
            }
            catch (Exception e){}
        }
        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)){
            //사용자가 상품의 이미지를 등록했다면 저장할 경로와 파일의 이름,파일을 파일의 바이트 배열을 파일 업로드 파라미터로 uploadFile메소드를 호출, 호출 결과 로컬에 저장된 파일의 이름을 imgName변수에 저장
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            //저장한 상품 이미지를 불러올 경로를 설정, 외부 리소르를 불러오는 urlPatterns로 WebMvcConfiig클래스에서 /images//**를 성정해주엇습니다. 또한 application.properties에서 설정한 uploadPath프로퍼티 경로인 "C:/shop/"아래 item 폴어데 이미지를 저장하므로 상품 이미지를 불러오는 경로로 "/images/item/을 붙여줍니다.
            imgUrl = "/images/item/" + imgName ; 
        }
        //상품이미지 정보 저장
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        //상품 이미지 아이디를 이용하여 기존에 저장했던 상품 이미지 엔티티를 조회
        if (!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);
            //기존 이미지 파일 삭제
            if (!StringUtils.isEmpty(savedItemImg.getImgName())){
                fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
            }
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName,imgName,imgUrl);
        }
    }
}
