package com.example.board.controller;

import com.example.board.model.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    // 메인 페이지 이동
    @GetMapping("")
    public String home() {
        log.info("home 호출");
        return "redirect:/list";
    }

    // 글쓰기 페이지 이동
    @GetMapping("write")
    public String writeForm() {
        return "write";
    }

    // 게시글 쓰기
    @PostMapping("write")
    public String write(@ModelAttribute Board board) {
        log.info("board: {}", board);
        board.setHit(0L);
        board.setCreated_time(LocalDateTime.now());
        boardRepository.saveBoard(board);
        return "redirect:/";
    }

    // 게시글 전체 보기
    @GetMapping("list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAllBoards();
        model.addAttribute("boards", boards);
        return "list";
    }

    // 게시글 읽기
    @GetMapping("read")
    public String read(@RequestParam Long id,
                       Model model) {
        log.info("id: {}", id);
        Board board = boardRepository.findBoard(id);
        board.setHit(board.getHit() + 1);
        model.addAttribute("board", board);
        return "read";
    }

    // 게시글 수정 페이지 이동
    @GetMapping("update")
    public String updateForm(@RequestParam Long id,
                             Model model) {
        log.info("id: {}", id);
        Board board = boardRepository.findBoard(id);
        model.addAttribute("board", board);
        return "update";
    }

    // 게시글 수정
    @PostMapping("update")
    public String update(@RequestParam Long id,
                         @ModelAttribute Board updateBoard) {
        log.info("board: {}", updateBoard);
        Board board = boardRepository.findBoard(id);
        if (board.getPassword().equals(updateBoard.getPassword())) {
            boardRepository.updateBoard(id, updateBoard);
        }
        return "redirect:/";
    }

    // 게시글 삭제
    @PostMapping("delete")
    public String remove(@RequestParam Long id,
                         @RequestParam String password) {
        Board board = boardRepository.findBoard(id);
        if (board.getPassword().equals(password)) {
            boardRepository.removeBoard(id);
        }
        return "redirect:/";
    }

}
