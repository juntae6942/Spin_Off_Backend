package Trendithon.SpinOff.domain.board.service;

import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.repository.BoardPopularPostRepository;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
import Trendithon.SpinOff.domain.board.repository.BoardSearchRepository;
import Trendithon.SpinOff.domain.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberService memberService;
    private final BoardRepository boardRepository;
    private final BoardSearchRepository boardSearchRepository;
    private final BoardPopularPostRepository boardPopularPostRepository;


    @Transactional
    public void save(BoardDto boardDTO) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName(); // 현재 사용자의 이메일 가져오기


        Board board = new Board();
        System.out.println(board);
        board.setTitle(boardDTO.getTitle());
        board.setDescription(boardDTO.getDescription());
        board.setProjBackground(boardDTO.getProjBackground());
        board.setCategory(boardDTO.getCategory());
        board.setGithubUrl(boardDTO.getGithubUrl());
        board.setMainFeature(boardDTO.getMainFeature());
        board.setProjUrl(boardDTO.getProjUrl());
        board.setTeamMembers(boardDTO.getTeamMembers());
        board.setContent(boardDTO.getContent());
        board.setBoardLike(boardDTO.getBoardLike());
        board.setWriter(currentUserEmail);
        board.setImageUrl(boardDTO.getImageUrl());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(boardDTO.getMemberPart());
        board.setMemberPart(jsonString);

        boardRepository.save(board);
    }
    public Page<Board> boardList(Pageable pageable){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return boardRepository.findAll(pageable);
    }
    public Page<Board> boardPopularList(Pageable pageable){
        return boardPopularPostRepository.findByBoardPopularPost(pageable);
    }
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardSearchRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public void saveUpdate(String boardDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BoardDto boardDTO1 = objectMapper.readValue(boardDTO, BoardDto.class);
        Board board = new Board();
        board.setBno(boardDTO1.getBno());
        board.setTitle(boardDTO1.getTitle());
        board.setContent(boardDTO1.getContent());
        board.setBoardLike(boardDTO1.getBoardLike());

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonString = objectMapper1.writeValueAsString(boardDTO1.getImageUrl());
        board.setImageUrl(jsonString);

        boardRepository.save(board);

    }

    @Transactional
    public List<BoardResponseDto> search(String title) throws JsonProcessingException {
        List<Board> boardEntities = boardRepository.findWithParams(title);
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (Board board : boardEntities) {
            boardResponseDtoList.add(BoardResponseDto.toDTO(board));
        }
        return boardResponseDtoList;
    }


    @Transactional
    public BoardResponseDto findByBoardId(Long boardId) throws JsonProcessingException {
        Optional<Board> boardEntityOptional = boardRepository.findById(boardId);
        if (boardEntityOptional.isPresent()) {
            Board board = boardEntityOptional.get();
            BoardResponseDto boardResponseDTO = BoardResponseDto.toDTO(boardEntityOptional.get());
            //boardResponseDTO.setProfileImage(board.getMember().getProfile());
            return boardResponseDTO;
        } else {
            return null; // 또는 다른 상태 코드를 사용
        }
    }

    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}

