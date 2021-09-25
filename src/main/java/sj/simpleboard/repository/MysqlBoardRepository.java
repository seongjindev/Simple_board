package sj.simpleboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MysqlBoardRepository extends BoardRepository {

}
