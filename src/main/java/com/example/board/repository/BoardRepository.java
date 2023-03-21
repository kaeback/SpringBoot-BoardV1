package com.example.board.repository;

import com.example.board.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class BoardRepository {
    private Map<Long, Board> boards = new HashMap<>();
    private Long sequence = 0L;

    // 게시물 등록
    public void saveBoard(Board board) {
        board.setId(++sequence);
        boards.put(board.getId(), board);
    }

    // 게시물 검색
    public Board findBoard(Long id) {
        return boards.get(id);
    }

    // 게시물 수정
    public void updateBoard(Long id, Board updateBoard) {
        Board board = boards.get(id);
        board.setTitle(updateBoard.getTitle());
        board.setContents(updateBoard.getContents());
        board.setUsername(updateBoard.getUsername());
    }

    // 게시물 삭제
    public void removeBoard(Long id) {
        boards.remove(id);
    }

    // 게시물 전체검색
    public List<Board> findAllBoards() {
        List<Board> list = new ArrayList<>();
        list.addAll(boards.values());
        return list;
    }
}
