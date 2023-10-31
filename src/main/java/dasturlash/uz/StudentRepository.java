package dasturlash.uz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(StudentDTO dto) {
        String sql = "INSERT INTO student (name,surname,created_date) values(:name,:surname,:createdDate)";

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("surname", dto.getSurname());
        params.put("createdDate", dto.getCreatedDate());

        sql = String.format(sql, params);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<StudentDTO> getStudentListByName(String name) {
        String sql = "select * from Student where name like :name";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "%" + name + "%");

        List<StudentDTO> studentList = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(StudentDTO.class));
        return studentList;
    }

}
