package Trendithon.SpinOff.domain.board.repository;

import Trendithon.SpinOff.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardSearchRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b WHERE b.projectName LIKE %:projectName%")
    Page<Board> findByBoardTitleContaining(@Param("projectName") String projectName, Pageable pageable);
}
