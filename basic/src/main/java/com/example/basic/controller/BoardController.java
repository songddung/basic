package com.example.basic.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.basic.model.Board;
import com.example.basic.model.Comment;
import com.example.basic.model.FileAtch;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.CommentRepository;
import com.example.basic.repository.FileAtchRepository;


@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;

@Autowired
CommentRepository commentRepository;

    @Autowired
    FileAtchRepository fileAtchRepository;

    @Autowired
    HttpSession session;

    @PostMapping("/comment")
    public String commnet(@ModelAttribute Comment comment,
    @RequestParam int boardId){
        String name = (String) session.getAttribute("name");
        if(name == null){
            name="anonymous";
        }
        comment.setWriter(name);
        comment.setCreDate(new Date());
        Board board = new Board();
        board.setId(boardId);
        comment.setBoard(board);
        commentRepository.save(comment);
        
        return "redirect:/board/detail?id=" +boardId;
    }

//댓글삭제
@GetMapping("/comment/remove")
public String commentRemove(@ModelAttribute Comment comment,
@RequestParam int boardId){
    commentRepository.delete(comment);
    
    //1번 new comment(),setId()
    //2번 ModelAttribute Comment comment
    return "redirect:/board/detail?id=" + boardId;
}


// 게시물삭제,로그인된이름과 작성자가 같을때 삭제
    @GetMapping("/remove")
    public String remove(@RequestParam int id, @ModelAttribute Board board){

        //로그인된 이름
        String loggedName = (int) session.getAttribute("id") + "";
        
        Optional<Board> dbBoard = boardRepository.findById(id);
        
        //현재 게시글의 작성자 이름
        String saveName = dbBoard.get().getMember();
        
        //자바의 문자열 비교 주의사항 - equals()
        if(saveName.equals(loggedName)){
        boardRepository.delete(board);
        }else{
            return "redirect:/board/detail?id=" +id;
        }
        
        return "redirect:/board/list";
    }

//수정
    @GetMapping("/edit")
    public String edit(@RequestParam int id
    , Model model){
        Optional<Board> opt = boardRepository.findById(id);
        model.addAttribute("board", opt.get());
        return "/board/edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Board board){
        boardRepository.save(board);
        return "redirect:/board/detail?id=" + board.getId();
    }







    @GetMapping("/detail")
    public String detail(@RequestParam int id
    , Model model){
        Optional<Board> opt = boardRepository.findById(id);
        model.addAttribute("board", opt.get());
        return "/board/detail";
    }





    // 입력
    @GetMapping("/write")
    public String write(){
        Object value = session.getAttribute("id");
        if(value == null){
            return "redirect:/auth/signin";
        }
                                //공백제거trim()
        return "board/write";
    }


    //트랜젝션 사용     오류는 나지만 게시글은 등록
    @Transactional(
                    // 지정한 오류는 rollback되지 않음
        // noRollbackFor = {ArithmeticException.class},

        // 지정한 오류만 rollback
    // rollbackFor = {ArithmeticException.class}
    )
    @PostMapping("/write")
    public String writePost(@ModelAttribute Board board,
    @RequestParam("file") MultipartFile mFile){
        //
        String originalFileName = mFile.getOriginalFilename();
       //게시글 저장(+추가된 게시글 번호 획득)
       Board saveBoard = boardRepository.save(board);
        // 파일 정보 저장
        FileAtch fileAtch = new FileAtch();
        fileAtch.setOriginalName(originalFileName);
        fileAtch.setSaveName(originalFileName);
        fileAtch.setBoard(saveBoard);
        fileAtch.setCreDate(new Date());
        // int a = 4/0;//오류발생 
        fileAtchRepository.save(fileAtch);

        return "redirect:/board/";
    }


 // 게시판 조회
    @GetMapping({"/list", "/"})
    public String list(Model model, 
    @RequestParam(defaultValue = "1") int p){
        //내림차순 정렬하기
        Direction dic = Direction.DESC;
        Sort sort = Sort.by(dic, "id");
        // 페이지 나누기
        Pageable page = PageRequest.of( p - 1, 5, sort);
        Page<Board> boardList = boardRepository.findAll(page);
        // List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList.getContent());
        return "board/list";
    }
}


        
