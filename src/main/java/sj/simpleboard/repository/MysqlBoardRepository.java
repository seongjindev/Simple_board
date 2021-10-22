package sj.simpleboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sj.simpleboard.domain.Board;
import sj.simpleboard.domain.Member;

import java.util.List;

@Repository
@Mapper
public interface MysqlBoardRepository extends BoardRepository {
    void memberSave(Member member);
    Member memberFindById(String id);
    int LastCon();
    List<Board> boardFindSubAll(Long st, Long size);

}
