package com.example.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.basic.model.Board;
import com.example.basic.model.FileAtch;
import com.example.basic.model.FileInfo;
import com.example.basic.repository.FileAtchRepository;

@Controller
public class UploadController {
@Autowired
FileAtchRepository fileAtchRepository;

    @GetMapping("/upload1")
    public String upload1() {
        return "upload1";
    }

    @PostMapping("/upload1")
    @ResponseBody
    // MultipartHttpServletRequest 사용 , transfer to 사용
    public String upload1Post(MultipartHttpServletRequest mRequest) {
        String result = "";
        MultipartFile mFile = mRequest.getFile("file");

        String fileName = mFile.getOriginalFilename();
        long size = mFile.getSize();
        // result += oName + "<br>" + mFile.getSize();

        // 폴더생성 ,폴더위치 신경쓰기
        File folder = new File("/Users/songhyeongwang/SpringBoot/files");
        folder.mkdirs();

        // 파일폴더, 파일이름 지정
        File file = new File("/Users/songhyeongwang/SpringBoot/files/" + fileName);

        // transferto가 있어야 저장
        try {
            mFile.transferTo(file);
        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return fileName + size;

    }

    // @ReauestParam 사용 
    @PostMapping("/upload2")
    @ResponseBody
    public String upload2(@RequestParam("file") MultipartFile mFile) {
        String result = "";
        String oName = mFile.getOriginalFilename();
        result += oName + "<br>" + mFile.getSize();
        
        //1. 중복파일이 존재하는지 확인
        File file = new File("/Users/songhyeongwang/SpringBoot/files/" + oName);
        boolean isFile =  file.isFile();

        String sName = oName;
        //2. 중복파일이 있다면 파일명 변경하기
        if(isFile){
            //                 목표 :  abc.jpg => abc_123123423.jpg
                                    //이름 자르기
            String name = oName.substring(0,oName.indexOf('.'));
                                    // 확장자 자르기
            String ext = oName.substring(oName.indexOf('.'));
                                        //유닉스 타임추가
            sName = name + System.currentTimeMillis() +ext;
            System.out.println(sName);
        }
        try {
            mFile.transferTo(new File("/Users/songhyeongwang/SpringBoot/files/" + sName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 데이터베이스에 데이터 넣기
        
        FileAtch fileAtch = new FileAtch();
        //원래이름과 바뀐이름 저장
        fileAtch.setOriginalName(oName);
        fileAtch.setSaveName(sName);
        fileAtch.setCreDate(new Date());
        // 외래키 지정
        Board board = new Board();
        board.setId(5);
        fileAtch.setBoard(board);

        //저장
        fileAtchRepository.save(fileAtch);
        return result;
    }

    // @ModelAttribute 사용 , 저장위치 지정안함 , Class 필요
    @PostMapping("/upload3")
    @ResponseBody
    public String upload3Post(@ModelAttribute FileInfo info) {
        String result = "";
        String oName = info.getFile().getOriginalFilename();
        result += oName + "<br>" + info.getFile().getSize();
        return result;
    }

    // 여러개의 file을 업로드 가능
    @PostMapping("/upload4")
    @ResponseBody
    public String upload4Post(
            @RequestParam("file") MultipartFile[] mFiles) {
        String result = "";
        
        //반복문 사용 
        //1번
        // for (int i = 0 ;i < mFile.length; i++ ){ 
        // MultipartFile mFile = mFile[i] ; }
        
        //2번
        for (MultipartFile mFile : mFiles) {
            String oName = mFile.getOriginalFilename();
            result += oName + " : " + mFile.getSize() + "<br>";
        }
        return result;
    }


    @GetMapping("/upload6")
    public String upload6() {
        return "upload6";
    }

    @PostMapping("/upload6")
    @ResponseBody
    public String upload6Post(MultipartHttpServletRequest mRequest) {
        String result = "";

        Iterator<String> fileNames = mRequest.getFileNames();
                
        
                //hasNext() fileNames에 있는 파일들을 모두호출       
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            List<MultipartFile> mFiles = mRequest.getFiles(fileName);
            for (MultipartFile mFile : mFiles) {
                String oName = mFile.getOriginalFilename();
                long size = mFile.getSize();
                result += oName + " : " + size + "<br>";
            }
        }
        return result;
    }




}
